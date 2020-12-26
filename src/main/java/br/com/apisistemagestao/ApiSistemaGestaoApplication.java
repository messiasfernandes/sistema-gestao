package br.com.apisistemagestao;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.apisistemagestao.config.property.SistemaGestaoApiProperty;



@SpringBootApplication
@EnableConfigurationProperties(SistemaGestaoApiProperty.class)
public class ApiSistemaGestaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSistemaGestaoApplication.class, args);
	}

}
