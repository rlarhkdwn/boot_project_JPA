package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Override
    public long post(CommentDTO commentDTO) {
        Comment comment = convertDTOToEntity(commentDTO);
        return commentRepository.save(comment).getCno();
    }

    @Override
    public List<CommentDTO> getList(Long bno) {
        // findBy** => **은 테이블 안에 있는 모든 칼럼 가능
        // select * from comment where ** = ?
        List<Comment> list = commentRepository.findByBno(bno, Sort.by("cno").descending());
        return list.stream().map(comment -> convertEntityToDTO(comment)).toList();
    }

    @Transactional
    @Override
    public long modify(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getCno())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글"));
        comment.setContent(commentDTO.getContent());
        return comment.getCno();
    }
}
