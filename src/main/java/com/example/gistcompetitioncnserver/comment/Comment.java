package com.example.gistcompetitioncnserver.comment;

import com.example.gistcompetitioncnserver.post.Post;
import com.example.gistcompetitioncnserver.post.PostRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Data
public class Comment {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )

    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "comment_id")
    private Long commentId;

    private String content;

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Builder
    public Comment(Long comment_id, String content, LocalDateTime created, Post post) {
        this.commentId = comment_id;
        this.content = content;
        this.created = created;
        this.post = post;
    }
}
