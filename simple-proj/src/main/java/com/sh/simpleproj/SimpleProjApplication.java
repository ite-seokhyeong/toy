package com.sh.simpleproj;


import com.sh.simpleproj.config.WebServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SimpleProjApplication {

	private static final Logger logger  = LoggerFactory.getLogger(SimpleProjApplication.class);

	public static void main(String[] args) {
		logger.info("!!!!!!!!Sping boot Logback {}", SimpleProjApplication.class.getSimpleName());
		new SpringApplicationBuilder(SimpleProjApplication.class)
				.initializers(new WebServerConfig())
				.run();


		//SpringApplication.run(SimpleProjApplication.class, args);
	}

}
