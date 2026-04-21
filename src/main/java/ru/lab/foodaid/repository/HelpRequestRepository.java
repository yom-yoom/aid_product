package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.HelpRequest;

import java.util.List;

public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {
    List<HelpRequest> findAllByOrderByCreatedAtDesc();
}
