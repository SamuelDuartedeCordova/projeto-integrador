package PJP2023.Projeto.integrador.Controller;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.core.codec.DataBufferEncoder;

import java.util.Date;

public class MainController {
    private TableView<Modelos> tblCarros;
    @FXML
    private TableColumn<Marcas, String> clnModelo;
    @FXML
    private TableColumn<Modelos, String> clnMarca;
    @FXML
    private TableColumn<Modelos, String> clnFabricacao;
    @FXML
    private TableColumn<Modelos, String> clnPlaca;
    @FXML
    private TableColumn<Modelos, String> clnRenavam;

    private TableColumn<Modelos, String> clnChassi;

    Carros car = new Carros;
    Private Stage stage;
    Integer index = -1;
    Integer c = 0;

    void initialize() {
        //Colunas da TableView
        clnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clnMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        clnFabricacao.setCellValueFactory(new PropertyValueFactory<>("fabricacao"));
        clnPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        clnRenavam.setCellValueFactory(new PropertyValueFactory<>("renavam"));
        clnChassi.setCellValueFactory(new PropertyValueFactory<>("chassi"));

        this.carregarLista();

        tblCarros.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Carros car = tblCarros.getSelectionModel().getSelectedItem();
                    Modelo.setText(car.getModelo());
                    Marca.setText(car.getMarca());
                    Fabricacao.setText(car.getFabricacao());
                    Placa.setText(car.getPlaca());
                    Renavam.setText(car.getRenavam());
                    Chassi.setText(car.getChassi());


                    for (Object item : tblCarros.getItems()) {
                        if (item instanceof Marcas) {
                            Marcas marca = (Marcas) item;
                            if (marca.getId() == mod.getIdMarcas()) {
                                clnModelo.setValue(marca);
                                break;

                            }
                        }
                    }
                }
            }
        });
    }
}