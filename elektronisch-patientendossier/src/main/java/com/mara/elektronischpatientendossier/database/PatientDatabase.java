package com.mara.elektronischpatientendossier.database;

import com.mara.elektronischpatientendossier.models.Notitie;
import com.mara.elektronischpatientendossier.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabase {
    public Patient getPatient(Integer idM) {
        Statement stm = StatementFactory.getInstance().createStatement();

        // query patient ophalen uit database
        String query = String.format(
                "SELECT id, voornaam, achternaam, geboortedatum, telefoonnummer, emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id FROM patiënt WHERE id = %d LIMIT 1;",
        idM);

        try {
            ResultSet resultaat = stm.executeQuery(query);
            resultaat.next(); //dan gaat hij naar het eerste resultaat
            Integer id = resultaat.getInt("id");
            String voornaam = resultaat.getString("voornaam");
            String achternaam = resultaat.getString("achternaam");
            String geboortedatum = resultaat.getString("geboortedatum");
            String telefoonnummer = resultaat.getString("telefoonnummer");
            String emailadres = resultaat.getString("emailadres");
            String adres = resultaat.getString("adres");
            String diagnose = resultaat.getString("diagnose");
            String medicijnen = resultaat.getString("medicijnen");
            String behandelend_arts = resultaat.getString("behandelend_arts");
            Integer behandeling = resultaat.getInt("behandeling");
            Integer behandelaar_id = resultaat.getInt("behandelaar_id");


            Patient patient = new Patient(id, voornaam, achternaam, geboortedatum, telefoonnummer,
                    emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id);


            return patient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;



    }


    public List<Patient> getAllPatients () {
        Statement stm = StatementFactory.getInstance().createStatement();

        List<Patient> patientList = new ArrayList<>();

        // query notitie ophalen uit database
        String query = String.format(
                "SELECT * FROM patiënt"
        );

        try {
            ResultSet resultaat = stm.executeQuery(query);
            while (resultaat.next()) {
                // een lijst aan maken zodat meerdere notities worden weergeven
                Integer id = resultaat.getInt("id");
                String voornaam = resultaat.getString("voornaam");
                String achternaam = resultaat.getString("achternaam");
                String geboortedatum = resultaat.getString("geboortedatum");
                String telefoonnummer = resultaat.getString("telefoonnummer");
                String emailadres = resultaat.getString("emailadres");
                String adres = resultaat.getString("adres");
                String diagnose = resultaat.getString("diagnose");
                String medicijnen = resultaat.getString("medicijnen");
                String behandelend_arts = resultaat.getString("behandelend_arts");
                Integer behandeling = resultaat.getInt("behandeling");
                Integer behandelaar_id = resultaat.getInt("behandelaar_id");

                Patient patientP = new Patient(id, voornaam, achternaam, geboortedatum, telefoonnummer, emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id);
                patientList.add(patientP);
            }

            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void opslaanPatient(Patient patient) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om dingen op te slaan in juiste kolommen in database
        String query = String.format("INSERT INTO patiënt (voornaam, achternaam, geboortedatum, telefoonnummer, emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d);",
                patient.getVoornaam(),
                patient.getAchternaam(),
                patient.getGeboortedatum(),
                patient.getTelefoonnummer(),
                patient.getEmailadres(),
                patient.getAdres(),
                patient.getDiagnose(),
                patient.getMedicijnen(),
                patient.getBehandelend_arts(),
                patient.getBehandeling(),
                patient.getBehandelaar_id()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public void updatePatient (Patient patient) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om patientgegevens te updaten
        String query = String.format("UPDATE patiënt SET voornaam = '%s', achternaam = '%s', geboortedatum = '%s', telefoonnummer = '%s', emailadres = '%s', adres = '%s', diagnose = '%s', medicijnen = '%s', behandelend_arts = '%s', behandeling = %d, behandelaar_id = %d WHERE id = %d;",
                patient.getVoornaam(),
                patient.getAchternaam(),
                patient.getGeboortedatum(),
                patient.getTelefoonnummer(),
                patient.getEmailadres(),
                patient.getAdres(),
                patient.getDiagnose(),
                patient.getMedicijnen(),
                patient.getBehandelend_arts(),
                patient.getBehandeling(),
                patient.getBehandelaar_id(),
                patient.getId()
        );

        System.out.println(query);

        try {
            stm.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }


    public void deletePatient (Patient patient) {
        Statement stm = StatementFactory.getInstance().createStatement();

        //query om patient te verwijderen
        String query = String.format("DELETE FROM patiënt WHERE id = %d;",
            patient.getId()
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
