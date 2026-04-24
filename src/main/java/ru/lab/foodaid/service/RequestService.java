package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lab.foodaid.dto.RequestCreateForm;
import ru.lab.foodaid.model.Beneficiary;
import ru.lab.foodaid.model.HelpRequest;
import ru.lab.foodaid.model.RequestStatus;
import ru.lab.foodaid.repository.HelpRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    private final HelpRequestRepository helpRequestRepository;
    private final RationService rationService;

    public RequestService(HelpRequestRepository helpRequestRepository,
                          RationService rationService) {
        this.helpRequestRepository = helpRequestRepository;
        this.rationService = rationService;
    }

    @Transactional
    public HelpRequest createRequest(RequestCreateForm form) {
        Beneficiary beneficiary = new Beneficiary(
                form.getFullName(),
                form.getPhone(),
                form.getAddress(),
                form.getCategory()
        );
        beneficiary.setBenefitStatus("Подтверждается");
        beneficiary.setCreatedAt(LocalDateTime.now());

        HelpRequest request = new HelpRequest(
                beneficiary,
                rationService.getById(form.getRationId()),
                RequestStatus.NEW,
                form.getAdminComment() != null ? form.getAdminComment() : ""
        );
        request.setDeliveryAddress(form.getDeliveryAddress() != null && !form.getDeliveryAddress().isBlank()
                ? form.getDeliveryAddress()
                : form.getAddress());
        request.setReceiptChannel(form.getReceiptChannel());
        return helpRequestRepository.save(request);
    }

    public List<HelpRequest> findAll() {
        return helpRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public HelpRequest getById(Long id) {
        return helpRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));
    }

    @Transactional
    public void updateStatus(Long id, RequestStatus status, String adminComment) {
        HelpRequest request = getById(id);
        request.setStatus(status);
        request.setAdminComment(adminComment);
        helpRequestRepository.save(request);
    }

    public long countAll() {
        return helpRequestRepository.count();
    }
}
