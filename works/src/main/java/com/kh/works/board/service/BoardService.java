package com.kh.works.board.service;

import com.kh.works.board.dao.BoardDao;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.board.vo.WishBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
    public BoardVo getBoardDetail(String boardNo, String loginNo) {

        BoardVo vo =dao.getBoardDetail(boardNo);

        if (vo != null){
            if (!loginNo.equals(vo.getEmpNo())){
                int no = Integer.parseInt(boardNo);
                dao.updateViewCount(no);
                vo.setViewCount(vo.getViewCount()+1);
            }
        }
        return vo;

    }

    public int editBoard(BoardVo vo, String boardNo) {

        System.out.println(vo + boardNo);
        int result = dao.editBoard(vo,boardNo);
        return result;
    }

    public int deleteBoard(String boardNo) {
        return dao.deleteBoard(boardNo);
    }

    public List<BoardVo> searchTitle(String title) {
        return dao.searchTitle(title);
    }

    public List<BoardVo> searchEmpName(String empName) {
        return dao.searchEmpName(empName);
    }

    public BoardVo myListDetail(String boardNo, String empNo) {
        return dao.myListDetail(boardNo , empNo);
    }

    public int wishBoard(WishBoardVo vo) {
        return dao.wishBoard(vo );
    }

    public int wishCancleBoard(WishBoardVo vo ) {
        return dao.wishCancleBoard(vo);
    }

    public boolean checkWishList(WishBoardVo vo ) {
        return dao.checkWishList(vo);
    }

    public List<WishBoardVo> myWishList() {
        return dao.myWishList();
    }
}
