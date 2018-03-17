package de.dc.spring.fx.dms.thymeleaf.ui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"de.dc.spring.fx.dms.thymeleaf.ui"})
public class DmsThymeLeafApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DmsThymeLeafApplication.class, args);
	}

}