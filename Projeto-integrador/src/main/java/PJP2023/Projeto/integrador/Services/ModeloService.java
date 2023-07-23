package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Modelos;
import PJP2023.Projeto.integrador.Models.VeiculoInfo;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ModeloService {
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
                modelos.setIdMarcas(rs.getInt(8));

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

            String sqlInsert = "insert into public.modelos (nome, cambio, combustivel, potencia, carroceria, portas, id_marcas) values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = conn.prepareStatement(sqlInsert);
            pre.setString(1, modelos.getNome());
            pre.setString(2, modelos.getCambio());
            pre.setString(3, modelos.getCombustivel());
            pre.setInt(4, modelos.getPotencia());
            pre.setString(5, modelos.getCarroceria());
            pre.setInt(6, modelos.getPortas());
            pre.setInt(7, modelos.getIdMarcas());


            pre.execute();

            pre.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletarModelos(int index){
        try {
            Connection conn = conexao.getConexao();

            String deleteSql = "delete from public.modelos where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, index);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean atualizarModelos(int index, Modelos modelos){
        try {
            Connection conn = conexao.getConexao();
            String updateSql = "update public.modelos set nome = ?, cambio = ?, combustivel = ?, potencia = ?, carroceria = ?, portas = ?, id_marcas = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(updateSql);
            ps.setString(1, modelos.getNome());

            try{
                ps.setString(2, modelos.getCambio());
            }catch (Exception e){
                System.out.println("Teste Cambio");
            }

            ps.setString(3, modelos.getCombustivel());

            try{
                ps.setInt(4, modelos.getPotencia());
            }catch (Exception e){
                ps.setNull(4, Types.INTEGER);
            }

            ps.setString(5, modelos.getCarroceria());
            ps.setInt(6, modelos.getPortas());
            ps.setInt(7, modelos.getIdMarcas());
            ps.setInt(8, index);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static int buscarIdMarca(String marca) {
        try {
            Connection conn = conexao.getConexao();

            String selectSql = "select id from marcas where nome_do_fabricante = '" + marca + "'";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Retorna -1 se a marca não for encontrada
    }

    public static Modelos carregarModeloId(int id) {
        return carregarModelos().stream()
                .filter(modelos -> modelos.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<VeiculoInfo> carregarInformacoesVeiculos() {
        List<VeiculoInfo> veiculoInfoList = new ArrayList<>();

        try {
            Connection conn = conexao.getConexao();
            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT m.nome_do_fabricante, mo.nome, v.ano_fabricacao, v.placa, v.renavam, v.chassi " +
                    "FROM veiculos v " +
                    "INNER JOIN modelos mo ON v.id_modelos = mo.id " +
                    "INNER JOIN marcas m ON mo.id_marcas = m.id;");

            while (rs.next()) {
                VeiculoInfo veiculoInfo = new VeiculoInfo();
                veiculoInfo.setClnModelo(rs.getString("nome"));
                veiculoInfo.setClnMarca(rs.getString("nome_do_fabricante"));
                veiculoInfo.setClnFabricacao(rs.getInt("ano_fabricacao"));
                veiculoInfo.setClnPlaca(rs.getString("placa"));
                veiculoInfo.setClnRenavam(rs.getLong("renavam"));
                veiculoInfo.setClnChassi(rs.getString("chassi"));

                veiculoInfoList.add(veiculoInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculoInfoList;
    }

}
