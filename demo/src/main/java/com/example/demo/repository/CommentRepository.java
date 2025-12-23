package com.example.demo.repository;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<Entity, Id class>
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 기본키가 아닌 일반 칼럼은 등록을 해야 사용할 수 있음
    List<Comment> findByBno(Long bno, Sort sort);
}
