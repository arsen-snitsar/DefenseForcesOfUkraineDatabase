module org.example.finalprojectalpha {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.finalprojectalpha to javafx.fxml;
    exports org.example.finalprojectalpha;
}