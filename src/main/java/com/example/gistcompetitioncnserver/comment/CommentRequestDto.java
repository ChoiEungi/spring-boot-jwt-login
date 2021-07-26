package com.example.gistcompetitioncnserver.comment;

import com.example.gistcompetitioncnserver.post.Post;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CommentRequestDto{
    private final Long userId;
    private final String content;

}
