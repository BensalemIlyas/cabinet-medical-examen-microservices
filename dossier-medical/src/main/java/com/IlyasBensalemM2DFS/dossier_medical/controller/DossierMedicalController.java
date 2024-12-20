package com.IlyasBensalemM2DFS.dossier_medical.controller;


import com.IlyasBensalemM2DFS.dossier_medical.delegate.PatientServiceDelegate;
import com.IlyasBensalemM2DFS.dossier_medical.delegate.PraticienServiceDelegate;
import com.IlyasBensalemM2DFS.dossier_medical.model.DossierMedical;
import com.IlyasBensalemM2DFS.dossier_medical.model.DossierMedicalComplet;
import com.IlyasBensalemM2DFS.dossier_medical.model.Patient;
import com.IlyasBensalemM2DFS.dossier_medical.model.Praticien;
import com.IlyasBensalemM2DFS.dossier_medical.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/dossier-medicaux")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;
    @Autowired
    private PraticienServiceDelegate praticienServiceDelegate;
    @Autowired
    private PatientServiceDelegate patientServiceDelegate;


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

    //TODO Pour un medecin et un patient recuperer la liste des visites medicales

    //TODO récuperer une visite médicale.

    //TODO créer un dossier medical

    //TODO ajouter une visite medical

}
