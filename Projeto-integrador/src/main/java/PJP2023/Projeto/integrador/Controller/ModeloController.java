package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.Services.ServiceMarca;
import PJP2023.Projeto.integrador.Services.ServiceModelo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Window;
import javafx.util.StringConverter;


import java.util.List;

public class ModeloController {
    @FXML
    private TextField nomeMdl;
    @FXML
    private TextField potenciaMdl;
    @FXML
    private TextField corMdl;
    @FXML
    private ChoiceBox marcaMdl;
    @FXML
    private ChoiceBox cambioMdl;
    @FXML
    private ChoiceBox combustivelMdl;
    @FXML
    private ChoiceBox portasMdl;
    @FXML
    private ChoiceBox carroceriaMdl;
    @FXML
    private TableView<Modelos> tblModelos;
    @FXML
    private TableColumn<Modelos, String> clnMarcaMdl;
    @FXML
    private TableColumn<Modelos, String> clnNomeMdl;
    @FXML
    private TableColumn<Modelos, String> clnCarroceriaMdl;
    @FXML
    private TableColumn<Modelos, String> clnCorMdl;
    @FXML
    private TableColumn<Modelos, String> clnPotenciaMdl;
    @FXML
    private Button btnExcluirMdl;
    Modelos mod = new Modelos();
    private Stage stage;
    Integer index = -1;

    @FXML
    void initialize() {
        btnExcluirMdl.setDisable(true);
        //Colunas da TableView
        clnMarcaMdl.setCellValueFactory(new PropertyValueFactory<>("marcaMdl"));
        clnNomeMdl.setCellValueFactory(new PropertyValueFactory<>("nomeMdl"));
        clnCarroceriaMdl.setCellValueFactory(new PropertyValueFactory<>("carroceriaMdl"));
        clnCorMdl.setCellValueFactory(new PropertyValueFactory<>("corMdl"));
        clnPotenciaMdl.setCellValueFactory(new PropertyValueFactory<>("potenciaMdl"));
        this.carregarLista();
        // Configurar o evento de clique duplo na tabela
        tblModelos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Modelos mod = tblModelos.getSelectionModel().getSelectedItem();
                    nomeMdl.setText(mod.getNome());
                    potenciaMdl.setText(Integer.toString(mod.getPotencia()));
                    corMdl.setText(mod.getCor());
                    marcaMdl.setValue(mod.getIdMarcas());
                    cambioMdl.setValue(mod.getCambio());
                    combustivelMdl.setValue(mod.getCombustivel());
                    portasMdl.setValue(mod.getPortas());
                    carroceriaMdl.setValue(mod.getCarroceria());
                    index = mod.getId();
                    btnExcluirMdl.setDisable(false);
                    System.out.println(mod.getIdMarcas());
                }
            }
        });
    }

    @FXML
    void SalvarModelo(ActionEvent event) {
        mod.setNome(nomeMdl.getText());
        if(!campoVazio(mod.getNome())){
            if (index == -1) {
                //Alerta de Inclusao
                Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                alertaSalvar.setTitle("Confirmaçao de Inclusao");
                alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
                alertaSalvar.showAndWait().ifPresent(resposta -> {
                    if (resposta == ButtonType.OK) {
                        //Adicionar novo item a Lista
                        //ServiceMarca.inserirMarcas(mar);
                        stage.close();
                    }
                });
            }else {
                Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                alertaSalvar.setTitle("Confirmaçao de Alteraçao");
                alertaSalvar.setHeaderText("Deseja Alterar o registro ?");
                alertaSalvar.showAndWait().ifPresent(resposta -> {
                    if (resposta == ButtonType.OK) {
                        //Atualiza item na lista
                        //ServiceMarca.atualizarMarcas(index, mod);
                        LimparCampos();
                        index = -1;
                        carregarLista();
                        btnExcluirMdl.setDisable(true);
                    }
                });
            }
        }else{
            nomeMdl.setStyle("-fx-background-color: pink;");
        }
    }
    @FXML
    void CancelarModelo(ActionEvent event) {
        stage.close();
    }
    @FXML
    void ExcluirModelo(ActionEvent event) {
        Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaExcluir.setTitle("Confirmaçao de Exclusão");
        alertaExcluir.setHeaderText("Deseja Excluir o registro ?");
        alertaExcluir.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                ServiceMarca.deletarMarcas(index);
                index = -1;
                LimparCampos();
                index = -1;
                carregarLista();
                btnExcluirMdl.setDisable(true);
            }
        });
    }
    //Recebe parametros da janela marcas
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void carregarLista() {
        try {
            tblModelos.getItems().remove(0, tblModelos.getItems().size());
            List<Modelos> modelosList = ServiceModelo.carregarModelos();
            tblModelos.getItems().addAll(modelosList);
            List<Marcas> marcas = ServiceMarca.carregarMarcas();
            marcaMdl.setItems(FXCollections.observableArrayList(marcas));
            marcaMdl.setConverter(new StringConverter<Marcas>() {
                @Override
                public String toString(Marcas marca) {
                    return marca.getMarca();
                }
                @Override
                public Marcas fromString(String string) {
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean campoVazio (String texto){
        if (texto == null || texto.trim().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void LimparCampos() {
        nomeMdl.setText("");
        nomeMdl.setStyle("-fx-background-color: white;");

    }
}
