package com.sparta.lv1_test.service;

import com.sparta.lv1_test.dto.CommentDto;
import com.sparta.lv1_test.entity.Comment;
import com.sparta.lv1_test.entity.User;
import com.sparta.lv1_test.repository.CommentRepository;
import com.sparta.lv1_test.repository.PostRepository;
import com.sparta.lv1_test.util.TockenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final TockenUtil tockenUtil;


    //댓글 등록
    public Optional<Comment> commentinsert(CommentDto comment, HttpServletRequest request) {
        User user = tockenUtil.tockencheck(request); // 토큰 유효성체크 및 토큰사용자 정보리턴
        postRepository.findById(comment.getPostid()).orElseThrow(// 게시글이 존재여부 확인
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        Comment com =  commentRepository.save(new Comment(user,comment)); // 댓글 등록
        return commentRepository.findById(com.getId()); // 댓글 조회
    }

    //댓글 수정
    public Optional<Comment> commentupdate(Long id,CommentDto commentDto, HttpServletRequest request) {
        User user = tockenUtil.tockencheck(request); // 토큰 유효성체크 및 토큰사용자 정보 리턴
        Comment comments = commentRepository.findById(id).orElseThrow( //댓글 존재여부 확인
                () -> new IllegalArgumentException("댓글 존재하지 않습니다")
        );
        if (comments.getId().equals(user.getId()) || user.getRole().equals("ADMIN")) { //권한 조회
            comments.updateComment(commentDto); // 댓글 수정
        } else {
            throw new IllegalStateException("수정 권한이 없습니다");
        }
        return commentRepository.findById(id);
    }


    //댓글 삭제
    public Long commentdelete(Long id, HttpServletRequest request) {
        User user = tockenUtil.tockencheck(request); // 토큰 유효성체크 및 토큰 사용자 정보 리턴
        Comment comments = commentRepository.findById(id).orElseThrow( //댓글 존재여부 확인
                () -> new IllegalArgumentException("댓글 존재하지 않습니다")
        );

        if (comments.getId().equals(user.getId()) || user.getRole().equals("ADMIN")) { //권한 조회
            commentRepository.deleteById(id); // 댓글 삭제
        } else {
            throw new IllegalStateException("삭제 권한이 없습니다");
        }
        return id;
    }

}
