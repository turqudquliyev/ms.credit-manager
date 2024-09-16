package az.ingress.dao.entity;

import az.ingress.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "credits")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private CreditStatus status;

    private Integer term;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;

    private Long conveyorId;
    private LocalDateTime checkDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = LAZY)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "credit", fetch = LAZY, cascade = {PERSIST, MERGE})
    private List<OfferEntity> offers;

    @OneToMany(mappedBy = "credit", fetch = LAZY, cascade = {PERSIST, MERGE})
    private List<StatusHistoryEntity> statusHistories;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var creditEntity = (CreditEntity) o;
        return Objects.equals(id, creditEntity.id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}