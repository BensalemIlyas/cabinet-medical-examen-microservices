package com.IlyasBensalemM2DFS.Api_Gateway.controller;

import com.IlyasBensalemM2DFS.Api_Gateway.model.DossierMedical;
import com.IlyasBensalemM2DFS.Api_Gateway.model.DossierMedicalComplet;
import com.IlyasBensalemM2DFS.Api_Gateway.model.VisiteMedicale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/dossier-medicaux")
public class DossierMedicalController {

    RestTemplate restTemplate = new RestTemplate();

    @Value("${services.dossier-medical-service.url}")
    String baseUrl;


    @GetMapping
    public List<DossierMedical> getAllDossierMedical(){
        String url = baseUrl;
        return this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DossierMedical>>(){}
        ).getBody();
    }

    @GetMapping("/{praticienId}/{patientId}")
    public DossierMedicalComplet getDossierMedical(@PathVariable String praticienId, @PathVariable String patientId) {
        String url = baseUrl + "/" + praticienId + "/" + patientId;

        ResponseEntity<DossierMedicalComplet> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                DossierMedicalComplet.class
        );
        return response.getBody();
    }


    @GetMapping("/{dossierMedicalId}")
    public DossierMedical getDossierMedical(@PathVariable String dossierMedicalId){
        String url = baseUrl + "/" + dossierMedicalId;

        ResponseEntity<DossierMedical> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                DossierMedical.class
        );
        return response.getBody();
    }


    @PostMapping("/ajout-dossier")
    public ResponseEntity<String> ajoutDossierMedical(@RequestBody DossierMedical dossierMedical){
        String url = baseUrl + "/ajout-dossier";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DossierMedical> requestEntity = new HttpEntity<>(dossierMedical, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        return ResponseEntity.status(response.getStatusCode()).body("Dossier médical ajouté avec succès");
    }

    @PostMapping("/ajout-visite/{dossierMedicalId}")
    public ResponseEntity<String> ajouterVisiteToDossierMedical(@PathVariable String dossierMedicalId, @RequestBody VisiteMedicale visiteMedicale){
        String url = baseUrl + "/ajout-visite/" + dossierMedicalId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<VisiteMedicale> requestEntity = new HttpEntity<>(visiteMedicale, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Void.class
        );
        return ResponseEntity.status(response.getStatusCode()).body("Visite ajoutée avec succès au dossier médical");
    }

}
