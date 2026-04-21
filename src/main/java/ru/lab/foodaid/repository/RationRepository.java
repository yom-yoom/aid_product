package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.Ration;

import java.util.List;

public interface RationRepository extends JpaRepository<Ration, Long> {
    List<Ration> findAllByActiveTrueOrderByNameAsc();
    long countByActiveTrue();
}
