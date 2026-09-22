module com.example.actividadparejas5 {
    requires javafx.controls;
    requires javafx.fxml;

    // FXMLLoader crea los controladores por reflexion: necesita el paquete abierto
    opens com.example.actividadparejas5 to javafx.fxml;
    opens com.example.actividadparejas5.controllers to javafx.fxml;

    // PropertyValueFactory lee los getters de Cliente por reflexion (javafx.base)
    opens com.example.actividadparejas5.models to javafx.base, javafx.fxml;

    exports com.example.actividadparejas5;
    exports com.example.actividadparejas5.controllers;
    exports com.example.actividadparejas5.models;
}
