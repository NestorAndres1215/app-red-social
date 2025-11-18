export interface RegisterUser {
    nombre: string;
    apellido: string;
    edad: number;
    fechaNacimiento: string; // formato YYYY-MM-DD
    genero: string;
    nacionalidad: string;
    presentacion?: string;
    codigoUsuario?: string;
    username: string;
    email: string;
    telefono: string;
    password: string;
}