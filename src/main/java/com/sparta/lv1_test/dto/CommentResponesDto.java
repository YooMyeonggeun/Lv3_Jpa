package com.sparta.lv1_test.dto;

import com.sparta.lv1_test.entity.Comment;
import com.sparta.lv1_test.entity.Timestamped;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponesDto {
    private Long id;

    private String writer;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;

    public CommentResponesDto(Comment comment) {
        this.id = comment.getId();
        this.writer = comment.getWriter();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifyAt = comment.getModifyAt();
    }
}
