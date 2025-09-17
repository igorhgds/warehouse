package henrique.igor.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private Long amount;

    private BigDecimal boughtPrice;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    private BigDecimal soldPrice;

    public void decAmount(){
        this.amount -= 1;
        if (this.amount == 0){
            this.status = StockStatus.UNAVAILABLE;
        }
    }

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public boolean isUnavailable(){
        return status == StockStatus.UNAVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(boughtPrice, that.boughtPrice) && status == that.status &&
                Objects.equals(soldPrice, that.soldPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, boughtPrice, status, soldPrice);
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
