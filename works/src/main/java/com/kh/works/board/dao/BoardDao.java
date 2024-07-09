package com.kh.works.board.dao;

import com.kh.works.board.mapper.BoardMapper;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.board.vo.WishBoardVo;
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
        return mapper.getdetailBoard(boardNo);
    }

    public int editBoard(BoardVo vo, String boardNo) {
        System.out.println(vo + boardNo);
        return mapper.editBoard(vo , boardNo);
    }

    public int deleteBoard(String boardNo) {
        return mapper.deleteBoard(boardNo);
    }

    public List<BoardVo> searchTitle(String title) {
        return mapper.searchTitle(title);
    }

    public List<BoardVo> searchEmpName(String empName) {
        return mapper.searchEmpName(empName);
    }

    public void updateViewCount(int no) {
        mapper.updateViewCount(no);
    }

    public int wishBoard(WishBoardVo vo) {
        return mapper.wishBoard(vo);
    }

    public BoardVo myListDetail(String boardNo, String empNo) {
        return mapper.myListDetail(boardNo , empNo);
    }

    public int wishCancleBoard(WishBoardVo vo) {
        return mapper.wishCanclaBoard(vo);
    }

    public boolean checkWishList(WishBoardVo vo) {
        return mapper.checkWishList(vo);
    }

    public List<WishBoardVo> myWishList() {
        return mapper.myWishList();
    }
}
