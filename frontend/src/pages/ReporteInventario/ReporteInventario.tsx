import React from 'react';
import { PackageSearch, History } from 'lucide-react';
import { Link } from 'react-router-dom';

const ReporteInventarioMenu: React.FC = () => {
  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-bold text-gray-800 mb-8">Reportes de Inventario</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <Link 
          to="/reporte-inventario/insumos" 
          className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow flex flex-col items-center text-center border border-gray-200 hover:border-blue-500"
        >
          <PackageSearch className="w-12 h-12 text-black mb-4" />
          <h2 className="text-xl font-semibold text-gray-700 mb-2">Inventario de Insumos</h2>
          <p className="text-gray-500">Visualiza el stock actual de insumos y su estado</p>
        </Link>
        <Link 
          to="/reporte-inventario/movimientos" 
          className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow flex flex-col items-center text-center border border-gray-200 hover:border-green-500"
        >
          <History className="w-12 h-12 text-black mb-4" />
          <h2 className="text-xl font-semibold text-gray-700 mb-2">Historial de Movimientos</h2>
          <p className="text-gray-500">Registro de todas las salidas y compras de insumos</p>
        </Link>
      </div>
    </div>
  );
};

export default ReporteInventarioMenu;