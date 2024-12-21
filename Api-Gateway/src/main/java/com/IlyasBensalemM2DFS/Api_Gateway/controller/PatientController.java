package com.IlyasBensalemM2DFS.Api_Gateway.controller;

import com.IlyasBensalemM2DFS.Api_Gateway.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {

    RestTemplate restTemplate = new RestTemplate();
    @Value("${services.patient-service.url}")
    String baseUrl;

    @GetMapping
    public List<Patient> getAllPatient(){
        String url = baseUrl;
        return  this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Patient>>(){}
        ).getBody();
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable String patientId) {
        String url = baseUrl + patientId;

        ResponseEntity<Patient> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                Patient.class
        );
        return response.getBody();
    }

    @PostMapping("/ajout")
    public ResponseEntity<String> ajoutPatient(@RequestBody Patient nouveauPatient) {
        String url = baseUrl + "/ajout";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestEntity = new HttpEntity<>(nouveauPatient, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        return ResponseEntity.status(response.getStatusCode()).body("Patient ajouté avec succès");
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierPatient(@PathVariable String id, @RequestBody Patient patientModifie) {
        String url = baseUrl + "/modifier/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Patient> requestEntity = new HttpEntity<>(patientModifie, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Patient modifié avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient non trouvé");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerPatient(@PathVariable String id) {
        String url = baseUrl + "/supprimer/" + id;

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Patient supprimé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient non trouvé.");
        }
    }
}
