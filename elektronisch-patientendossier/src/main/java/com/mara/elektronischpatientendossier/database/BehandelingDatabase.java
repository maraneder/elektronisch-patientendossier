package com.mara.elektronischpatientendossier.database;

import com.mara.elektronischpatientendossier.models.Behandeling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BehandelingDatabase {
    public Behandeling getBehandeling(Integer idM) {
        Statement stm = StatementFactory.getInstance().createStatement();

        // query behandeling ophalen uit database
        String query = String.format(
                "SELECT id, naam, beschrijving FROM behandeling WHERE id = %d;",
                idM);

        try {
            ResultSet resultaat = stm.executeQuery(query);
            resultaat.next(); //dan gaat hij naar het eerste resultaat
            Integer id = resultaat.getInt("id");
            String naam = resultaat.getString("naam");
            String beschrijving = resultaat.getString("beschrijving");


            Behandeling behandeling = new Behandeling(id, naam, beschrijving);
            return behandeling;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;



    }

    public void opslaanBehandeling (Behandeling behandeling) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om dingen op te slaan in juiste kolommen in database
        String query = String.format("INSERT INTO behandeling VALUES (%d, '%s', '%s');",
                behandeling.getId(),
                behandeling.getNaam(),
                behandeling.getBeschrijving()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void updateBehandeling (Behandeling behandeling) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om behandelinggegevens te updaten
        String query = String.format("UPDATE behandeling SET naam = '%s', beschrijving = '%s') WHERE id = %d;",
                behandeling.getNaam(),
                behandeling.getBeschrijving(),
                behandeling.getId()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }


    public void deleteBehandeling (Behandeling behandeling) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om behandeling te verwijderen
        String query = String.format("DELETE * FROM behandeling WHERE id = %d;",
                behandeling.getId()
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
