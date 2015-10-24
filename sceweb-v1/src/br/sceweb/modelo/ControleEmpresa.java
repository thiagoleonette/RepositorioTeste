package br.sceweb.modelo;

public class ControleEmpresa {
	EmpresaDAO empresaDAO;

	public ControleEmpresa() {
		empresaDAO = new EmpresaDAO();
	}

	public String cadastrarEmpresa(String cnpj, String nomeDaEmpresa, String nomeFantasia, String endereco,
			String telefone) {
		String msg = "";
		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		try {
			empresa.setCnpj(cnpj);
			empresa.setNomeDaEmpresa(nomeDaEmpresa);
			empresa.setNomeFantasia(nomeFantasia);
			empresa.setEndereco(endereco);
			empresa.setTelefone(telefone);
			empresaDAO.adiciona(empresa);
			msg = "cadastro realizado com sucesso";

		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}

	public String excluirEmpresa(String cnpj) {
		String msg = "";
		try {
			empresaDAO.exclui(cnpj);
			msg = "excluido com sucesso";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
}
