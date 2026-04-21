package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lab.foodaid.model.*;
import ru.lab.foodaid.repository.DeliveryRepository;
import ru.lab.foodaid.repository.HelpRequestRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final HelpRequestRepository helpRequestRepository;
    private final VolunteerService volunteerService;

    public DeliveryService(DeliveryRepository deliveryRepository,
                           HelpRequestRepository helpRequestRepository,
                           VolunteerService volunteerService) {
        this.deliveryRepository = deliveryRepository;
        this.helpRequestRepository = helpRequestRepository;
        this.volunteerService = volunteerService;
    }

    @Transactional
    public void assignDelivery(Long requestId, Long volunteerId, LocalDateTime scheduledAt, String deliveryComment) {
        HelpRequest request = helpRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));
        Volunteer volunteer = volunteerService.getById(volunteerId);

        Delivery delivery = request.getDelivery();
        if (delivery == null) {
            delivery = new Delivery();
            delivery.setHelpRequest(request);
        }
        delivery.setVolunteer(volunteer);
        delivery.setScheduledAt(scheduledAt);
        delivery.setStatus(DeliveryStatus.PLANNED);
        delivery.setResultComment(deliveryComment);

        request.setStatus(RequestStatus.ASSIGNED);
        request.setAdminComment("Назначена доставка");
        request.setDelivery(delivery);

        deliveryRepository.save(delivery);
        helpRequestRepository.save(request);
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAllByOrderByStatusAscScheduledAtAsc();
    }

    public long countAll() {
        return deliveryRepository.count();
    }

    @Transactional
    public void completeDelivery(Long deliveryId, String resultComment) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Доставка не найдена"));

        delivery.setStatus(DeliveryStatus.DONE);
        delivery.setDeliveredAt(LocalDateTime.now());
        delivery.setResultComment(resultComment);

        HelpRequest request = delivery.getHelpRequest();
        request.setStatus(RequestStatus.DONE);
        request.setAdminComment("Доставка завершена");

        deliveryRepository.save(delivery);
        helpRequestRepository.save(request);
    }
}
