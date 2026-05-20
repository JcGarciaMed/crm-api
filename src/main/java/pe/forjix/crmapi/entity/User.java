package pe.forjix.crmapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue // Hibernate genera automáticamente el UUID v4
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // insertable = false evita que JPA intente enviar este campo, dejando que MySQL use el DEFAULT CURRENT_TIMESTAMP
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
