package com.kh.works.board.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardImgVo {

    private String no;
    private String imgName;
    private String boardNo;
    private List<MultipartFile> imgNames;
}
