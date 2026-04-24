package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import ru.lab.foodaid.model.Volunteer;
import ru.lab.foodaid.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public List<Volunteer> findActive() {
        return volunteerRepository.findAllByAvailabilityStatusOrderByFullNameAsc("Доступен");
    }

    public Volunteer getById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Волонтёр не найден"));
    }

    public Volunteer save(Volunteer volunteer) {
        if (volunteer.getAvailabilityStatus() == null || volunteer.getAvailabilityStatus().isBlank()) {
            volunteer.setAvailabilityStatus("Доступен");
        }
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> findAll() {
        return volunteerRepository.findAllByOrderByFullNameAsc();
    }
}
