package com.IlyasBensalemM2DFS.dossier_medical.delegate;

import com.IlyasBensalemM2DFS.dossier_medical.model.Praticien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PraticienServiceDelegate {

    private RestTemplate restTemplate = new RestTemplate();

    public Praticien getPraticien(String praticienId){
        String url = "http://host.docker.internal:8082/praticiens/{praticienId}";
        ResponseEntity<Praticien> praticiensReponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Praticien>() {},
                praticienId
        );
        return praticiensReponse.getBody();

    }

}
