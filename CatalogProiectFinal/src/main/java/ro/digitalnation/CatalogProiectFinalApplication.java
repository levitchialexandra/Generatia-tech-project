package ro.digitalnation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "basic")
@EntityScan(basePackages = "basic")

public class CatalogProiectFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogProiectFinalApplication.class, args);
	}

}
