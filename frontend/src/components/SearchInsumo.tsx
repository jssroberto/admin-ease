import React, { useState, useEffect, useRef } from "react";
import { ScanBarcode } from "lucide-react";
import axios from "axios";
import { Insumo } from "@/types/types";

interface SearchInsumoProps {
  onSelectInsumo: (insumo: Insumo) => void;
}

const SearchInsumo: React.FC<SearchInsumoProps> = ({ onSelectInsumo }) => {

  const [searchTerm, setSearchTerm] = useState<string>("");
  const [results, setResults] = useState<Insumo[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [showResults, setShowResults] = useState<boolean>(false);
  const searchRef = useRef<HTMLDivElement>(null);


  // Busqueda de insumo en la API
  const searchInsumos = async (term: string) => {
    if (!term.trim()) {
      setResults([]);
      return;
    }

    setLoading(true);
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/insumo/search`, {
        params: { query: term }
      });

      console.log("API Response:", response);

      if (Array.isArray(response.data)) {
        const formattedResults: Insumo[] = response.data.map(item => ({
          id: item.id.toString(),
          codigo: item.codigo || '', 
          nombre: item.nombre,
          cantidad: 1,
          categoria: item.categoria || '', 
          cantidadDisponible: item.stock,
          unidad: item.unidad
        }));
        setResults(formattedResults);
      } else {
        console.error("Unexpected response format:", response.data);
        setResults([]);
      }
    } catch (error) {
      //console.error("Error al buscar insumos:", error);
      setResults([]);
    } finally {
      setLoading(false);
    }
  };

  // Timeout en la busqueda
  useEffect(() => {
    const debounceTimer = setTimeout(() => {
      if (searchTerm) {
        searchInsumos(searchTerm);
      } else {
        setResults([]);
      }
    }, 300);

    return () => clearTimeout(debounceTimer);
  }, [searchTerm]);

  // Cerrar la busqueda cuando se haga click fuera del componente
  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (searchRef.current && !searchRef.current.contains(event.target as Node)) {
        setShowResults(false);
      }
    }

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  // Seleccionar un insumo de la lista
  const handleSelectInsumo = (insumo: Insumo) => {
    onSelectInsumo(insumo);
    setSearchTerm("");
    setShowResults(false);
  };

  // Funcionalidad de escaneo (NOT YET IMPLEMENTED)
  const handleScan = () => {
    alert("Funcionalidad de escaneo pendiente de implementar");
  };

  // interfaz grafica
  return (
    <div className="mb-6" ref={searchRef}>
      <label className="block text-sm font-normal text-gray-600 mb-1">
        Escanea o busca el insumo
      </label>
      <div className="flex gap-2 mb-2">
        <button 
          onClick={handleScan}
          className="flex items-center justify-center bg-[#4B6ABB] hover:bg-blue-800 text-white px-4 py-2 rounded-md cursor-pointer transition duration-200 ease-in-out"
        >
          <ScanBarcode size={20} className="mr-2" />
          Escanea el insumo
        </button>
      </div>
      <div className="relative">
        <input
          type="text"
          placeholder="Buscar insumo..."
          value={searchTerm}
          onChange={(e) => {
            setSearchTerm(e.target.value);
            setShowResults(true);
          }}
          onFocus={() => setShowResults(true)}
          className="block w-full bg-white border border-gray-300 rounded-md py-2 px-3 text-base focus:outline-none focus:ring-blue-500 focus:border-blue-500"
        />
        
        {showResults && (
          <div className="absolute z-10 w-full mt-1 bg-white shadow-lg rounded-md max-h-60 overflow-auto border border-gray-200">
            {loading && <div className="p-4 text-center text-gray-500">Cargando...</div>}
            
            {!loading && results.length === 0 && searchTerm && (
              <div className="p-4 text-center text-gray-500">No se encontraron insumos</div>
            )}
            
            {!loading && results.length > 0 && (
              <ul className="divide-y divide-gray-200">
                {results.map((insumo) => (
                  <li 
                    key={insumo.id}
                    className="p-3 hover:bg-gray-100 cursor-pointer"
                    onClick={() => handleSelectInsumo(insumo)}
                  >
                    <div className="flex justify-between">
                      <span className="font-medium">{insumo.nombre}</span>
                      <span className="text-sm text-gray-500">
                        {insumo.stock} {insumo.unidad}
                      </span>
                    </div>
                  </li>
                ))}
              </ul>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default SearchInsumo;