package com.sparta.lv1_test.controller;

import com.sparta.lv1_test.dto.PostAndCommentAllDto;
import com.sparta.lv1_test.dto.PostRequestDto;
import com.sparta.lv1_test.entity.Post;
import com.sparta.lv1_test.entity.User;
import com.sparta.lv1_test.jwt.JwtUtil;
import com.sparta.lv1_test.security.UserDetailsImpl;
import com.sparta.lv1_test.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private final JwtUtil jwtUtil;

    @GetMapping("/") //기본페이지
    public ModelAndView home(){
        return new ModelAndView("index");

    }

    @PostMapping("/api/posts") // 게시판 데이터 저장
    public Post createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/api/posts") // 게시판 데이터 전체 조회
    public List<PostAndCommentAllDto> getPosts(){
        return postService.getPosts();
    }

    @PutMapping("/api/posts/{id}") // 게시판 데이터 수정
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, requestDto,userDetails.getUser());
    }


    @DeleteMapping("/api/posts/{id}") // 게시판 데이터 삭제
    public Long deletePost(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id,userDetails.getUser());
    }

    @GetMapping("/api/posts/{id}") // 선택한 게시판 데이터 조회
    public PostAndCommentAllDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }



}
