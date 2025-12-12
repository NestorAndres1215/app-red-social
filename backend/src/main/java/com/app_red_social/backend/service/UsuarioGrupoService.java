package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.dto.request.UsuarioGrupoRequest;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.model.UsuarioGrupo;
import com.app_red_social.backend.repository.UsuarioGrupoRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsuarioGrupoService {

    private final UsuarioGrupoRepository usuarioGrupoRepository;

    public List<UsuarioGrupo> listar() {
        return usuarioGrupoRepository.findAll();
    }

    public UsuarioGrupo registrar(UsuarioGrupoRequest usuarioGrupoRequest) {
        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());
        UsuarioGrupo usuarioGrupo = UsuarioGrupo.builder()
                .codigo(nuevoCodigo)
                .nombre(usuarioGrupoRequest.getNombre())
                .descripcion(usuarioGrupoRequest.getDescripcion())
                .foto(usuarioGrupoRequest.getFoto())
                .privacidad(usuarioGrupoRequest.getPrivacidad())
                .estado(usuarioGrupoRequest.getEstado())
                .creador(usuarioGrupoRequest.getCreador())
                .fechaRegistro(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();

        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public String ultimoCodigo() {
        return usuarioGrupoRepository.obtenerCodigo();
    }


    public UsuarioGrupo obtenerGrupo(String codigo) {
        return usuarioGrupoRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public UsuarioGrupo actualizar(String codigo, UsuarioGrupoRequest usuarioGrupoRequest) {
        UsuarioGrupo usuarioGrupo = obtenerGrupo(codigo);
        usuarioGrupo.setNombre(usuarioGrupoRequest.getNombre());
        usuarioGrupo.setDescripcion(usuarioGrupoRequest.getDescripcion());
        usuarioGrupo.setFechaActualizacion(LocalDateTime.now());

        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public UsuarioGrupo actualizarFoto(String codigo, MultipartFile file) {
        UsuarioGrupo usuarioGrupo = obtenerGrupo(codigo);

        if (file.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }

        try {
            if (usuarioGrupo.getFoto() != null && !usuarioGrupo.getFoto().isEmpty()) {

                Path oldFile = Paths.get("uploads/grupos").resolve(usuarioGrupo.getFoto());

                if (Files.exists(oldFile)) {
                    Files.delete(oldFile);
                }
            }

            String nombreNormalizado = normalizarNombre(usuarioGrupo.getNombre());

            String original = Objects.requireNonNull(file.getOriginalFilename());
            String extension = original.substring(original.lastIndexOf("."));

            String fileName = usuarioGrupo.getCodigo() + "_" + nombreNormalizado + extension;

            Path uploadDir = Paths.get("uploads/grupos");

            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            usuarioGrupo.setFoto(fileName);

            return usuarioGrupoRepository.save(usuarioGrupo);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen");
        }
    }


    private String normalizarNombre(String nombre) {
        if (nombre == null) return null;

        String result = nombre.toUpperCase();

        result = result.replace("Ñ", "N");
        result = java.text.Normalizer.normalize(result, java.text.Normalizer.Form.NFD);
        result = result.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        result = result.replaceAll("[^A-Z0-9]", "_");

        return result;
    }


}
