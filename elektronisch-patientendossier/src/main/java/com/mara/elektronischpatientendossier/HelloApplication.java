package com.mara.elektronischpatientendossier;

import com.mara.elektronischpatientendossier.database.NotitieDatabase;
import com.mara.elektronischpatientendossier.database.PatientDatabase;
import com.mara.elektronischpatientendossier.database.StatementFactory;
import com.mara.elektronischpatientendossier.models.Notitie;
import com.mara.elektronischpatientendossier.models.Patient;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    HBox root = new HBox();
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        StatementFactory.getInstance().createStatement();

        root.setPadding(new Insets(40, 40,40, 40));
        root.setSpacing(40);
        navbar(root);
        hoofdscherm(root);

    }

    public void navbar(HBox pane){
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
        pane.getChildren().add(nav);
    }


    public void hoofdscherm(HBox pane) {
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

        pane.getChildren().addAll(lijstPatient);

        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // Retrieve the selected patient from the TableView
                Patient selectedPatient = tableView.getSelectionModel().getSelectedItem();

                // Retrieve the complete information of the selected patient from the database
                Patient completePatient = patientDatabase.getPatient(selectedPatient.getId());

                if (completePatient != null) {
                    // Display the complete information of the selected patient
                    System.out.println(completePatient.toString());// Replace this with your own logic to display the information
                    pane.getChildren().clear();
                    navbar(root);
                    pane.getChildren().add(tableView);
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

        btnDeleteNote.setOnAction(actionEvent -> {
            Notitie selectedNote = tableNote.getSelectionModel().getSelectedItem();
            noteDeletePopup(selectedNote);
            System.out.println("hier");
        });

        btnNewNote.setOnAction(actionEvent -> {
            NotitieDatabase ndb = new NotitieDatabase();
            Notitie notie = noteAanmaakPopup(p);
            ndb.opslaanNotitie(notie);
        });

        btnEditNote.setOnAction(actionEvent -> {
            NotitieDatabase ndb = new NotitieDatabase();
            Notitie noteAanpas = noteAanpasPopup(p);
            ndb.updateNotitie(noteAanpas);
        });

        buttons.getChildren().addAll(btnNewPatient, btnEditPatient, btnDeletePatient, btnNewNote, btnEditNote, btnDeleteNote);
        patientDetails.getChildren().addAll(patientgegevens, buttons);
        patientDetails.setSpacing(20);

        return patientDetails;
    }

    public void noteDeletePopup(Notitie not) {
        Dialog dialog = new Dialog<>();
        dialog.setTitle("BEVESTIGING");
        dialog.setHeaderText("BEVESTIG VERWIJDEREN");

        GridPane bevestig = new GridPane();

        ButtonType bevestigJaType = new ButtonType("BEVESTIG", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(bevestigJaType);
        Node bevestigJa = dialog.getDialogPane().lookupButton(bevestigJaType);

        ButtonType bevestigNeeType = new ButtonType("ANNULEER", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(bevestigNeeType);
        Node bevestigNee = dialog.getDialogPane().lookupButton(bevestigNeeType);


        bevestigJa.addEventFilter(ActionEvent.ACTION, event -> {
            NotitieDatabase ndb = new NotitieDatabase();
            ndb.deleteNotitie(not);
        });

        bevestigNee.addEventFilter(ActionEvent.ACTION, event -> {

        });

        dialog.showAndWait();
    }

    public Notitie noteAanpasPopup(Patient pat){
        String notitie_text = null;
        String datum = null;
        Integer behandelaar_id = null;
        Integer id = null;
        Integer patient_id = pat.getId();

        Dialog dialog = new Dialog<>();
        dialog.setTitle("Notitie aanpassen");
        dialog.setHeaderText("Voer gegevens in");

        GridPane invul = new GridPane();
        Text datumT = new Text("Datum: ");
        invul.add(datumT, 0, 0);
        TextField datumTF = new TextField();
        invul.add(datumTF, 1, 0);

        Text notitieT = new Text("Vul in: ");
        invul.add(notitieT, 0, 1);
        TextField notitieTF = new TextField();
        invul.add(notitieTF, 1, 1);

        Text behandelaar_idT = new Text("Uw behandelaar id: ");
        invul.add(behandelaar_idT, 0, 2);
        TextField behandelaar_idTF = new TextField();
        invul.add(behandelaar_idTF, 1, 2);

        ButtonType okButtonType = new ButtonType("Verder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);

        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (
                // kijken of velden leeg zijn
                    datumTF.getText().isEmpty() ||
                            notitieTF.getText().isEmpty() ||
                            behandelaar_idTF.getText().isEmpty()
            ) {
                // foutmelding als niet alles is ingevuld
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Vul alle gegevens in!");
                errorAlert.setContentText("Controleer of u alle gegevens heeft ingevuld");
                errorAlert.showAndWait();

                event.consume();
                return;
            }

            try {
                Integer.parseInt(behandelaar_idTF.getText());
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("FOUT");
                errorAlert.setContentText("Vul geldig id in");
                errorAlert.showAndWait();
                event.consume();
                return;
            }

        });

        dialog.getDialogPane().setContent(invul);

        dialog.showAndWait();
        behandelaar_id = Integer.parseInt(behandelaar_idTF.getText());
        notitie_text = notitieTF.getText();
        datum = datumTF.getText();

        Notitie notitie = new Notitie(id, notitie_text, datum, behandelaar_id, patient_id);
        return notitie;
    }


    public Notitie noteAanmaakPopup(Patient pat){
        String notitie_text = null;
        String datum = null;
        Integer behandelaar_id = null;
        Integer id = null;
        Integer patient_id = pat.getId();

        Dialog dialog = new Dialog<>();
        dialog.setTitle("Nieuwe notitie aanmaken");
        dialog.setHeaderText("Voer gegevens in");

        GridPane invul = new GridPane();
        Text datumT = new Text("Datum: ");
        invul.add(datumT, 0, 0);
        TextField datumTF = new TextField();
        invul.add(datumTF, 1, 0);

        Text notitieT = new Text("Vul in: ");
        invul.add(notitieT, 0, 1);
        TextField notitieTF = new TextField();
        invul.add(notitieTF, 1, 1);

        Text behandelaar_idT = new Text("Uw behandelaar id: ");
        invul.add(behandelaar_idT, 0, 2);
        TextField behandelaar_idTF = new TextField();
        invul.add(behandelaar_idTF, 1, 2);

        ButtonType okButtonType = new ButtonType("Verder", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (
                // kijken of velden leeg zijn
                            datumTF.getText().isEmpty() ||
                            notitieTF.getText().isEmpty() ||
                            behandelaar_idTF.getText().isEmpty()
            ) {
                // foutmelding als niet alles is ingevuld
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Vul alle gegevens in!");
                errorAlert.setContentText("Controleer of u alle gegevens heeft ingevuld");
                errorAlert.showAndWait();

                event.consume();
                return;
            }

            try {
                Integer.parseInt(behandelaar_idTF.getText());
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("FOUT");
                errorAlert.setContentText("Vul geldig id in");
                errorAlert.showAndWait();
                event.consume();
                return;
            }

        });

        dialog.getDialogPane().setContent(invul);

        dialog.showAndWait();
        behandelaar_id = Integer.parseInt(behandelaar_idTF.getText());
        notitie_text = notitieTF.getText();
        datum = datumTF.getText();

        Notitie notitie = new Notitie(id, notitie_text, datum, behandelaar_id, patient_id);
        return notitie;
    }

    public static void main(String[] args) {
        launch();
    }
}