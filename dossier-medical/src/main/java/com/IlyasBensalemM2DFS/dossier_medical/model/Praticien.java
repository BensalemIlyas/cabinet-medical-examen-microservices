package com.IlyasBensalemM2DFS.dossier_medical.model;


import java.util.Objects;

public class Praticien {

    private String id;

    private String nom;

    private String prenom;

    private String specialite;

    private String email;

    private String telephone;

    public Praticien() {
    }

    public Praticien(String id, String nom, String prenom, String specialite, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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
    public boolean equals(Object o) {
        if (!(o instanceof Praticien praticien)) return false;
        return Objects.equals(id, praticien.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}