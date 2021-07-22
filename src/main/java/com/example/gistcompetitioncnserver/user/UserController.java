package com.example.gistcompetitioncnserver.user;

import com.example.gistcompetitioncnserver.config.PasswordEncoder;
import com.example.gistcompetitioncnserver.login.LoginRequest;
import com.example.gistcompetitioncnserver.registration.RegistrationRequest;
import com.example.gistcompetitioncnserver.registration.RegistrationService;
import com.mysql.cj.log.Log;
import lombok.AllArgsConstructor;
import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserDaoService service;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> retrieveUser(@PathVariable long id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UsernameNotFoundException(String.format("ID[%s] not found", id));
        }

        // use HATEOS
        EntityModel<User> resource = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;

    }

    @PostMapping("/registeration")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    //로그인 시도하려면 이렇게 해도?
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
        //Registrationservice로
    }

    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequest request){
        return userService.loginUser(request);
    }


//    @PostMapping("")
//    public ResponseEntity<User> createUser(@RequestBody User user){
//
//        User savedUser = service.save(user);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedUser)
//                .toUri();
//
//        return ResponseEntity.created(location).build();
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

}
