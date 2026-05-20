package pe.forjix.crmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.forjix.crmapi.model.DealStage;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealRequestDTO {

    private UUID contactId;
    private String title;
    private BigDecimal amount;
    private DealStage stage;

    // En un proyecto real con Spring Security, el ID del creador se extrae
    // automáticamente del token JWT, pero para esta práctica lo enviamos en el JSON.
    private UUID createdById;
}
