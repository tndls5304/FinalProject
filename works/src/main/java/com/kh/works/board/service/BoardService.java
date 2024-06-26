package com.kh.works.board.service;

import com.kh.works.board.dao.BoardDao;
import com.kh.works.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;

    //작성 화면 보여주기
    public int write(BoardVo vo) {
        int result = dao.write(vo);
        return result;
    }


    public List<BoardVo> getBoardList() {
        List<BoardVo> voList = dao.getBoardList();
        return  voList;
    }

    public List<BoardVo> myBoardList(String empNo) {

        List<BoardVo> voList = dao.myBoardList(empNo);
        return voList;

    }
    public BoardVo getBoardDetail(String boardNo) {
        System.out.println("service No" + boardNo);
        return dao.getBoardDetail(boardNo);
    }
}
