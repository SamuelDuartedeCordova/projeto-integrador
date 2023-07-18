package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceModelo {
    private static ConexaoDatabase conexao = new ConexaoDatabase();
    public static List<Marcas> carregarModelos(){
        List<Marcas> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from modelos;");


            while (rs.next()){
               /* Modelos modelos = new Modelos();
                modelos.setId(rs.getInt(1));
                modelos.setNome(rs.getString(2));
                modelos.setCambio(rs.getString(3));
                modelos.setCombustivel(rs.getString(4));
                modelos.setPotencia(rs.getInt(5));
                modelos.setCarroceria(rs.getString(6));
                modelos.setPortas(rs.getInt(7));
                modelos.setCor(rs.getString(8));
                out.add(modelos);*/
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return out;
    }

}
