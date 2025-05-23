-- Insert Areas (unchanged as per request)
INSERT INTO area (nombre) VALUES
('Cocina'),
('Barra'),
('Panadería');

-- Insert Categorías Insumo
INSERT INTO categoria_insumo (nombre) VALUES
('Lácteos'),
('Carnes y Embutidos'),
('Verduras y Frutas'),
('Panadería'),
('Bebidas');

-- Insert Categorías Producto
INSERT INTO categoria_producto (nombre) VALUES
('Bebidas'),
('Desayunos'),
('Postres');

-- Insert Permisos
INSERT INTO permiso (nombre) VALUES
('Gestionar Usuarios'),
('Procesar Compras'),
('Generar Reportes Inventario');

-- Insert Proveedores
INSERT INTO proveedor (correo, nombre, telefono) VALUES
('contacto@lacharcuteria.com', 'La Charcutería', '6441234567'),
('ventas@verdurasfrescas.com', 'Verduras Frescas', '6442345678'),
('distribuidora@lacteosdelvalle.com', 'Lácteos del Valle', '6443456789'),
('pan@artesanos.com', 'Pan Artesanos', '6444567890'),
('bebidas@distribuidora.com', 'Bebidas del Norte', '6445678901');

-- Insert Razon Merma
INSERT INTO razon_merma (descripcion) VALUES
('Producto Caducado'),
('Dañado en preparación'),
('Exceso de inventario'),
('Defecto de calidad');

-- Insert Roles
INSERT INTO rol (nombre) VALUES
('Administrador'),
('Gerente');

-- Insert Unidades de Medida
INSERT INTO unidad_medida (nombre) VALUES
('Litro'),
('Kilogramo'),
('Unidad'),
('Gramo');

-- Insert Productos
INSERT INTO producto (nombre, categoria_producto_id) VALUES
('Café Refill', 1),          -- Bebidas
('Croque Madame', 2),        -- Desayunos
('Pan Francés Frutos Rojos', 3), -- Postres
('Avocado Toast', 2),        -- Desayunos
('Chilaquiles Verdes', 2);   -- Desayunos

-- Insert Insumos
INSERT INTO insumo (codigo, nombre, stock, categoria_insumo_id, unidad_medida_id) VALUES
('7501234567890', 'Queso Gruyere', 15.0, 1, 2),
('7501234567891', 'Jamón Serrano', 10.0, 2, 2),
('7501234567892', 'Aguacate', 25.0, 3, 3),
('7501234567893', 'Pan Brioche', 20.0, 4, 3),
('7501234567894', 'Café Molido', 30.0, 5, 2),
('7501234567895', 'Frutos Rojos', 12.0, 3, 2),
('7501234567896', 'Salsa Poblana', 10.0, 3, 1),
('7501234567897', 'Huevo', 100.0, 1, 3);

-- Insert Usuarios
INSERT INTO usuario (nombre, contrasena, rol_id) VALUES
('admin_moi', 'Admin2025!', 1),
('gerente_moi', 'Gerente2025!', 2);

-- Insert Insumos-Producto
INSERT INTO insumos_producto (cantidad, insumo_id, producto_id) VALUES
(0.1, 1, 1), -- Queso Gruyere for Café (minimal, as coffee may use milk)
(0.2, 1, 2), -- Queso Gruyere for Croque Madame
(0.3, 2, 2), -- Jamón Serrano for Croque Madame
(0.2, 3, 2), -- Aguacate for Croque Madame
(0.1, 3, 3), -- Aguacate for Pan Francés Frutos Rojos
(0.2, 4, 2), -- Pan Brioche for Croque Madame
(0.5, 5, 1), -- Café Molido for Café Refill
(0.1, 6, 3), -- Frutos Rojos for Pan Francés Frutos Rojos
(0.2, 3, 4), -- Aguacate for Avocado Toast
(0.1, 7, 4), -- Salsa Poblana for Chilaquiles Verdes
(0.2, 8, 2), -- Huevo for Croque Madame
(0.2, 8, 4), -- Huevo for Avocado Toast
(0.2, 8, 5); -- Huevo for Chilaquiles Verdes

-- Insert Movimientos Insumo (more movements: COMPRA, MERMA, SALIDA)
INSERT INTO movimiento_insumo (tipo_movimiento, cantidad, insumo_id) VALUES
('COMPRA', 20.0, 1), -- Compra de Queso Gruyere
('COMPRA', 15.0, 2), -- Compra de Jamón Serrano
('COMPRA', 30.0, 3), -- Compra de Aguacate
('COMPRA', 25.0, 4), -- Compra de Pan Brioche
('COMPRA', 40.0, 5), -- Compra de Café Molido
('COMPRA', 15.0, 6), -- Compra de Frutos Rojos
('COMPRA', 12.0, 7), -- Compra de Salsa Poblana
('COMPRA', 150.0, 8), -- Compra de Huevo
('MERMA', 1.0, 1),  -- Merma de Queso Gruyere (Caducado)
('MERMA', 0.5, 2),  -- Merma de Jamón Serrano (Dañado)
('MERMA', 2.0, 3),  -- Merma de Aguacate (Exceso)
('MERMA', 1.0, 4),  -- Merma de Pan Brioche (Caducado)
('MERMA', 2.0, 5),  -- Merma de Café Molido (Defecto)
('MERMA', 0.5, 6),  -- Merma de Frutos Rojos (Dañado)
('MERMA', 0.3, 7),  -- Merma de Salsa Poblana (Exceso)
('MERMA', 5.0, 8),  -- Merma de Huevo (Dañado)
('SALIDA', 2.0, 1), -- Salida de Queso Gruyere (Cocina)
('SALIDA', 1.5, 2), -- Salida de Jamón Serrano (Cocina)
('SALIDA', 3.0, 3), -- Salida de Aguacate (Cocina)
('SALIDA', 2.0, 4), -- Salida de Pan Brioche (Panadería)
('SALIDA', 5.0, 5), -- Salida de Café Molido (Barra)
('SALIDA', 1.0, 6), -- Salida de Frutos Rojos (Cocina)
('SALIDA', 1.5, 7), -- Salida de Salsa Poblana (Cocina)
('SALIDA', 10.0, 8); -- Salida de Huevo (Cocina)

-- Insert Compras
INSERT INTO compra (fecha, total, proveedor_id, usuario_id) VALUES
(NOW(), 2500.0, 1, 1), -- Compra de charcutería (Admin)
(NOW(), 1800.0, 2, 2), -- Compra de verduras (Gerente)
(NOW(), 3200.0, 3, 2), -- Compra de lácteos (Gerente)
(NOW(), 1500.0, 4, 2), -- Compra de panadería (Gerente)
(NOW(), 2000.0, 5, 2); -- Compra de bebidas (Gerente)

-- Insert Compra Insumos
INSERT INTO compra_insumo (id, precio_unitario, compra_id) VALUES
(1, 200.0, 1), -- Queso Gruyere
(2, 350.0, 2), -- Jamón Serrano
(3, 50.0, 2),  -- Aguacate
(4, 30.0, 4),  -- Pan Brioche
(5, 100.0, 5), -- Café Molido
(6, 150.0, 2), -- Frutos Rojos
(7, 80.0, 2),  -- Salsa Poblana
(8, 2.0, 3);   -- Huevo

-- Insert Mermas
INSERT INTO merma (fecha, usuario_id) VALUES
(NOW(), 1), -- Admin registra merma
(NOW(), 2), -- Gerente registra merma
(NOW(), 2), -- Gerente registra merma
(NOW(), 2), -- Gerente registra merma
(NOW(), 1); -- Admin registra merma

-- Insert Merma Insumos
INSERT INTO merma_insumo (id, merma_id, razon_id) VALUES
(9, 1, 1),  -- Merma de Queso Gruyere (Caducado)
(10, 2, 2), -- Merma de Jamón Serrano (Dañado)
(11, 3, 3), -- Merma de Aguacate (Exceso)
(12, 4, 1), -- Merma de Pan Brioche (Caducado)
(13, 5, 4); -- Merma de Café Molido (Defecto)

-- Insert Salidas
INSERT INTO salida (fecha, area_id, usuario_id) VALUES
(NOW(), 1, 2), -- Salida Cocina (Gerente)
(NOW(), 2, 2), -- Salida Barra (Gerente)
(NOW(), 3, 2), -- Salida Panadería (Gerente)
(NOW(), 1, 2), -- Salida Cocina (Gerente)
(NOW(), 2, 2); -- Salida Barra (Gerente)

-- Insert Salida Insumos
INSERT INTO salida_insumo (id, salida_id) VALUES
(14, 1), -- Salida de insumos para Cocina
(15, 2), -- Salida de insumos para Barra
(16, 3), -- Salida de insumos para Panadería
(17, 4), -- Salida de insumos para Cocina
(18, 5); -- Salida de insumos para Barra

-- Insert Permisos-Rol
INSERT INTO permisos_rol (rol_id, permiso_id) VALUES
(1, 1), -- Administrador: Gestionar Usuarios
(1, 2), -- Administrador: Procesar Compras
(1, 3), -- Administrador: Generar Reportes Inventario
(2, 2), -- Gerente: Procesar Compras
(2, 3); -- Gerente: Generar Reportes Inventario