import type { Summary } from "../../types/Investment";
import "./SummaryCards.css";

interface Props {
  summary: Summary;
}

export default function SummaryCards({ summary }: Props) {
  return (
    <div className="summary-grid">
      <div className="card">
        <p>Total Investido</p>
        <h2>R$ {summary.totalInvested?.toFixed(2)}</h2>
      </div>
      <div className="card">
        <p>Total de Ativos</p>
        <h2>{summary.assetCount}</h2>
      </div>
    </div>
  );
}