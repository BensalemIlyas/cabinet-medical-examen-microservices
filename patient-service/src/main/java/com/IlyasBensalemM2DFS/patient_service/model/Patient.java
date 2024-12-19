package com.IlyasBensalemM2DFS.patient_service.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Détails d'un patient")
public class Patient {

    @Schema(description = "Identifiant unique du patient",requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "Nom du patient", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    @Schema(description = "Prénom du patient",  requiredMode = Schema.RequiredMode.REQUIRED)
    private String prenom;

    @Schema(description = "Date de naissance du patient", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dateNaissance;

    @Schema(description = "Email du patient", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Numéro de téléphone du patient", requiredMode = Schema.RequiredMode.REQUIRED)
    private String telephone;

    public Patient() {
    }

    public Patient(String id, String nom, String prenom, String dateNaissance, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Patient patient)) return false;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
