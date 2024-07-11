package com.kh.works.board.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Delete;

@Data
public class BoardImgVo {
    private String no;
    private String boardNo;
    private String imgName;
}
