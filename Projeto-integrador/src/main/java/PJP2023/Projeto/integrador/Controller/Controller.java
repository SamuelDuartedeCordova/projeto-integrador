package PJP2023.Projeto.integrador.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Node;


public class Controller {
    @FXML
    private TextField ano1;
    @FXML
    private TextField anoFabricacao;
    @FXML
    private Button btnExcluirMarca;
    @FXML
    private Button btnExcluirModelo;
    @FXML
    private Button btnExcluirVeiculo;
    @FXML
    private Button btnSalvarMarca;
    @FXML
    private Button btnSalvarModelo;
    @FXML
    private Button btnSalvarVeiculo;
    @FXML
    private TextField cambio;
    @FXML
    private TextField carroceria;
    @FXML
    private Label chassi;
    @FXML
    private TextField cilindrada;
    @FXML
    private TableColumn<?, ?> coliunaCombustivel;
    @FXML
    private TableColumn<?, ?> colunaAno;
    @FXML
    private TableColumn<?, ?> colunaCambio;
    @FXML
    private TableColumn<?, ?> colunaCarroceria;
    @FXML
    private TableColumn<?, ?> colunaChassi;
    @FXML
    private TableColumn<?, ?> colunaCilindrada;
    @FXML
    private TableColumn<?, ?> colunaCor;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaPlaca;
    @FXML
    private TableColumn<?, ?> colunaPortas;
    @FXML
    private TableColumn<?, ?> colunaPotencia;
    @FXML
    private TableColumn<?, ?> colunaRenavam;
    @FXML
    private TextField combustivel;
    @FXML
    private TextField cor;
    @FXML
    private TextField nome;
    @FXML
    private TextField nomeDoFabricante;
    @FXML
    private TextField placa;
    @FXML
    private TextField portas;
    @FXML
    private TextField potencia;
    @FXML
    private TextField renavam;
    @FXML
    void CadastroMarca(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cdtmarcas.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            MarcaController marcaController = loader.getController();
            marcaController.setStage(stage);

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void CadastroModelo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cdtmodelos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            ModeloController modeloController = loader.getController();
            modeloController.setStage(stage);

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void CadastroVeiculo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cdtveiculos.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            VeiculoController veiculoController = loader.getController();
            veiculoController.setStage(stage);

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

