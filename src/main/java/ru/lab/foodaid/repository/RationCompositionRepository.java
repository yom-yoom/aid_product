package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.RationComposition;
import ru.lab.foodaid.model.RationCompositionId;

import java.util.List;

public interface RationCompositionRepository extends JpaRepository<RationComposition, RationCompositionId> {
    List<RationComposition> findByRation_IdOrderByProduct_NameAsc(Long rationId);
}
