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

interface Insumo {
  id: string;
  nombre: string;
  cantidad: number;
  cantidadDisponible?: number;
  unidad?: string;
}

interface Area {
  id: number;
  nombre: string;
}

const SalidasInsumos: React.FC = () => {
  const [areas, setAreas] = useState<Area[]>([]);
  const [areaSeleccionada, setAreaSeleccionada] = useState<Area | null>(null);
  const [insumoSeleccionado, setInsumoSeleccionado] = useState<Insumo | null>(
    null
  );
  const [cantidadSeleccionada, setCantidadSeleccionada] = useState<number>(10);
  const [insumosSalida, setInsumosSalida] = useState<Insumo[]>([]);
  const [isProcessing, setIsProcessing] = useState<boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [infoMessage, setInfoMessage] = useState<string | null>(null);

  // Funcion para manejar la seleccion de insumo
  const handleInsumoSelect = (insumo: Insumo) => {
    setInsumoSeleccionado({
      ...insumo,
      cantidad: 1, // Inicializar con cantidad 1
    });
    setCantidadSeleccionada(1);
  };

  // useEffect para cargar areas
  useEffect(() => {
    axios
      .get<Area[]>("http://localhost:8080/api/v1/area")
      .then((response) => {
        setAreas(response.data);
      })
      .catch((error) => {
        console.error("Error cargando areas", error);
      });
  }, []);

  // useEffect para imprimir lista d einsumos cada vez q se modifica
  useEffect(() => {
    console.log("Insumos de salida actualizados (uE):", insumosSalida);
  }, [insumosSalida]);

  // Funcion para cambiar la cantidad de un insumo
  const cambiarCantidad = (id: string, nuevaCantidad: number) => {
    setInsumosSalida((prevInsumos) => {
      const updatedInsumos = prevInsumos.map((insumo) =>
        insumo.id === id ? { ...insumo, cantidad: nuevaCantidad } : insumo
      );
      return updatedInsumos;
    });
  };

  // Funcion para eliminar un insumo de la tabla
  const eliminarInsumo = (id: string) => {
    setInsumosSalida((prevInsumos) => {
      const updatedInsumos = prevInsumos.filter((insumo) => insumo.id !== id);
      return updatedInsumos;
    });
  };

  // Funcion para agregar un insumo a la tabla
  const agregarInsumo = () => {
    console.log("agregando...");
    if (cantidadSeleccionada < 1) {
      setErrorMessage("La cantidad debe ser mayor o igual a 1.");
      return;
    }

    if (insumoSeleccionado) {
      const insumoExistente = insumosSalida.find(
        (i) => i.id === insumoSeleccionado.id
      );

      const nuevaCantidad =
        (insumoExistente?.cantidad || 0) + cantidadSeleccionada;

      if (
        insumoSeleccionado.cantidadDisponible !== undefined &&
        nuevaCantidad > insumoSeleccionado.cantidadDisponible
      ) {
        setErrorMessage(
          `La cantidad total (${nuevaCantidad}) supera el stock disponible (${insumoSeleccionado.cantidadDisponible}).`
        );
        return;
      }

      if (insumoExistente) {
        cambiarCantidad(insumoSeleccionado.id, nuevaCantidad);
      } else {
        setInsumosSalida((prevInsumos) => {
          const updatedInsumos = [
            ...prevInsumos,
            {
              ...insumoSeleccionado,
              cantidad: cantidadSeleccionada,
              area: areaSeleccionada,
            },
          ];

          return updatedInsumos;
        });
      }

      setInsumoSeleccionado(null);
      setCantidadSeleccionada(1);
    }
  };

  const decrementarCantidad = () => {
    if (cantidadSeleccionada > 1) {
      setCantidadSeleccionada(cantidadSeleccionada - 1);
    }
  };

  const incrementarCantidad = () => {
    setCantidadSeleccionada(cantidadSeleccionada + 1);
  };

  const finalizarRegistro = async () => {
    if (insumosSalida.length === 0) {
      setInfoMessage("No hay insumos para registrar");
      return;
    }

    setIsProcessing(true);
    try {
      const payload = {
        areaId: areaSeleccionada?.id,
        usuarioId: 1, // Hardcoded
        salidaInsumoRequests: insumosSalida.map((insumo) => ({
          insumoId: parseInt(insumo.id),
          cantidad: insumo.cantidad,
        })),
      };

      console.log("Enviando payload", payload)

      await axios.post("http://localhost:8080/api/v1/salida", payload);

      setInfoMessage("Salida de insumos registrada correctamente");
      setInsumosSalida([]);
    } catch (error) {
      console.error("Error al registrar salidas:", error);
      alert("Error al registrar las salidas de insumos");
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <div className="flex h-full p-6">
      {/* Manejo de errores y mensajes */}
      {errorMessage && (
        <ErrorAlert
          message={errorMessage || ""}
          onClose={() => setErrorMessage(null)}
        />
      )}
      {infoMessage && (
        <MessageAlert
          message={infoMessage || ""}
          onClose={() => setInfoMessage(null)}
        />
      )}

      {/* left section */}
      <div className="flex flex-col w-1/2 pr-6">
        <h1 className="text-2xl font-medium mb-6">
          Gestionar salidas de insumos
        </h1>

        <div className="mb-4">
          <label className="block text-sm font-normal text-gray-600 mb-1">
            Área
          </label>

          <Select
            onValueChange={(value) => {
              const selected = areas.find((area) => area.nombre === value);
              if (selected) setAreaSeleccionada(selected);
            }}
          >
            <SelectTrigger className="w-[180px]">
              <SelectValue placeholder={areaSeleccionada?.nombre} />
            </SelectTrigger>
            <SelectContent>
              {areas.map((area) => (
                <SelectItem key={area.id} value={area.nombre}>
                  {area.nombre}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        {/* nuevo componente */}
        <SearchInsumo onSelectInsumo={handleInsumoSelect} />

        {insumoSeleccionado && (
          <div className="mb-6">
            <div className="mb-2">
              <label className="block text-sm font-normal text-gray-600 mb-1">
                Insumo
              </label>
              <div className="text-base font-medium">
                {insumoSeleccionado.nombre}
              </div>
              <div className="text-sm text-gray-500">
                {insumoSeleccionado.cantidadDisponible || 0}{" "}
                {insumoSeleccionado.unidad || "unidades"} disponibles
              </div>
            </div>

            <div className="mb-4">
              <label className="block text-sm font-normal text-gray-600 mb-1">
                Cantidad
              </label>
              <div className="flex items-center">
                <button
                  onClick={decrementarCantidad}
                  disabled={cantidadSeleccionada <= 1}
                  className="bg-[#4B6ABB] text-white hover:bg-[#213977] p-2 rounded-md w-8 h-8 flex items-center justify-center cursor-pointer transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <Minus />
                </button>
                <input
                  type="number"
                  value={cantidadSeleccionada}
                  onChange={(e) => {
                    const value = Math.max(1, parseInt(e.target.value) || 1); // Evitar valores negativos
                    if (
                      insumoSeleccionado?.cantidadDisponible !== undefined &&
                      value > insumoSeleccionado.cantidadDisponible
                    ) {
                      setCantidadSeleccionada(
                        insumoSeleccionado.cantidadDisponible
                      );
                    } else {
                      setCantidadSeleccionada(value);
                    }
                  }}
                  min={1} // Evitar valores negativos desde el input
                  max={insumoSeleccionado?.cantidadDisponible}
                  className="mx-2 w-16 text-center border border-gray-300 rounded-md py-2"
                />
                <button
                  onClick={incrementarCantidad}
                  disabled={
                    insumoSeleccionado.cantidadDisponible !== undefined &&
                    cantidadSeleccionada >=
                      insumoSeleccionado.cantidadDisponible
                  }
                  className="bg-[#4B6ABB] text-white hover:bg-[#213977] p-2 rounded-md w-8 h-8 flex items-center justify-center cursor-pointer transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <Plus color="#fff" />
                </button>
              </div>
            </div>

            <div>
              <button
                onClick={agregarInsumo}
                className="bg-[#4B6ABB] hover:bg-[#213977] text-white px-4 py-2 rounded-md flex items-center gap-2 cursor-pointer transition duration-200 ease-in-out mt-4"
              >
                <Plus size={18} />
                Agregar Insumo
              </button>
            </div>
          </div>
        )}
      </div>

      <div className="flex flex-col w-1/2">
        {insumosSalida.length > 0 && (
          <div className="bg-white rounded-lg shadow overflow-hidden mb-6">
            <div className="grid grid-cols-12 bg-[#4B6ABB] text-white p-3">
              <div className="col-span-8 font-medium">Insumo</div>
              <div className="col-span-4 text-center font-medium">Cantidad</div>
            </div>

            {insumosSalida.map((insumo) => (
              <div
                key={insumo.id}
                className="grid grid-cols-12 border-b border-gray-200 p-3"
              >
                <div className="col-span-8">{insumo.nombre}</div>
                <div className="col-span-4 flex items-center justify-between">
                  <button
                    onClick={() =>
                      cambiarCantidad(
                        insumo.id,
                        Math.max(1, insumo.cantidad - 1)
                      )
                    }
                    className="text-gray-500 hover:text-gray-700 cursor-pointer transition duration-200 ease-in-out"
                  >
                    <Minus size={16} />
                  </button>
                  <span>{insumo.cantidad}</span>
                  <button
                    onClick={() =>
                      cambiarCantidad(insumo.id, insumo.cantidad + 1)
                    }
                    className="text-gray-500 hover:text-gray-700 cursor-pointer transition duration-200 ease-in-out"
                  >
                    <Plus size={16} />
                  </button>
                  <button
                    onClick={() => eliminarInsumo(insumo.id)}
                    className="text-red-500 hover:text-red-700 ml-2 cursor-pointer transition duration-200 ease-in-out"
                  >
                    <Trash2 size={18} />
                  </button>
                </div>
              </div>
            ))}

            <div className="p-3 text-sm text-gray-500">
              {insumosSalida.length} productos a salir
            </div>
          </div>
        )}
        <div className="mt-auto flex justify-end">
          <button
            onClick={finalizarRegistro}
            disabled={isProcessing || insumosSalida.length === 0}
            className="bg-[#213977] hover:bg-blue-900 text-white px-6 py-3 rounded-md font-medium cursor-pointer transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {isProcessing ? "Procesando..." : "Finalizar Registro"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default SalidasInsumos;
