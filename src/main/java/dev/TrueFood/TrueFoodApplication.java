package dev.TrueFood;

import dev.TrueFood.repositories.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrueFoodApplication {
//
//	private static Initializer initiator;
//
//	@Autowired
//	public TrueFoodApplication(Initializer initiator) {
//		TrueFoodApplication.initiator = initiator;
//	}

	public static void main(String[] args) {

		SpringApplication.run(TrueFoodApplication.class, args);
//		initiator.initial();
	}

}
