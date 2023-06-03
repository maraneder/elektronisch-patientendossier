package com.mara.elektronischpatientendossier;

import com.mara.elektronischpatientendossier.database.*;
import com.mara.elektronischpatientendossier.models.Behandelaar;
import com.mara.elektronischpatientendossier.models.Behandeling;
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
    private boolean hoofdschermDisplayed = false; // bool om bij te houden of hoofdscherm al is aangemaakt
    private boolean behandelingenSchermDisplayed = false;
    private boolean behandelaarSchermDisplayed = false;
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        StatementFactory.getInstance().createStatement();

        root.setPadding(new Insets(40, 40,40, 40));
        root.setSpacing(40);

        //while (login() == false);

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

        // functie om te kijken of hoofdscherm() al bestaat, zodat er niet meerdere worden aangemaakt
        patienten.setOnAction(actionEvent -> {
            if (!hoofdschermDisplayed) {
                pane.getChildren().clear();
                navbar(root);
                hoofdscherm(pane);
                hoofdschermDisplayed = true;
                behandelingenSchermDisplayed = false; //zet behandelingenscherm op false zodat het weer klopt en als je naar behandelingenscherm gaat, je die te zien krijgt
                behandelaarSchermDisplayed = false;
            }
        });

        behandelingen.setOnAction(actionEvent -> {
            if (!behandelingenSchermDisplayed) {
                pane.getChildren().clear();
                navbar(root);
                behandelingenscherm(pane);
                behandelingenSchermDisplayed = true;
                hoofdschermDisplayed = false; // zet hoofdscherm op false
                behandelaarSchermDisplayed = false;
            }
        });

        behandelaren.setOnAction(actionEvent -> {
            if (!behandelaarSchermDisplayed) {
                pane.getChildren().clear();
                navbar(root);
                behandelaarscherm(pane);
                behandelaarSchermDisplayed = true;
                hoofdschermDisplayed = false; // zet hoofdscherm op false
                behandelingenSchermDisplayed = false;
            }
        });
    }


    public void behandelaarscherm(HBox pane) {
        VBox lijstBehandelaren = new VBox();
        TableView<Behandelaar> tableBehandelaar = new TableView<>();
        TableColumn<Behandelaar, String> voornaamColumn = new TableColumn<>("Voornaam");
        TableColumn<Behandelaar, String> achternaamColumn = new TableColumn<>("Achternaam");
        TableColumn<Behandelaar, String> geboortedatumColumn = new TableColumn<>("Geboortedatum");

        voornaamColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVoornaam()));
        achternaamColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAchternaam()));
        geboortedatumColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGeboortedatum()));

        tableBehandelaar.getColumns().addAll(voornaamColumn, achternaamColumn, geboortedatumColumn);

        BehandelaarDatabase behandelaarDatabase = new BehandelaarDatabase();
        List<Behandelaar> behandelaars = behandelaarDatabase.getAllBehandelaren(); // Fetch all patients from the database using the instance of PatientDatabase
        tableBehandelaar.setItems(FXCollections.observableArrayList(behandelaars));

        tableBehandelaar.setPrefHeight(620);
        tableBehandelaar.setPrefWidth(900);
        lijstBehandelaren.getChildren().add(tableBehandelaar);

        pane.getChildren().addAll(lijstBehandelaren);
    }




    public void behandelingenscherm(HBox pane) {
        VBox lijstBehandelingen = new VBox();
        TableView<Behandeling> tableBehandeling = new TableView<>();
        TableColumn<Behandeling, String> naamColumn = new TableColumn<>("Naam");
        TableColumn<Behandeling, String> beschrijvingColumn = new TableColumn<>("Beschrijving");

        naamColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNaam()));
        beschrijvingColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBeschrijving()));


        tableBehandeling.getColumns().addAll(naamColumn, beschrijvingColumn);

        BehandelingDatabase behandelingDatabase = new BehandelingDatabase();
        List<Behandeling> behandelings = behandelingDatabase.getAllBehandelingen(); // Fetch all patients from the database using the instance of PatientDatabase
        tableBehandeling.setItems(FXCollections.observableArrayList(behandelings));

        tableBehandeling.setPrefHeight(620);
        tableBehandeling.setPrefWidth(900);
        lijstBehandelingen.getChildren().add(tableBehandeling);

        pane.getChildren().addAll(lijstBehandelingen);
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
        Text telefoonnumerText = new Text(p.getTelefoonnummer());
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

        btnDeletePatient.setOnAction(actionEvent -> {
            Patient selectedPatient = p; //geselecteerde patient binnenhalen
            patientDeletePopup(selectedPatient); // popup starten
        });

        btnEditPatient.setOnAction(actionEvent -> {
            Patient selectedPatient = p; //geselecteerde patient binnenhalen
            Patient updatePatient = patientAanpasPopup(selectedPatient); //roept patientpopup op
            if (updatePatient != null) { // checkt of de geupdate patient niet null is. zorgt ervoor dat de gebruiker ook veranderingen heeft gemaakt
                PatientDatabase pdb = new PatientDatabase();
                pdb.updatePatient(updatePatient); //stuurt aanpassing door naar de database
                System.out.println("Patient updated successfully!");
            }
        });

        btnNewPatient.setOnAction(actionEvent -> {
            PatientDatabase pdb = new PatientDatabase();
            Patient pat = patientAanmaakPopup();
            pdb.opslaanPatient(pat); //stuurt patient door naar database
        });

        btnDeleteNote.setOnAction(actionEvent -> {
            Notitie selectedNote = tableNote.getSelectionModel().getSelectedItem(); // selecteerd de notite die verwijderd moet worden
            noteDeletePopup(selectedNote); // laat notitie verwijderen

        });

        btnNewNote.setOnAction(actionEvent -> {
            NotitieDatabase ndb = new NotitieDatabase();
            Notitie notie = noteAanmaakPopup(p);
            ndb.opslaanNotitie(notie); // stuurt notitie door naar database
        });

        btnEditNote.setOnAction(actionEvent -> {
                Notitie selectedNote = tableNote.getSelectionModel().getSelectedItem(); //haalt geselecteerde notitie
                Notitie updatedNote = noteAanpasPopup(selectedNote); // opent popup

                if (updatedNote != null) { // checkt of hij niet null is en zorgt ervoor dat er ook aanpassingen worden gedaan.
                    NotitieDatabase ndb = new NotitieDatabase();
                    ndb.updateNotitie(updatedNote); //stuurt door naar database
                }

        });

        buttons.getChildren().addAll(btnNewPatient, btnEditPatient, btnDeletePatient, btnNewNote, btnEditNote, btnDeleteNote);
        patientDetails.getChildren().addAll(patientgegevens, buttons);
        patientDetails.setSpacing(20);

        return patientDetails;
    }


    public void patientDeletePopup(Patient pat) {
        Dialog dialog = new Dialog<>(); //popup scherm maken
        dialog.setTitle("BEVESTIGING");
        dialog.setHeaderText("BEVESTIG VERWIJDEREN");

        ButtonType bevestigJaType = new ButtonType("BEVESTIG", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(bevestigJaType);
        Node bevestigJa = dialog.getDialogPane().lookupButton(bevestigJaType); //bevestigings knopje

        ButtonType bevestigNeeType = new ButtonType("ANNULEER", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(bevestigNeeType);
        Node bevestigNee = dialog.getDialogPane().lookupButton(bevestigNeeType); // annuleer knopje


        bevestigJa.addEventFilter(ActionEvent.ACTION, event -> {
            PatientDatabase pdb = new PatientDatabase();
            pdb.deletePatient(pat); // als bevestigd is, delete patient
        });

        bevestigNee.addEventFilter(ActionEvent.ACTION, event -> {
            // anders niets
        });

        dialog.showAndWait();
    }


    public Patient patientAanpasPopup(Patient pat){
        Integer id = pat.getId();
        String voornaam = null;
        String achternaam = null;
        String geboortedatum = null;
        String telefoonnummer = null;
        String emailadres = null;
        String adres = null;
        String diagnose = null;
        String medicijnen = null;
        String behandelend_arts = null;
        Integer behandeling = null;
        Integer behandelaar_id = null;



        Dialog dialog = new Dialog<>(); //popup scherm maken
        dialog.setTitle("Nieuwe patient aanmaken");
        dialog.setHeaderText("Voer gegevens in");

        GridPane invul = new GridPane();
        // vakjes voor scherm om in te vullen
        Text voornaamT = new Text("Voornaam: ");
        invul.add(voornaamT, 0, 0);
        TextField voornaamTF = new TextField();
        invul.add(voornaamTF, 1, 0);

        Text achternaamT = new Text("Achternaam: ");
        invul.add(achternaamT, 0, 1);
        TextField achternaamTF = new TextField();
        invul.add(achternaamTF, 1, 1);

        Text geboortedatumT = new Text("Geboortedatum: ");
        invul.add(geboortedatumT, 0, 2);
        TextField geboortedatumTF = new TextField();
        invul.add(geboortedatumTF, 1, 2);

        Text telefoonnummerT = new Text("Telefoonnummer: ");
        invul.add(telefoonnummerT, 0, 3);
        TextField telefoonnummerTF = new TextField();
        invul.add(telefoonnummerTF, 1, 3);

        Text emailadresT = new Text("Emailadres: ");
        invul.add(emailadresT, 0, 4);
        TextField emailadresTF = new TextField();
        invul.add(emailadresTF, 1, 4);

        Text adresT = new Text("Adres: ");
        invul.add(adresT, 0, 5);
        TextField adresTF = new TextField();
        invul.add(adresTF, 1, 5);

        Text diagnoseT = new Text("Diagnose: ");
        invul.add(diagnoseT, 0, 6);
        TextField diagnoseTF = new TextField();
        invul.add(diagnoseTF, 1, 6);

        Text medicijnenT = new Text("Medicijnen: ");
        invul.add(medicijnenT, 0, 7);
        TextField medicijnenTF = new TextField();
        invul.add(medicijnenTF, 1, 7);

        Text behandelend_artsT = new Text("Behandelend Arts: ");
        invul.add(behandelend_artsT, 0, 8);
        TextField behandelend_artsTF = new TextField();
        invul.add(behandelend_artsTF, 1, 8);

        Text behandelingT = new Text("Behandeling: ");
        invul.add(behandelingT, 0, 9);
        TextField behandelingTF = new TextField();
        invul.add(behandelingTF, 1, 9);

        Text behandelaar_idT = new Text("Uw behandelaar id: ");
        invul.add(behandelaar_idT, 0, 10);
        TextField behandelaar_idTF = new TextField();
        invul.add(behandelaar_idTF, 1, 10);

        ButtonType okButtonType = new ButtonType("Opslaan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (
                // kijken of velden leeg zijn
                    voornaamTF.getText().isEmpty() ||
                            achternaamTF.getText().isEmpty() ||
                            geboortedatumTF.getText().isEmpty() ||
                            telefoonnummerTF.getText().isEmpty() ||
                            emailadresTF.getText().isEmpty() ||
                            adresTF.getText().isEmpty() ||
                            diagnoseTF.getText().isEmpty() ||
                            medicijnenTF.getText().isEmpty() ||
                            behandelend_artsTF.getText().isEmpty() ||
                            behandelingTF.getText().isEmpty() ||
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
                Integer.parseInt(behandelingTF.getText());
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

        // haalt info uit vakjes
        voornaam = voornaamTF.getText();
        achternaam = achternaamTF.getText();
        geboortedatum = geboortedatumTF.getText();
        telefoonnummer = telefoonnummerTF.getText();
        emailadres = emailadresTF.getText();
        adres = adresTF.getText();
        diagnose = diagnoseTF.getText();
        medicijnen = medicijnenTF.getText();
        behandelend_arts = behandelend_artsTF.getText();
        behandeling = Integer.parseInt(behandelingTF.getText());
        behandelaar_id = Integer.parseInt(behandelaar_idTF.getText());

        // maakt patientobject aan met informatie uit vakjes en geeft deze terug
        Patient patient = new Patient(id, voornaam, achternaam, geboortedatum, telefoonnummer, emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id);
        return patient;
    }


    public Patient patientAanmaakPopup(){
        Integer id = null;
        String voornaam = null;
        String achternaam = null;
        String geboortedatum = null;
        String telefoonnummer = null;
        String emailadres = null;
        String adres = null;
        String diagnose = null;
        String medicijnen = null;
        String behandelend_arts = null;
        Integer behandeling = null;
        Integer behandelaar_id = null;



        Dialog dialog = new Dialog<>(); // popup aanmaken
        dialog.setTitle("Nieuwe patient aanmaken");
        dialog.setHeaderText("Voer gegevens in");

        GridPane invul = new GridPane();
        Text voornaamT = new Text("Voornaam: ");
        invul.add(voornaamT, 0, 0);
        TextField voornaamTF = new TextField();
        invul.add(voornaamTF, 1, 0);

        Text achternaamT = new Text("Achternaam: ");
        invul.add(achternaamT, 0, 1);
        TextField achternaamTF = new TextField();
        invul.add(achternaamTF, 1, 1);

        Text geboortedatumT = new Text("Geboortedatum: ");
        invul.add(geboortedatumT, 0, 2);
        TextField geboortedatumTF = new TextField();
        invul.add(geboortedatumTF, 1, 2);

        Text telefoonnummerT = new Text("Telefoonnummer: ");
        invul.add(telefoonnummerT, 0, 3);
        TextField telefoonnummerTF = new TextField();
        invul.add(telefoonnummerTF, 1, 3);

        Text emailadresT = new Text("Emailadres: ");
        invul.add(emailadresT, 0, 4);
        TextField emailadresTF = new TextField();
        invul.add(emailadresTF, 1, 4);

        Text adresT = new Text("Adres: ");
        invul.add(adresT, 0, 5);
        TextField adresTF = new TextField();
        invul.add(adresTF, 1, 5);

        Text diagnoseT = new Text("Diagnose: ");
        invul.add(diagnoseT, 0, 6);
        TextField diagnoseTF = new TextField();
        invul.add(diagnoseTF, 1, 6);

        Text medicijnenT = new Text("Medicijnen: ");
        invul.add(medicijnenT, 0, 7);
        TextField medicijnenTF = new TextField();
        invul.add(medicijnenTF, 1, 7);

        Text behandelend_artsT = new Text("Behandelend Arts: ");
        invul.add(behandelend_artsT, 0, 8);
        TextField behandelend_artsTF = new TextField();
        invul.add(behandelend_artsTF, 1, 8);

        Text behandelingT = new Text("Behandeling: ");
        invul.add(behandelingT, 0, 9);
        TextField behandelingTF = new TextField();
        invul.add(behandelingTF, 1, 9);

        Text behandelaar_idT = new Text("Uw behandelaar id: ");
        invul.add(behandelaar_idT, 0, 10);
        TextField behandelaar_idTF = new TextField();
        invul.add(behandelaar_idTF, 1, 10);

        ButtonType okButtonType = new ButtonType("Opslaan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (
                // kijken of velden leeg zijn
                    voornaamTF.getText().isEmpty() ||
                            achternaamTF.getText().isEmpty() ||
                            geboortedatumTF.getText().isEmpty() ||
                            telefoonnummerTF.getText().isEmpty() ||
                            emailadresTF.getText().isEmpty() ||
                            adresTF.getText().isEmpty() ||
                            diagnoseTF.getText().isEmpty() ||
                            medicijnenTF.getText().isEmpty() ||
                            behandelend_artsTF.getText().isEmpty() ||
                            behandelingTF.getText().isEmpty() ||
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
                Integer.parseInt(behandelingTF.getText());
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

        //haalt ingevulde info uit vakjes
        voornaam = voornaamTF.getText();
        achternaam = achternaamTF.getText();
        geboortedatum = geboortedatumTF.getText();
        telefoonnummer = telefoonnummerTF.getText();
        emailadres = emailadresTF.getText();
        adres = adresTF.getText();
        diagnose = diagnoseTF.getText();
        medicijnen = medicijnenTF.getText();
        behandelend_arts = behandelend_artsTF.getText();
        behandeling = Integer.parseInt(behandelingTF.getText());
        behandelaar_id = Integer.parseInt(behandelaar_idTF.getText());

        //maakt patientobject met text uit de vakjes en geeft deze terug
        Patient patient = new Patient(id, voornaam, achternaam, geboortedatum, telefoonnummer, emailadres, adres, diagnose, medicijnen, behandelend_arts, behandeling, behandelaar_id);
        return patient;
    }




    public void noteDeletePopup(Notitie not) {
        Dialog dialog = new Dialog<>(); // maakt popup
        dialog.setTitle("BEVESTIGING");
        dialog.setHeaderText("BEVESTIG VERWIJDEREN");

        ButtonType bevestigJaType = new ButtonType("BEVESTIG", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(bevestigJaType);
        Node bevestigJa = dialog.getDialogPane().lookupButton(bevestigJaType);

        ButtonType bevestigNeeType = new ButtonType("ANNULEER", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(bevestigNeeType);
        Node bevestigNee = dialog.getDialogPane().lookupButton(bevestigNeeType);


        bevestigJa.addEventFilter(ActionEvent.ACTION, event -> {
            NotitieDatabase ndb = new NotitieDatabase();
            ndb.deleteNotitie(not); // als bevestigd, delete notitie
        });

        bevestigNee.addEventFilter(ActionEvent.ACTION, event -> {
            //anders niets
        });

        dialog.showAndWait();
    }

    public Notitie noteAanpasPopup(Notitie not){
        Integer id = not.getId();
        String notitie_text = null;
        String datum = null;
        Integer behandelaar_id = null;
        Integer patient_id = not.getPatient_id();

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

        //maakt notitie met info uit vakjes
        Notitie notitie = new Notitie(id, notitie_text, datum, behandelaar_id, patient_id);
        return notitie;
    }

    public static void main(String[] args) {
        launch();
    }
}