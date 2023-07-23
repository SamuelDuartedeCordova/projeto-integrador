package PJP2023.Projeto.integrador.Controller;
import PJP2023.Projeto.integrador.Models.Veiculo;
import PJP2023.Projeto.integrador.Models.VeiculoInfo;
import PJP2023.Projeto.integrador.Services.ServiceModelo;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Node;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    @FXML
    private TableView<VeiculoInfo> tblCarros;
    @FXML
    private TableColumn<VeiculoInfo, String> clnModelo;
    @FXML
    private TableColumn<VeiculoInfo, String> clnMarca;
    @FXML
    private TableColumn<VeiculoInfo, Integer> clnFabricacao;
    @FXML
    private TableColumn<VeiculoInfo, String> clnPlaca;
    @FXML
    private TableColumn<VeiculoInfo, Long> clnRenavam;
    @FXML
    private TableColumn<VeiculoInfo, String> clnChassi;

    @FXML
    public void initialize() {
        // Mapear os atributos do objeto VeiculoInfo para as colunas da TableView
        clnModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClnModelo()));
        clnMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClnMarca()));
        clnFabricacao.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getClnFabricacao()).asObject());
        clnPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClnPlaca()));
        clnRenavam.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getClnRenavam()).asObject());
        clnChassi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClnChassi()));

        // Carregar as informações dos veículos do banco de dados e preencher a TableView
        atualizarListaVeiculos();
    }

    @FXML
    void CadastroMarca(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cdtmarcas.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            MarcaController marcaController = loader.getController();
            marcaController.setStage(stage);
            stage.setOnHidden(e -> atualizarListaVeiculos());

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
            stage.setOnHidden(e -> atualizarListaVeiculos());

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
            stage.setOnHidden(e -> atualizarListaVeiculos());

            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void atualizarListaVeiculos() {
        List<VeiculoInfo> veiculoInfoList = ServiceModelo.carregarInformacoesVeiculos();
        tblCarros.setItems(FXCollections.observableArrayList(veiculoInfoList));
    }
}

