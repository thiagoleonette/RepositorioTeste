package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpresaDAO {

	public int adicionar(Empresa empresa) {
		PreparedStatement ps;
		int codigoRetorno = 0;

		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			String query = "insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) values (?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeDaEmpresa());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}

	public int excluir(String cnpj){
		PreparedStatement ps;
		int codigoRetorno = 0;
		
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			String query = "delete from empresa where cnpj = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, cnpj);
			codigoRetorno = ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}
}
