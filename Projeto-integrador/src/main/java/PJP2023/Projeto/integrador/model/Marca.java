package PJP2023.Projeto.integrador.model;

public class Marca {
    private String nome_do_fabricante;
    public Marca() {

    }

    public Marca(String nome_do_fabricante) {
        this.nome_do_fabricante = nome_do_fabricante;
    }

    public String getNome_do_fabricante() {
        return nome_do_fabricante;
    }

    public void setNome_do_fabricante(String nome_do_fabricante) {
        this.nome_do_fabricante = nome_do_fabricante;
    }
}
