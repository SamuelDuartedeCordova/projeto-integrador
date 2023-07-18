package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Marcas;
import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceModelo {
    private static ConexaoDatabase conexao = new ConexaoDatabase();
    public static List<Modelos> carregarModelos(){
        List<Modelos> out = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("select * from public.modelos;");


            while (rs.next()){

                Modelos modelos = new Modelos();
               
                modelos.setId(rs.getInt(1));
                modelos.setNome(rs.getString(2));
                modelos.setCambio(rs.getString(3));
                modelos.setCombustivel(rs.getString(4));
                modelos.setPotencia(rs.getInt(5));
                modelos.setCarroceria(rs.getString(6));
                modelos.setPortas(rs.getInt(7));
                modelos.setCor(rs.getString(8));
                out.add(modelos);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void inserirModelos(Modelos modelos){
        try {
            Connection conn = conexao.getConexao();

            String sqlInsert = "insert into public.modelos (id, nome, cambio, combustivel, potencia, carroceria, portas, cor) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setInt(1, modelos.getId());
            pre.setString(2, modelos.getNome());
            pre.setString(3, modelos.getCambio());
            pre.setString(4, modelos.getCombustivel());
            pre.setInt(5, modelos.getPotencia());
            pre.setString(6, modelos.getCarroceria());
            pre.setInt(7, modelos.getPortas());
            pre.setString(8, modelos.getCor());


            pre.execute();

            pre.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletarModelos(int idModelos){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from public.marcas where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idModelos);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarModelos(int idMarcas, Modelos modelos){
        try {
            Connection conn = conexao.getConexao();

            String updateSql = "update public.modelos (id, nome, cambio, combustivel, potencia, carroceria, portas, cor) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setInt(1, modelos.getId());
            ps.setString(2, modelos.getNome());
            ps.setString(3, modelos.getCambio());
            ps.setString(4, modelos.getCombustivel());
            ps.setInt(5, modelos.getPotencia());
            ps.setString(6, modelos.getCarroceria());
            ps.setInt(7, modelos.getPortas());
            ps.setString(8, modelos.getCor());

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
