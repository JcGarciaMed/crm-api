package pe.forjix.crmapi.dto;

import lombok.*;
import pe.forjix.crmapi.model.DealStage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DealResponseDTO {

    private UUID id;
    private String title;
    private BigDecimal amount;
    private DealStage stage;
    private LocalDateTime createdAt;

    // Datos aplanados del contacto para evitar recursión infinita en el JSON
    private UUID contactId;
    private String contactFullName;
}
