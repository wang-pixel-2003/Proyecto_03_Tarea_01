package project.logic.entity.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    @Query("SELECT c FROM Categoria c WHERE c.nombre = ?1")
    Categoria findByNombre(String nombre);

}
