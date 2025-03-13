Table Usuario {
  id int [primary key, increment]
  nombre varchar(50) [unique, not null]
  password varchar(255) [not null]
  tipo int [not null] // 1: Fábrica, 2: Punto de venta, 3: Financiero
}

Table Pieza {
  id int [primary key, increment]
  tipo varchar(100) [not null]
  costo decimal(10,2) [not null]
}

Table Computadora {
  id int [primary key, increment]
  nombre varchar(100) [unique, not null]
  precio decimal(10,2) [not null]
}

Table EnsamblePiezas {
  id int [primary key, increment]
  computadora_id int [not null, ref: > Computadora.id]
  pieza_id int [not null, ref: > Pieza.id]
  cantidad int [not null]
}

Table EnsamblarComputadora {
  id int [primary key, increment]
  computadora_id int [not null, ref: > Computadora.id]
  usuario_id int [not null, ref: > Usuario.id]
  fecha date [not null]
   costo DECIMAL(10,2) [not null]
}

Table Cliente {
  id int [primary key, increment]
  nit varchar(20) [unique, not null]
  nombre varchar(100) [not null]
  direccion varchar(255) [not null]
}

Table Venta {
  id int [primary key, increment]
  cliente_id int [not null, ref: > Cliente.id]
  usuario_id int [not null, ref: > Usuario.id] // Usuario que realiza la venta
  fecha datetime [not null]
  total decimal(10,2) [not null]
}

Table VentaDetalle {
  id int [primary key, increment]
  venta_id int [not null, ref: > Venta.id]
  computadora_id int [not null, ref: > Computadora.id]
  cantidad int [not null]
  precio_unitario decimal(10,2) [not null]
}

Table Devolucion {
  id int [primary key, increment]
  venta_id int [not null, ref: > Venta.id]
  fecha date [not null]
  perdida decimal(10,2) [not null] // costo del computador / 3
}

Table Reporte {
  id int [primary key, increment]
  usuario_id int [not null, ref: > Usuario.id]
  tipo varchar(50) [not null] // ventas, devoluciones, ganancias, etc.
  fecha_inicio datetime [not null]
  fecha_fin datetime [not null]
  archivo_csv varchar(255) // Ruta del archivo CSV generado
}
