package PJP2023.Projeto.integrador.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MarcaController {
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void SalvarMarca(ActionEvent event) {
    }
    @FXML
    void CancelarMarca(ActionEvent event) {
        stage.close();
    }
    @FXML
    void ExcluirMarca(ActionEvent event) {
    }
}
