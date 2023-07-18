package PJP2023.Projeto.integrador.Models;

import java.util.Date;

public class Veiculo {

    private Integer id;
    private Integer renavam;
    private Date anoFabricacao;
    private Integer idModelos;
    private String placa;
    private String chassi;

    public Veiculo(Integer id, Integer renavam, Date anoFabricacao, Integer idModelos, String placa, String chassi) {
        this.id = id;
        this.renavam = renavam;
        this.anoFabricacao = anoFabricacao;
        this.idModelos = idModelos;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Veiculo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRenavam() {
        return renavam;
    }

    public void setRenavam(Integer renavam) {
        this.renavam = renavam;
    }

    public Date getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Date anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getIdModelos() {
        return idModelos;
    }

    public void setIdModelos(Integer idModelos) {
        this.idModelos = idModelos;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
}
