package com.IlyasBensalemM2DFS.dossier_medical.delegate;

import com.IlyasBensalemM2DFS.dossier_medical.model.Patient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientServiceDelegate {

    private RestTemplate restTemplate = new RestTemplate();

    public Patient getPatient(String patientId){
        String url = "http://localhost:8081/patients/{patientId}";

        ResponseEntity<Patient> patientResponse =  restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Patient>() {},
                patientId
        );

        return patientResponse.getBody();
    }
}
