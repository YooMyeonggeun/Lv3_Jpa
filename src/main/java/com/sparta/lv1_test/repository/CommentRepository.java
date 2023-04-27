package com.sparta.lv1_test.repository;

import com.sparta.lv1_test.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
