package pe.forjix.crmapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", columnDefinition = "BINARY(16)")
    private Company company;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", columnDefinition = "BINARY(16)")
    private User createdBy;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // ==========================================
    // RELACIÓN MUCHOS A MUCHOS (Lado Propietario)
    // ==========================================
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "contact_tag", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "contact_id", columnDefinition = "BINARY(16)"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", columnDefinition = "BINARY(16)")
    )
    private Set<Tag> tags = new HashSet<>();
}
