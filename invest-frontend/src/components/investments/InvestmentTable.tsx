import type { Investment } from "../../types/Investment";
import "./InvestmentTable.css";

interface Props {
  investments: Investment[];
  onDelete: (id: number) => void;
}

export default function InvestmentTable({ investments, onDelete }: Props) {
  return (
    <div className="table-card">
      <h3>Meus Investimentos</h3>
      <table>
        <thead>
          <tr>
            <th>Ativo</th>
            <th>Tipo</th>
            <th>Qtd</th>
            <th>Preço</th>
            <th>Total</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {investments.map(inv => (
            <tr key={inv.id}>
              <td>{inv.symbol}</td>
              <td>{inv.type}</td>
              <td>{inv.quantity}</td>
              <td>R$ {inv.purchasePrice}</td>
              <td>R$ {inv.totalInvested}</td>
              <td>
                <button onClick={() => onDelete(inv.id)}>Excluir</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}