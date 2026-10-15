package fr.exemple.signalements;

import org.springframework.boot.SpringApplication;

public class TestSignalementsApplication {

	public static void main(String[] args) {
		SpringApplication.from(SignalementsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
