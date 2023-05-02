package com.sparta.lv1_test.dto;

import com.sparta.lv1_test.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostAndCommentAllDto {
    private Post post ; // 게시글 리스트
    private List<CommentResponesDto> CommentResponesDto; // 코멘트 리스트

    public PostAndCommentAllDto(Post post, List<CommentResponesDto> commentDtoList) {
        this.post = post;
        this.CommentResponesDto = commentDtoList;
    }
}
