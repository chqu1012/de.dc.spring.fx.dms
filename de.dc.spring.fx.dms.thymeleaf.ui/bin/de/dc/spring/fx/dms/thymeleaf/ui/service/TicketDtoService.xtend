package de.dc.spring.fx.dms.thymeleaf.ui.service

import de.dc.spring.fx.dms.shared.model.Ticket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class TicketDtoService {

	public static String PORT = "8090"
	public static String TICKETS = "tickets"
	public static String REQUEST = String::format("http://localhost:%s/%s", PORT, TICKETS)

	@Autowired RestTemplate restTemplate

	def getTickets() {
		val result = restTemplate.getForObject(REQUEST, typeof(Ticket[]))
		result.forEach[e|println(e)]
		result
	}

	def getTicketById(long id) {
		restTemplate.getForObject('''«REQUEST»/«id»''', Ticket)
	}

	def deleteTicketById(long id) {
		restTemplate.delete('''«REQUEST»/«id»'''.toString)
	}

	def update(Ticket ticket) {
		var headers = new HttpHeaders
		headers.add("Accept", MediaType::APPLICATION_JSON_VALUE)

		var requestBody = new HttpEntity(ticket, headers)
		restTemplate.put(REQUEST + "/" + ticket.id, requestBody, (#[] as Object[]))
	}

	def create(Ticket ticket) {
		var headers = new HttpHeaders
		headers.add("Accept", MediaType::APPLICATION_JSON_VALUE)
		headers.setContentType(MediaType.APPLICATION_JSON)

		val requestBody = new HttpEntity(ticket, headers)
		val result = restTemplate.postForEntity(REQUEST, requestBody, Ticket)
		result.body
	}
}
