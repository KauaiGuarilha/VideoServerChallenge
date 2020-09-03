package com.videoserverchallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Video Server Challege",
				version = "1.0",
				description = "Project Documentation", contact =
			    @Contact(name = "Kauai Guarilha",
				url = "https://www.linkedin.com/in/kauai-guarilha/",
				email = "kauai.guarilha@hotmail.com")))
public class VideoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServerApplication.class, args);
	}
}