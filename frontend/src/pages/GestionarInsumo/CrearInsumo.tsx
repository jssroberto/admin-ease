import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

interface InsumoFormData {
  codigo: string;
  nombre: string;
  stock: number;
  unidadMedidaId: number;
  categoriaInsumoId: number;
}

const CrearInsumo: React.FC = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<InsumoFormData>({
    codigo: '',
    nombre: '',
    stock: 0,
    unidadMedidaId: 1, 
    categoriaInsumoId: 1
  });
  const [categorias, setCategorias] = useState<any[]>([]);
  const [unidadesMedida, setUnidadesMedida] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  
  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const [catRes, uniRes] = await Promise.all([
          axios.get('http://localhost:8080/api/v1/categoriaInsumo'),
          axios.get('http://localhost:8080/api/v1/unidadMedida')
        ]);
        setCategorias(catRes.data);
        setUnidadesMedida(uniRes.data);
      } catch (err) {
        setError('Error loading form data');
      }
    };
    fetchData();
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'stock' ? parseFloat(value) : name.endsWith('Id') ? parseInt(value) : value
    }));
    console.log(formData);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');

    try {
      await axios.post('http://localhost:8080/api/v1/insumo', formData);
      navigate('/gestionar-insumos');
    } catch (err) {
      setError('Error creating insumo');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex flex-col max-w-4xl mr-auto p-6">
  <h1 className="text-2xl font-medium mb-6">Crear nuevo insumo</h1>
  
  {error && <div className="mb-4 text-red-500">{error}</div>}

  <form onSubmit={handleSubmit} className="space-y-4">
    <div className="grid grid-cols-2 gap-6">
      <div className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">Código</label>
          <input
            type="text"
            name="codigo"
            value={formData.codigo}
            onChange={handleChange}
            placeholder='INS0010000001'
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            required
            title="Campo necesario"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">Nombre</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
            placeholder='Harina'
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            required
          />
        </div>

        {/* <div>
          <label className="block text-sm font-medium text-gray-700">Stock</label>
          <input
            type="number"
            name="stock"
            value={formData.stock}
            onChange={handleChange}
            step="1"
            min="1"
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            required
          />
        </div> */}
      </div>

      <div className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700">Categoría</label>
          <select
            name="categoriaInsumoId"
            value={formData.categoriaInsumoId}
            onChange={handleChange}
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            required
          >
            {categorias.map(cat => (
              <option key={cat.id} value={cat.id}>{cat.nombre}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">Unidad de Medida</label>
          <select
            name="unidadMedidaId"
            value={formData.unidadMedidaId}
            onChange={handleChange}
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            required
          >
            {unidadesMedida.map(uni => (
              <option key={uni.id} value={uni.id}>{uni.nombre}</option>
            ))}
          </select>
        </div>
      </div>
    </div>

    <div className="flex justify-end space-x-3 pt-4">
      <button
        type="button"
        onClick={() => navigate('/gestionar-insumos')}
        className="py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 cursor-pointer"
      >
        Volver
      </button>
      <button
        type="submit"
        disabled={isLoading}
        className="py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-[#4B6ABB] hover:bg-[#213977] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 disabled:opacity-50 cursor-pointer transition duration-200 ease-in-out"
      >
        {isLoading ? 'Creando...' : 'Crear Insumo'}
      </button>
    </div>
  </form>
</div>
  );
};

export default CrearInsumo;