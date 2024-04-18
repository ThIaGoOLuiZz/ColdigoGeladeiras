package br.com.coldigogeladeiras.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.jdbcinterface.ProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;

public class JDBCProdutoDAO implements ProdutoDAO{
	private Connection conexao;
	
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public boolean inserir(Produto produto) {
		
		String comando = "INSERT INTO produtos " 
					+ "(id, categoria, modelo, capacidade, valor, marcas_id)"
					+ "VALUES (?,?,?,?,?,?)";
		
		PreparedStatement p;
		
		try {
			//PREPARA O COMANDO PARA EXECUÇÃO NO BD EM QUE NOS CONECTAMOS
			p = this.conexao.prepareStatement(comando);
			
			//SUBSTITUI NO COMANDO OS "?" PELOS VALORES DO PRODUTO
			p.setInt(1, produto.getId());
			p.setString(2, produto.getCategoria());
			p.setString(3, produto.getModelo());
			p.setInt(4, produto.getCapacidade());
			p.setFloat(5, produto.getValor());
			p.setInt(6, produto.getMarcaId());
			
			//EXECUTA COMANDO NO BD
			p.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<JsonObject> buscarPorNome(String nome){
		
		String comando = "SELECT produtos.*, marcas.nome AS marca FROM produtos INNER JOIN marcas ON produtos.marcas_id = marcas.id";
		
		if(!nome.equals("")) {
			comando += " WHERE modelo LIKE '%" + nome + "%'"; 
		}
		
		comando += " ORDER BY categoria ASC, marcas.nome ASC, modelo ASC";
		
		List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
		JsonObject produto = null;
		
		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String categoria = rs.getString("categoria");
				String modelo = rs.getString("modelo");
				int capacidade = rs.getInt("capacidade");
				float valor = rs.getFloat("valor");
				String marcaNome = rs.getString("marca");
				
				if(categoria.equals("1")) {
					categoria = "Geladeira";
				}else if(categoria.equals("2")){
					categoria = "Freezer";
				}
				
				produto = new JsonObject();
				produto.addProperty("id", id);
				produto.addProperty("categoria", categoria);
				produto.addProperty("modelo", modelo);
				produto.addProperty("capacidade", capacidade);
				produto.addProperty("valor", valor);
				produto.addProperty("marcaNome", marcaNome);
				
				listaProdutos.add(produto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaProdutos;
		
		
	}
}