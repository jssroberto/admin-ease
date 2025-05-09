import React, { useEffect, useState } from "react";
import axios from "axios";
import { Minus, Plus, Trash2 } from "lucide-react";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../components/ui/select";
import SearchInsumo from "@/components/SearchInsumo";
import ErrorAlert from "@/components/ErrorAlert";
import MessageAlert from "@/components/MessageAlert";
import { Insumo, InsumoCompra } from "@/types/types";

interface Proveedor {
  id: number;
  nombre: string;
}

const CompraInsumos: React.FC = () => {
  const [proveedores, setProveedores] = useState<Proveedor[]>([]);
  const [proveedorSeleccionado, setProveedorSeleccionado] =
    useState<Proveedor | null>(null);
  const [insumoSeleccionado, setInsumoSeleccionado] = useState<Insumo | null>(
    null
  );
  const [cantidadSeleccionada, setCantidadSeleccionada] = useState<number>(1);
  const [precioUnitario, setPrecioUnitario] = useState<number>(0);
  const [insumosCompra, setInsumosCompra] = useState<InsumoCompra[]>([]);
  const [isProcessing, setIsProcessing] = useState<boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [infoMessage, setInfoMessage] = useState<string | null>(null);

  // Handle insumo selection
  const handleInsumoSelect = (insumo: Insumo) => {
    const insumoCompra: InsumoCompra = {
      ...insumo,
      cantidadCompra: 1,
      precioUnitario: 0,
    };
    setInsumoSeleccionado(insumoCompra);

    setCantidadSeleccionada(1);
    setPrecioUnitario(0);
  };

  // Load proveedores
  useEffect(() => {
    axios
      .get<Proveedor[]>("http://localhost:8080/api/v1/proveedor")
      .then((response) => {
        setProveedores(response.data);
      })
      .catch((error) => {
        console.error("Error cargando proveedores", error);
      });
  }, []);

  // Update insumos list when changed
  useEffect(() => {
    console.log("Insumos de compra actualizados:", insumosCompra);
  }, [insumosCompra]);

  // Change quantity for an insumo
  const cambiarCantidad = (id: string, nuevaCantidad: number) => {
    setInsumosCompra((prevInsumos) =>
      prevInsumos.map((insumo) =>
        insumo.id === id ? { ...insumo, cantidadCompra: nuevaCantidad } : insumo
      )
    );
  };

  // Change price for an insumo
  const cambiarPrecio = (id: string, nuevoPrecio: number) => {
    setInsumosCompra((prevInsumos) =>
      prevInsumos.map((insumo) =>
        insumo.id === id ? { ...insumo, precioUnitario: nuevoPrecio } : insumo
      )
    );
  };

  // Remove insumo from list
  const eliminarInsumo = (id: string) => {
    setInsumosCompra((prevInsumos) =>
      prevInsumos.filter((insumo) => insumo.id !== id)
    );
  };

  // Add insumo to list
  const agregarInsumo = () => {
    if (!insumoSeleccionado) return;

    if (cantidadSeleccionada < 1) {
      setErrorMessage("La cantidad debe ser mayor o igual a 1");
      return;
    }

    if (precioUnitario <= 0) {
      setErrorMessage("El precio unitario debe ser mayor que 0");
      return;
    }

    const insumoExistente = insumosCompra.find(
      (i) => i.id === insumoSeleccionado.id
    );

    if (insumoExistente) {
      setInsumosCompra((prevInsumos) =>
        prevInsumos.map((insumo) =>
          insumo.id === insumoSeleccionado.id
            ? {
                ...insumo,
                cantidadCompra: cantidadSeleccionada,
                precioUnitario: precioUnitario,
              }
            : insumo
        )
      );
    } else {
      setInsumosCompra((prevInsumos) => [
        ...prevInsumos,
        {
          ...insumoSeleccionado,
          cantidadCompra: cantidadSeleccionada,
          precioUnitario: precioUnitario,
        } as InsumoCompra, // Type assertion here
      ]);
    }

    setInsumoSeleccionado(null);
    setCantidadSeleccionada(1);
    setPrecioUnitario(0);
  };
  // Finalize purchase
  const finalizarCompra = async () => {
    if (insumosCompra.length === 0) {
      setInfoMessage("No hay insumos para registrar");
      return;
    }

    if (!proveedorSeleccionado) {
      setErrorMessage("Debe seleccionar un proveedor");
      return;
    }

    setIsProcessing(true);
    try {
      const payload = {
        proveedorId: proveedorSeleccionado.id,
        usuarioId: 1, // Hardcoded for now, because we don't have the auth CU
        compraInsumos: insumosCompra.map((insumo) => ({
          insumoId: parseInt(insumo.id),
          cantidad: insumo.cantidadCompra,
          precioUnitario: insumo.precioUnitario,
        })),
      };

      await axios.post("http://localhost:8080/api/v1/compra", payload);

      setInfoMessage("Compra registrada correctamente");
      setInsumosCompra([]);
    } catch (error) {
      console.error("Error al registrar compra:", error);
      setErrorMessage("Error al registrar la compra");
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <div className="flex h-full p-6">
      {/* Error and info messages */}
      {errorMessage && (
        <ErrorAlert
          message={errorMessage}
          onClose={() => setErrorMessage(null)}
        />
      )}
      {infoMessage && (
        <MessageAlert
          message={infoMessage}
          onClose={() => setInfoMessage(null)}
        />
      )}

      {/* Left section - Form */}
      <div className="flex flex-col w-1/2 pr-6">
        <h1 className="text-2xl font-medium mb-6">
          Registrar Compra de Insumos
        </h1>

        {/* Proveedor selection */}
        <div className="mb-4">
          <label className="block text-sm font-normal text-gray-600 mb-1">
            Proveedor
          </label>
          <Select
            onValueChange={(value) => {
              const selected = proveedores.find(
                (prov) => prov.nombre === value
              );
              setProveedorSeleccionado(selected || null);
            }}
          >
            <SelectTrigger className="w-full">
              <SelectValue placeholder="Seleccione un proveedor" />
            </SelectTrigger>
            <SelectContent>
              {proveedores.map((prov) => (
                <SelectItem key={prov.id} value={prov.nombre}>
                  {prov.nombre}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        {/* Insumo search */}
        <SearchInsumo onSelectInsumo={handleInsumoSelect} />

        {/* selcted insumo form */}
        {insumoSeleccionado && (
          <div className="mb-6">
            <div className="mb-2">
              <label className="block text-sm font-normal text-gray-600 mb-1">
                Insumo seleccionado
              </label>
              <div className="text-base font-medium">
                {insumoSeleccionado.nombre}
              </div>
              {insumoSeleccionado.unidad && (
                <div className="text-sm text-gray-500">
                  Unidad: {insumoSeleccionado.unidad}
                </div>
              )}
            </div>

            {/* quant controls */}
            <div className="mb-4">
              <label className="block text-sm font-normal text-gray-600 mb-1">
                Cantidad
              </label>
              <div className="flex items-center">
                <button
                  onClick={() =>
                    setCantidadSeleccionada((prev) => Math.max(1, prev - 1))
                  }
                  disabled={cantidadSeleccionada <= 1}
                  className="bg-[#4B6ABB] text-white hover:bg-[#213977] p-2 rounded-md w-8 h-8 flex items-center justify-center cursor-pointer transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <Minus size={16} />
                </button>
                <input
                  type="number"
                  value={cantidadSeleccionada}
                  onChange={(e) =>
                    setCantidadSeleccionada(
                      Math.max(1, parseInt(e.target.value) || 1)
                    )
                  }
                  min={1}
                  className="mx-2 w-16 text-center border border-gray-300 rounded-md py-2"
                />
                <button
                  onClick={() => setCantidadSeleccionada((prev) => prev + 1)}
                  className="bg-[#4B6ABB] text-white hover:bg-[#213977] p-2 rounded-md w-8 h-8 flex items-center justify-center cursor-pointer transition duration-200 ease-in-out"
                >
                  <Plus size={16} />
                </button>
              </div>
            </div>

            {/* price input */}
            <div className="mb-4">
              <label className="block text-sm font-normal text-gray-600 mb-1">
                Precio Unitario
              </label>
              <input
                type="number"
                value={precioUnitario}
                onChange={(e) =>
                  setPrecioUnitario(parseFloat(e.target.value) || 0)
                }
                min="0"
                step="0.01"
                className="w-full border border-gray-300 rounded-md py-2 px-3"
                placeholder="0.00"
              />
            </div>

            {/* button to add to the table */}
            <button
              onClick={agregarInsumo}
              className="bg-[#4B6ABB] hover:bg-[#213977] text-white px-4 py-2 rounded-md flex items-center gap-2 cursor-pointer transition duration-200 ease-in-out"
            >
              <Plus size={18} />
              Agregar a la compra
            </button>
          </div>
        )}
      </div>

      {/* right section : Insumoscomrpra table */}
      <div className="flex flex-col w-1/2">
        {insumosCompra.length > 0 && (
          <div className="bg-white rounded-lg shadow overflow-hidden mb-6">
            <div className="grid grid-cols-12 bg-[#4B6ABB] text-white p-3">
              <div className="col-span-5 font-medium">Insumo</div>
              <div className="col-span-2 text-center font-medium">Cantidad</div>
              <div className="col-span-3 text-center font-medium">
                Precio Unit.
              </div>
              <div className="col-span-2"></div>
            </div>

            {insumosCompra.map((insumo) => (
              <div
                key={insumo.id}
                className="grid grid-cols-12 border-b border-gray-200 p-3"
              >
                <div className="col-span-5">{insumo.nombre}</div>

                <div className="col-span-2 flex items-center justify-center">
                  <button
                    onClick={() =>
                      cambiarCantidad(
                        insumo.id,
                        Math.max(1, insumo.cantidadCompra - 1)
                      )
                    }
                    className="text-gray-500 hover:text-gray-700"
                  >
                    <Minus size={16} />
                  </button>
                  <span className="mx-2">{insumo.cantidadCompra}</span>
                  <button
                    onClick={() =>
                      cambiarCantidad(insumo.id, insumo.cantidadCompra + 1)
                    }
                    className="text-gray-500 hover:text-gray-700"
                  >
                    <Plus size={16} />
                  </button>
                </div>

                <div className="col-span-3 flex items-center justify-center">
                  <input
                    type="number"
                    value={insumo.precioUnitario}
                    onChange={(e) =>
                      cambiarPrecio(insumo.id, parseFloat(e.target.value) || 0)
                    }
                    min="0"
                    step="0.01"
                    className="w-20 text-center border border-gray-300 rounded-md py-1 px-2"
                  />
                </div>

                <div className="col-span-2 flex justify-end">
                  <button
                    onClick={() => eliminarInsumo(insumo.id)}
                    className="text-red-500 hover:text-red-700"
                  >
                    <Trash2 size={18} />
                  </button>
                </div>
              </div>
            ))}

            <div className="p-3 text-right font-medium">
              Total: $
              {insumosCompra
                .reduce(
                  (sum, insumo) =>
                    sum + insumo.cantidadCompra * insumo.precioUnitario,
                  0
                )
                .toFixed(2)}
            </div>
          </div>
        )}

        <div className="mt-auto flex justify-end">
          <button
            onClick={finalizarCompra}
            disabled={
              isProcessing ||
              insumosCompra.length === 0 ||
              !proveedorSeleccionado
            }
            className="bg-[#213977] hover:bg-blue-900 text-white px-6 py-3 rounded-md font-medium cursor-pointer transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {isProcessing ? "Procesando..." : "Finalizar Compra"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default CompraInsumos;
