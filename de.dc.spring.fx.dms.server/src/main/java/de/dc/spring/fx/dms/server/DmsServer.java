package de.dc.spring.fx.dms.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.dc.spring.fx.dms.server.repository.TicketRepository;

@SpringBootApplication
@ComponentScan(basePackages="de.dc.spring.fx.dms.server")
public class DmsServer implements CommandLineRunner{

	@Autowired TicketRepository ticketRepository;
	
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
//			System.out.println("CREATE TICKET: "+createTicket);
//			ticketRepository.save(createTicket);
//		}
	}
}
