package br.edu.ufop.web.investapi.service;

import br.edu.ufop.web.investapi.dto.*;
import br.edu.ufop.web.investapi.exception.ResourceNotFoundException;
import br.edu.ufop.web.investapi.model.Investment;
import br.edu.ufop.web.investapi.model.InvestmentType;
import br.edu.ufop.web.investapi.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class InvestmentService {

    private final InvestmentRepository repository;

    public InvestmentService(InvestmentRepository repository) {
        this.repository = repository;
    }

    public InvestmentResponseDTO create(InvestmentRequestDTO dto) {

        validate(dto);

        Investment investment = mapToEntity(dto);
        Investment saved = repository.save(investment);

        return mapToResponse(saved);
    }

    public List<InvestmentResponseDTO> findAll(InvestmentType type) {

        List<Investment> investments =
                (type == null)
                        ? repository.findAll()
                        : repository.findByType(type);

        return investments.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public InvestmentResponseDTO update(Long id, InvestmentRequestDTO dto) {

        validate(dto);

        Investment investment = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ativo não encontrado"));

        investment.setType(dto.getType());
        investment.setSymbol(dto.getSymbol());
        investment.setQuantity(dto.getQuantity());
        investment.setPurchasePrice(dto.getPurchasePrice());
        investment.setPurchaseDate(dto.getPurchaseDate());

        Investment updated = repository.save(investment);

        return mapToResponse(updated);
    }

    public void delete(Long id) {

        Investment investment = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ativo não encontrado"));

        repository.delete(investment);
    }

    public SummaryDTO getSummary() {

        List<Investment> investments = repository.findAll();

        BigDecimal totalInvested = BigDecimal.ZERO;
        Map<InvestmentType, BigDecimal> totalByType = new HashMap<>();

        for (Investment inv : investments) {

            BigDecimal total = inv.getPurchasePrice()
                    .multiply(BigDecimal.valueOf(inv.getQuantity()));

            totalInvested = totalInvested.add(total);

            totalByType.merge(inv.getType(), total, BigDecimal::add);
        }

        SummaryDTO summary = new SummaryDTO();
        summary.setTotalInvested(totalInvested);
        summary.setTotalByType(totalByType);
        summary.setAssetCount((long) investments.size());

        return summary;
    }

    private void validate(InvestmentRequestDTO dto) {

        if (dto.getType() == null) {
            throw new IllegalArgumentException("Tipo do investimento é obrigatório");
        }

        if (dto.getSymbol() == null || dto.getSymbol().isBlank()) {
            throw new IllegalArgumentException("Símbolo do ativo é obrigatório");
        }

        if (dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (dto.getPurchasePrice() == null ||
                dto.getPurchasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        if (dto.getPurchaseDate() == null) {
            throw new IllegalArgumentException("Data de compra é obrigatória");
        }
    }

    private Investment mapToEntity(InvestmentRequestDTO dto) {

        Investment investment = new Investment();
        investment.setType(dto.getType());
        investment.setSymbol(dto.getSymbol());
        investment.setQuantity(dto.getQuantity());
        investment.setPurchasePrice(dto.getPurchasePrice());
        investment.setPurchaseDate(dto.getPurchaseDate());

        return investment;
    }

    private InvestmentResponseDTO mapToResponse(Investment investment) {

        InvestmentResponseDTO dto = new InvestmentResponseDTO();

        dto.setId(investment.getId());
        dto.setType(investment.getType());
        dto.setSymbol(investment.getSymbol());
        dto.setQuantity(investment.getQuantity());
        dto.setPurchasePrice(investment.getPurchasePrice());
        dto.setPurchaseDate(investment.getPurchaseDate());

        BigDecimal total = investment.getPurchasePrice()
                .multiply(BigDecimal.valueOf(investment.getQuantity()));

        dto.setTotalInvested(total);

        return dto;
    }
}