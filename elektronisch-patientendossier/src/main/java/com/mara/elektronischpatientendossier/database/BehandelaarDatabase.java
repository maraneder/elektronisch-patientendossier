package com.mara.elektronischpatientendossier.database;

import com.mara.elektronischpatientendossier.models.Behandelaar;
import com.mara.elektronischpatientendossier.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BehandelaarDatabase {

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
