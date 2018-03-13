package de.dc.spring.fx.dms.server.repository;

import de.dc.spring.fx.dms.server.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public interface CategoryRepository extends JpaRepository<Category, Long> {
  public abstract List<Category> findByName(final String name);
}