package com.safetynet.alerts;

import com.safetynet.alerts.data.JsonDatabase;
import com.safetynet.alerts.service.JsonDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AlertsApplication implements CommandLineRunner {
	@Value("${database.file}")
	private String fileName;

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonDataService dataService = new JsonDataService(getFile());

		JsonDatabase db = new JsonDatabase(dataService);
		db.loadDataFromJSON();
	}

	@Bean
	public File getFile() throws IOException {
		ClassPathResource resource = new ClassPathResource("/data/" + fileName);
		return resource.getFile();
	}
}
