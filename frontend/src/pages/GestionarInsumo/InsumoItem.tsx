import axios from "axios";
import { Pen, Trash } from "lucide-react";
import React, { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";

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
  onRemove?: (id: string) => void;
  onDeleteSuccess?: () => void;
}

const InsumoItem: React.FC<InsumoItemProps> = ({
  id,
  codigo,
  nombre,
  stock,
  unidad,
  categoria,
  onRemove,
  onDeleteSuccess,
}) => {
  const navigate = useNavigate();
  const [isDeleting, setItDeleting] = useState(false);
  const [isUpdating, setIsUpdating] = useState(false);

  const handleDelete = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    if (!window.confirm(`Estas seguro de eliminar el insumo ${nombre}`)) {
      return;
    }
    setItDeleting(true);
    try {
      await axios.delete(`http://localhost:8080/api/v1/insumo/${id}`);
      onRemove?.(id);
      onDeleteSuccess?.();
    } catch (error) {
      console.log("Error deleting insumo", error);
    } finally {
      setItDeleting(false);
    }
  };

  const handleEdit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    navigate(`/gestionar-insumos/editar/${id}`);
  }

  return (
    <div className="flex justify-between items-center p-4 border-b border-gray-200 hover:bg-gray-50 transition-colors">
      <div className="flex-1">
        <div className="flex items-baseline gap-2">
          <h3 className="text-lg font-medium text-gray-800">{nombre}</h3>
          <span className="text-sm text-gray-500 font-mono">{codigo}</span>
        </div>

        <div className="flex gap-3 mt-1 text-sm text-gray-600">
          <span>
            <strong>Stock:</strong> {stock} {unidad && `(${unidad})`}
          </span>
          {categoria && (
            <span>
              <strong>Categoría:</strong> {categoria.nombre}
            </span>
          )}
        </div>
      </div>

      <div className="flex gap-2">
      <button
          onClick={handleEdit}
          disabled={isUpdating}
          className={`p-2 flex justify-between align-middle ${
            isUpdating
              ? "bg-gray-100 text-gray-400"
              : "bg-blue-100 text-blue-600 hover:bg-blue-200"
          } rounded transition-colors text-sm cursor-pointer`}
        >
          <Pen size={16} />
        </button>
        <button
          onClick={handleDelete}
          disabled={isDeleting}
          className={`p-2 flex justify-between align-middle ${
            isDeleting
              ? "bg-gray-100 text-gray-400"
              : "bg-red-100 text-red-600 hover:bg-red-200"
          } rounded transition-colors text-sm cursor-pointer`}
        >
          <Trash size={16} />
        </button>
      </div>
    </div>
  );
};

export default InsumoItem;
