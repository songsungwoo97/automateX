package com.example.AutomateX.service.mail;



import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {

    private final JavaMailSender emailSender;

    private String ePw; //인증 코드

    private MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException { //메시지 생성

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상 성정
        message.setSubject("이메일 인증 테스트"); //제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 AutomateX입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("songsungwoo9200@gmail.com","AutomateX"));//보내는 사람

        return message;
    }

    public static String createKey() { //인증코드 생성
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0 -> key.append((char) (rnd.nextInt(26) + 97));

                //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                case 1 -> key.append((char) (rnd.nextInt(26) + 65));

                //  A~Z
                case 2 -> key.append((rnd.nextInt(10)));

                // 0~9
            }
        }
        return key.toString();
    }

    @Transactional
    public String sendMessage(String to) throws MessagingException, UnsupportedEncodingException { //메시지 전송

        ePw = createKey(); // 인증코드 생성

        MimeMessage message = createMessage(to); //메일 전송

        emailSender.send(message);

        return ePw; //인증코드 반환
    }
}
