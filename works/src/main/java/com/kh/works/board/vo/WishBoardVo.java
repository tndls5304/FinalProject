package com.kh.works.board.vo;

import lombok.Data;

@Data
public class WishBoardVo {
    private Integer boardWishNo;
    private Integer noticeWishNo;
    private Integer empNo;
    private String name;
    private String boardNo;
    private String title;
    private String content;
    private String viewCount;
    private String crtnDate;
    private String mdfdDate;
    private String delYn;
    private String fileName;
    private String img;
}
