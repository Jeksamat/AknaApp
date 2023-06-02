module com.example.msspartialtest {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.msspartialtest to javafx.fxml;
    exports com.example.msspartialtest;
}