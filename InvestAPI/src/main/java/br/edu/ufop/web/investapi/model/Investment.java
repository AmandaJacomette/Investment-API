package br.edu.ufop.web.investapi.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvestmentType type;

    private String symbol;

    private Integer quantity;

    private BigDecimal purchasePrice;

    private LocalDate purchaseDate;

    public void setType(InvestmentType type) { this.type = type; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }

    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
}
