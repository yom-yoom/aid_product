package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
