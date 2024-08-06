package com.kh.works.admin.email.service;

import com.kh.works.admin.email.entity.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


//---------------수인------------------------------------

/**
 * 메일 발송 서비스
 * @apiNote 사용자에게 회원 가입 메일 발송
 * @since 24. 06. 29
 * @author suin Lee
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    /**
     * 메일발송
     * @param emailMessage 발송할 메일 정보
     */
    public void sendMail(EmailMessage emailMessage) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(emailMessage.getMessage(),true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("Success");



        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }


//    public static void main(String[] args) {
//        EmailService o;
//        o.sendMail(null);
//
//    }

}
