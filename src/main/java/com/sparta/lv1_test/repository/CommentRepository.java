package com.sparta.lv1_test.repository;

import com.sparta.lv1_test.dto.CommentRequestDto;
import com.sparta.lv1_test.dto.CommentResponesDto;
import com.sparta.lv1_test.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post.id = :id order by c.modifyAt desc")
    List<CommentResponesDto> findbyPostId(@Param("id") Long id);

    void deleteByPostId(Long id);
}
