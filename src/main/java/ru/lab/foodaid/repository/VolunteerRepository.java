package ru.lab.foodaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lab.foodaid.model.Volunteer;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findAllByActiveTrueOrderByFullNameAsc();
}
