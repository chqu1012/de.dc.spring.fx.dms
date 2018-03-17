package de.dc.spring.fx.dms.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"de.dc.spring.fx.dms"})
@EnableJpaRepositories("de.dc.spring.fx.dms.shared.repository")
@EntityScan(value = {"de.dc.spring.fx.dms.shared.model"})
public class DmsServer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DmsServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Random r = new Random();
//		for (int i = 0; i < 10; i++) {
//			String name = "Test User "+i;
//			String description = "Test Description "+i*r.nextInt();
//			int categoryId=i*r.nextInt();
//			int userId =i*r.nextInt();
//			LocalDateTime createdOn = LocalDateTime.now();
//			Ticket createTicket = new Ticket(name, description, categoryId, userId, createdOn);
////			System.out.println("CREATE TICKET: "+createTicket);
//			ticketRepository.save(createTicket);
//		}
	}
}
