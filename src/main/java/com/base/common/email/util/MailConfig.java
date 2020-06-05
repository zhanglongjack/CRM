package com.base.common.email.util;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

	@Bean
	public JavaMailSenderImpl mailSender(Environment env) {
		System.out.println("host==="+env.getProperty("mailserver.host"));
		// spring自带了一个MailSender的实现接口
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// 邮件服务器主机名
		mailSender.setHost(env.getProperty("mailserver.host"));
		// 邮件服务器监听的端口号
		mailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
		// 用户名称
		mailSender.setUsername(env.getProperty("mailserver.username"));
		// 密码，这里不是邮箱的密码，而是开启服务器后，生成的授权码
		mailSender.setPassword(env.getProperty("mailserver.password"));
		// ssl加密，需要加，否则无法运行
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		mailSender.setJavaMailProperties(props);
		return mailSender;
	}
}
