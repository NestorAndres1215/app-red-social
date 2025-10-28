export interface HistorialUsuarioModel {
  id?: string;              // Opcional si lo genera el backend
  usuarioId: string;        // ID del usuario (o username si prefieres)
  accion: string;           // Qué hizo: "login", "actualizó perfil", "publicó", "comentó"
  descripcion: string;      // Más detalle: "Usuario actualizó su foto de perfil"
  fecha: string;            // Fecha y hora en ISO
}