import "./index.css";
import React, { useState } from "react";
import { Routes, Route, Navigate, useLocation } from "react-router-dom";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Inicio from "./pages/Inicio";
import GestionarInsumos from "./pages/GestionarInsumo/GestionarInsumos";
import SalidasInsumos from "./pages/SalidaInsumos";
import CompraInsumos from "./pages/CompraInsumos";
import ReporteInventario from "./pages/ReporteInventario/ReporteInventario";
import ReporteCostos from "./pages/ReporteCostos";
import CrearInsumo from "./pages/GestionarInsumo/CrearInsumo";
import EditarInsumo from "./pages/GestionarInsumo/EditarInsumo";
import InventarioInsumos from "./pages/ReporteInventario/InventarioInsumos";
import HistorialMovimientos from "./pages/ReporteInventario/HistorialMovimientos";
import LoginPage from "./pages/LoginPage";

function RequireAuth({ isAuthenticated, children }: { isAuthenticated: boolean; children: React.ReactNode }) {
  const location = useLocation();
  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }
  return <>{children}</>;
}

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  return (
    <Routes>
      <Route
        path="/login"
        element={<LoginPage onLogin={() => setIsAuthenticated(true)} />}
      />
      <Route
        path="/*"
        element={
          <RequireAuth isAuthenticated={isAuthenticated}>
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
                    <Route path="/gestionar-insumos/crear" element={<CrearInsumo />} />
                    <Route path="/gestionar-insumos/editar/:id" element={<EditarInsumo />} />
                    <Route path="/reporte-inventario/insumos" element={<InventarioInsumos />} />
                    <Route path="/reporte-inventario/movimientos" element={<HistorialMovimientos />} />
                  </Routes>
                </main>
              </div>
            </div>
          </RequireAuth>
        }
      />
    </Routes>
  );
}

export default App;
