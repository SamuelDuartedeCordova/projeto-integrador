package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Veiculo;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    private static ConexaoDatabase conexaoDatabase = new ConexaoDatabase();

    public static List<Veiculo> carregarVeiculo() throws SQLException {
        List<Veiculo> out = new ArrayList<>();
        Connection conn = conexaoDatabase.getConexao();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.veiculos");

        while (resultSet.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(Integer.valueOf(String.valueOf(resultSet.getInt(1))));
            String renavamStr = resultSet.getString(2);
            veiculo.setRenavam(new BigInteger(renavamStr));
            veiculo.setPlaca(resultSet.getString(3));
            veiculo.setAnoFabricacao(resultSet.getInt(4));
            veiculo.setChassi(resultSet.getString(5));
            veiculo.setIdModelos(resultSet.getInt(6));
            veiculo.setCor(resultSet.getString(7));
            out.add(veiculo);
        }
        return out;
    }

    public static void salvarVeiculo(Veiculo veiculo) {
        try {
            Connection conn = conexaoDatabase.getConexao();
            String insertSql = "insert into veiculos (renavam, placa, ano_Fabricacao, chassi, id_modelos, cor) values (?,?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(insertSql);

            pre.setBigDecimal(1, new BigDecimal(veiculo.getRenavam()));
            pre.setString(2, veiculo.getPlaca());
            pre.setInt(3, veiculo.getAnoFabricacao());
            pre.setString(4, veiculo.getChassi());
            pre.setInt(5, veiculo.getIdModelos());
            pre.setString(6, veiculo.getCor());
            pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deletarVeiculo(int idModelo) {
        try {
            Connection conn = conexaoDatabase.getConexao();

            String deleteSql = "delete from veiculos where id = ?";

            PreparedStatement pre = conn.prepareStatement(deleteSql);
            pre.setInt(1, idModelo);

            return pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean atualizarVeiculo(int index2, Veiculo veiculo){
        try {
            Connection conn = conexaoDatabase.getConexao();

            String updateSql = "update veiculos set id_modelos = ?, renavam = ?, placa = ?, ano_Fabricacao = ?, chassi = ?, cor = ?  where id = ?";

            PreparedStatement pre = conn.prepareStatement(updateSql);

            pre.setInt(1, veiculo.getIdModelos());
            pre.setBigDecimal(2, new BigDecimal(veiculo.getRenavam()));
            pre.setString(3, veiculo.getPlaca());
            pre.setInt(4, veiculo.getAnoFabricacao());
            pre.setString(5, veiculo.getChassi());
            pre.setString(6, veiculo.getCor());
            pre.setInt(7, index2);

            return pre.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static int buscarIdModelo(String marca) {
        try {
            Connection conn = conexaoDatabase.getConexao();

            String selectSql = "select id from modelos where nome = '" + marca + "'";

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

}
