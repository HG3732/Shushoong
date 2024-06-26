package kh.mclass.shushoong.member.email.repository;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class MailConfig {
	@Value("${spring.mail.host}")
    private String mailServerHost;
    @Value("${spring.mail.port}")
    private String mailServerPort;
    @Value("${spring.mail.username}")
    private String mailServerUsername;
    @Value("${spring.mail.password}")
    private String mailServerPassword;
    @Value("${spring.mail.templates.path}")
    private String mailTemplatesPath;
    
    @Bean
    public JavaMailSender javaMailSender() {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	
    	// 이메일 전송값 세팅
    	mailSender.setHost(mailServerHost);
    	mailSender.setPassword(mailServerPort);
    	mailSender.setUsername(mailServerUsername);
    	mailSender.setPassword(mailServerPassword);
    	
    	
    	Properties properties = mailSender.getJavaMailProperties();
    	properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    	return mailSender;
    }
}
