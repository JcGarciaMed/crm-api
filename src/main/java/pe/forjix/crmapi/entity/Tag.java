package pe.forjix.crmapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    // Lado inverso de la relación Muchos a Muchos
    @ManyToMany(mappedBy = "tags")
    private Set<Contact> contacts = new HashSet<>();
}
