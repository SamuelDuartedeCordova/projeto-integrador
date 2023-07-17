package PJP2023.Projeto.integrador.Services;

import PJP2023.Projeto.integrador.Models.Veiculo;
import PJP2023.Projeto.integrador.database.ConexaoDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {

    private static ConexaoDatabase conexaoDatabase = new ConexaoDatabase();

    public static List<Veiculo> carregarVeiculo() throws SQLException, ClassNotFoundException {
        List<Veiculo> out = new ArrayList<>();
        Connection conn = conexaoDatabase.getConexao();
        Statement statement = conn.createStatement();
        ResultSet   resultSet = statement.executeQuery("SELECT * FROM public.veiculos");

        while (resultSet.next()){
            Veiculo veiculo = new Veiculo();
            veiculo.setRenavam(resultSet.getInt(1));
        }

    }
}
