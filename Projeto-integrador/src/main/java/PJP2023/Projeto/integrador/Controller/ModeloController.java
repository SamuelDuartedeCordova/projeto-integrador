package PJP2023.Projeto.integrador.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Window;

public class ModeloController {
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void SalvarModelo(ActionEvent event) {
    }
    @FXML
    void CancelarModelo(ActionEvent event) {
        stage.close();
    }
    @FXML
    void ExcluirModelo(ActionEvent event) {
    }
}
