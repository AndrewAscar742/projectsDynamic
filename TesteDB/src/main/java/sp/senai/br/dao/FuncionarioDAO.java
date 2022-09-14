package sp.senai.br.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Cargo;
import models.Funcionario;

public class FuncionarioDAO {
	private final String dbURL = "jdbc:mysql://localhost:3307/Empresa";
	private final String username = "root";
	private final String password = "";
	private Connection conn = null;
	private List<Funcionario> lista = new ArrayList<>();

	public List<Funcionario> listagem() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			ResultSet result = null;

			if (conn != null) {
				Statement statement = conn.createStatement();
				result = statement.executeQuery("SELECT * FROM tb_funcionario");
			}

			while (result.next()) {
				// result.getString(0), ele pega a coluna pelo index

				String nome = result.getString("nome");
				String cpf = result.getString("cpf");
				String cargo = result.getString("cargo");

				Funcionario fun = new Funcionario(nome, cpf, Enum.valueOf(Cargo.class, cargo));
				lista.add(fun);

			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}

		return lista;

	}

	public void alterar(String nome, String cpf, Cargo cargo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			
			PreparedStatement stm = conn.prepareStatement("UPDATE tb_funcionario set nome=(?), cpf=(?),"
					+ "cargo(?) WHERE id = 2");
			
			stm.setString(1, nome);
			stm.setString(2, cpf);
			stm.setString(3, cargo.name());
			
			stm.execute();
			stm.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void novo(Funcionario f) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);

			PreparedStatement stm = conn
					.prepareStatement("INSERT INTO tb_funcionario " + "(nome, cpf, cargo) VALUES(?,?,?)");

			stm.setString(1, f.getNome());
			stm.setString(2, f.getCpf());
			stm.setString(3, f.getCargo());
			
			stm.execute();
			stm.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover() {

	}
}
