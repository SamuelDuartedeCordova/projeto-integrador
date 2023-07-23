package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Services.MarcaService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class MarcaController {
    @FXML
    private TextField campoMarca;
    @FXML
    private TableView<Marcas> tblMarca;
    @FXML
    private TableColumn<Marcas, String> clnMarca;
    @FXML
    private Button btnExcluirMarca;
    Marcas mar = new Marcas();
    private Stage stage;
    Integer index = -1;

    @FXML
    void initialize() {
        btnExcluirMarca.setDisable(true);
        //Colunas da TableView
        clnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        this.carregarLista();
        // Configurar o evento de clique duplo na tabela
        tblMarca.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Marcas mar = tblMarca.getSelectionModel().getSelectedItem();
                    campoMarca.setText(mar.getMarca());
                    index = mar.getId();
                    btnExcluirMarca.setDisable(false);
                    System.out.println(mar.getMarca());
                }
            }
        });
    }

    @FXML
    void salvarMarca(ActionEvent event) {
        mar.setMarca(campoMarca.getText());
        if(!campoVazio(mar.getMarca())){
            if (index == -1) {
                //Alerta de Inclusao
                Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                alertaSalvar.setTitle("Confirmaçao de Inclusao");
                alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
                alertaSalvar.showAndWait().ifPresent(resposta -> {
                    if (resposta == ButtonType.OK) {
                        //Adicionar novo item a Lista
                        MarcaService.inserirMarcas(mar);

                    }
                });
            }else {
                Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                alertaSalvar.setTitle("Confirmaçao de Alteraçao");
                alertaSalvar.setHeaderText("Deseja Alterar o registro ?");
                alertaSalvar.showAndWait().ifPresent(resposta -> {
                    if (resposta == ButtonType.OK) {
                        //Atualiza item na lista
                        MarcaService.atualizarMarcas(index, mar);
                        limparCampos();
                        index = -1;
                        carregarLista();
                        btnExcluirMarca.setDisable(true);
                    }
                });
            }
        }else{
            campoMarca.setStyle("-fx-background-color: pink;");
        }
    }
    @FXML
    void cancelarMarca(ActionEvent event) {
        stage.close();
    }
    @FXML
    void excluirMarca(ActionEvent event) {

        Alert alertaExcluir = new Alert(Alert.AlertType.WARNING);
        alertaExcluir.setTitle("ATENÇÃO!");
        alertaExcluir.setHeaderText("Ao excluir uma marca, todos os modelos dela serão excluidos também ! Deseja realmente fazer a exclusão?");
        alertaExcluir.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                try {
                    MarcaService.deletarMarcas(index);
                    limparCampos();
                    index = -1;
                    carregarLista();
                    btnExcluirMarca.setDisable(true);
                }catch (Exception e){
                    System.out.println("erro no excluir");
                } finally {
                    System.out.println("Try catch finalizado");
                }
            }

        });
    }
    //Recebe parametros da janela
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void carregarLista() {
        try {
            tblMarca.getItems().remove(0, tblMarca.getItems().size());
            List<Marcas> marcasList = MarcaService.carregarMarcas();
            tblMarca.getItems().addAll(marcasList);
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

    public void limparCampos() {
        campoMarca.setText("");
        campoMarca.setStyle("-fx-background-color: white;");

    }
}
