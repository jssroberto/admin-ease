import React, { useEffect, useState } from "react";
import { Search, Calendar } from "lucide-react";

interface Salida {
  id: number;
  fecha: string;
  areaId?: number;
  area?: {
    id: number;
    nombre: string;
  };
  usuarioId?: number;
  usuario?: {
    id: number;
    nombre: string;
  };
  salidaInsumos?: {
    id: number;
    cantidad: number;
    insumo?: {
      id: number;
      nombre: string;
      codigo: string;
    };
  }[];
}

interface Compra {
  id: number;
  fecha: string;
  total: number;
  proveedorNombre: {
    id: number;
    nombre: string;
    telefono: string;
    correo: string;
  };
  usuarioNombre: {
    id: number;
    nombre: string;
    rolDTO: any;
  };
  compraInsumos: {
    id: number;
    precioUnitario: number;
    cantidad: number;
    insumo: {
      id: number;
      codigo: string;
      nombre: string;
      stock: number;
    };
  }[];
}

const HistorialMovimientos: React.FC = () => {
  // State for salidas
  const [salidas, setSalidas] = useState<Salida[]>([]);
  const [salidasLoading, setSalidasLoading] = useState(true);
  const [salidasFilter, setSalidasFilter] = useState({
    fechaDesde: "",
    fechaHasta: "",
    search: "",
    areaId: "",
  });

  // State for compras
  const [compras, setCompras] = useState<Compra[]>([]);
  const [comprasLoading, setComprasLoading] = useState(true);
  const [comprasFilter, setComprasFilter] = useState({
    fechaDesde: "",
    fechaHasta: "",
    search: "",
    proveedorId: "",
  });

  // State for proveedores
  const [proveedores, setProveedores] = useState<{ id: number; nombre: string }[]>([]);

  const fetchSalidas = async () => {
    try {
      setSalidasLoading(true);
      // No params, endpoint es /api/v1/salida
      const response = await fetch(
        `http://localhost:8080/api/v1/salida`
      );
      const data = await response.json();
      setSalidas(data);
    } catch (error) {
      console.error("Error fetching salidas:", error);
    } finally {
      setSalidasLoading(false);
    }
  };

  const fetchCompras = async () => {
    try {
      setComprasLoading(true);
      const params = new URLSearchParams();
      if (comprasFilter.fechaDesde)
        params.append("fechaDesde", comprasFilter.fechaDesde);
      if (comprasFilter.fechaHasta)
        params.append("fechaHasta", comprasFilter.fechaHasta);
      if (comprasFilter.search) params.append("search", comprasFilter.search);
      if (comprasFilter.proveedorId)
        params.append("proveedorId", comprasFilter.proveedorId);

      const response = await fetch(
        `http://localhost:8080/api/v1/compra?${params.toString()}`
      );
      const data = await response.json();
      setCompras(data);
    } catch (error) {
      console.error("Error fetching compras:", error);
    } finally {
      setComprasLoading(false);
    }
  };

  // Fetch proveedores
  const fetchProveedores = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/proveedor");
      const data = await response.json();
      setProveedores(data);
    } catch (error) {
      console.error("Error fetching proveedores:", error);
    }
  };

  useEffect(() => {
    fetchSalidas();
    fetchCompras();
    fetchProveedores();
  }, [salidasFilter, comprasFilter]);

  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString("es-ES", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
  };

  // Filter compras by insumo name (case-insensitive), proveedorId, and date range if present
  const filteredCompras = compras.filter((compra) => {
    // Filter by search (insumo name)
    let matchesSearch = true;
    if (comprasFilter.search) {
      const searchLower = comprasFilter.search.toLowerCase();
      matchesSearch = compra.compraInsumos.some((ci) =>
        ci.insumo.nombre.toLowerCase().includes(searchLower)
      );
    }

    // Filter by proveedorId
    let matchesProveedor = true;
    if (comprasFilter.proveedorId) {
      matchesProveedor = String(compra.proveedorNombre.id) === comprasFilter.proveedorId;
    }

    // Filter by fechaDesde and fechaHasta
    let matchesFecha = true;
    if (comprasFilter.fechaDesde) {
      matchesFecha =
        matchesFecha &&
        new Date(compra.fecha) >= new Date(comprasFilter.fechaDesde + "T00:00:00");
    }
    if (comprasFilter.fechaHasta) {
      matchesFecha =
        matchesFecha &&
        new Date(compra.fecha) <= new Date(comprasFilter.fechaHasta + "T23:59:59");
    }

    return matchesSearch && matchesProveedor && matchesFecha;
  });

  // Filtrar salidas por fechas
  const filteredSalidas = salidas.filter((salida) => {
    let matchesFecha = true;
    if (salidasFilter.fechaDesde) {
      matchesFecha = matchesFecha &&
        new Date(salida.fecha) >= new Date(salidasFilter.fechaDesde + 'T00:00:00');
    }
    if (salidasFilter.fechaHasta) {
      matchesFecha = matchesFecha &&
        new Date(salida.fecha) <= new Date(salidasFilter.fechaHasta + 'T23:59:59');
    }
    return matchesFecha;
  });

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-bold text-gray-800 mb-6">
        Historial de Movimientos
      </h1>

      {/* Compras Section */}
      <div className="bg-white p-4 rounded-lg shadow">
        <h2 className="text-xl font-semibold text-gray-700 mb-4">
          Compras de Insumos
        </h2>

        {/* Compras Filters */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-4">
          <div className="relative">
            <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="date"
              className="pl-10 pr-4 py-2 border rounded w-full"
              placeholder="Desde"
              value={comprasFilter.fechaDesde}
              onChange={(e) =>
                setComprasFilter({
                  ...comprasFilter,
                  fechaDesde: e.target.value,
                })
              }
            />
          </div>
          <div className="relative">
            <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="date"
              className="pl-10 pr-4 py-2 border rounded w-full"
              placeholder="Hasta"
              value={comprasFilter.fechaHasta}
              onChange={(e) =>
                setComprasFilter({
                  ...comprasFilter,
                  fechaHasta: e.target.value,
                })
              }
            />
          </div>
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="text"
              placeholder="Buscar insumo o proveedor..."
              className="pl-10 pr-4 py-2 border rounded w-full"
              value={comprasFilter.search}
              onChange={(e) =>
                setComprasFilter({ ...comprasFilter, search: e.target.value })
              }
            />
          </div>
          <select
            className="p-2 border rounded w-full"
            value={comprasFilter.proveedorId}
            onChange={(e) =>
              setComprasFilter({
                ...comprasFilter,
                proveedorId: e.target.value,
              })
            }
          >
            <option value="">Todos los proveedores</option>
            {proveedores.map((proveedor) => (
              <option key={proveedor.id} value={proveedor.id}>
                {proveedor.nombre}
              </option>
            ))}
          </select>
        </div>

        {/* Compras Table */}
        {comprasLoading ? (
          <div className="text-center py-8">Cargando compras...</div>
        ) : (
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Fecha
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Proveedor
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Usuario
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Insumos
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Total
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {filteredCompras.map((compra) => (
                  <tr key={`compra-${compra.id}`}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {formatDate(compra.fecha)}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {compra.proveedorNombre.nombre}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {compra.usuarioNombre?.nombre}
                    </td>
                    <td className="px-6 py-4 text-sm text-gray-500">
                      {compra.compraInsumos.map((ci) => (
                        <div key={ci.id}>
                          {ci.insumo.nombre} ({ci.cantidad} x $
                          {ci.precioUnitario})
                        </div>
                      ))}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                      ${compra.total}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Salidas Section */}
      <div className="mb-12 bg-white p-4 rounded-lg shadow">
        <h2 className="text-xl font-semibold text-gray-700 mb-4">
          Salidas de Insumos
        </h2>

        {/* Salidas Filters */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-4">
          <div className="relative">
            <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="date"
              className="pl-10 pr-4 py-2 border rounded w-full"
              placeholder="Desde"
              value={salidasFilter.fechaDesde}
              onChange={(e) =>
                setSalidasFilter({
                  ...salidasFilter,
                  fechaDesde: e.target.value,
                })
              }
            />
          </div>
          <div className="relative">
            <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="date"
              className="pl-10 pr-4 py-2 border rounded w-full"
              placeholder="Hasta"
              value={salidasFilter.fechaHasta}
              onChange={(e) =>
                setSalidasFilter({
                  ...salidasFilter,
                  fechaHasta: e.target.value,
                })
              }
            />
          </div>
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-gray-400" />
            <input
              type="text"
              placeholder="Buscar insumo o usuario..."
              className="pl-10 pr-4 py-2 border rounded w-full"
              value={salidasFilter.search}
              onChange={(e) =>
                setSalidasFilter({ ...salidasFilter, search: e.target.value })
              }
            />
          </div>
          <select
            className="p-2 border rounded w-full"
            value={salidasFilter.areaId}
            onChange={(e) =>
              setSalidasFilter({ ...salidasFilter, areaId: e.target.value })
            }
          >
            <option value="">Todas las áreas</option>
            {/* Populate with areas from your API */}
          </select>
        </div>

        {/* Salidas Table */}
        {salidasLoading ? (
          <div className="text-center py-8">Cargando salidas...</div>
        ) : (
          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Fecha
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Área ID
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Usuario ID
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Cantidades de Insumos
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {filteredSalidas.map((salida) => (
                  <tr key={`salida-${salida.id}`}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {formatDate(salida.fecha)}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {salida.area?.nombre
                        ? salida.area.nombre
                        : salida.areaId === 1
                        ? 'Cocina'
                        : salida.areaId === 2
                        ? 'Bar'
                        : salida.areaId === 3
                        ? 'Almacén'
                        : salida.areaId}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {salida.usuarioId === 1
                        ? 'admin'
                        : salida.usuarioId === 2
                        ? 'cajero1'
                        : salida.usuarioId === 3
                        ? 'chef1'
                        : salida.usuarioId}
                    </td>
                    <td className="px-6 py-4 text-sm text-gray-500">
                      {salida.salidaInsumos?.length
                        ? salida.salidaInsumos.map((si) => (
                            <div key={si.id}>
                              Cantidad: {si.cantidad}
                            </div>
                          ))
                        : "No hay insumos"}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default HistorialMovimientos;
