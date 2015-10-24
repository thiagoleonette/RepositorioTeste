package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	
	static Empresa empresa;
	static EmpresaDAO empresaDao;

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
		empresaDao.excluir(empresa.getCnpj());
		assertEquals(1, empresaDao.adicionar(empresa));
		empresaDao.excluir(empresa.getCnpj());
	}
	
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastrar_empresa_com_cnpj_ja_cad(){
		empresaDao.adicionar(empresa);
		assertEquals(0, empresaDao.adicionar(empresa));
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
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDao.excluir(empresa.getCnpj());
	}

}
