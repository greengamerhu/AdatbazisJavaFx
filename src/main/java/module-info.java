module hu.petrik.adatbazisjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.adatbazisjavafx to javafx.fxml;
    exports hu.petrik.adatbazisjavafx;
}