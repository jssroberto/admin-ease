import React from "react";
import { useNavigate } from "react-router-dom";

const LoginPage: React.FC<{ onLogin: () => void }> = ({ onLogin }) => {
  const navigate = useNavigate();

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    onLogin();
    navigate("/");
  };

  return (
    <div className="flex h-screen">
      {/* Lado izquierdo con logo y fondo */}
      <div className="hidden md:flex flex-col justify-center items-center w-1/2 bg-[#0153B6] relative">
        <img
          src="/images/background.png"
          alt="Background"
          className="w-full h-full object-cover"
        />
        <img
          src="/images/logo.svg" /* Assuming icon.svg is named logo.svg and is in public/images */
          alt="Icon"
          className="absolute w-128"
        />
      </div>

      {/* Lado derecho con formulario de login */}
      <div className="flex flex-1 justify-center items-center bg-gray-50">
        <form
          onSubmit={handleLogin}
          className="bg-white rounded-xl shadow-lg p-10 w-full max-w-md border border-gray-200"
        >
          <h2 className="text-2xl font-bold mb-2 text-center">Inicia sesión</h2>
          <div className="flex items-center mb-4"></div>
          <div className="mb-4">
            <label className="block text-gray-700 mb-1 text-sm">
              ID de usuario
            </label>
            <div className="relative">
              <span className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
                <svg width="18" height="18" fill="none" viewBox="0 0 24 24">
                  <circle
                    cx="12"
                    cy="8"
                    r="4"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                  <path
                    d="M4 20c0-2.21 3.582-4 8-4s8 1.79 8 4"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                </svg>
              </span>
              <input
                type="text"
                className="w-full pl-10 pr-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300 text-sm"
                placeholder="ID de usuario"
                required
              />
            </div>
          </div>
          <div className="mb-2">
            <label className="block text-gray-700 mb-1 text-sm">
              Contraseña
            </label>
            <div className="relative">
              <span className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
                <svg width="18" height="18" fill="none" viewBox="0 0 24 24">
                  <path
                    d="M17 11V7a5 5 0 10-10 0v4"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                  <rect
                    x="5"
                    y="11"
                    width="14"
                    height="8"
                    rx="2"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                </svg>
              </span>
              <input
                type="password"
                className="w-full pl-10 pr-3 py-2 border rounded focus:outline-none focus:ring focus:border-#0153B6 text-sm"
                placeholder="Contraseña"
                required
              />
              <span className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 cursor-pointer">
                <svg width="18" height="18" fill="none" viewBox="0 0 24 24">
                  <path
                    d="M1 12s4-7 11-7 11 7 11 7-4 7-11 7S1 12 1 12z"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                  <circle
                    cx="12"
                    cy="12"
                    r="3"
                    stroke="#A0AEC0"
                    strokeWidth="1.5"
                  />
                </svg>
              </span>
            </div>
          </div>
          <button
            type="submit"
            className="w-full bg-[#0153B6] hover:bg-[#013d8a] text-white py-2 rounded-lg font-semibold text-base transition mb-2 cursor-pointer mt-10"
          >
            Iniciar sesión
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
