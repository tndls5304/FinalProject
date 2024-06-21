package com.kh.works.board.service;

import com.kh.works.board.dao.BoardDao;
import com.kh.works.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;

    //작성 화면 보여주기
    public int write(BoardVo vo) {

        int result = dao.write(vo);
        return result;

    }
}
