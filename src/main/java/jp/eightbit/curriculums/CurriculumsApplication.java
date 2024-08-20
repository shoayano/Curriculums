package jp.eightbit.curriculums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CurriculumsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CurriculumsApplication.class, args);
	}
	
	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CurriculumsApplication.class);
	}

}
