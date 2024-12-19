package com.IlyasBensalemM2DFS.rendez_vous_service.model;

import com.google.api.client.util.DateTime;

import java.util.Objects;
import java.util.UUID;

public class RendezVous {

    private String id;

    private String patientId;

    private String praticienId;

    private DateTime startTime;

    private DateTime endTime;

    private String googleEventId;

    private String status;


    public RendezVous() {
        this.id = UUID.randomUUID().toString();
    }

    public RendezVous(String id, String patientId, String praticienId, DateTime startTime, DateTime endTime, String googleEventId, String status) {
        this.id = id;
        this.patientId = patientId;
        this.praticienId = praticienId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.googleEventId = googleEventId;
        this.status = status;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPraticienId() {
        return praticienId;
    }

    public void setPraticienId(String praticienId) {
        this.praticienId = praticienId;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getGoogleEventId() {
        return googleEventId;
    }

    public void setGoogleEventId(String googleEventId) {
        this.googleEventId = googleEventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RendezVous that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
