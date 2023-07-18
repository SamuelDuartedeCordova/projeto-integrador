package PJP2023.Projeto.integrador.Models;

public class Marcas {
    private Integer id;
    private String marca;

    public Marcas(Integer id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public Integer getId() {
        return id;
    }

    public Marcas() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
