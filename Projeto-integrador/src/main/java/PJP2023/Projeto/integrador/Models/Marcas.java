package PJP2023.Projeto.integrador.Models;

public class Marcas {

    public Marcas(int id, String nome) {
        this.nome = nome;
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
