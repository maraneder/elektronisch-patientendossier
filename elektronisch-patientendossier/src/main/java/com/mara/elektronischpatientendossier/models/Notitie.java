package com.mara.elektronischpatientendossier.models;

public class Notitie {
    private Integer id;
    private String notitie_text;
    private String datum;
    private Integer behandelaar_id;
    private Integer patient_id;



    public Notitie(Integer id, String notitie_text, String datum, Integer behandelaar_id, Integer patient_id) {
        this.id = id;
        this.notitie_text = notitie_text;
        this.datum = datum;
        this.behandelaar_id = behandelaar_id;
        this.patient_id = patient_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotitie_text() {
        return notitie_text;
    }

    public void setNotitie_text(String notitie_text) {
        this.notitie_text = notitie_text;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getBehandelaar_id() {
        return behandelaar_id;
    }

    public void setBehandelaar_id(Integer behandelaar_id) {
        this.behandelaar_id = behandelaar_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Notitie{" +
                "id=" + id +
                ", notitie_text='" + notitie_text + '\'' +
                ", datum='" + datum + '\'' +
                ", behandelaar_id=" + behandelaar_id +
                ", patient_id=" + patient_id +
                '}';
    }

}
