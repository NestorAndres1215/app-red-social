export interface HistorialUsuarioModel {
  codigo?: string;
  fecha: string;
  estado: string;
  detalle: string;

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