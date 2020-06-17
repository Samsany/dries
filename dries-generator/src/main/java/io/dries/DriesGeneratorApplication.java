package io.dries;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZSY
 */
@SpringBootApplication
@MapperScan("io.dries.dao")
public class DriesGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriesGeneratorApplication.class, args);
	}
}
