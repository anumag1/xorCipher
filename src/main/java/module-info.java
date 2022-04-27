module com.anumag.xor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anumag.xor to javafx.fxml;
    exports com.anumag.xor;
}