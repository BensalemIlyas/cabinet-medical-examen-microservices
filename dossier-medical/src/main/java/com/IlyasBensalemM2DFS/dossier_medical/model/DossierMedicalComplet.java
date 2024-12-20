package com.IlyasBensalemM2DFS.dossier_medical.model;

import java.util.List;

public class DossierMedicalComplet {

    private String id;

    private Patient patient;

    private Praticien praticien;

    private List<VisiteMedicale> visites;

    public DossierMedicalComplet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<VisiteMedicale> getVisites() {
        return visites;
    }

    public void setVisites(List<VisiteMedicale> visites) {
        this.visites = visites;
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
    }
}
