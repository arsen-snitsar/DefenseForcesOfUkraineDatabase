module org.example.finalprojectalpha {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.finalprojectalpha to javafx.fxml;
    exports org.example.finalprojectalpha;
}