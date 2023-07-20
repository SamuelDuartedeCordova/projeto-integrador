package PJP2023.Projeto.integrador.Models;

public class VeiculoInfo {
    private String clnModelo;
    private String clnMarca;
    private int clnFabricacao;
    private String clnPlaca;
    private long clnRenavam;

    public VeiculoInfo() {
    }

    public String getClnModelo() {
        return clnModelo;
    }

    public void setClnModelo(String clnModelo) {
        this.clnModelo = clnModelo;
    }

    public VeiculoInfo(String clnModelo, String clnMarca, int clnFabricacao, String clnPlaca, long clnRenavam, String clnChassi) {
        this.clnModelo = clnModelo;
        this.clnMarca = clnMarca;
        this.clnFabricacao = clnFabricacao;
        this.clnPlaca = clnPlaca;
        this.clnRenavam = clnRenavam;
        this.clnChassi = clnChassi;
    }

    public String getClnMarca() {
        return clnMarca;
    }

    public void setClnMarca(String clnMarca) {
        this.clnMarca = clnMarca;
    }

    public int getClnFabricacao() {
        return clnFabricacao;
    }

    public void setClnFabricacao(int clnFabricacao) {
        this.clnFabricacao = clnFabricacao;
    }

    public String getClnPlaca() {
        return clnPlaca;
    }

    public void setClnPlaca(String clnPlaca) {
        this.clnPlaca = clnPlaca;
    }

    public long getClnRenavam() {
        return clnRenavam;
    }

    public void setClnRenavam(long clnRenavam) {
        this.clnRenavam = clnRenavam;
    }

    public String getClnChassi() {
        return clnChassi;
    }

    public void setClnChassi(String clnChassi) {
        this.clnChassi = clnChassi;
    }

    private String clnChassi;
}
