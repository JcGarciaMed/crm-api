package pe.forjix.crmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.forjix.crmapi.entity.Deal;

import java.util.UUID;

@Repository
public interface DealRepository extends JpaRepository<Deal, UUID> {
    // Aquí puedes agregar consultas personalizadas en el futuro si lo necesitas
}
