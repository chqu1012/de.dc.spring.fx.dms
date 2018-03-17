package de.dc.spring.fx.dms.server.controller;

import de.dc.spring.fx.dms.server.controller.IRestController;
import de.dc.spring.fx.dms.shared.model.Category;
import de.dc.spring.fx.dms.shared.repository.CategoryRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@SuppressWarnings("all")
public class CategoryController implements IRestController<Category> {
  @Autowired
  private CategoryRepository categoryRepository;
  
  @GetMapping("/categories")
  @Override
  public List<Category> getAll() {
    return this.categoryRepository.findAll();
  }
  
  @GetMapping("/categories/{id}")
  @Override
  public Category findById(@PathVariable final long id) {
    return this.categoryRepository.findById(Long.valueOf(id)).get();
  }
  
  @DeleteMapping("/categories/{id}")
  @Override
  public void deleteById(@PathVariable final long id) {
    this.categoryRepository.deleteById(Long.valueOf(id));
  }
  
  @PutMapping("/categories/{id}")
  @Override
  public ResponseEntity<Object> update(@RequestBody final Category category, @PathVariable final long id) {
    ResponseEntity<Object> _xblockexpression = null;
    {
      Optional<Category> categoryOptional = this.categoryRepository.findById(Long.valueOf(id));
      boolean _isPresent = categoryOptional.isPresent();
      boolean _not = (!_isPresent);
      if (_not) {
        return ResponseEntity.notFound().<Object>build();
      }
      category.setId(Long.valueOf(id));
      this.categoryRepository.<Category>save(category);
      _xblockexpression = ResponseEntity.noContent().<Object>build();
    }
    return _xblockexpression;
  }
  
  @PostMapping("/categories")
  @Override
  public ResponseEntity<Object> create(@RequestBody final Category category) {
    Category saveCategory = this.categoryRepository.<Category>save(category);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
      saveCategory.getId()).toUri();
    return ResponseEntity.created(location).<Object>build();
  }
}
