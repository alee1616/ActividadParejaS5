module com.example.actividadparejas5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.actividadparejas5 to javafx.fxml;
    exports com.example.actividadparejas5;
}