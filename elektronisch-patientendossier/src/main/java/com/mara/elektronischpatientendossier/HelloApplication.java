package com.mara.elektronischpatientendossier;

import com.mara.elektronischpatientendossier.database.NotitieDatabase;
import com.mara.elektronischpatientendossier.database.PatientDatabase;
import com.mara.elektronischpatientendossier.database.StatementFactory;
import com.mara.elektronischpatientendossier.models.Notitie;
import com.mara.elektronischpatientendossier.models.Patient;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HBox root = new HBox();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        StatementFactory.getInstance().createStatement();

        root.setPadding(new Insets(40, 40,40, 40));
        root.setSpacing(40);
        hoofdscherm(root);

    }


    public void hoofdscherm(HBox pane) {

        // navigatie links
        Button patienten = new Button("PATIENTEN");
        Button behandelingen = new Button("BEHANDELINGEN");
        Button behandelaren = new Button("BEHANDELAREN");

        patienten.setMinSize(120, 20);
        behandelingen.setMinSize(120, 20);
        behandelaren.setMinSize(120, 20);

        VBox nav = new VBox();
        nav.getChildren().add(patienten);
        nav.getChildren().add(behandelingen);
        nav.getChildren().add(behandelaren);

        nav.setSpacing(20);

        // lijst patienten

        VBox lijstPatient = new VBox();
        TableView<Patient> tableView = new TableView<>();
        TableColumn<Patient, String> firstNameColumn = new TableColumn<>("Voornaam");
        TableColumn<Patient, String> lastNameColumn = new TableColumn<>("Achternaam");

        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVoornaam()));
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAchternaam()));


        tableView.getColumns().addAll(firstNameColumn, lastNameColumn);

        PatientDatabase patientDatabase = new PatientDatabase();
        List<Patient> patients = patientDatabase.getAllPatients(); // Fetch all patients from the database using the instance of PatientDatabase
        tableView.setItems(FXCollections.observableArrayList(patients));

        tableView.setPrefHeight(620);
        lijstPatient.getChildren().add(tableView);

        pane.getChildren().addAll(nav, lijstPatient);

        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // Retrieve the selected patient from the TableView
                Patient selectedPatient = tableView.getSelectionModel().getSelectedItem();

                // Retrieve the complete information of the selected patient from the database
                Patient completePatient = patientDatabase.getPatient(selectedPatient.getId());

                if (completePatient != null) {
                    // Display the complete information of the selected patient
                    System.out.println(completePatient.toString());// Replace this with your own logic to display the information
                    pane.getChildren().add(laatPatientZien(completePatient));
                }
            }
        });





    }


    public Pane laatPatientZien(Patient p){
        HBox patientDetails = new HBox();

        VBox patientgegevens = new VBox();

        GridPane patientd = new GridPane();
        Text voornaamLabel = new Text("Voornaam: ");
        Text voornaamText = new Text(p.getVoornaam());
        patientd.add(voornaamLabel, 0, 0);
        patientd.add(voornaamText, 1, 0);
        Text achternaamLabel = new Text("Achternaam: ");
        Text achternaamText = new Text(p.getAchternaam());
        patientd.add(achternaamLabel, 2, 0);
        patientd.add(achternaamText, 3, 0);
        Text geboortedatumLabel = new Text("Geboortedatum: ");
        Text geboortedatumText = new Text(p.getGeboortedatum());
        patientd.add(geboortedatumLabel, 0, 1);
        patientd.add(geboortedatumText,1, 1);
        Text telefoonnummerLabel = new Text("Telefoonnummer: ");
        Text telefoonnumerText = new Text(p.getTelefoonnumer());
        patientd.add(telefoonnummerLabel, 2, 1);
        patientd.add(telefoonnumerText, 3, 1);
        Text emailadresLabel = new Text("Emailadres: ");
        Text emailadresText = new Text(p.getEmailadres());
        patientd.add(emailadresLabel, 0, 2);
        patientd.add(emailadresText, 1, 2);
        Text adresLabel = new Text("Adres: ");
        Text adresText = new Text(p.getAdres());
        patientd.add(adresLabel, 2, 2);
        patientd.add(adresText, 3, 2);
        Text diagnoseLabel = new Text("Diagnose: ");
        Text diagnoseText = new Text(p.getDiagnose());
        patientd.add(diagnoseLabel, 0, 3);
        patientd.add(diagnoseText, 1, 3);
        Text medicijnenLabel = new Text("Medicijnen: ");
        Text medicijnenText = new Text(p.getMedicijnen());
        patientd.add(medicijnenLabel, 2, 3);
        patientd.add(medicijnenText, 3, 3);
        Text behandelend_artsLabel = new Text("Behandelend Arts: ");
        Text behandelend_artsText = new Text(p.getBehandelend_arts());
        patientd.add(behandelend_artsLabel, 0, 4);
        patientd.add(behandelend_artsText, 1, 4);
        Text behandelingLabel = new Text("Behandeling: ");
        Text behandelingText = new Text(p.getBehandeling().toString());
        patientd.add(behandelingLabel, 2, 4);
        patientd.add(behandelingText, 3, 4);
        Text behandelaar_idLabel = new Text("Behandelaar: ");
        Text behandelaar_idText = new Text(p.getBehandelaar_id().toString());
        patientd.add(behandelaar_idLabel, 0, 5);
        patientd.add(behandelaar_idText, 1, 5);
        patientd.setHgap(10);
        patientd.setVgap(5);



        TableView<Notitie> tableNote = new TableView<>();
        TableColumn<Notitie, String> datum = new TableColumn<>("Datum");
        TableColumn<Notitie, String> behandelaar_id = new TableColumn<>("Behandelaar");
        TableColumn<Notitie, String> notitie_text = new TableColumn<>("Notitie");

        datum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatum()));
        behandelaar_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBehandelaar_id().toString()));
        notitie_text.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotitie_text()));

        tableNote.setPrefHeight(700);
        tableNote.getColumns().addAll(datum, behandelaar_id, notitie_text);

        NotitieDatabase noteDatabase = new NotitieDatabase();
        List<Notitie> note = noteDatabase.getNotitie(p); // Fetch all notes from the database using the instance of PatientDatabase
        tableNote.setItems(FXCollections.observableArrayList(note));

        patientgegevens.getChildren().addAll(patientd, tableNote);
        patientgegevens.setSpacing(20);


        VBox buttons = new VBox();

        Button btnNewPatient = new Button("Patiënt Toevoegen");
        Button btnEditPatient = new Button("Patiënt Aanpassen");
        Button btnDeletePatient = new Button("Patiënt Verwijderen");
        Button btnNewNote = new Button("Notitie Toevoegen");
        Button btnEditNote = new Button("Notitie Aanpassen");
        Button btnDeleteNote = new Button("Notitie Verwijderen");

        btnNewPatient.setMinSize(120, 20);
        btnEditPatient.setMinSize(120, 20);
        btnDeletePatient.setMinSize(120, 20);
        btnNewNote.setMinSize(120, 20);
        btnEditNote.setMinSize(120, 20);
        btnDeleteNote.setMinSize(120, 20);
        buttons.setSpacing(20);

        buttons.getChildren().addAll(btnNewPatient, btnEditPatient, btnDeletePatient, btnNewNote, btnEditNote, btnDeleteNote);
        patientDetails.getChildren().addAll(patientgegevens, buttons);
        patientDetails.setSpacing(20);

        return patientDetails;
    }


    public static void main(String[] args) {
        launch();
    }
}