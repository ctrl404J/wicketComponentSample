package service;

import java.util.ArrayList;
import java.util.List;

import PracticeDto.BoardDto;

public class BoardService {

    private List<BoardDto> boardList;

    public BoardService() {
        this.boardList = new ArrayList<>();
    }

    public List<BoardDto> getList() {
        for (int i = 0; i < 10; i++) {
            BoardDto boardDto = new BoardDto(); // 반복마다 새로운 BoardDto 객체 생성
            boardDto.setName("이름예를들면" + i);
            boardDto.setAge("20" + i);
            boardDto.setTel("010-1234-1234"+i);
            boardList.add(boardDto);
        }
        return boardList;
    }
}
