package com.mara.elektronischpatientendossier.database;

import com.mara.elektronischpatientendossier.models.Behandelaar;
import com.mara.elektronischpatientendossier.models.Behandeling;
import com.mara.elektronischpatientendossier.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BehandelaarDatabase {

    public List<Behandelaar> getAllBehandelaren() {
        Statement stm = StatementFactory.getInstance().createStatement();

        List<Behandelaar> behandelaarList = new ArrayList<>();

        // query behandelaar ophalen uit database
        String query = String.format("SELECT * FROM behandelaar");

        try {
            ResultSet resultaat = stm.executeQuery(query);
            while (resultaat.next()) {
                // een lijst aan maken zodat meerdere behandelaren worden weergeven
                Integer id = resultaat.getInt("id");
                String voornaam = resultaat.getString("voornaam");
                String achternaam = resultaat.getString("achternaam");
                String geboortedatum = resultaat.getString("geboortedatum");

                Behandelaar beh = new Behandelaar(id, voornaam, achternaam, geboortedatum);
                behandelaarList.add(beh);
            }

            return behandelaarList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Behandelaar getBehandelaar(Integer idM) {
        Statement stm = StatementFactory.getInstance().createStatement();

        // query behandelaar ophalen uit database
        String query = String.format(
                "SELECT id, voornaam, achternaam, geboortedatum FROM behandelaar WHERE id = %d;",
                idM);

        try {
            ResultSet resultaat = stm.executeQuery(query);
            resultaat.next(); //dan gaat hij naar het eerste resultaat
            Integer id = resultaat.getInt("id");
            String voornaam = resultaat.getString("voornaam");
            String achternaam = resultaat.getString("achternaam");
            String geboortedatum = resultaat.getString("geboortedatum");


            Behandelaar behandelaar = new Behandelaar(id, voornaam, achternaam, geboortedatum);
            return behandelaar;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;



    }

    public void opslaanBehandelaar(Behandelaar behandelaar) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om dingen op te slaan in juiste kolommen in database
        String query = String.format("INSERT INTO behandelaar VALUES (%s, '%s', '%s');",
                behandelaar.getVoornaam(),
                behandelaar.getAchternaam(),
                behandelaar.getGeboortedatum()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void updateBehandelaar (Behandelaar behandelaar) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om behandelaargegevens te updaten
        String query = String.format("UPDATE behandelaar SET voornaam = '%s', achternaam = '%s', geboortedatum = '%s') WHERE id = %d;",
                behandelaar.getVoornaam(),
                behandelaar.getAchternaam(),
                behandelaar.getGeboortedatum(),
                behandelaar.getId()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }


    public void deleteBehandelaar (Behandelaar behandelaar) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om behandeling te verwijderen
        String query = String.format("DELETE * FROM behandelaar WHERE id = %d;",
                behandelaar.getId()
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
