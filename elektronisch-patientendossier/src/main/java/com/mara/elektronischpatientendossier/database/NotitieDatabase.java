package com.mara.elektronischpatientendossier.database;

import com.mara.elektronischpatientendossier.models.Notitie;
import com.mara.elektronischpatientendossier.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotitieDatabase {

    public List<Notitie> getNotitie (Patient patient) {
        Statement stm = StatementFactory.getInstance().createStatement();

        List<Notitie> notitieList = new ArrayList<>();

        // query notitie ophalen uit database
        String query = String.format(
                "SELECT * FROM notitie WHERE patient_id = %d",
                patient.getId()
                );

        try {
            ResultSet resultaat = stm.executeQuery(query);
            while (resultaat.next()) {
                // een lijst aan maken zodat meerdere notities worden weergeven
                Integer id = resultaat.getInt("id");
                String notitie_text = resultaat.getString("notitie_text");
                String datum = resultaat.getString("datum");
                Integer behandelaar_id = resultaat.getInt("behandelaar_id");
                Integer patient_id = resultaat.getInt("patient_id");

                Notitie notitie = new Notitie(id, notitie_text, datum, behandelaar_id, patient_id);
                notitieList.add(notitie);
            }

            return notitieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void opslaanNotitie (Notitie notitie) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om dingen op te slaan in juiste kolommen in database
        String query = String.format("INSERT INTO notitie (notitie_text, datum, behandelaar_id, patient_id) VALUES ('%s', '%s', %d, %d);",
                notitie.getNotitie_text(),
                notitie.getDatum(),
                notitie.getBehandelaar_id(),
                notitie.getPatient_id()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void updateNotitie (Notitie notitie) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om notities te updaten
        String query = String.format("UPDATE notitie SET notitie_text = '%s', datum = '%s', behandelaar_id = %d, patient_id = %d WHERE id = %d;",
                notitie.getNotitie_text(),
                notitie.getDatum(),
                notitie.getBehandelaar_id(),
                notitie.getPatient_id(),
                notitie.getId()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void deleteNotitie (Notitie notitie) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om notitie te verwijderen
        String query = String.format("DELETE FROM notitie WHERE id = %d;",
                notitie.getId()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

}
