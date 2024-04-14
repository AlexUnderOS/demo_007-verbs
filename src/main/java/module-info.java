module com.alex.demo_007 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alex.demo_007 to javafx.fxml;
    exports com.alex.demo_007;
}