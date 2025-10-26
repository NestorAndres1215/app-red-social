package com.red_social.analysis_service.service;

import com.red_social.analysis_service.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final EventoUsuarioService eventoUsuarioService;
    private  final EstadisticasService estadisticasService;

    @KafkaListener(topics = "user-events-topic", groupId = "user-events-group", containerFactory = "userEventKafkaListenerContainerFactory")
    public void consume(UserEvent event) {
        System.out.println("📥 Evento recibido desde Kafka -> " + event);

        // Guardar evento en la BD
        eventoUsuarioService.registrar(event);
        estadisticasService.recalcularEstadisticas();
    }
}
