package com.sparta.lv1_test.entity;

import com.sparta.lv1_test.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

        //고유번호
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        //작성자
        @Column(nullable = false)
        private String writer;

        //작성내용
        @Column(nullable = false)
        private String content;

        //작성된 게시글의 id번호
        @Column(nullable = false)
        private Long postid;

        @ManyToOne(fetch = LAZY, cascade = CascadeType.REMOVE)
        @JoinColumn(name = "user_name")
        private User user;

        public Comment (User user, CommentDto comdto){
                this.writer = user.getUsername();
                this.content = comdto.getContent();
                this.postid = comdto.getPostid();
        }
        
        //업데이트
        public void updateComment(CommentDto commentDto){
                this.id = commentDto.getPostid();
                this.content = commentDto.getContent();
        }

}
