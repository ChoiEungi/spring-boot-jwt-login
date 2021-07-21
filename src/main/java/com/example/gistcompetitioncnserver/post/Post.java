package com.example.gistcompetitioncnserver.post;

import com.example.gistcompetitioncnserver.comment.Comment;
import com.example.gistcompetitioncnserver.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "post_id")
    private Long postId;

    private String title;

    private String description;

    private LocalDateTime created;

    private boolean answered;

    private int accepted;

    //foreign key
    @Column(name = "user_id")
    private int userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commentId")
    private List<Comment> comment;


}
