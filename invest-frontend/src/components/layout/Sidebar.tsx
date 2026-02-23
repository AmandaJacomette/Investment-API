import "./Sidebar.css";

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <h2 className="logo">InvestPro</h2>
      <nav>
        <a className="active">Dashboard</a>
        <a>Investimentos</a>
      </nav>
    </aside>
  );
}