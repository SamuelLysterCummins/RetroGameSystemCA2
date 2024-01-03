module com.example.retrovideogamesystemca2 {

    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires java.logging;


    opens com.example.retrovideogamesystemca2 to javafx.fxml, xstream;
    exports com.example.retrovideogamesystemca2;
}