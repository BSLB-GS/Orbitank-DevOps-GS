package br.com.orbitank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${orbitank.mail.from}")
    private String mailFrom;

    public void sendVerificationCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject("Orbitank - Recuperação de Senha");
        message.setText("Seu código de recuperação é: " + code + "\n\nEle expira em 10 minutos.");

        mailSender.send(message);
    }
}