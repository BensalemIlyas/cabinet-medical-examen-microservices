package com.IlyasBensalemM2DFS.praticien_service.controller;

import com.IlyasBensalemM2DFS.praticien_service.model.Praticien;
import com.IlyasBensalemM2DFS.praticien_service.service.PraticienService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/praticiens")
public class PraticienController {

    @Autowired
    PraticienService praticienService;

    @Operation(summary = "Obtenir la liste de tous les praticiens", description = "Récupère tous les praticiens de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des praticiens récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<Praticien> getAllPraticiens() {
        return praticienService.getAllPraticiens();
    }

    @Operation(summary = "Obtenir les informations sur un praticien à partir de son id", description = "Récupère un praticien de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information sur le praticien renvoyé avec succès"),
            @ApiResponse(responseCode = "404", description = "Praticien n'a pas été trouvé")
    })
    @GetMapping("/{praticienId}")
    public Praticien getPraticienById(@PathVariable String praticienId) {
        Praticien praticien = praticienService.getPraticien(praticienId);
        if (praticien == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Praticien not found with ID: " + praticienId);
        }
        return praticien;
    }

    @Operation(summary = "Obtenir la liste de tous les praticiens pour une spécialité donnée", description = "Récupère tous les praticiens de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des praticiens pour une spécialité donnée récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("/specialite/{specialite}")
    public List<Praticien> getPraticienBySpecialite(@PathVariable String specialite) {
       return praticienService.getPraticienBySpecialite(specialite);
    }

    @Operation(summary = "Ajouter un nouveau praticien", description = "Ajoute un praticien dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "praticien ajouté avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping("/ajout")
    public void ajoutPraticien(@RequestBody Praticien praticien) {
        praticienService.ajoutPraticien(praticien);
    }

    @Operation(summary = "Modifier un praticien", description = "Met à jour les informations d'un praticien existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Praticien mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Praticien non trouvé")
    })
    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierPraticien(
            @PathVariable String id,
            @RequestBody Praticien praticien) {

        boolean isUpdate = praticienService.modifierPraticien(id, praticien);
        if(isUpdate) {
            return ResponseEntity.ok("Praticien modifié avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praticien avec ID "+ id + " non trouvé.");
        }
    }

    @Operation(summary = "Supprimer un praticien", description = "Supprime un praticien de la base de données en utilisant son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Praticien supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Praticien non trouvé")
    })
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerPraticien(@PathVariable String id) {
        boolean isDeleted = praticienService.supprimerPraticien(id);
        if(isDeleted) {
            return ResponseEntity.ok("Praticien supprimé avec succès");
        }else {
            return ResponseEntity.status(404).body("Patient non trouvé");
        }
    }
}
