package br.edu.ufop.web.investapi.dto;

import br.edu.ufop.web.investapi.model.InvestmentType;
import java.math.BigDecimal;
import java.util.Map;

public class SummaryDTO {

    private BigDecimal totalInvested;
    private Map<InvestmentType, BigDecimal> totalByType;
    private Long assetCount;

    public BigDecimal getTotalInvested() { return totalInvested; }
    public void setTotalInvested(BigDecimal totalInvested) { this.totalInvested = totalInvested; }

    public Map<InvestmentType, BigDecimal> getTotalByType() { return totalByType; }
    public void setTotalByType(Map<InvestmentType, BigDecimal> totalByType) { this.totalByType = totalByType; }

    public Long getAssetCount() { return assetCount; }
    public void setAssetCount(Long assetCount) { this.assetCount = assetCount; }
}