module com.mara.elektronischpatientendossier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.mara.elektronischpatientendossier to javafx.fxml;
    exports com.mara.elektronischpatientendossier;
}