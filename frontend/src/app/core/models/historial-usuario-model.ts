export interface HistorialUsuarioModel {
  codigo?: string;              // Opcional si lo genera el backend
  fecha: string;        // ID del usuario (o username si prefieres)
  estado: string;           // Qué hizo: "login", "actualizó perfil", "publicó", "comentó"
  detalle: string;      // Más detalle: "Usuario actualizó su foto de perfil"
         // Fecha y hora en ISO
}