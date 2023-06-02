package com.mara.elektronischpatientendossier;

import com.mara.elektronischpatientendossier.database.PatientDatabase;
import com.mara.elektronischpatientendossier.database.StatementFactory;
import com.mara.elektronischpatientendossier.models.Patient;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        StatementFactory.getInstance().createStatement();

        hoofdscherm(root);

    }


    public void hoofdscherm(GridPane pane) {

        // navigatie links
        Button patienten = new Button("PATIENTEN");
        Button behandelingen = new Button("BEHANDELINGEN");
        Button behandelaren = new Button("BEHANDELAREN");

        patienten.setMinSize(120, 20);
        behandelingen.setMinSize(120, 20);
        behandelaren.setMinSize(120, 20);

        pane.add(patienten, 0, 1);
        pane.add(behandelingen, 0, 2);
        pane.add(behandelaren, 0, 3);

        GridPane.setHalignment(patienten, HPos.CENTER);
        GridPane.setHalignment(behandelingen, HPos.CENTER);
        GridPane.setHalignment(behandelaren, HPos.CENTER);

        GridPane.setValignment(patienten, VPos.CENTER);
        GridPane.setValignment(behandelingen, VPos.CENTER);
        GridPane.setValignment(behandelaren, VPos.CENTER);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(5);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        RowConstraints row4 = new RowConstraints();
        row4.setPercentHeight(10);
        pane.getRowConstraints().addAll(row1, row2, row3, row4);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(15);
        pane.getColumnConstraints().addAll(col1);



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

        lijstPatient.getChildren().add(tableView);

        pane.add(lijstPatient, 1, 1);


    }


    public static void main(String[] args) {
        launch();
    }
}