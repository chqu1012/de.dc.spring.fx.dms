package de.dc.spring.fx.dms.service

import de.dc.spring.fx.dms.shared.model.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CategoryDtoService extends BaseDtoService<Category>{
		
	@Autowired RestTemplate restTemplate
	 
	override resource() { 
		"categories"
	}
	
	override create(Category t) {
		restTemplate.postForEntity(url, t.createHttpEntity, Category);
	}
	
	override findById(long id) {
		restTemplate.getForObject('''«url»/«id»''', Category)
	}
	
	override getAll(){
		restTemplate.getForObject(url, typeof(Category[]))
	}
	
	override update(Category t) {
		restTemplate.put('''«url»/«t.id»''', t.createHttpEntity, (#[] as Object[]))
	}
	
	override deleteById(long id) {
		restTemplate.delete('''«url»/«id»'''.toString)
	}
	
}