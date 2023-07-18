package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceMarca {

    private static ConexaoDatabase conexao = new ConexaoDatabase();

    public static List<Marcas> carregarMarcas(){
        List<Marcas> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from marcas;");

            while (rs.next()){
                //Marcas marcas = new Marcas(rs.getInt("id"), rs.getString("nome_do_fabricante"));
                //out.add(marcas);
                int id = rs.getInt("id");
                String nome = rs.getString("nome_do_fabricante");
                Marcas marcas = new Marcas(id, nome);
                out.add(marcas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void inserirMarcas(Marcas marcas){
        try {
            Connection conn = conexao.getConexao();

            String sqlInsert = "insert into public.marcas (nome_do_fabricante) values (?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setString(1, marcas.getMarca());


            pre.execute();

            pre.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletarMarcas(int idMarcas){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from public.marcas where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idMarcas);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarMarcas(int index, Marcas mar){
        try {
            Connection conn = conexao.getConexao();

            String updateSql = "update public.marcas set nome_do_fabricante = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setString(1, mar.getMarca());
            ps.setInt(2, index);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
