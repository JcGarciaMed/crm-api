package pe.forjix.crmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.forjix.crmapi.entity.Contact;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
}