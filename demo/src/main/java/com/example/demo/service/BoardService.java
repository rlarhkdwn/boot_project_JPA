package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.BoardFileDTO;
import com.example.demo.dto.FileDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.File;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface BoardService {
    // 추상메서드만 가능한 인터페이스
    // default method : 인터페이스에서 큐칙을 잡거나, 로직을 잡거나 할 때 사용
    // 호환성 유지

    /* BoardDto => Board 객체로 변환
    *  BoardDto(class) : bno, title, writer, content, readCount, cmtQty, fileQty, regDate, modDAte
    *  Board(entity) : bno, title, writer, content, readCount, cmtQty, fileQty
    *  화면 => DB
    * */
    default Board convertDTOToEntity(BoardDTO boardDTO){
        return Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .writer(boardDTO.getWriter())
                .content(boardDTO.getContent())
                .readCount(boardDTO.getReadCount())
                .cmtQty(boardDTO.getCmtQty())
                .fileQty(boardDTO.getFileQty())
                .build();
    }

    /* 반대 케이스
    *  DB => 화면
    *  Board => BoardDTO
    *  */
    default BoardDTO convertEntityToDTO(Board board){
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .writer(board.getWriter())
                .content(board.getContent())
                .readCount(board.getReadCount())
                .cmtQty(board.getCmtQty())
                .fileQty(board.getFileQty())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();
    }

    // File
    default File convertDTOToEntity(FileDTO fileDTO){
        return File.builder()
                .uuid(fileDTO.getUuid())
                .saveDir(fileDTO.getSaveDir())
                .fileName(fileDTO.getFileName())
                .fileType(fileDTO.getFileType())
                .fileSize(fileDTO.getFileSize())
                .bno(fileDTO.getBno())
                .build();
    }

    default FileDTO convertEntityToDTO(File file){
        return FileDTO.builder()
                .uuid(file.getUuid())
                .saveDir(file.getSaveDir())
                .fileName(file.getFileName())
                .fileType(file.getFileType())
                .fileSize(file.getFileSize())
                .bno(file.getBno())
                .regDate(file.getRegDate())
                .modDate(file.getModDate())
                .build();
    }

    Long insert(BoardDTO boardDTO);

    // Page<BoardDTO> getList(int pageNo);
    Page<BoardDTO> getList(int pageNo, String type, String keyword);

    BoardFileDTO getDetail(long bno);

    Long modify(BoardFileDTO boardFileDTO);

    void delete(BoardDTO boardDTO);

    Long insert(BoardFileDTO boardFileDTO);

    FileDTO getFile(String uuid);

    long deleteFile(String uuid);

    List<FileDTO> getTodayFileList(String today);
}
