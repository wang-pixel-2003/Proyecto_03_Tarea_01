package project.logic.entity.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Category findByName(String name);

}
