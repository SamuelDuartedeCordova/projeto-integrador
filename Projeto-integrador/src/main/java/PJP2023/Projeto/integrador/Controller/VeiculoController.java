package PJP2023.Projeto.integrador.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class VeiculoController {
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void SalvarVeiculo(ActionEvent event) {
    }
    @FXML
    void CancelarVeiculo(ActionEvent event) {
        stage.close();
    }
    @FXML
    void ExcluirVeiculo(ActionEvent event) {
    }
}
