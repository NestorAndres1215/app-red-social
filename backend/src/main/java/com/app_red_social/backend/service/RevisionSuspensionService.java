package com.app_red_social.backend.service;

import com.app_red_social.backend.model.RevisionSuspension;
import com.app_red_social.backend.repository.RevisionSuspensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionSuspensionService {

    private final RevisionSuspensionRepository revisionSuspensionRepository;

    public String obtenerUltimoCodigo() {
        return revisionSuspensionRepository.obtenerUltimoCodigo();
    }

    public List<RevisionSuspension> listarRevision() {
        return revisionSuspensionRepository.findAll();
    }

    public RevisionSuspension registroSuspenso(RevisionSuspension revisionSuspension) {
        return revisionSuspensionRepository.save(revisionSuspension);
    }

}
