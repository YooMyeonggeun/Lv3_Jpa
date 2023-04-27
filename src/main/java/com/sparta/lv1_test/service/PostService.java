package com.sparta.lv1_test.service;


import com.sparta.lv1_test.dto.PostRequestDto;
import com.sparta.lv1_test.entity.Post;
import com.sparta.lv1_test.entity.User;
import com.sparta.lv1_test.jwt.JwtUtil;
import com.sparta.lv1_test.repository.PostRepository;
import com.sparta.lv1_test.util.TockenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final TockenUtil tockenUtil;

    public Post createPost(PostRequestDto requestDto, HttpServletRequest request){
        // 유효한 토큰값 username에 넣기
        Post post = new Post(tockenUtil.tockencheck(request).getUsername(),requestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() { //게시물 생성순으로 전체조회
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 id")
        );
    }


    public Long updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request){
        //유효한 토큰값 체크
        User user = tockenUtil.tockencheck(request);
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 id")
        );
        if(user.getUsername().equals(post.getUsername())){
            post.updatePost(requestDto);
        }
        return post.getId();
    }


    public Long deletePost(Long id, HttpServletRequest request) {
        User user =  tockenUtil.tockencheck(request);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 id")
        );

        // 삭제권한 체크
        if(user.getUsername().equals(post.getUsername())){
            postRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("삭제권한이 없습니다");
        }
        return id;
    }



}
