package de.dc.spring.fx.dms.server.repository

import de.dc.spring.fx.dms.server.model.Category
import java.util.List
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository 
interface CategoryRepository extends JpaRepository<Category, Long> {
	
	def List<Category> findByName(String name)
}