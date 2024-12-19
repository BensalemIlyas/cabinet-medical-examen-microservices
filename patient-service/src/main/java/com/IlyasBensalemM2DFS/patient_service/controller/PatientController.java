package com.IlyasBensalemM2DFS.patient_service.controller;

import com.IlyasBensalemM2DFS.patient_service.model.Patient;
import com.IlyasBensalemM2DFS.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/ajout")
    public void ajoutPatient(@RequestBody Patient nouveauPatient) {
        patientService.ajoutPatient(nouveauPatient);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierPatient(
            @PathVariable String id,
            @RequestBody Patient patientModifie) {
        boolean isUpdated = patientService.modifierPatient(id, patientModifie);
        if (isUpdated) {
            return ResponseEntity.ok("Patient modifié avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient avec ID " + id + " non trouvé.");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerPatient(@PathVariable String id) {
        boolean isDeleted = patientService.supprimerPatient(id);
        if (isDeleted) {
            return ResponseEntity.ok("Patient supprimé avec succès.");
        } else {
            return ResponseEntity.status(404).body("Patient non trouvé.");
        }
    }


}
