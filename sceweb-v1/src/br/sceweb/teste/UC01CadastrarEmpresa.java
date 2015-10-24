package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.ControleEmpresa;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	
	static Empresa empresa;
	static EmpresaDAO empresaDao;
	static ControleEmpresa ce;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresa = new Empresa();
		empresaDao = new EmpresaDAO();
		
		empresa.setCnpj("89424232000180");
		empresa.setEndereco("rua taquari");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("empresa x");
		empresa.setTelefone("2222");
	}

	/*
	 * CT01 - Caso de Teste 01
	 * UC01 - Caso de Uso 01
	 * FB - Fluxo Básico
	 */
	@Test
	public void CT01UC01FBCadastrar_empresa_com_sucesso() {
		empresaDao.exclui(empresa.getCnpj());
		assertEquals(1, empresaDao.adiciona(empresa));
		empresaDao.exclui(empresa.getCnpj());
	}
	
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastrar_empresa_com_cnpj_ja_cad(){
		empresaDao.adiciona(empresa);
		assertEquals(0, empresaDao.adiciona(empresa));
	}
	
	@Test
	public void CT03UC01A3Cadastra_empresa_cnpj_invalido(){
		Empresa empresa2 = new Empresa();
		try {
			empresa2.setCnpj("11111111111111");
			fail("deveria disparar uma exception");
		} catch (Exception e) {
			assertEquals("CNPJ inválido!", e.getMessage() );
		}
	}
	
	@Test
	public void CT04UC01A4Cadastra_empresa_com_dados_invalidos(){
		Empresa empresa2 = new Empresa();
		try {
			empresa2.setNomeDaEmpresa("");
			fail("deveria disparar uma exception");
		} catch (Exception e) {
			assertEquals("nome da empresa inválido!", e.getMessage() );
		}
	}
	
	@Test
	public void CT05UC01A5Cadastra_empresa_com_sucesso_pelo_controle(){
		ControleEmpresa ce = new ControleEmpresa();
		ce.excluirEmpresa(empresa.getCnpj());
		assertEquals("cadastro realizado com sucesso", ce.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(),
				empresa.getNomeFantasia(),empresa.getEndereco(),empresa.getTelefone()));
		ce.excluirEmpresa(empresa.getCnpj());
	}
	
	@Test(expected = RuntimeException.class)
	public void CT06UC01A2Cadastrar_empresa_com_cnpj_ja_cad_pelo_controle(){
		assertEquals("cadastro realizado com sucesso", ce.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(),
				empresa.getNomeFantasia(),empresa.getEndereco(),empresa.getTelefone()));
		assertEquals("cadastro realizado com sucesso", ce.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(),
				empresa.getNomeFantasia(),empresa.getEndereco(),empresa.getTelefone()));
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDao.exclui(empresa.getCnpj());
	}

}
