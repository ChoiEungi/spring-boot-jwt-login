package com.example.gistcompetitioncnserver.comment;

import com.example.gistcompetitioncnserver.post.Post;
import com.example.gistcompetitioncnserver.post.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addComment(CommentRequestDto commentRequestDto, Long id){
        Post post = postRepository.getById(id);
        Comment comment = commentRepository.save(
                Comment.builder()
                        .content(commentRequestDto.getContent())
                        .created(LocalDateTime.now())
                        .post(post)
                        .build());

    }

}
