package com.sparta.lv1_test.controller;

import com.sparta.lv1_test.dto.CommentDto;
import com.sparta.lv1_test.entity.Comment;
import com.sparta.lv1_test.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

        private final CommentService commentService;

        // 댓글 등록
        @PostMapping("/api/comment")
        public Optional<Comment> commentinsert(@RequestBody CommentDto comment, HttpServletRequest request){
               return commentService.commentinsert(comment,request);
        }

        // 댓글 수정
        @PutMapping("/api/post/{id}")
        public Optional<Comment> commmentupdate(@PathVariable Long id, @RequestBody CommentDto comment, HttpServletRequest request){
                return commentService.commentupdate(id,comment,request);
        }


        //댓글 삭제
        @DeleteMapping("/api/post/{id}")
        public Long commentdelete(@PathVariable Long id , HttpServletRequest request){
                return commentService.commentdelete(id, request);
        }


}
