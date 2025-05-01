import { Pen, Trash } from 'lucide-react';
import React from 'react';

interface InsumoItemProps {
  id: string;
  codigo: string;
  nombre: string;
  stock?: number;  
  unidad?: string;
  categoria?: {  
    id: string;
    nombre: string;
  };
  onEdit?: () => void;
  onRemove?: () => void;
}

const InsumoItem: React.FC<InsumoItemProps> = ({
  codigo,
  nombre,
  stock,
  unidad,
  categoria,
  onEdit,
  onRemove,
}) => {
  return (
    <div className="flex justify-between items-center p-4 border-b border-gray-200 hover:bg-gray-50 transition-colors">
      <div className="flex-1">
        <div className="flex items-baseline gap-2">
          <h3 className="text-lg font-medium text-gray-800">{nombre}</h3>
          <span className="text-sm text-gray-500 font-mono">{codigo}</span>
        </div>
        
        <div className="flex gap-3 mt-1 text-sm text-gray-600">
          <span><strong>Stock:</strong> {stock} {unidad && `(${unidad})`}</span>
          {categoria && <span><strong>Categoría:</strong> {categoria.nombre}</span>}
        </div>
      </div>

      <div className="flex gap-2">
        <button 
          onClick={(e) => {
            e.stopPropagation();
            onEdit?.();
          }}
          className="p-2 flex justify-between align-middle bg-blue-100 text-blue-600 rounded hover:bg-blue-200 transition-colors text-sm cursor-pointer"
        >
          <Pen
            size={16}
          />
        </button>
        <button 
          onClick={(e) => {
            e.stopPropagation();
            onRemove?.();
          }}
          className="p-2 flex justify-between align-middle bg-red-100 text-red-600 rounded hover:bg-red-200 transition-colors text-sm cursor-pointer"
        >
          <Trash
            size={16}
          />
        </button>
      </div>
    </div>
  );
};

export default InsumoItem;