module com.example.retrovideogamesystemca2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.retrovideogamesystemca2 to javafx.fxml;
    exports com.example.retrovideogamesystemca2;
}