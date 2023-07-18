package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Veiculo;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {

    private static ConexaoDatabase conexaoDatabase = new ConexaoDatabase();

    public static List<Veiculo> carregarVeiculo() throws SQLException {
        List<Veiculo> out = new ArrayList<>();
        Connection conn = conexaoDatabase.getConexao();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.veiculos");

        while (resultSet.next()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(Integer.valueOf(String.valueOf(resultSet.getInt(1))));
            veiculo.setRenavam(resultSet.getInt(2));
            veiculo.setAnoFabricacao(resultSet.getDate(3));
            veiculo.setIdModelos(resultSet.getInt(4));
            veiculo.setPlaca(resultSet.getString(5));
            veiculo.setChassi(resultSet.getString(6));

            out.add(veiculo);
        }

        return out;
    }

    public static void salvarVeiculo(Veiculo veiculo) {
        try {

            Connection conn = conexaoDatabase.getConexao();

            String insertSql = "insert into veiculos (id, renavam, placa, ano_Fabricacao, chassi, id_modelo) values (?,?,?,?,?,?))";

            PreparedStatement pre = conn.prepareStatement(insertSql);

            pre.setInt(1, veiculo.getId());
            pre.setInt(2, veiculo.getRenavam());
            pre.setString(3, veiculo.getPlaca());
            pre.setDate(4, (Date) veiculo.getAnoFabricacao());
            pre.setString(5, veiculo.getChassi());
            pre.setInt(6, veiculo.getIdModelos());

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

            String updateSql = "update veiculos set id = ?, renavam = ?, placa = ?, ano_Fabricacao = ?, chassi = ?  where id_modelo = ?";

            PreparedStatement pre = conn.prepareStatement(updateSql);

            pre.setInt(1, veiculo.getId());
            pre.setInt(2, veiculo.getRenavam());
            pre.setString(3, veiculo.getPlaca());
            pre.setDate(4, (Date) veiculo.getAnoFabricacao());
            pre.setString(5, veiculo.getChassi());
            pre.setInt(6, veiculo.getIdModelos());
            pre.setInt(7, index2);

            return pre.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }

}
