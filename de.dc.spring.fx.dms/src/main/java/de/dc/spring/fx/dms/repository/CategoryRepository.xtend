package de.dc.spring.fx.dms.repository

import java.util.List
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import de.dc.spring.fx.dms.model.Category

@Repository 
interface CategoryRepository extends JpaRepository<Category, Long> {
	
	def List<Category> findByName(String name)
}
