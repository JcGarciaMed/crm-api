package pe.forjix.crmapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.forjix.crmapi.dto.DealRequestDTO;
import pe.forjix.crmapi.dto.DealResponseDTO;
import pe.forjix.crmapi.entity.Contact;
import pe.forjix.crmapi.entity.Deal;
import pe.forjix.crmapi.entity.User;
import pe.forjix.crmapi.repository.ContactRepository;
import pe.forjix.crmapi.repository.DealRepository;
import pe.forjix.crmapi.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Transactional
    public DealResponseDTO createDeal(DealRequestDTO request) {
        // 1. Buscar dependencias
        Contact contact = contactRepository.findById(request.getContactId())
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con ID: " + request.getContactId()));

        User user = null;
        if (request.getCreatedById() != null) {
            user = userRepository.findById(request.getCreatedById())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }

        // 2. Construir la entidad
        Deal deal = new Deal();
        deal.setTitle(request.getTitle());
        deal.setAmount(request.getAmount());
        deal.setStage(request.getStage());
        deal.setContact(contact);
        deal.setCreatedBy(user);

        // 3. Guardar en BD
        Deal savedDeal = dealRepository.save(deal);

        // 4. Retornar el DTO
        return mapToDTO(savedDeal);
    }

    @Transactional(readOnly = true)
    public List<DealResponseDTO> getAllDeals() {
        return dealRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DealResponseDTO getDealById(UUID id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));
        return mapToDTO(deal);
    }

    // Método auxiliar para mapear Entidad -> DTO
    private DealResponseDTO mapToDTO(Deal deal) {
        return DealResponseDTO.builder()
                .id(deal.getId())
                .title(deal.getTitle())
                .amount(deal.getAmount())
                .stage(deal.getStage())
                .createdAt(deal.getCreatedAt())
                .contactId(deal.getContact() != null ? deal.getContact().getId() : null)
                .contactFullName(deal.getContact() != null ?
                        deal.getContact().getFirstName() + " " + deal.getContact().getLastName() : "Sin Contacto")
                .build();
    }
}
