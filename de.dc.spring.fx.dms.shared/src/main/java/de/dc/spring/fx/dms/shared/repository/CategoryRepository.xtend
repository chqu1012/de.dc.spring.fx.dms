package de.dc.spring.fx.dms.shared.repository

import java.util.List
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import de.dc.spring.fx.dms.shared.model.Category

@Repository 
interface CategoryRepository extends JpaRepository<Category, Long> {
	
	def List<Category> findByName(String name)
}
