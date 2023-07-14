package PJP2023.Projeto.integrador.model;

public class Modelo {
    private int id;
    private String nome;
    private String cambio;
    private String combustivel;
    private int potencia;
    private int cilindrada;
    private String carroceria;
    private int portas;
    private String cor;

    public Modelo() {

    }

    public Modelo(int id, String nome, String cambio, String combustivel, int potencia, int cilindrada, String carroceria, int portas, String cor) {
        this.id = id;
        this.nome = nome;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.potencia = potencia;
        this.cilindrada = cilindrada;
        this.carroceria = carroceria;
        this.portas = portas;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
