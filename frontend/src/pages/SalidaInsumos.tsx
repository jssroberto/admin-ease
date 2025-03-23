import React, { useState } from "react";
import axios from "axios";
import { ScanBarcode, Minus, Plus, Trash, Trash2 } from "lucide-react";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../components/ui/select";
import SearchInsumo from "@/components/SearchInsumo";

interface Insumo {
  id: string;
  nombre: string;
  cantidad: number;
  cantidadDisponible?: number;
  unidad?: string;
}

const SalidasInsumos: React.FC = () => {

  // Estado para el area
  const [areaSeleccionada, setAreaSeleccionada] = useState<string>("Cocina");

  // Estado para la busqueda
  const [terminoBusqueda, setTerminoBusqueda] = useState<string>("");

  // Estado para el insumo seleccionado
  const [insumoSeleccionado, setInsumoSeleccionado] = useState<Insumo | null>(null);

  const [cantidadSeleccionada, setCantidadSeleccionada] = useState<number>(10);

  const [insumosSalida, setInsumosSalida] = useState<Insumo[]>([]);

  const [isProcessing, setIsProcessing] = useState<boolean>(false);

  const handleInsumoSelect = (insumo: Insumo) => {
    setInsumoSeleccionado({
      ...insumo,
      cantidad: 1 // Inicializar con cantidad 1
    });
    setCantidadSeleccionada(1);
  };

  const cambiarCantidad = (id: string, nuevaCantidad: number) => {
    setInsumosSalida((prevInsumos) =>
      prevInsumos.map((insumo) =>
        insumo.id === id ? { ...insumo, cantidad: nuevaCantidad } : insumo
      )
    );
  };

  const eliminarInsumo = (id: string) => {
    setInsumosSalida((prevInsumos) =>
      prevInsumos.filter((insumo) => insumo.id !== id)
    );
  };

  const agregarInsumo = () => {
    if (insumoSeleccionado) {
      const insumoExistente = insumosSalida.find(
        (i) => i.id === insumoSeleccionado.id
      );

      if (insumoExistente) {
        cambiarCantidad(insumoSeleccionado.id, cantidadSeleccionada);
      } else {
        setInsumosSalida([
          ...insumosSalida,
          {
            ...insumoSeleccionado,
            cantidad: cantidadSeleccionada,
          },
        ]);
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
      alert("Debe seleccionar al menos un insumo");
      return;
    }

    setIsProcessing(true);
    try {
      const salidas = insumosSalida.map(insumo => ({
        insumoId: parseInt(insumo.id),
        cantidad: insumo.cantidad,
        area: areaSeleccionada,
        fecha: new Date().toISOString()
      }));

      await axios.post('/api/salidas', salidas);
      alert('Salidas de insumos registradas correctamente');
      setInsumosSalida([]);
    } catch (error) {
      console.error('Error al registrar salidas:', error);
      alert('Error al registrar las salidas de insumos');
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <div className="flex h-full p-6">
      {/* left section */}
      <div className="flex flex-col w-1/2 pr-6">
        <h1 className="text-2xl font-medium mb-6">
          Gestionar salidas de insumos
        </h1>

        <div className="mb-4">
          <label className="block text-sm font-normal text-gray-600 mb-1">
            Área
          </label>
         
            <Select onValueChange={(value) => setAreaSeleccionada(value)}>
            <SelectTrigger className="w-[180px]">
              <SelectValue placeholder={areaSeleccionada} />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="Cocina">Cocina</SelectItem>
              <SelectItem value="Almacén">Almacén</SelectItem>
              <SelectItem value="Bar">Bar</SelectItem>
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
                {insumoSeleccionado.cantidadDisponible || 0} {insumoSeleccionado.unidad || 'unidades'} disponibles
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
                    const value = parseInt(e.target.value) || 1;
                    if (insumoSeleccionado.cantidadDisponible !== undefined && 
                        value > insumoSeleccionado.cantidadDisponible) {
                      setCantidadSeleccionada(insumoSeleccionado.cantidadDisponible);
                    } else {
                      setCantidadSeleccionada(value);
                    }
                  }}
                  min={1}
                  max={insumoSeleccionado.cantidadDisponible}
                  className="mx-2 w-16 text-center border border-gray-300 rounded-md py-2"
                />
                <button
                  onClick={incrementarCantidad}
                  disabled={insumoSeleccionado.cantidadDisponible !== undefined && 
                          cantidadSeleccionada >= insumoSeleccionado.cantidadDisponible}
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
            {isProcessing ? 'Procesando...' : 'Finalizar Registro'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default SalidasInsumos;