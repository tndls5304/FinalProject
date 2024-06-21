package com.kh.works.board.mapper;

import com.kh.works.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;

public interface BoardMapper {

    @Insert()
    int write(BoardVo vo);
}
