import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:3000",
  timeout: 5000,
});

api.interceptors.response.use(
  response => response,
  error => {
    if (error.response) {
      console.error("Erro da API:", error.response.data);
    } else {
      console.error("Erro de conexão com servidor");
    }
    return Promise.reject(error);
  }
);