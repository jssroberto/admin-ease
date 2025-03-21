import "./index.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Inicio from "./pages/Inicio";
import GestionarInsumos from "./pages/GestionarInsumos";
import SalidasInsumos from "./pages/SalidaInsumos";
import CompraInsumos from "./pages/CompraInsumos";
import ReporteInventario from "./pages/ReporteInventario";
import ReporteCostos from "./pages/ReporteCostos";

function App() {
  return (
    <div className="flex flex-col h-screen">
      <Navbar />
      <div className="flex flex-1 items-start">
        <Sidebar />
        <main className="flex-1 p-4 bg-white">
          <Routes>
            <Route path="/" element={<Inicio />} />
            <Route path="/gestionar-insumos" element={<GestionarInsumos />} />
            <Route path="/salidas-insumos" element={<SalidasInsumos />} />
            <Route path="/compras-insumos" element={<CompraInsumos />} />
            <Route path="/reporte-inventario" element={<ReporteInventario />} />
            <Route path="/reporte-costos" element={<ReporteCostos />} />
          </Routes>
        </main>
      </div>
    </div>
  );
}

export default App;
