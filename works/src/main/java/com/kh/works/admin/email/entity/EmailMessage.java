package com.kh.works.admin.email.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//수인--------------------------------

/**
 * 이메일 Message VO
 *
 * @since 24. 06. 29
 * @author 이수인
 */
@Data
public class EmailMessage {

    /** 메일 제목 */
    private String subject;

    /** 메일 내용 */
    private String message;

    /** 받는 메일주소 */
    private String to;
}