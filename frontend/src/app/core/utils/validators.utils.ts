export function obtenerMaxFechaNacimiento(mayorDeEdad: number = 18): string {
  const hoy = new Date();
  const anio = hoy.getFullYear() - mayorDeEdad;
  const mes = String(hoy.getMonth() + 1).padStart(2, '0');
  const dia = String(hoy.getDate()).padStart(2, '0');
  return `${anio}-${mes}-${dia}`;
}

/** Filtra un string y devuelve solo los números */
export function filtrarSoloNumeros(valor: string): string {
  return valor.replace(/[^0-9]/g, '');
}

export function edadConvertir(valor: string): number {
  const nacimiento = new Date(valor);
  const hoy = new Date();
  let edad = hoy.getFullYear() - nacimiento.getFullYear();
  const m = hoy.getMonth() - nacimiento.getMonth();
  if (m < 0 || (m === 0 && hoy.getDate() < nacimiento.getDate())) {
    edad--;
  }

  return edad;
}

export function obtenerImagenPerfil(perfil: string | null | undefined): string | null {
  const base = 'data:image/jpeg;base64,';
  return perfil ? base + perfil : null;
}