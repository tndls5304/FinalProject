package com.kh.works.board.dao;

import com.kh.works.board.mapper.BoardMapper;
import com.kh.works.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper mapper;

    public int write(BoardVo vo) {
        int result = mapper.write(vo);
        return result;
    }

    public List<BoardVo> getBoardList() {
        List<BoardVo> voList = mapper.getBoardList();
        return voList;
    }

    public List<BoardVo> myBoardList(String empNo) {
        List<BoardVo> voList = mapper.myBoardList(empNo);
        return voList;
    }


    public BoardVo getBoardDetail(String boardNo) {
        System.out.println("daoNum =" + boardNo);
        return mapper.getdetailBoard(boardNo);
    }

    public int editBoard(BoardVo vo, String boardNo) {
        System.out.println(vo + boardNo);
        return mapper.editBoard(vo , boardNo);
    }
}
