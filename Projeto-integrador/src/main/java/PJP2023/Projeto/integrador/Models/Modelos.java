package PJP2023.Projeto.integrador.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Modelos {
    public Modelos() {
    }

    private Integer id;
    private String nome;
    private String cambio;
    private String combustivel;
    private String carroceria;
    private Integer potencia;
    private Integer idMarcas;
    private Integer portas;
    private String nomeMarca;

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    public Modelos(Integer id, String nome, String cambio, String combustivel, String carroceria, Integer potencia, Integer idMarcas, Integer portas) {
        this.id = id;
        this.nome = nome;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.carroceria = carroceria;
        this.potencia = potencia;
        this.idMarcas = idMarcas;
        this.portas = portas;
    }



    public Modelos(Integer id, String nome, String cambio, String combustivel, String carroceria, Integer potencia, Integer idMarcas) {
        this.id = id;
        this.nome = nome;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.carroceria = carroceria;
        this.potencia = potencia;
        this.portas = portas;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getIdMarcas() {
        return idMarcas;
    }

    public void setIdMarcas(Integer idMarcas) {
        this.idMarcas = idMarcas;
    }

    public Integer getPortas() {
        return portas;
    }

    public void setPortas(Integer portas) {
        this.portas = portas;
    }
}
