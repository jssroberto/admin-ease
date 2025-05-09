export interface Insumo {
    id: string;
    codigo: string;
    nombre: string;
    cantidad: number;
    categoria: string;
    stock?: number;
    unidad?: string;
  }

  export interface InsumoCompra extends Insumo {
    precioUnitario: number;
    cantidadCompra: number;
}

export interface Area {
  id: number;
  nombre: string;
}

export interface Categoria {
    id: string;
    nombre: string;
  }

  