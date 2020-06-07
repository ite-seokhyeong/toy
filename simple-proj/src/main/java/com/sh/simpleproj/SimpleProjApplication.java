package com.sh.simpleproj;

import com.sh.simpleproj.config.WebServerConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SimpleProjApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SimpleProjApplication.class)
		.initializers(new WebServerConfig()).run();
		//SpringApplication.run(SimpleProjApplication.class, args);
	}

}
