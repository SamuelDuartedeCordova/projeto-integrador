package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.Services.MarcaService;
import PJP2023.Projeto.integrador.Services.ModeloService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.util.List;

public class ModeloController {
    @FXML
    private TextField nomeMdl;
    @FXML
    private TextField potenciaMdl;
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
    private TableColumn<Marcas, String> clnMarcaMdl;
    @FXML
    private TableColumn<Modelos, String> clnNomeMdl;
    @FXML
    private TableColumn<Modelos, String> clnCarroceriaMdl;
    @FXML
    private TableColumn<Modelos, String> clnCambioMdl;
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
        clnMarcaMdl.setCellValueFactory(new PropertyValueFactory<>("nomeMarca"));
        clnNomeMdl.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnCarroceriaMdl.setCellValueFactory(new PropertyValueFactory<>("carroceria"));
        clnCambioMdl.setCellValueFactory(new PropertyValueFactory<>("cambio"));
        clnPotenciaMdl.setCellValueFactory(new PropertyValueFactory<>("potencia"));

        //Itens Fixos ChoiseBox
        ObservableList<String> opcoesCambio = FXCollections.observableArrayList("", "Automático","Manual");
        ObservableList<String> opcoesCombustivel = FXCollections.observableArrayList("", "Gasolina","Etanol","Diesel","Flex");
        ObservableList<Integer> opcoesPortas = FXCollections.observableArrayList( 0, 2, 3, 4, 5 );
        ObservableList<String> opcoesCarroceria = FXCollections.observableArrayList("", "Sedã","Hatch","SUV","Picape");
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
                    for (Object item : marcaMdl.getItems()) {
                        if (item instanceof Marcas) {
                            Marcas marca = (Marcas) item;
                            if (marca.getId() == mod.getIdMarcas()) {
                                marcaMdl.setValue(marca);
                                break;
                            }
                        }
                    }

                    cambioMdl.setValue(mod.getCambio());
                    combustivelMdl.setValue(mod.getCombustivel());
                    portasMdl.setValue(mod.getPortas());
                    carroceriaMdl.setValue(mod.getCarroceria());
                    index = mod.getId();
                    btnExcluirMdl.setDisable(false);
                }
            }
        });
    }

    @FXML
    public void salvarModelo() {
        Modelos mod = new Modelos();
        c = 0;

        //Verificar Nome
        if(!campoVazio(nomeMdl.getText())){
            mod.setNome(nomeMdl.getText());
        }else{
            nomeMdl.setStyle("-fx-background-color: pink;");
            c ++;
        }

        //Verificar Potencia
        try{
            if(apenasNumeros(potenciaMdl.getText()) ){
                mod.setPotencia(Integer.parseInt(potenciaMdl.getText()));
            }else if(!campoVazio(potenciaMdl.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Potencia Invalida");
                alert.setHeaderText("Potencia Informado Invalida, Digite apenas numeros.");
                alert.showAndWait();
                nomeMdl.setStyle("-fx-background-color: pink;");
                c ++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Marca
        if (marcaMdl != null) {
            Marcas marca = (Marcas) marcaMdl.getValue();
            mod.setIdMarcas(ModeloService.buscarIdMarca(marca.getMarca()));
        } else {
            marcaMdl.setStyle("-fx-background-color: pink;");
            c++;
        }

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



        //Incluir Novo Registro
        if(index == -1 && c == 0){
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Inclusao");
            alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    //Adicionar novo item a Lista
                    ModeloService.inserirModelos(mod);

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
                    ModeloService.atualizarModelos(index, mod);
                    this.carregarLista();
                    this.limparCampos();
                    index = -1;
                }
            });
        }
    }

    @FXML
    void cancelarModelo(ActionEvent event) {
        stage.close();
    }

    @FXML
    void excluirModelo(ActionEvent event) {
        Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaExcluir.setTitle("Confirmaçao de Exclusão");
        alertaExcluir.setHeaderText("Deseja Excluir o registro ?");
        alertaExcluir.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                ModeloService.deletarModelos(index);
                index = -1;
                limparCampos();
                carregarLista();
                btnExcluirMdl.setDisable(true);
            }
        });
    }
    //Recebe parametros da janela
    public void setStage(Stage stage) {

        this.stage = stage;
    }

   public void carregarLista() {
       try {
           tblModelos.getItems().remove(0, tblModelos.getItems().size());
           List<Modelos> modelosList = ModeloService.carregarModelos();

           for (Modelos modelo : modelosList) {
               int idMarca = modelo.getIdMarcas();
               Marcas marca = MarcaService.carregarMarcaPorId(idMarca);
               if (marca != null) {
                   modelo.setNomeMarca(marca.getMarca());
               } else {
                   modelo.setNomeMarca("");
               }
           }

           List<Marcas> marcas = MarcaService.carregarMarcas();
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

           tblModelos.getItems().addAll(modelosList);
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
        nomeMdl.setText("");
        potenciaMdl.setText("");
        marcaMdl.getSelectionModel().clearSelection();
        cambioMdl.getSelectionModel().clearSelection();
        combustivelMdl.getSelectionModel().clearSelection();
        portasMdl.getSelectionModel().clearSelection();
        carroceriaMdl.getSelectionModel().clearSelection();
        nomeMdl.setStyle("-fx-background-color: white;");
    }

    public boolean apenasNumeros(String texto) {
        return texto.matches("[0-9]+");
    }
}
