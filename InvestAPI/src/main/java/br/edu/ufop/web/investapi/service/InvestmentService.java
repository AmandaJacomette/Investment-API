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
        Investment investment = mapToEntity(dto);
        return mapToResponse(repository.save(investment));
    }

    public List<InvestmentResponseDTO> findAll(InvestmentType type) {
        List<Investment> investments =
                type == null ? repository.findAll() : repository.findByType(type);

        return investments.stream().map(this::mapToResponse).toList();
    }

    public InvestmentResponseDTO update(Long id, InvestmentRequestDTO dto) {
        Investment investment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ativo não encontrado"));

        investment.setType(dto.getType());
        investment.setSymbol(dto.getSymbol());
        investment.setQuantity(dto.getQuantity());
        investment.setPurchasePrice(dto.getPurchasePrice());
        investment.setPurchaseDate(dto.getPurchaseDate());

        return mapToResponse(repository.save(investment));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ativo não encontrado");
        }
        repository.deleteById(id);
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
