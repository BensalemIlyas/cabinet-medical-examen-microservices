package com.IlyasBensalemM2DFS.Api_Gateway.controller;

import com.IlyasBensalemM2DFS.Api_Gateway.model.Praticien;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/praticiens")
public class PraticienController {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${services.praticien-service.url}")
    String baseUrl;

    @GetMapping
    public List<Praticien> getAllPraticiens() {
        String url = baseUrl;
        return this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Praticien>>(){}
        ).getBody();
    }

    @GetMapping("/{praticienId}")
    public Praticien getPraticien(@PathVariable String praticienId) {
        String url = baseUrl + "/" + praticienId;

        ResponseEntity<Praticien> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Praticien.class
        );
        return response.getBody();
    }

    @GetMapping("/specialite/{specialite}")
    public List<Praticien> getPraticienBySpecialite(@PathVariable String specialite) {
        String url = baseUrl + "/specialite/" + specialite;

        ResponseEntity<List<Praticien>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Praticien>>(){}
        );
        return response.getBody();
    }

    @PostMapping("/ajout")
    public ResponseEntity<String> ajoutPraticien(@RequestBody Praticien nouveauPraticien) {
        String url = baseUrl + "/ajout";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Praticien> requestEntity = new HttpEntity<>(nouveauPraticien, httpHeaders);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        return ResponseEntity.status(response.getStatusCode()).body("Praticien ajouté avec succès");
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierPraticien(@PathVariable String id, @RequestBody Praticien praticienModifie) {
        String url = baseUrl + "/modifier/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Praticien> requestEntity = new HttpEntity<>(praticienModifie, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Praticien modifié avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praticien non trouvé");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerPraticien(@PathVariable String id) {
        String url = baseUrl + "/supprimer/" + id;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Praticien supprimé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Praticien non trouvé.");
        }
    }
}
