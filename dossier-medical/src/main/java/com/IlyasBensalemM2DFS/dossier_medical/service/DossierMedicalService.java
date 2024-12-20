package com.IlyasBensalemM2DFS.dossier_medical.service;

import com.IlyasBensalemM2DFS.dossier_medical.model.DossierMedical;
import com.IlyasBensalemM2DFS.dossier_medical.model.VisiteMedicale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalService {

    private static List<DossierMedical> dossierMedicauxDatabase = new ArrayList<>();

    static{
        VisiteMedicale visiteMedicale = new VisiteMedicale();
        visiteMedicale.setDateVisite("2024-12-20");
        visiteMedicale.setDiagnostique("Rhume");
        visiteMedicale.setTraitement("Paracetamol, vitamines");
        visiteMedicale.setNotes("patient présente des symptomes de fatigue ainsi que une tension assez limite");
        DossierMedical dossierMedicalSouadUmbert = new DossierMedical();
        dossierMedicalSouadUmbert.setPatientId("10bff7de-e88d-4c76-a8e1-fe0f4893af3e");
        dossierMedicalSouadUmbert.setPraticienId("185db10b-2795-47d1-ad74-dbc2547e90c4");
        dossierMedicalSouadUmbert.getVisites().add(visiteMedicale);
        dossierMedicauxDatabase.add(dossierMedicalSouadUmbert);
    }


    public DossierMedical getDossierMedical(String praticienId, String patientId) {
        return dossierMedicauxDatabase.stream()
                .filter(dm -> dm.getPraticienId().equals(praticienId) && dm.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null );
    }

    public DossierMedical getDossierMedical(String dossierMedicalId) {
        return dossierMedicauxDatabase.stream()
                .filter(dm -> dm.getId().equals(dossierMedicalId))
                .findFirst()
                .orElse(null);
    }

    public List<DossierMedical> getAllDossierMedical() {
        return dossierMedicauxDatabase;
    }


    public void ajoutDossierMedical(DossierMedical dossierMedical) {
        dossierMedicauxDatabase.add(dossierMedical);
    }

    public void ajouterVisiteToDossierMedical(String dossierMedicalId, VisiteMedicale visiteMedicale) {
        DossierMedical dossierMedical = getDossierMedical(dossierMedicalId);
        dossierMedical.getVisites().add(visiteMedicale);
    }
}
