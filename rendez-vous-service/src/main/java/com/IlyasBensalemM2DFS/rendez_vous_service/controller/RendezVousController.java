package com.IlyasBensalemM2DFS.rendez_vous_service.controller;

import com.IlyasBensalemM2DFS.rendez_vous_service.dto.RendezVousDto;
import com.IlyasBensalemM2DFS.rendez_vous_service.model.RendezVous;
import com.IlyasBensalemM2DFS.rendez_vous_service.service.RendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rendez-vous")
public class RendezVousController {


    private RendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau rendez vous")
    public ResponseEntity<RendezVous> createAppointment(@RequestBody RendezVousDto rendezVousDto) throws  IOException {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setPatientId(rendezVousDto.getPatientId());
        rendezVous.setPraticienId(rendezVousDto.getPraticienId());
        rendezVous.setStartTime(rendezVousDto.getStartTime());
        rendezVous.setEndTime(rendezVousDto.getEndTime());

        return ResponseEntity.ok(rendezVousService.creerRendezVous(rendezVous));
    }


}
