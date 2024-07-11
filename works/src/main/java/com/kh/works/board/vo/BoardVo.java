package com.kh.works.board.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
public class BoardVo {

    private String boardNo;
    private String empNo;
    private String title;
    private String content;
    private String viewCount;
    private String crtnDate;
    private String mdfdDate;
    private String delYn;
    private List<MultipartFile> img;
    private String imgNames;
    private String name;

}
