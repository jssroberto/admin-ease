import { JSX, useState } from 'react';
import { Home, Package, LogOut, ShoppingCart, FileText } from 'lucide-react';
import { Link } from 'react-router-dom';

interface LinkItem {
  name: string;
  icon: JSX.Element;
  path: string;
}

const Sidebar: React.FC = () => {
  const [active, setActive] = useState<string>('Inicio');

  const links: LinkItem[] = [
    { name: 'Inicio', icon: <Home size={20} />, path: '/' },
    { name: 'Gestionar insumos', icon: <Package size={20} />, path: '/gestionar-insumos' },
    { name: 'Salidas de insumos', icon: <LogOut size={20} />, path: '/salidas-insumos' },
    { name: 'Compras de insumos', icon: <ShoppingCart size={20} />, path: '/compras-insumos' },
    { name: 'Generar reporte inventario', icon: <FileText size={20} />, path: '/reporte-inventario' },
    { name: 'Generar reporte costos', icon: <FileText size={20} />, path: '/reporte-costos' },
  ];

  return (
    <div className="w-auto min-h-screen bg-white shadow-md pl-2 pr-2 border-r border-gray-200">
      <nav className="mt-6">
        {links.map((link) => (
          <Link
            to={link.path}
            key={link.name}
            onClick={() => setActive(link.name)}
            className={`flex items-center gap-4 px-4 py-3 cursor-pointer rounded-lg ${
              active === link.name
                ? 'bg-blue-900 text-white'
                : 'text-black hover:bg-gray-100'
            } mb-1`}
          >
            {link.icon}
            <span>{link.name}</span>
          </Link>
        ))}
      </nav>
    </div>
  );
};

export default Sidebar;
