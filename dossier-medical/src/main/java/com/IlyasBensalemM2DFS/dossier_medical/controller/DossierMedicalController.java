package com.IlyasBensalemM2DFS.dossier_medical.controller;


import com.IlyasBensalemM2DFS.dossier_medical.delegate.PatientServiceDelegate;
import com.IlyasBensalemM2DFS.dossier_medical.delegate.PraticienServiceDelegate;
import com.IlyasBensalemM2DFS.dossier_medical.model.*;
import com.IlyasBensalemM2DFS.dossier_medical.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dossier-medicaux")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;
    @Autowired
    private PraticienServiceDelegate praticienServiceDelegate;
    @Autowired
    private PatientServiceDelegate patientServiceDelegate;


    @GetMapping("/")
    public List<DossierMedical> getAllDossierMedical(){
        return dossierMedicalService.getAllDossierMedical();
    }

    @GetMapping("/{praticienId}/{patientId}")
    public DossierMedicalComplet getDossierMedical(
            @PathVariable String praticienId,
            @PathVariable String patientId
    ){
        Praticien praticien =  praticienServiceDelegate.getPraticien(praticienId);
        if(praticien == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Praticien not found with ID: " + praticienId);
        }
        Patient patient = patientServiceDelegate.getPatient(patientId);
        if(patient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with ID: " + patientId);
        }
        DossierMedical dossierMedical = dossierMedicalService.getDossierMedical(praticienId, patientId);
        if(dossierMedical == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dossier medical not found");
        }
        DossierMedicalComplet dossierMedicalComplet = new DossierMedicalComplet();
        dossierMedicalComplet.setId(dossierMedical.getId());
        dossierMedicalComplet.setPraticien(praticien);
        dossierMedicalComplet.setPatient(patient);
        dossierMedicalComplet.setVisites(dossierMedical.getVisites());
        return dossierMedicalComplet;
    }

    @GetMapping("/{dossierMedicalId}")
    public DossierMedical getDossierMedical(@PathVariable String dossierMedicalId){
        return dossierMedicalService.getDossierMedical(dossierMedicalId);
    }

    @PostMapping("/ajout-dossier")
    public void ajoutDossierMedical(@RequestBody DossierMedical dossierMedical){
        dossierMedical.setId(UUID.randomUUID().toString());
        dossierMedicalService.ajoutDossierMedical(dossierMedical);
    }

    @PostMapping("/ajout-visite/{dossierMedicalId}")
    public void ajouterVisiteToDossierMedical(@PathVariable String dossierMedicalId, @RequestBody VisiteMedicale visiteMedicale){
        this.dossierMedicalService.ajouterVisiteToDossierMedical(dossierMedicalId, visiteMedicale);
    }
}
