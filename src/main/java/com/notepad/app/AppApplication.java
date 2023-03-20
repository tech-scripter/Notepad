package com.notepad.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*
	--- START
	HOW TO INJECT PROTOTYPE SCOPE BEAN INTO SINGLETON SCOPE BEAN
	 */
//	@Bean
//	@Scope("prototype")
//	public Color color() {
//		Random random = new Random();
//		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
//	}
//
//	@Bean
//	@Scope("singleton")
//	public ColorFrame colorFrame() {
//		return new ColorFrame() {
//			@Override
//			public Color getColor() {
//				return color();
//			}
//		};
//	}

	/*
	--- END
	HOW TO INJECT PROTOTYPE SCOPE BEAN INTO SINGLETON SCOPE BEAN
	 */

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
