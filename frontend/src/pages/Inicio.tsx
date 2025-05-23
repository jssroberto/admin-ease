import {
  BarChart3,
  Box,
  FileText,
  History,
  Package,
  ShoppingCart,
} from "lucide-react";
import React from "react";
import { Link } from "react-router-dom";

const Inicio: React.FC = () => {
  const menuItems = [
    {
      title: "Gestionar Insumos",
      description: "Administra el inventario de insumos",
      icon: <Package className="w-12 h-12" />,
      path: "/gestionar-insumos",
      color: "from-blue-500 to-blue-500",
    },
    {
      title: "Compras",
      description: "Registra nuevas compras de insumos",
      icon: <ShoppingCart className="w-12 h-12" />,
      path: "/compras-insumos",
      color: "from-blue-400 to-blue-400",
    },
    {
      title: "Salidas",
      description: "Gestiona las salidas de insumos",
      icon: <Box className="w-12 h-12" />,
      path: "/salidas-insumos",
      color: "from-blue-300 to-blue-300",
    },
    {
      title: "Reporte de Costos",
      description: "Visualiza los costos y gastos",
      icon: <BarChart3 className="w-12 h-12" />,
      path: "/pages/reporte-costos",
      color: "from-blue-600 to-blue-600",
    },
    {
      title: "Inventario",
      description: "Control de stock y existencias",
      icon: <FileText className="w-12 h-12" />,
      path: "/reporte-inventario/insumos",
      color: "from-blue-500 to-blue-500",
    },
    {
      title: "Historial",
      description: "Visualiza los movimientos",
      icon: <History className="w-12 h-12" />,
      path: "reporte-inventario/movimientos",
      color: "from-blue-400 to-blue-400",
    },
  ];

  return (
    <div className="min-h-screen flex flex-col items-center">
      {/* Header Section con esquinas redondeadas arriba y abajo */}
      <header className="w-full bg-[#0153B6] py-6 text-center text-white rounded-t-3xl rounded-b-3xl">
        <div className="flex flex-col items-center max-w-3xl mx-auto px-2">
          <h1 className="text-5xl md:text-6xl font-bold tracking-tight text-white mb-3">
            ¡Bienvenido a AdminEase!
          </h1>
          <p className="text-3xl md:text-4xl text-blue-100 font-light max-w-3xl mx-auto mb-3 drop-shadow-sm leading-snug">
            Sistema integral para la gestión eficiente de insumos y recursos.
          </p>
          <p className="text-xl md:text-2xl text-blue-200 italic max-w-2xl mx-auto"></p>
        </div>
      </header>

      {/* Main Section */}
      <main className="flex-grow w-full max-w-7xl px-4 sm:px-6 lg:px-8 mt-10">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {menuItems.map((item, index) => (
            <Link
              key={index}
              to={item.path}
              className="group block bg-white rounded-lg shadow-md hover:shadow-xl hover:scale-101 transition-all duration-300 overflow-hidden"
            >
              <div
                className={`p-6 bg-gradient-to-r ${item.color} text-white flex items-center justify-center`}
              >
                {item.icon}
              </div>
              <div className="p-4">
                <h3 className="text-xl font-semibold text-gray-800 group-hover:text-blue-600 transition-colors duration-300">
                  {item.title}
                </h3>
                <p className="text-gray-600 mt-2">{item.description}</p>
              </div>
            </Link>
          ))}
        </div>
      </main>
    </div>
  );
};

export default Inicio;
