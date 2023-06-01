package com.mara.elektronischpatientendossier.models;

public class Behandeling {
    private Integer id;
    private String naam;
    private String beschrijving;

    public Behandeling(Integer id, String naam, String beschrijving) {
        this.id = id;
        this.naam = naam;
        this.beschrijving = beschrijving;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override
    public String toString() {
        return "Behandeling{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                '}';
    }

}
