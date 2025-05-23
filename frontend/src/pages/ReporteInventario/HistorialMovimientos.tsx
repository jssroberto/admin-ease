import { Calendar, Search } from "lucide-react";
import React, { useCallback, useEffect, useState } from "react"; // Added useCallback

interface Salida {
  id: number;
  fecha: string;
  areaId?: number;
  areaNombre?: string;
  area?: {
    id: number;
    nombre: string;
  };
  usuarioId?: number;
  usuarioNombre?: string;
  usuario?: {
    id: number;
    nombre: string;
  };
  salidaInsumos?: {
    id: number;
    cantidad: number;
    insumoId?: number;
    insumoNombre?: string;
    insumo?: {
      id: number;
      nombre: string;
      codigo: string;
    };
  }[];
}

interface Area {
  id: number;
  nombre: string;
}

interface RolDTO {
  // Define a more specific type for RolDTO if possible
  id: number;
  nombre: string;
  // Add other properties of RolDTO if known
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
    rolDTO: RolDTO; // Use the defined RolDTO interface
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
  const [proveedores, setProveedores] = useState<
    { id: number; nombre: string }[]
  >([]);
  // State for areas
  const [areas, setAreas] = useState<Area[]>([]);

  const fetchSalidas = useCallback(async () => {
    try {
      setSalidasLoading(true);
      const params = new URLSearchParams();
      if (salidasFilter.fechaDesde)
        params.append("fechaDesde", salidasFilter.fechaDesde);
      if (salidasFilter.fechaHasta)
        params.append("fechaHasta", salidasFilter.fechaHasta);
      // Client-side filtering for search and areaId will be applied after fetching
      const response = await fetch(
        `http://localhost:8080/api/v1/salida?${params.toString()}`
      );
      const data = await response.json();
      setSalidas(data);
    } catch (error) {
      console.error("Error fetching salidas:", error);
    } finally {
      setSalidasLoading(false);
    }
  }, [salidasFilter.fechaDesde, salidasFilter.fechaHasta]); // Dependencies for useCallback

  const fetchCompras = useCallback(async () => {
    try {
      setComprasLoading(true);
      const params = new URLSearchParams();
      if (comprasFilter.fechaDesde)
        params.append("fechaDesde", comprasFilter.fechaDesde);
      if (comprasFilter.fechaHasta)
        params.append("fechaHasta", comprasFilter.fechaHasta);
      // Search and proveedorId will be filtered client-side

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
  }, [comprasFilter.fechaDesde, comprasFilter.fechaHasta]); // Dependencies for useCallback

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

  // Fetch areas
  const fetchAreas = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/area");
      const data = await response.json();
      setAreas(data);
    } catch (error) {
      console.error("Error fetching areas:", error);
    }
  };

  useEffect(() => {
    fetchProveedores();
    fetchAreas();
  }, []); // Fetch static data once on mount

  useEffect(() => {
    fetchSalidas();
  }, [fetchSalidas]); // Refetch salidas when fetchSalidas changes (due to date filters)

  useEffect(() => {
    fetchCompras();
  }, [fetchCompras]); // Refetch compras when fetchCompras changes (due to its filters)

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
    // Filter by search (insumo name or proveedor name)
    let matchesSearch = true;
    if (comprasFilter.search) {
      const searchLower = comprasFilter.search.toLowerCase();
      const searchInInsumos = compra.compraInsumos.some((ci) =>
        ci.insumo.nombre.toLowerCase().includes(searchLower)
      );
      const searchInProveedor = compra.proveedorNombre.nombre
        .toLowerCase()
        .includes(searchLower);
      matchesSearch = searchInInsumos || searchInProveedor;
    }

    // Filter by proveedorId
    let matchesProveedor = true;
    if (comprasFilter.proveedorId) {
      matchesProveedor =
        String(compra.proveedorNombre.id) === comprasFilter.proveedorId;
    }

    // Filter by fechaDesde and fechaHasta
    let matchesFecha = true;
    if (comprasFilter.fechaDesde) {
      matchesFecha =
        matchesFecha &&
        new Date(compra.fecha) >=
          new Date(comprasFilter.fechaDesde + "T00:00:00");
    }
    if (comprasFilter.fechaHasta) {
      matchesFecha =
        matchesFecha &&
        new Date(compra.fecha) <=
          new Date(comprasFilter.fechaHasta + "T23:59:59");
    }

    return matchesSearch && matchesProveedor && matchesFecha;
  });

  // Filter salidas by date, search (insumo or user), and areaId
  const filteredSalidas = salidas.filter((salida) => {
    let matchesFecha = true; // Date filtering is handled by API, but kept for consistency if API changes
    if (salidasFilter.fechaDesde) {
      matchesFecha =
        matchesFecha &&
        new Date(salida.fecha) >=
          new Date(salidasFilter.fechaDesde + "T00:00:00");
    }
    if (salidasFilter.fechaHasta) {
      matchesFecha =
        matchesFecha &&
        new Date(salida.fecha) <=
          new Date(salidasFilter.fechaHasta + "T23:59:59");
    }

    let matchesArea = true;
    if (salidasFilter.areaId) {
      matchesArea = String(salida.areaId) === salidasFilter.areaId;
    }

    let matchesSearch = true;
    if (salidasFilter.search) {
      const searchLower = salidasFilter.search.toLowerCase();
      const searchInInsumos =
        salida.salidaInsumos?.some(
          (si) =>
            si.insumoNombre?.toLowerCase().includes(searchLower) ||
            si.insumo?.nombre.toLowerCase().includes(searchLower)
        ) || false;
      const searchInUsuario =
        salida.usuarioNombre?.toLowerCase().includes(searchLower) ||
        salida.usuario?.nombre.toLowerCase().includes(searchLower) ||
        String(salida.usuarioId).toLowerCase().includes(searchLower) ||
        false;
      matchesSearch = searchInInsumos || searchInUsuario;
    }

    return matchesFecha && matchesArea && matchesSearch;
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
                    <td className="px-6 py-4 whitespace-nowrap text-sm font-medium"></td>
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
            {areas.map((area) => (
              <option key={area.id} value={area.id}>
                {area.nombre}
              </option>
            ))}
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
                    Área
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Usuario
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
                      {salida.areaNombre ||
                        salida.area?.nombre ||
                        salida.areaId}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {salida.usuarioNombre ||
                        salida.usuario?.nombre ||
                        salida.usuarioId}
                    </td>
                    <td className="px-6 py-4 text-sm text-gray-500">
                      {salida.salidaInsumos?.length
                        ? salida.salidaInsumos.map((si) => (
                            <div key={si.id}>
                              {si.insumoNombre
                                ? `${si.insumoNombre}: `
                                : si.insumo?.nombre
                                ? `${si.insumo.nombre}: `
                                : ""}
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
