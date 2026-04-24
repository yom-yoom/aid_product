package ru.lab.foodaid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lab.foodaid.model.Product;
import ru.lab.foodaid.model.Ration;
import ru.lab.foodaid.model.RationComposition;
import ru.lab.foodaid.model.RationCompositionId;
import ru.lab.foodaid.repository.ProductRepository;
import ru.lab.foodaid.repository.RationCompositionRepository;
import ru.lab.foodaid.repository.RationRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RationService {

    private final RationRepository rationRepository;
    private final RationCompositionRepository compositionRepository;
    private final ProductRepository productRepository;

    public RationService(RationRepository rationRepository,
                         RationCompositionRepository compositionRepository,
                         ProductRepository productRepository) {
        this.rationRepository = rationRepository;
        this.compositionRepository = compositionRepository;
        this.productRepository = productRepository;
    }

    public List<Ration> findAll() {
        return rationRepository.findAllByOrderByNameAsc();
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

    public List<RationComposition> getCompositions(Long rationId) {
        return compositionRepository.findByRation_IdOrderByProduct_NameAsc(rationId);
    }

    @Transactional
    public RationComposition addProductToRation(Long rationId, Long productId, BigDecimal quantity) {
        Ration ration = getById(rationId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));

        RationCompositionId id = new RationCompositionId(rationId, productId);
        return compositionRepository.findById(id)
                .map(existing -> {
                    existing.setQuantity(quantity);
                    return compositionRepository.save(existing);
                })
                .orElseGet(() -> compositionRepository.save(new RationComposition(ration, product, quantity)));
    }

    public void removeProductFromRation(Long rationId, Long productId) {
        compositionRepository.deleteById(new RationCompositionId(rationId, productId));
    }

    @Transactional
    public void deactivate(Long rationId) {
        Ration ration = getById(rationId);
        ration.setActive(false);
        rationRepository.save(ration);
    }

    @Transactional
    public void activate(Long rationId) {
        Ration ration = getById(rationId);
        ration.setActive(true);
        rationRepository.save(ration);
    }

    public List<Ration> findActiveRations() {
        return rationRepository.findByActiveTrueOrderByNameAsc();
    }
}
