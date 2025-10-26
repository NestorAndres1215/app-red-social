package com.red_social.analysis_service.service;

import com.red_social.analysis_service.constants.EstadisticasConstants;
import com.red_social.analysis_service.dto.UserEvent;
import com.red_social.analysis_service.model.*;
import com.red_social.analysis_service.repository.*;
import com.red_social.analysis_service.utils.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadisticasService {

    private final EventoUsuarioRepository eventoUsuarioRepository;
    private final EstadisticasGeneroRepository estadisticasGeneroRepository;
    private final EstadisticasPaisRepository estadisticasPaisRepository;
    private final EstadisticasEdadRepository estadisticasEdadRepository;
    private final EstadisticasEstadoRepository estadisticasEstadoRepository;
    private final EstadisticasVerificacionRepository estadisticasVerificacionRepository;
    private final EstadisticasRolesRepository estadisticasRolesRepository;
    private final EstadisticasSemanaRepository estadisticasSemanaRepository;
    private final EstadisticasProveedorRepository estadisticasProveedorRepository;

    public void recalcularEstadisticas() {
        List<EventoUsuario> eventos = eventoUsuarioRepository.findAll();
        if (eventos.isEmpty()) return;
        calcularGenero(eventos);
        calcularPaises(eventos);
        calcularEdades(eventos);
        calcularEstados(eventos);
        calcularVerificados(eventos);
        calcularRoles(eventos);
        calcularProveedores(eventos);
    }

    //METODOS DE VALIDAR
    private String normalizarGenero(String genero) {
        if (genero == null) return "OTROS";
        genero = genero.trim().toUpperCase();
        if (genero.startsWith("M")) return "M";
        if (genero.startsWith("F")) return "F";
        return "OTROS";
    }

    private double calcularPorcentaje(long parte, long total) {
        return total == 0 ? 0.0 : Math.round((parte * 100.0 / total) * 100.0) / 100.0;
    }

    private String generarCodigo(Supplier<String> obtenerUltimoCodigo) {
        return SecuenciaUtil.generarSiguienteCodigo(obtenerUltimoCodigo.get());
    }

    private String obtenerRangoEdad(EventoUsuario e) {
        Integer edad = e.getEdad();
        if (edad == null) return "Desconocido";
        if (edad <= 25) return "18-25";
        if (edad <= 35) return "26-35";
        if (edad <= 45) return "36-45";
        if (edad <= 60) return "46-60";
        return "60+";
    }

    //CALCULAR DE REGISTRAR TODOS

    private void calcularGenero(List<EventoUsuario> eventos) {
        long total = eventos.size();

        Map<String, Long> conteoPorGenero = eventos.stream()
                .collect(Collectors.groupingBy(e -> normalizarGenero(e.getGenero()), Collectors.counting()));

        conteoPorGenero.forEach((genero, totalGenero) -> {

            EstadisticasGenero estadistica = estadisticasGeneroRepository.findByGenero(genero)
                    .orElseGet(() -> EstadisticasGenero.builder()
                            .codigo(generarCodigo(estadisticasGeneroRepository::obtenerCodigo))
                            .genero(genero)
                            .nuevosSemana(0)
                            .build()
                    );

            estadistica.setPorcentaje(calcularPorcentaje(totalGenero, total));
            estadistica.setTotalUsuarios(totalGenero.intValue());
            estadistica.setFechaActualizacion(LocalDateTime.now());

            estadisticasGeneroRepository.save(estadistica);
        });
    }

    private void calcularPaises(List<EventoUsuario> eventos) {
        long total = eventos.size();

        Map<String, Long> conteoPorPais = eventos.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getPais() != null ? e.getPais() : EstadisticasConstants.DESCONOCIDO,
                        Collectors.counting()
                ));

        conteoPorPais.forEach((pais, totalPais) -> {

            EstadisticasPais estadistica = estadisticasPaisRepository.findByPais(pais)
                    .orElseGet(() -> EstadisticasPais.builder()
                            .codigo(generarCodigo(estadisticasPaisRepository::obtenerCodigo))
                            .pais(pais)
                            .nuevosSemana(0)
                            .build()
                    );

            estadistica.setPorcentaje(calcularPorcentaje(totalPais, total));
            estadistica.setTotalUsuarios(totalPais.intValue());
            estadistica.setFechaActualizacion(LocalDateTime.now());

            estadisticasPaisRepository.save(estadistica);
        });
    }

    private void calcularEdades(List<EventoUsuario> eventos) {
        long total = eventos.size();

        Map<String, Long> conteoPorRango = eventos.stream()
                .collect(Collectors.groupingBy(this::obtenerRangoEdad, Collectors.counting()));

        conteoPorRango.forEach((rango, totalRango) -> {

            EstadisticasEdad estadistica = estadisticasEdadRepository.findByRangoEdad(rango)
                    .orElseGet(() -> EstadisticasEdad.builder()
                            .codigo(generarCodigo(estadisticasEdadRepository::obtenerCodigo))
                            .rangoEdad(rango)
                            .nuevosSemana(0)
                            .build()
                    );

            estadistica.setPorcentaje(calcularPorcentaje(totalRango, total));
            estadistica.setTotalUsuarios(totalRango.intValue());
            estadistica.setFechaActualizacion(LocalDateTime.now());

            estadisticasEdadRepository.save(estadistica);
        });
    }


    private void calcularEstados(List<EventoUsuario> eventos) {
        long total = eventos.size();
        if (total == 0) return;

        eventos.stream()
                .collect(Collectors.groupingBy(EventoUsuario::getEstado, Collectors.counting()))
                .forEach((estado, totalEstado) -> {

                    EstadisticasEstado estadistica = estadisticasEstadoRepository.findByEstado(estado)
                            .orElseGet(() -> EstadisticasEstado.builder()
                                    .codigo(generarCodigo(estadisticasEstadoRepository::obtenerCodigo))
                                    .estado(estado)
                                    .nuevoSemana(0)
                                    .build()
                            );

                    estadistica.setPorcentaje(calcularPorcentaje(totalEstado, total));
                    estadistica.setTotalUsuarios(totalEstado.intValue());
                    estadistica.setFechaActualizacion(LocalDateTime.now());

                    estadisticasEstadoRepository.save(estadistica);
                });
    }


    public void calcularVerificados(List<EventoUsuario> eventos) {
        long total = eventos.size();

        eventos.stream()
                .collect(Collectors.groupingBy(e -> Boolean.TRUE.toString().equalsIgnoreCase(e.getVerificado()) ? "Verificado" : "No Verificado",
                        Collectors.counting()))
                .forEach((estado, totalEstado) -> {

                    EstadisticasVerificacion estadistica = estadisticasVerificacionRepository.findByEstado(estado)
                            .orElseGet(() ->
                                    EstadisticasVerificacion.builder()
                                            .codigo(generarCodigo(estadisticasVerificacionRepository::obtenerCodigo))
                                            .estado(estado)
                                            .build()
                            );

                    estadistica.setPorcentaje(calcularPorcentaje(totalEstado, total));
                    estadistica.setTotalUsuarios(totalEstado.intValue());
                    estadistica.setFechaActualizacion(LocalDateTime.now());

                    estadisticasVerificacionRepository.save(estadistica);
                });
    }

    public void calcularRoles(List<EventoUsuario> eventos) {
        long total = eventos.size();
        eventos.stream()
                .collect(Collectors.groupingBy(EventoUsuario::getRol, Collectors.counting()))
                .forEach((rol, totalRol) -> {
                    EstadisticasRoles estadistica = estadisticasRolesRepository.findByRoles(rol)
                            .orElseGet(() ->
                                    EstadisticasRoles.builder()
                                            .codigo(generarCodigo(estadisticasVerificacionRepository::obtenerCodigo))
                                            .roles(rol)
                                            .nuevoSemana(0)
                                            .build()
                            );
                    estadistica.setPorcentaje(calcularPorcentaje(totalRol, total));
                    estadistica.setTotalUsuarios(totalRol.intValue());
                    estadistica.setFechaActualizacion(LocalDateTime.now());

                    estadisticasRolesRepository.save(estadistica);
                });
    }

    public void calcularProveedores(List<EventoUsuario> eventos) {
        long total = eventos.size();
        eventos.stream()
                .collect(Collectors.groupingBy(EventoUsuario::getProveedor, Collectors.counting()))
                .forEach((proveedor, totalProveedor) -> {
                    EstadisticasProveedor estadistica = estadisticasProveedorRepository.findByProveedor(proveedor)
                            .orElseGet(() ->
                                    EstadisticasProveedor.builder()
                                            .codigo(generarCodigo(estadisticasProveedorRepository::obtenerCodigo))
                                            .proveedor(proveedor)
                                            .nuevoSemana(0)
                                            .build()
                            );
                    estadistica.setPorcentaje(calcularPorcentaje(totalProveedor, total));
                    estadistica.setTotalUsuarios(totalProveedor.intValue());
                    estadistica.setFechaActualizacion(LocalDateTime.now());

                    estadisticasProveedorRepository.save(estadistica);
                });
    }


    @Scheduled(cron = "59 59 23 ? * SUN")
    //  @Scheduled(cron = "0 * * * * *")
    public void calcularEstadisticaSemana() {
        System.out.println("⏰ Ejecutando cálculo semanal...");

        // Semana actual
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime primerDiaSemana = hoy.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime ultimoDiaSemana = hoy.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        int numeroSemana = hoy.get(WeekFields.ISO.weekOfYear());
        String mesAnio = String.format("%02d%04d", hoy.getMonthValue(), hoy.getYear());

        // 🔹 Obtener usuarios registrados en la semana
        List<EventoUsuario> registrosSemana = eventoUsuarioRepository.findByFechaRegistroBetween(primerDiaSemana, ultimoDiaSemana);
        long totalSemana = registrosSemana.size();

        // 🔹 Obtener semana anterior (última guardada en BD)
        Optional<EstadisticasSemana> semanaAnteriorOpt = estadisticasSemanaRepository.findTopByOrderByNumeroSemanaDesc();
        double porcentajeCambio = 0.0;

        if (semanaAnteriorOpt.isPresent()) {
            EstadisticasSemana semanaAnterior = semanaAnteriorOpt.get();
            if (semanaAnterior.getTotalRegistros() > 0) {
                porcentajeCambio = ((double) (totalSemana - semanaAnterior.getTotalRegistros())
                        / semanaAnterior.getTotalRegistros()) * 100.0;
            }
        }

        // 🔹 Crear nueva estadística
        EstadisticasSemana estadistica = EstadisticasSemana.builder()
                .codigo(UUID.randomUUID().toString().substring(0, 8))
                .numeroSemana(numeroSemana)
                .mesAnio(mesAnio)
                .primerDiaSemana(primerDiaSemana)
                .ultimoDiaSemana(ultimoDiaSemana)
                .totalRegistros((int) totalSemana)
                .porcentajeCambio(Math.round(porcentajeCambio * 100.0) / 100.0)
                .fechaRegistro(LocalDate.now())
                .build();


        estadisticasSemanaRepository.save(estadistica);

        System.out.println("✅ Estadística semanal registrada: " + estadistica);
    }
}
