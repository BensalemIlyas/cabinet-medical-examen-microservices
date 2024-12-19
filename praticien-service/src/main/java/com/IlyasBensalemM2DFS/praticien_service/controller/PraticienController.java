package com.IlyasBensalemM2DFS.praticien_service.controller;

import com.IlyasBensalemM2DFS.praticien_service.model.Praticien;
import com.IlyasBensalemM2DFS.praticien_service.service.PraticienService;
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

    @GetMapping
    public List<Praticien> getAllPraticiens() {
        return praticienService.getAllPraticiens();
    }

    @GetMapping("/{praticienId}")
    public Praticien getPraticienById(@PathVariable String praticienId) {
        Praticien praticien = praticienService.getPraticien(praticienId);
        if (praticien == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Praticien not found with ID: " + praticienId);
        }
        return praticien;
    }

    @GetMapping("/specialite/{specialite}")
    public List<Praticien> getPraticienBySpecialite(@PathVariable String specialite) {
       return praticienService.getPraticienBySpecialite(specialite);
    }

    @PostMapping("/ajout")
    public void ajoutPraticien(@RequestBody Praticien praticien) {
        praticienService.ajoutPraticien(praticien);
    }

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
