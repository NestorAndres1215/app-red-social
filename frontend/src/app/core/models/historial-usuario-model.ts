export interface HistorialUsuarioModel {
  codigo?: string;
  fecha?: string;
  estado: string;
  detalle: string;
  titulo: string;
  modulo: string;
  usuario: String;
}

export interface HistorialListModel {
  codigoHistorial: string;
  detalleHistorial: string;
  estadoHistorial: string;
  fechaHistorial: string;
  horaHistorial: string;
  moduloHistorial: string;
  tituloHistorial: string;
  usuarioHistorial: string;
}