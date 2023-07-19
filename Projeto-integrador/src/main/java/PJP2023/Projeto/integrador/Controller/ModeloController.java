package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.Services.ServiceMarca;
import PJP2023.Projeto.integrador.Services.ServiceModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    Integer c = 0;


    @FXML
    void initialize() {
        btnExcluirMdl.setDisable(true);
        //Colunas da TableView
        clnMarcaMdl.setCellValueFactory(new PropertyValueFactory<>("marcaMdl"));
        clnNomeMdl.setCellValueFactory(new PropertyValueFactory<>("nomeMdl"));
        clnCarroceriaMdl.setCellValueFactory(new PropertyValueFactory<>("carroceriaMdl"));
        clnCorMdl.setCellValueFactory(new PropertyValueFactory<>("corMdl"));
        clnPotenciaMdl.setCellValueFactory(new PropertyValueFactory<>("potenciaMdl"));
        //Itens Fixos ChoiseBox
        ObservableList<String> opcoesCambio = FXCollections.observableArrayList("Automático","Manual");
        ObservableList<String> opcoesCombustivel = FXCollections.observableArrayList("Gasolina","Etanol","Diesel","Flex");
        ObservableList<Integer> opcoesPortas = FXCollections.observableArrayList(2, 3, 4, 5);
        ObservableList<String> opcoesCarroceria = FXCollections.observableArrayList("Sedã","Hatch","SUV","Picape");
        carroceriaMdl.setItems(opcoesCarroceria);
        portasMdl.setItems(opcoesPortas);
        combustivelMdl.setItems(opcoesCombustivel);
        cambioMdl.setItems(opcoesCambio);
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
        c = 0;

        //Verificar Nome
        if(!campoVazio(nomeMdl.getText())){
            mod.setNome(nomeMdl.getText());
        }else{
            nomeMdl.setStyle("-fx-background-color: pink;");
            c ++;
        }

        //Verificar Potencia
        if(apenasNumeros(potenciaMdl.getText())){
            mod.setPotencia(Integer.parseInt(potenciaMdl.getText()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Potencia Invalida");
            alert.setHeaderText("Potencia Informado Invalida, Digite apenas numeros.");
            alert.showAndWait();
            nomeMdl.setStyle("-fx-background-color: pink;");
            c ++;
        }

        //Verificar Marca
        if (marcaMdl != null) {
            Marcas marca = (Marcas) marcaMdl.getValue();
            mod.setIdMarcas(ServiceModelo.buscarIdMarca(marca.getMarca()));
            //System.out.println(mod.getIdMarcas());
        } else {
            marcaMdl.setStyle("-fx-background-color: pink;");
            c++;
            //System.out.println(mod.getIdMarcas());
        }
        System.out.println(mod.getIdMarcas());
        //Verificar Cambio
        try{
            mod.setCambio(String.valueOf(cambioMdl.getValue()));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Combustivel
        try{
            mod.setCombustivel(String.valueOf(combustivelMdl.getValue()));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Portas
        try{
            mod.setPortas(Integer.parseInt(String.valueOf(portasMdl.getValue())));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Carroceria
        try{
            mod.setCarroceria(String.valueOf(carroceriaMdl.getValue()));
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Cor
        try{
            mod.setCor(corMdl.getText());
        }catch (Exception e){
            e.printStackTrace();
        }

        //Incluir Novo Registro
        if(index == -1 && c == 0){
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Inclusao");
            alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    //Adicionar novo item a Lista
                    ServiceModelo.inserirModelos(mod);
                    stage.close();
                }
            });
        }

        //Atualizar Registro Existente
        if (c == 0 && index != -1) {
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Alteraçao");
            alertaSalvar.setHeaderText("Deseja Alterar o registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    ServiceModelo.atualizarModelos(index, mod);
                    this.carregarLista();
                    this.LimparCampos();
                    index = -1;
                }
            });
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
                ServiceModelo.deletarModelos(index);
                index = -1;
                LimparCampos();
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

    public boolean apenasNumeros(String texto) {
        return texto.matches("[0-9]+");
    }
}
