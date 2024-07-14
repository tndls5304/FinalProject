package com.kh.works.inputstream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ShowImgController {

    @GetMapping(value = "/img/profile/{profileImgName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProfileImg(@PathVariable("profileImgName") String profile) throws IOException {
        /*
        InputStream imageStream = new FileInputStream("D:///img/profile/" + profile);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
        */

        String projectPath = System.getProperty("user.dir"); //실행시 경로가 아닌 현재 프로젝트 경로를 들고옴
        String imgPath = projectPath + "\\src\\main\\resources\\static\\img\\profile\\" + profile;
        try (InputStream imageStream = new FileInputStream(imgPath)){       //닫아주는게 포함됨
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
        }
    }
}
