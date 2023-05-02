package com.sparta.lv1_test.dto;

import com.sparta.lv1_test.entity.Timestamped;
import lombok.Getter;

@Getter
public class CommentRequestDto{
    private Long postId; // 게시글 번호
    private String content; //내용

}
