import axios from "axios";
import React, { useEffect, useState } from "react";

interface UnidadMedida {
  id: number;
  nombre: string;
  abreviatura: string;
}

interface CategoriaInsumo {
  id: number;
  nombre: string;
}

interface InsumoFromAPI {
  id: number;
  codigo: string;
  nombre: string;
  stock: number;
  unidadMedidaId: number;
  categoriaInsumoId: number;
}

interface Insumo extends InsumoFromAPI {
  estado: "Disponible" | "Agotado" | "Bajo Stock";
  unidadMedida?: UnidadMedida;
  categoriaInsumo?: CategoriaInsumo;
}

const ReporteInventario: React.FC = () => {
  const [insumosFetched, setInsumosFetched] = useState<InsumoFromAPI[]>([]);
  const [unidadesMedida, setUnidadesMedida] = useState<any[]>([]);
  const [categoriasInsumo, setCategoriasInsumo] = useState<any[]>([]);

  // filters states
  const [searchTerm, setSearchTerm] = React.useState("");
  const [categoryFilter, setCategoryFilter] = React.useState<string>("all");
  const [stockFilter, setStockFilter] = React.useState<string>("all");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [insumoRes, uniRes, catRes] = await Promise.all([
          axios.get("http://localhost:8080/api/v1/insumo"),
          axios.get("http://localhost:8080/api/v1/unidadMedida"),
          axios.get("http://localhost:8080/api/v1/categoriaInsumo"),
        ]);
        setInsumosFetched(insumoRes.data);
        setUnidadesMedida(uniRes.data);
        setCategoriasInsumo(catRes.data);
      } catch (err) {
        console.log("Error cargando data");
      }
    };
    fetchData();
  }, []);

  const getEstado = (
    stock: number
  ): "Disponible" | "Agotado" | "Bajo Stock" => {
    if (stock === 0) return "Agotado";
    if (stock <= 10) return "Bajo Stock";
    return "Disponible";
  };

  const insumos: Insumo[] = insumosFetched.map((item) => ({
    ...item,
    estado: getEstado(item.stock),
    unidadMedida: unidadesMedida.find((um) => um.id === item.unidadMedidaId),
    categoriaInsumo: categoriasInsumo.find(
      (ci) => ci.id === item.categoriaInsumoId
    ),
  }));

  const getRowClass = (estado: string) => {
    switch (estado) {
      case "Bajo Stock":
        return "bg-yellow-50";
      case "Agotado":
        return "bg-red-50";
      default:
        return "bg-white";
    }
  };

  const filteredInsumos = insumos.filter((insumo) => {
    // Search filter
    const matchesSearch =
      insumo.nombre.toLowerCase().includes(searchTerm.toLowerCase()) ||
      insumo.codigo.toLowerCase().includes(searchTerm.toLowerCase());

    // Category filter
    const matchesCategory =
      categoryFilter === "all" ||
      categoryFilter === "all" ||
      categoriasInsumo.find((ci) => ci.id === insumo.categoriaInsumoId)
        ?.nombre === categoryFilter;

    // Stock filter
    const matchesStock =
      stockFilter === "all" ||
      (stockFilter === "low" && getEstado(insumo.stock) === "Bajo Stock") ||
      (stockFilter === "out" && getEstado(insumo.stock) === "Agotado") ||
      (stockFilter === "high" && getEstado(insumo.stock) === "Disponible");

    return matchesSearch && matchesCategory && matchesStock;
  });

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-bold text-gray-800 mb-6">
        Reportes de Inventario
      </h1>

        <div className="mb-6 bg-white w-2/3">
            <div className="flex flex-col md:flex-row gap-4">
              <div className="flex-1">
                <input
                  type="text"
                  placeholder="Buscar por nombre o código..."
                  className="w-full p-2 border rounded"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
              </div>

              <select
                className="p-2 border rounded"
                value={categoryFilter}
                onChange={(e) => setCategoryFilter(e.target.value)}
              >
                <option value="all">Todas las categorías</option>
                {categoriasInsumo.map((cat) => (
                  <option key={cat.id} value={cat.nombre}>
                    {cat.nombre}
                  </option>
                ))}
              </select>

              <select
                className="p-2 border rounded"
                value={stockFilter}
                onChange={(e) => setStockFilter(e.target.value)}
              >
                <option value="all">Todos los estados</option>
                <option value="high">Stock disponible</option>
                <option value="low">Bajo stock</option>
                <option value="out">Agotado</option>
              </select>
            </div>
          </div>

      <div className="mb-12">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-semibold text-blue-900">
            Inventario de Insumos
          </h2>

          <div className="flex space-x-2">
            <div className="flex items-center">
              <div className="w-3 h-3 bg-green-100 border border-green-300 rounded-full mr-1"></div>
              <span className="text-xs text-gray-600">Disponible</span>
            </div>
            <div className="flex items-center">
              <div className="w-3 h-3 bg-yellow-100 border border-yellow-300 rounded-full mr-1"></div>
              <span className="text-xs text-gray-600">Bajo Stock</span>
            </div>
            <div className="flex items-center">
              <div className="w-3 h-3 bg-red-100 border border-red-300 rounded-full mr-1"></div>
              <span className="text-xs text-gray-600">Agotado</span>
            </div>
          </div>
        </div>

        <div className="bg-white shadow-md rounded-lg overflow-hidden">
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Código
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Nombre
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Categoría
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Stock
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Unidad
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Estado
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {filteredInsumos.map((insumo) => (
                  <tr key={insumo.id} className={getRowClass(insumo.estado)}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {insumo.codigo}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                      {insumo.nombre}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {insumo.categoriaInsumo?.nombre || "N/A"}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {insumo.stock}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {insumo.unidadMedida?.nombre || "N/A"}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap">
                      <span
                        className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full 
                        ${
                          insumo.estado === "Disponible"
                            ? "bg-green-100 text-green-800"
                            : ""
                        }
                        ${
                          insumo.estado === "Bajo Stock"
                            ? "bg-yellow-100 text-yellow-800"
                            : ""
                        }
                        ${
                          insumo.estado === "Agotado"
                            ? "bg-red-100 text-red-800"
                            : ""
                        }`}
                      >
                        {insumo.estado}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ReporteInventario;
