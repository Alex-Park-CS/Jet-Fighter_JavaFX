module ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx to javafx.fxml;
    exports ca.bcit.comp2522.termproject.comp2522202410termprojectjavafx;
}