package ru.lab.foodaid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RationCompositionId implements Serializable {

    @Column(name = "id_ration")
    private Long rationId;

    @Column(name = "id_product")
    private Long productId;

    public RationCompositionId() {}

    public RationCompositionId(Long rationId, Long productId) {
        this.rationId = rationId;
        this.productId = productId;
    }

    public Long getRationId() { return rationId; }
    public void setRationId(Long rationId) { this.rationId = rationId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RationCompositionId that)) return false;
        return Objects.equals(rationId, that.rationId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rationId, productId);
    }
}
