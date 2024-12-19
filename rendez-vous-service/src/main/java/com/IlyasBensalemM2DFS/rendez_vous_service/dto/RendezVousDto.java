package com.IlyasBensalemM2DFS.rendez_vous_service.dto;

import com.google.api.client.util.DateTime;

public class RendezVousDto {

    private String patientId;

    private String praticienId;

    private DateTime startTime;

    private DateTime endTime;

    public RendezVousDto() {
    }

    public String getPraticienId() {
        return praticienId;
    }

    public void setPraticienId(String praticienId) {
        this.praticienId = praticienId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

}
