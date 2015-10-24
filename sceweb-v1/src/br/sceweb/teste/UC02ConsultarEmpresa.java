package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {
	
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

	@Test
	public void CT01UC02ConsultarEmpresaComSucesso() {
		empresaDao.adiciona(empresa);
		assertTrue(empresa.getCnpj().equals("89424232000180"));
	    assertTrue(empresa.equals(empresaDao.consultaEmpresas("89424232000180")));
		empresaDao.exclui("89424232000180");

	}
	
	@Test
	public void CT02UC02ConsultarTodasEmpresaComSucesso() {
		empresaDao.adiciona(empresa);
		Empresa empresa2 = new Empresa();
		empresa2.setNomeDaEmpresa("empresa y");
		empresa2.setCnpj("76866310000155");
		empresa2.setNomeFantasia("empresa y");
		empresa2.setEndereco("rua qualquer");
		empresa2.setTelefone("1111");
		empresaDao.adiciona(empresa2);
		assertEquals(2,empresaDao.consultaEmpresas().size());
		empresaDao.exclui("89424232000180");
		empresaDao.exclui("76866310000155");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDao.exclui("89424232000180");
		empresaDao.exclui("76866310000155");
	}

	

}
