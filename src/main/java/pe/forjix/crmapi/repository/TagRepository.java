package pe.forjix.crmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.forjix.crmapi.entity.Tag;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    // Ejemplo de método útil: buscar la etiqueta por su nombre exacto
    java.util.Optional<Tag> findByName(String name);
}
