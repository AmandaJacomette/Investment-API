import { useEffect, useState } from "react";
import { api } from "../api/client";
import Loader from "../components/ui/Loader.tsx";
import Toast from "../components/ui/Toast.tsx";
import InvestmentTable from "../components/investments/InvestmentTable";
import InvestmentForm from "../components/investments/InvestmentForm";
import SummaryCards from "../components/investments/SummaryCards";
import "./Dashboard.css";

export default function Dashboard() {
  const [investments, setInvestments] = useState([]);
  const [summary, setSummary] = useState({ totalInvested: 0, assetCount: 0, totalByType: {} });
  const [loading, setLoading] = useState(true);
  const [toast, setToast] = useState({ message: "", type: "" });

  const loadData = async () => {
    try {
      setLoading(true);
      const res = await api.get("/investments");
      const sum = await api.get("/investments/summary");
      setInvestments(res.data);
      setSummary(sum.data);
    } catch {
      setToast({ message: "Erro ao carregar dados", type: "error" });
    } finally {
      setLoading(false);
    }
  };

  const deleteInvestment = async (id: any) => {
    try {
      await api.delete(`/investments/${id}`);
      setToast({ message: "Investimento removido", type: "success" });
      loadData();
    } catch {
      setToast({ message: "Erro ao remover", type: "error" });
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  if (loading) return <Loader />;

  return (
    <div className="dashboard-container">
      <Toast message={toast.message} type={toast.type} />
      <div className="dashboard-header">
        <h1>InvestPro</h1>
      </div>
      <SummaryCards summary={summary} />
      <div className="dashboard-content">
        <InvestmentForm refresh={loadData} />
        <InvestmentTable investments={investments} onDelete={deleteInvestment} />
      </div>
    </div>
  );
}