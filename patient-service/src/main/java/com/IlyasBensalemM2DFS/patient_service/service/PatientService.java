package com.IlyasBensalemM2DFS.patient_service.service;

import com.IlyasBensalemM2DFS.patient_service.model.Patient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private static Set<Patient> patientDatabase = new HashSet<>();

    static{
        Patient john = new Patient(
                UUID.randomUUID().toString(),
                "John",
                "Snow",
                "10/03/2001",
                "johnSnow@gmail.com",
                "0658145863"
        );
        Patient souad = new Patient(
                "10bff7de-e88d-4c76-a8e1-fe0f4893af3e",
                "Souad",
                "Akrif",
                "13/09/2002",
                "souadAkrif@gmail.com",
                "0658145863"
        );
        patientDatabase.add(john);
        patientDatabase.add(souad);
    }

    public List<Patient> getAllPatients(){
        return new ArrayList<>(patientDatabase);
    }

    public Patient getPatient(String patientId) {
        return patientDatabase.stream()
                .filter(patient -> patient.getId().equals(patientId.trim()))
                .findFirst()
                .orElse(null);
    }

    public void ajoutPatient(Patient patient){
        patient.setId(UUID.randomUUID().toString());
        patientDatabase.add(patient);
    }

    public boolean modifierPatient(String id, Patient patientModifie) {
        return patientDatabase.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .map(patient -> {
                    patient.setNom(patientModifie.getNom());
                    patient.setPrenom(patientModifie.getPrenom());
                    patient.setDateNaissance(patientModifie.getDateNaissance());
                    patient.setEmail(patientModifie.getEmail());
                    patient.setTelephone(patientModifie.getTelephone());
                    return true;
                })
                .orElse(false);
    }

    public boolean supprimerPatient(String id) {
        return patientDatabase.removeIf(patient -> patient.getId().equals(id));
    }
}
