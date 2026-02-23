import { useState } from "react";
import { api } from "../../api/client.ts";
import "./InvestmentForm.css";

interface Props {
  refresh: () => void;
}

export default function InvestmentForm({ refresh }: Props) {
  const [form, setForm] = useState({
    type: "ACAO",
    symbol: "",
    quantity: 0,
    purchasePrice: 0.00,
    purchaseDate: ""
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await api.post("/investments", form);
    refresh();
  };

  return (
    <div className="form-card">
      <h3>Novo Investimento</h3>
      <form onSubmit={handleSubmit}>
        <input placeholder="Símbolo"
          onChange={e => setForm({...form, symbol: e.target.value})} />
        <select
          value={form.type}
          onChange={e => setForm({...form, type: e.target.value})}>
            <option value="ACAO">Ação</option>
            <option value="CRIPTO">Cripto</option>
            <option value="FUNDO">Fundo</option>
            <option value="RENDA_FIXA">Renda Fixa</option>
            <option value="OUTRO">Outro</option>
        </select>
        <input type="number" placeholder="Quantidade"
          onChange={e => setForm({...form, quantity: Number(e.target.value)})} />
        <input type="number" step="0.01" placeholder="Preço"
          onChange={e => setForm({...form, purchasePrice: Number(e.target.value)})} />
        <input type="date"
          onChange={e => setForm({...form, purchaseDate: e.target.value})} />
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
}