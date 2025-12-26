package com.example.demo.handler;

import com.example.demo.dto.FileDTO;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class FileSweeper {
    // 매일 정해진 시간에 스케줄러 실행
    // 매일 해당 날짜 경로의 파일(DB)과 폴더안의 파일이 일치하는지 비교
    // DB == 폴더의 파일이 일치하는지 확인
    // 일치하는 파일만 남기고, 일치하지 않는 파일은 삭제 (폴더에서 삭제)

    private final BoardService boardService;
    private final String BASE_PATH = "D:\\web_0826_kkj\\_myProject\\_java\\_fileUpload\\";

    // cron 방식 = 초 분 시 일 요일 년도(생략가능)
    @Scheduled(cron = "0 26 17 * * *")
    public void fileSweeper(){
        String today = LocalDate.now().toString().replace("-", java.io.File.separator);
        List<FileDTO> fileList = boardService.getTodayFileList(today);
        log.info(">>> sweeper fileList {}", fileList);

        List<String> currentFile = new ArrayList<>();
        for (FileDTO fileDTO : fileList) {
            String fileName = today + File.separator + fileDTO.getUuid() + "_" + fileDTO.getFileName();
            currentFile.add(BASE_PATH + fileName);
            // 이미지 파일이라면 썸네일도 추가
            if (fileDTO.getFileType() == 1){
                String fileThName = today + File.separator + fileDTO.getUuid() + "_th_" + fileDTO.getFileName();
                currentFile.add(BASE_PATH + fileThName);
            }
        }
        log.info(">>> currentFile {}", currentFile);

        // today 경로 기반 저장된 파일 검색
        File dir = Paths.get(BASE_PATH + today).toFile();

        // 경로안에 있는 파일을 가져오기 (배열로 리턴)
        File[] allFileObject = dir.listFiles();

        // allFileObject와 DB에 있는 파일괍 ㅣ교
        for (File file : allFileObject) {
            String storedFileName = file.toPath().toString();
            if (!currentFile.contains(storedFileName)){
                file.delete(); // 리스트(DB)에 없다면 삭제
            }
        }

    }
}
