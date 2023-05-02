package com.sparta.lv1_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.lv1_test.dto.CommentRequestDto;
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
        @ManyToOne(fetch = LAZY, cascade = CascadeType.REMOVE)
        @JoinColumn(name = "post_id")
        @JsonIgnore
        private Post post;



        //댓글작성
        public Comment (Post post, User user, CommentRequestDto comdto){
                this.post = post;
                this.writer = user.getUsername();
                this.content = comdto.getContent();
        }
        
        //댓글 업데이트
        public void updateComment(Long id, CommentRequestDto comdto){
                this.id = id;
                this.content = comdto.getContent();
        }

}
