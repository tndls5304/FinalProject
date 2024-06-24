package com.kh.works.board.dao;

import com.kh.works.board.mapper.BoardMapper;
import com.kh.works.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper mapper;

    public int write(BoardVo vo) {

        int result = mapper.write(vo);
        return result;
    }
}
