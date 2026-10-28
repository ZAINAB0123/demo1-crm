package ru.zainab.dealservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;

@Entity
@Table(name = "deals")
@Getter
@Setter
@NoArgsConstructor()
public class Deal extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String title;
    @JdbcTypeCode(Types.LONGVARCHAR)
    private String description;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private DealStatus status;

    public Deal(String title, String description, BigDecimal amount) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.status = DealStatus.NEW;
    }
}
