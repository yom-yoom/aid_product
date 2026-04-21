package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import ru.lab.foodaid.model.Ration;
import ru.lab.foodaid.repository.RationRepository;

import java.util.List;

@Service
public class RationService {

    private final RationRepository rationRepository;

    public RationService(RationRepository rationRepository) {
        this.rationRepository = rationRepository;
    }

    public List<Ration> findActiveRations() {
        return rationRepository.findAllByActiveTrueOrderByNameAsc();
    }

    public Ration getById(Long id) {
        return rationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Рацион не найден"));
    }

    public Ration save(Ration ration) {
        return rationRepository.save(ration);
    }

    public long countActive() {
        return rationRepository.countByActiveTrue();
    }
}
