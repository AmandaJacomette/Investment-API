import "./Toast.css";

export default function Toast({ message, type }: { message: string; type: string }) {
  if (!message) return null;

  return (
    <div className={`toast ${type}`}>
      {message}
    </div>
  );
}