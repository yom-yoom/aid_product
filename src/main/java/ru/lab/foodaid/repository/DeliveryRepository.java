package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.Delivery;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByOrderByStatusAscScheduledAtAsc();
    List<Delivery> findAllByVolunteerIdOrderByStatusAscScheduledAtAsc(Long volunteerId);
}
