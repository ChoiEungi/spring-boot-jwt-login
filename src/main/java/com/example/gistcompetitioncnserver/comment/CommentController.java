package com.example.gistcompetitioncnserver.comment;

import com.example.gistcompetitioncnserver.post.Post;
import com.example.gistcompetitioncnserver.post.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {

    private final CommentService commentService;

//    @GetMapping("/{id}/comment")
//    public List<Comment> retrieveAllComment(@PathVariable Long id){
//
//    }

    @PostMapping("/{id}/comments")
    public String retrieveComment(@PathVariable Long id , @RequestBody CommentRequestDto commentRequestDto){
        commentService.addComment(commentRequestDto, id);

        return "done";

    }


}



