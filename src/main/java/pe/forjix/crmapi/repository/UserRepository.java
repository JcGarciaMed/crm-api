package pe.forjix.crmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.forjix.crmapi.entity.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Ejemplo de método útil para un CRM: buscar un usuario por su correo
    Optional<User> findByEmail(String email);
}
