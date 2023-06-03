package com.mara.elektronischpatientendossier.models;

public class Patient {
    private Integer id;
    private String voornaam;
    private String achternaam;
    private String geboortedatum;
    private String telefoonnummer;
    private String emailadres;
    private String adres;
    private String diagnose;
    private String medicijnen;
    private String behandelend_arts;
    private Integer behandeling;
    private Integer behandelaar_id;

    public Patient(Integer id, String voornaam, String achternaam, String geboortedatum, String telefoonnummer, String emailadres, String adres, String diagnose, String medicijnen, String behandelend_arts, Integer behandeling, Integer behandelaar_id) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.telefoonnummer = telefoonnummer;
        this.emailadres = emailadres;
        this.adres = adres;
        this.diagnose = diagnose;
        this.medicijnen = medicijnen;
        this.behandelend_arts = behandelend_arts;
        this.behandeling = behandeling;
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

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
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

    public Integer getBehandeling() {
        return behandeling;
    }

    public void setBehandeling(Integer behandeling) {
        this.behandeling = behandeling;
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
                ", telefoonnummer='" + telefoonnummer + '\'' +
                ", emailadres='" + emailadres + '\'' +
                ", adres='" + adres + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", medicijnen='" + medicijnen + '\'' +
                ", behandelend_arts='" + behandelend_arts + '\'' +
                ", behandling=" + behandeling +
                ", behandelaar_id=" + behandelaar_id +
                '}';
    }
}
