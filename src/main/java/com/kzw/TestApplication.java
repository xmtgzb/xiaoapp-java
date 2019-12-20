package com.kzw;

import com.kzw.Repo.impl.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication(scanBasePackages = {"com.kzw"})
@EnableJpaRepositories(repositoryBaseClass = BaseRepository.class)
@EntityScan(basePackages = "com.kzw")
@ComponentScan({"com.kzw"})
public class TestApplication {
private static Logger log = LoggerFactory.getLogger(TestApplication.class);


	public static void main(String[] args) {
		try{
			SpringApplication.run(TestApplication.class, args);
		}catch (Exception e){
			log.error("error",e);
		}

	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("20MB"); //KB,MB
		//设置总上传数据总大小
		factory.setMaxRequestSize("1024MB");
		return factory.createMultipartConfig();
	}
}