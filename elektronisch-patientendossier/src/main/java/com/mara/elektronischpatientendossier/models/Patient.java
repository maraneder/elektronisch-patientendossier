package com.mara.elektronischpatientendossier.models;

public class Patient {
    private Integer id;
    private String voornaam;
    private String achternaam;
    private String geboortedatum;
    private String telefoonnumer;
    private String emailadres;
    private String adres;
    private String diagnose;
    private String medicijnen;
    private String behandelend_arts;
    private Integer behandling;
    private Integer behandelaar_id;

    public Patient(Integer id, String voornaam, String achternaam, String geboortedatum, String telefoonnumer, String emailadres, String adres, String diagnose, String medicijnen, String behandelend_arts, Integer behandling, Integer behandelaar_id) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.telefoonnumer = telefoonnumer;
        this.emailadres = emailadres;
        this.adres = adres;
        this.diagnose = diagnose;
        this.medicijnen = medicijnen;
        this.behandelend_arts = behandelend_arts;
        this.behandling = behandling;
        this.behandelaar_id = behandelaar_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getTelefoonnumer() {
        return telefoonnumer;
    }

    public void setTelefoonnumer(String telefoonnumer) {
        this.telefoonnumer = telefoonnumer;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getMedicijnen() {
        return medicijnen;
    }

    public void setMedicijnen(String medicijnen) {
        this.medicijnen = medicijnen;
    }

    public String getBehandelend_arts() {
        return behandelend_arts;
    }

    public void setBehandelend_arts(String behandelend_arts) {
        this.behandelend_arts = behandelend_arts;
    }

    public Integer getBehandling() {
        return behandling;
    }

    public void setBehandling(Integer behandling) {
        this.behandling = behandling;
    }

    public Integer getBehandelaar_id() {
        return behandelaar_id;
    }

    public void setBehandelaar_id(Integer behandelaar_id) {
        this.behandelaar_id = behandelaar_id;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum='" + geboortedatum + '\'' +
                ", telefoonnumer='" + telefoonnumer + '\'' +
                ", emailadres='" + emailadres + '\'' +
                ", adres='" + adres + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", medicijnen='" + medicijnen + '\'' +
                ", behandelend_arts='" + behandelend_arts + '\'' +
                ", behandling=" + behandling +
                ", behandelaar_id=" + behandelaar_id +
                '}';
    }
}
