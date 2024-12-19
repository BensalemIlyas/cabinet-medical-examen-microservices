package com.IlyasBensalemM2DFS.patient_service.controller;

import com.IlyasBensalemM2DFS.patient_service.model.Patient;
import com.IlyasBensalemM2DFS.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtenir la liste de tous les patients", description = "Récupère tous les patients de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des patients récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @Operation(summary = "Ajouter un nouveau patient", description = "Ajoute un patient dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient ajouté avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping("/ajout")
    public void ajoutPatient(@RequestBody Patient nouveauPatient) {
        patientService.ajoutPatient(nouveauPatient);
    }

    @Operation(summary = "Modifier un patient", description = "Met à jour les informations d'un patient existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
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

    @Operation(summary = "Supprimer un patient", description = "Supprime un patient de la base de données en utilisant son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
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
