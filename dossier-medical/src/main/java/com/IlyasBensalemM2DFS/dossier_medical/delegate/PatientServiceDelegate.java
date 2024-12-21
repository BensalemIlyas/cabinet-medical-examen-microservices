package com.IlyasBensalemM2DFS.dossier_medical.delegate;

import com.IlyasBensalemM2DFS.dossier_medical.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientServiceDelegate {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${services.patient-service.url}")
    private String patientServiceUrl;

    public Patient getPatient(String patientId){
        String url = patientServiceUrl;

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
