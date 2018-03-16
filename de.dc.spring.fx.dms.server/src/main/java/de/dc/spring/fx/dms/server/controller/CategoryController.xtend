package de.dc.spring.fx.dms.server.controller

import de.dc.spring.fx.dms.server.repository.TicketRepository
import de.dc.spring.fx.dms.shared.model.Ticket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import de.dc.spring.fx.dms.shared.model.Category
import de.dc.spring.fx.dms.server.repository.CategoryRepository

@RestController 
class CategoryController implements IRestController<Category> {
	
	@Autowired CategoryRepository categoryRepository

	@GetMapping("/categories") 
	override getAll() {
		categoryRepository.findAll
	}

	@GetMapping("/categories/{id}") 
	override findById(@PathVariable long id) {
		categoryRepository.findById(id).get
	}

	@DeleteMapping("/categories/{id}") 
	override deleteById(@PathVariable long id) {
		categoryRepository.deleteById(id)
	}

	@PutMapping("/categories/{id}") 
	override update(@RequestBody Category category, @PathVariable long id) {
		var categoryOptional = categoryRepository.findById(id)
		
		if(!categoryOptional.isPresent) 
			return ResponseEntity.notFound.build
		
		category.setId(id)
		categoryRepository.save(category)
		ResponseEntity.noContent.build
	}

	@PostMapping("/categories") 
	override create(@RequestBody Category category) {
		var saveCategory = categoryRepository.save(category)
		var location = ServletUriComponentsBuilder.fromCurrentRequest.path("/{id}").buildAndExpand(
			saveCategory.id).toUri
		return ResponseEntity.created(location).build
	}
}
