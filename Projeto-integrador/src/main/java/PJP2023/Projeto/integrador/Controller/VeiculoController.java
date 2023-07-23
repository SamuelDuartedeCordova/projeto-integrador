package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.Models.Veiculo;
import PJP2023.Projeto.integrador.Services.ModeloService;
import PJP2023.Projeto.integrador.Services.VeiculoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

public class VeiculoController {
    @FXML
    private TextField placaVcl;
    @FXML
    private TextField anoVcl;
    @FXML
    private TextField renavamVcl;
    @FXML
    private TextField chassiVcl;
    @FXML
    private TextField corVcl;
    @FXML
    private ChoiceBox modeloVcl;
    @FXML
    private TableView <Veiculo>tblVcl;
    @FXML
    private TableColumn <Veiculo, String>clnModeloVcl;
    @FXML
    private TableColumn <Veiculo, String>clnPlacaVcl;
    @FXML
    private TableColumn <Veiculo, Integer>clnRenavamVcl;
    @FXML
    private TableColumn <Veiculo, String>clnChassiVcl;
    @FXML
    private TableColumn <Veiculo, Integer>clnAnoVcl;
    @FXML
    private Button btnExcluirVcl;
    Veiculo vei = new Veiculo();
    private Stage stage;
    Integer index = -1;
    Integer c = 0;

    @FXML
    void initialize() {
        btnExcluirVcl.setDisable(true);
        //Colunas da TableView
        clnModeloVcl.setCellValueFactory(new PropertyValueFactory<>("nomeModelo"));
        clnPlacaVcl.setCellValueFactory(new PropertyValueFactory<>("placa"));
        clnRenavamVcl.setCellValueFactory(new PropertyValueFactory<>("renavam"));
        clnChassiVcl.setCellValueFactory(new PropertyValueFactory<>("chassi"));
        clnAnoVcl.setCellValueFactory(new PropertyValueFactory<>("anoFabricacao"));

        this.carregarLista();

        // Configurar o evento de clique duplo na tabela
        tblVcl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Veiculo vei = tblVcl.getSelectionModel().getSelectedItem();
                    placaVcl.setText(vei.getPlaca());
                    anoVcl.setText(String.valueOf(vei.getAnoFabricacao()));
                    renavamVcl.setText(String.valueOf(vei.getRenavam()));
                    chassiVcl.setText(vei.getChassi());
                    corVcl.setText(vei.getCor());
                    for (Object item : modeloVcl.getItems()) {
                        if (item instanceof Modelos) {
                            Modelos modelo = (Modelos) item;
                            if (modelo.getId() == vei.getIdModelos()) {
                                modeloVcl.setValue(modelo);
                                break;
                            }
                        }
                    }
                    index = vei.getId();
                    btnExcluirVcl.setDisable(false);
                }
            }
        });
    }


    @FXML
    void salvarVeiculo(ActionEvent event) throws ParseException {

        Veiculo vei = new Veiculo();
        c = 0;

        //Verificar Placa
        if (validarPlacaBrasileira(placaVcl.getText().toUpperCase()) || validarPlacaMercosul(placaVcl.getText().toUpperCase())) {
            vei.setPlaca(placaVcl.getText().toUpperCase());
            placaVcl.setStyle("-fx-background-color: white;");
        } else if (campoVazio(placaVcl.getText())) {
            placaVcl.setStyle("-fx-background-color: red;");
            c++;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Placa Invalida");
            alert.setHeaderText("Placa Informado Invalida, Digite uma placa Valida.");
            alert.showAndWait();
            placaVcl.setStyle("-fx-background-color: red;");
            c++;
        }

        //Verificar Ano
        if (campoVazio(anoVcl.getText())) {
            anoVcl.setStyle("-fx-background-color: red;");
            c++;
        } else if (anoValido(anoVcl.getText())){
            vei.setAnoFabricacao(Integer.parseInt(anoVcl.getText()));
            anoVcl.setStyle("-fx-background-color: white;");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ano Invalido");
            alert.setHeaderText("Ano de fabricação Informado Invalida, Digite um ano Valido.");
            alert.showAndWait();
            anoVcl.setStyle("-fx-background-color: red;");
            c++;
        }

        //Verificar renavam
        if(campoVazio(renavamVcl.getText())){
            renavamVcl.setStyle("-fx-background-color: red;");
            c++;
        }else if(apenasNumeros(renavamVcl.getText()) && (renavamVcl.getText().length() == 11)){
            BigInteger renavam = new BigInteger(renavamVcl.getText());
            vei.setRenavam(renavam);
            renavamVcl.setStyle("-fx-background-color: white;");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Renavam Invalido");
            alert.setHeaderText("Renavam Informado Invalida, Digite um Renavam Valido.");
            alert.showAndWait();
            renavamVcl.setStyle("-fx-background-color: red;");
            c++;
        }

        //Verificar Chassi
        if(campoVazio(chassiVcl.getText())){
            chassiVcl.setStyle("-fx-background-color: red;");
            c++;
        }else if(chassiVcl.getText().length() == 17 && chassiVcl.getText().matches("^[A-HJ-NPR-Z0-9]*$")){
            vei.setChassi(chassiVcl.getText());
            chassiVcl.setStyle("-fx-background-color: white;");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chassi Invalido");
            alert.setHeaderText("Chassi Informado Invalida, Digite um Chassi Valido.");
            alert.showAndWait();
            chassiVcl.setStyle("-fx-background-color: red;");
            c++;
        }
        //Verificar Cor
        try{
            vei.setCor(corVcl.getText());
        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar Modelo
        try{
            if (modeloVcl != null) {
                Modelos modelo = (Modelos) modeloVcl.getValue();
                vei.setIdModelos(VeiculoService.buscarIdModelo(modelo.getNome()));
            } else {
                modeloVcl.setStyle("-fx-background-color: red;");
                c++;
            }
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
                    VeiculoService.salvarVeiculo(vei);

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
                    VeiculoService.atualizarVeiculo(index, vei);
                    index = -1;
                    btnExcluirVcl.setDisable(true);
                    this.limparCampos();
                    this.carregarLista();
                }
            });
        }
    }

    @FXML
    void cancelarVeiculo(ActionEvent event) {
        stage.close();
    }

    @FXML
    void excluirVeiculo(ActionEvent event) {
        Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaExcluir.setTitle("Confirmaçao de Exclusão");
        alertaExcluir.setHeaderText("Deseja Excluir o registro ?");
        alertaExcluir.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                VeiculoService.deletarVeiculo(index);
                index = -1;
                btnExcluirVcl.setDisable(true);
                limparCampos();
                carregarLista();
            }
        });
    }

    public void carregarLista() {
        try {
            // Limpa a tabela de veículos
            tblVcl.getItems().clear();

            // Carrega a lista de veículos do banco de dados
            java.util.List<Veiculo> veiculoList = VeiculoService.carregarVeiculo();

            for (Veiculo veiculo : veiculoList) {
                int idModelo = veiculo.getIdModelos();
                Modelos modelo = ModeloService.carregarModeloId(idModelo);

                if (modelo != null) {
                    veiculo.setNomeModelo(modelo.getNome());
                } else {
                    veiculo.setNomeModelo(""); //atribuir um valor vazio quando não houver modelo
                }
            }

            // Carrega a lista de modelos do banco de dados
            List<Modelos> modelosList = ModeloService.carregarModelos();
            ObservableList<Modelos> modelosObservableList = FXCollections.observableArrayList(modelosList);

            // Configura o ChoiceBox de modelos
            modeloVcl.setItems(modelosObservableList);

            modeloVcl.setConverter(new StringConverter<Modelos>() {
                @Override
                public String toString(Modelos modelo) {
                    return modelo.getNome();
                }

                @Override
                public Modelos fromString(String string) {
                    return null;
                }
            });

            // Adiciona a lista de veículos na tabela
            tblVcl.getItems().addAll(veiculoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Recebe parametros da janela
    public void setStage(Stage stage) {
        this.stage = stage;
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
        placaVcl.setText("");
        anoVcl.setText("");
        renavamVcl.setText("");
        chassiVcl.setText("");
        corVcl.setText("");
        modeloVcl.getSelectionModel().clearSelection();
        placaVcl.setStyle("-fx-background-color: white;");
        anoVcl.setStyle("-fx-background-color: white;");
        renavamVcl.setStyle("-fx-background-color: white;");
        chassiVcl.setStyle("-fx-background-color: white;");
        modeloVcl.setStyle("-fx-background-color: white;");
    }

    public boolean apenasNumeros(String texto) {
        return texto.matches("[0-9]+");
    }

    public static boolean validarPlacaBrasileira(String placa) {
        // Verificar se a placa tem 7 caracteres
        if (placa == null || placa.length() != 7) {
            return false;
        }

        // Extrair as partes da placa (três letras e quatro dígitos)
        String letras = placa.substring(0, 3);
        String digitos = placa.substring(3);

        // Verificar se as letras são compostas apenas por caracteres alfabéticos
        if (!letras.matches("[A-Z]{3}")) {
            return false;
        }

        // Verificar se os dígitos são compostos apenas por números
        if (!digitos.matches("[0-9]{4}")) {
            return false;
        }

        // Se todas as verificações passaram, a placa é válida
        return true;
    }

    public static boolean validarPlacaMercosul(String placa) {
        // Verificar se a placa tem 7 caracteres
        if (placa == null || placa.length() != 7) {
            return false;
        }

        // Extrair as partes da placa (quatro letras, um hífen e dois dígitos)
        String letras = placa.substring(0, 4);
        String digitos = placa.substring(5);

        // Verificar se as letras são compostas apenas por caracteres alfabéticos
        if (!letras.matches("[A-Z]{4}")) {
            return false;
        }

        // Verificar se o hífen está na posição correta
        if (!placa.substring(4, 5).equals("-")) {
            return false;
        }

        // Verificar se os dígitos são compostos apenas por números
        if (!digitos.matches("[0-9]{2}")) {
            return false;
        }

        // Se todas as verificações passaram, a placa é válida
        return true;
    }

    public static boolean anoValido(String valor) {
        // Verificar se a String contém apenas números
        if (!valor.matches("\\d+")) {
            return false;
        }

        // Verificar se a String tem exatamente 4 dígitos
        if (valor.length() != 4) {
            return false;
        }

        // Converter a String para um valor numérico (int)
        int ano;
        try {
            ano = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return false;
        }

        // Verificar se o ano é maior que 0
        return ano > 0;
    }

}
