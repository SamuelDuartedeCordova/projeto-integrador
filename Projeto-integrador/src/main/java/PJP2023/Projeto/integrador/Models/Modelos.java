package PJP2023.Projeto.integrador.Models;

public class Modelos {

    private Integer id;
    private String nome;
    private String cambio;
    private String combustivel;
    private String carroceria;
    private String cor;
    private Integer potencia;
    private Integer idMarcas;

    public Modelos(Integer idMarcas) {
        this.idMarcas = idMarcas;
    }

    public Integer getIdMarcas() {
        return idMarcas;
    }

    public void setIdMarcas(Integer idMarcas) {
        this.idMarcas = idMarcas;
    }

    public Modelos(Integer id, String nome, String cambio, String combustivel, String carroceria, String cor, Integer potencia, Integer idMarcas) {
        this.id = id;
        this.nome = nome;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.carroceria = carroceria;
        this.cor = cor;
        this.potencia = potencia;
        this.portas = portas;
    }

    private Integer portas;

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Integer getPortas() {
        return portas;
    }

    public void setIdMarcas(Integer idMarcas) {
        this.idMarcas = idMarcas;
    }
}
