package de.dc.spring.fx.dms.ui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import de.dc.spring.fx.dms.shared.model.Ticket;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DmsClient {

	private static final Logger log = LoggerFactory.getLogger(DmsClient.class);

	public static void main(String args[]) {
		SpringApplication.run(DmsClient.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Ticket[] ticket = restTemplate.getForObject(
					"http://localhost:8090/tickets", Ticket[].class);
			for (Ticket t : ticket) {
				System.out.println(t);
			}
			log.info(ticket.toString());
		};
	}
}