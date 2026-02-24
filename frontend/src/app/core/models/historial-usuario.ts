export interface HistorialUsuario {
  codigo?: string;
  fecha?: string;
  estado: string;
  detalle: string;
  titulo: string;
  modulo: string;
  usuario: String;
}

export interface HistorialListado {
  codigoHistorial: string;
  detalleHistorial: string;
  estadoHistorial: string;
  fechaHistorial: string;
  horaHistorial: string;
  moduloHistorial: string;
  tituloHistorial: string;
  usuarioHistorial: string;
}