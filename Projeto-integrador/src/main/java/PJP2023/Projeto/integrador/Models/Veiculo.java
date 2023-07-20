package PJP2023.Projeto.integrador.Models;

import java.math.BigInteger;
import java.util.Date;

public class Veiculo {

    private Integer id;
    private BigInteger renavam;
    private Integer anoFabricacao;
    private Integer idModelos;
    private String placa;
    private String chassi;
    private String nomeModelo;

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public Veiculo(Integer id, BigInteger renavam, Integer anoFabricacao, Integer idModelos, String placa, String chassi) {
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

    public BigInteger getRenavam() {
        return renavam;
    }

    public void setRenavam(BigInteger renavam) {
        this.renavam = renavam;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
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
