import SearchBar from "@/components/ui/Searchbar";
import { Insumo } from "@/types/types";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import InsumoItem from "./InsumoItem";

const GestionarInsumos: React.FC = () => {
  const [insumos, setInsumos] = useState<Insumo[]>([]);
  const [filteredInsumos, setFilteredInsumos] = useState<Insumo[]>(insumos);
  const [searchTerm, setSearchTerm] = useState("");

  const fetchInsumos = async () => {
    axios
      .get<Insumo[]>("http://localhost:8080/api/v1/insumo")
      .then((response) => {
        setInsumos(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("Error cargando insumos", error);
      });
  };

  const handleDeleteSuccess = () => {
    console.log("onSuccessDelete triggered!!!");
    fetchInsumos();
  };

  // cargar insumos
  useEffect(() => {
    fetchInsumos();
  }, []);

  // filtrar por busqueda
  useEffect(() => {
    const results = insumos.filter((insumo) =>
      insumo.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredInsumos(results);
  }, [insumos, searchTerm]);

  return (
    <div className="flex flex-col">
      <h1 className="text-2xl font-medium mb-6">Gestionar insumos</h1>
      <h2 className="text-gray-800 text-md mb-4">Buscar insumo</h2>

      <div className="flex items-center mb-4">
        <div className="w-1/2">
          <SearchBar
            placeholder="Buscar insumo..."
            value={searchTerm}
            onChange={setSearchTerm}
          />
        </div>
        <Link
          to="/gestionar-insumos/crear"
          className="ml-4 px-4 py-2 bg-[#213977] text-white rounded hover:bg-[#213977] cursor-pointer"
        >
          {" "}
          {/* Changed a to Link and href to to */}
          Nuevo insumo
        </Link>
      </div>

      <div>
        {filteredInsumos.length > 0 ? (
          filteredInsumos.map((insumo) => (
            <InsumoItem
              key={insumo.id}
              id={insumo.id}
              codigo={insumo.codigo}
              nombre={insumo.nombre}
              stock={insumo.stock}
              unidad={insumo.unidad}
              onDeleteSuccess={handleDeleteSuccess}
            />
          ))
        ) : (
          <div className="text-center py-8 text-gray-500">
            {searchTerm
              ? "No se encontraron insumos que coincidan con la búsqueda."
              : "No hay insumos disponibles."}
          </div>
        )}
      </div>
    </div>
  );
};

export default GestionarInsumos;
