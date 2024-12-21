package com.IlyasBensalemM2DFS.Api_Gateway.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DossierMedical {

    private String id;

    private String patientId;

    private String praticienId;

    private List<VisiteMedicale> visites = new ArrayList<>();

    public DossierMedical() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPraticienId() {
        return praticienId;
    }

    public void setPraticienId(String praticienId) {
        this.praticienId = praticienId;
    }

    public List<VisiteMedicale> getVisites() {
        return visites;
    }

    public void setVisites(List<VisiteMedicale> visites) {
        this.visites = visites;
    }

}
