package ru.lab.foodaid.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "ration_composition")
public class RationComposition {

    @EmbeddedId
    private RationCompositionId id;

    @ManyToOne(optional = false)
    @MapsId("rationId")
    @JoinColumn(name = "id_ration", nullable = false)
    private Ration ration;

    @ManyToOne(optional = false)
    @MapsId("productId")
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    public RationComposition() {}

    public RationComposition(Ration ration, Product product, BigDecimal quantity) {
        this.id = new RationCompositionId(ration.getId(), product.getId());
        this.ration = ration;
        this.product = product;
        this.quantity = quantity;
    }

    public RationCompositionId getId() { return id; }
    public void setId(RationCompositionId id) { this.id = id; }
    public Ration getRation() { return ration; }
    public void setRation(Ration ration) { this.ration = ration; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
}
