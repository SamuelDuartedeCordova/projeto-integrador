package PJP2023.Projeto.integrador.model;

public class Veiculo {
    private int renavam;
    private String placa;
    private int ano_fabricacao;
    private String chassi;

    public Veiculo() {

    }

    public Veiculo(int renavam, String placa, int ano_fabricacao, String chassi) {
        this.renavam = renavam;
        this.placa = placa;
        this.ano_fabricacao = ano_fabricacao;
        this.chassi = chassi;
    }

    public int getRenavam() {
        return renavam;
    }

    public void setRenavam(int renavam) {
        this.renavam = renavam;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(int ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
}
