package br.edu.ufop.web.investapi.repository;

import br.edu.ufop.web.investapi.model.Investment;
import br.edu.ufop.web.investapi.model.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findByType(InvestmentType type);
}
