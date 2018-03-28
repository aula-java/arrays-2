package br.edu.ifrs.canoas.java.array;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {

	Empresa empresa;
	int quantidadeFuncionarios = 10;

	@Before
	public void setup() {
		empresa = new Empresa();
		empresa.funcionarios = new Funcionario[quantidadeFuncionarios];
	}

	@Test
	public void testAdiciona11Funcionarios() {
		Funcionario meuFuncionario;
		//Adiciona funcion�rio 0 at� 9
		for (int i = 0; i < quantidadeFuncionarios; i++) {
			meuFuncionario = criaFuncionario("Funcionario " + i, 1000 + i * 100);
			assertTrue("Testa adicicionar o funcionario "+i, empresa.adiciona(meuFuncionario));
		}
		
		//Adiciona funcionario 10
		meuFuncionario = criaFuncionario("Funcionario 10", 1100);
		assertFalse("Nao eh possivel adicionar o funcionario 10, limite estourado", empresa.adiciona(meuFuncionario));
	}

	@Test
	public void testAdicionarFuncionarioNulo() {
		assertFalse("N�o eh permitido inserir funcionario NULL", empresa.adiciona(null));
	}
	
	
	@Test
	public void testRecuperarSalarioFuncionariosCadastrados() {
		
		Funcionario f = criaFuncionario("Funcionario 1", 100);
		empresa.adiciona(f);
		assertEquals("O salario total deve ser 100", 100, empresa.getSalarioTodosFuncionarios(), 0);
		
		f = criaFuncionario("Funcionario 2", 100);
		empresa.adiciona(f);
		assertEquals("O salario total deve ser 100+100", 200, empresa.getSalarioTodosFuncionarios(), 0);

		f = criaFuncionario("Funcionario 3", 200);
		empresa.adiciona(f);
		assertEquals("O salario total deve ser 100+100+200", 400, empresa.getSalarioTodosFuncionarios(), 0);

		f = criaFuncionario("Funcionario 4", 300);
		empresa.adiciona(f);
		assertEquals("O salario total deve ser 700", 700, empresa.getSalarioTodosFuncionarios(), 0);
		
	}
	
	@Test
	public void testVerificaSeFuncionarioEhDaEmpresa() {
		
		Funcionario func1 = criaFuncionario("Funcionario 1", 100);
		Funcionario func2 = criaFuncionario("Funcionario 2", 100);
		
		empresa.adiciona(func1);
		
		assertTrue("O Funcionario 1 pertence a empresa", empresa.contem(func1));
		assertFalse("O Funcionario 2 NAO pertence a empresa", empresa.contem(func2));
		assertFalse("O Funcionario NULL NAO pertence a empresa", empresa.contem(null));
		
	}
	
	
	@Test
	public void testVerificaDemissaoDeFuncionario() {
		Funcionario funcionarios[] = new Funcionario[quantidadeFuncionarios];
		assertEquals("O array vazio deve retornar 0, pois n�o ha quem demitir", empresa.demiteFuncionarios(funcionarios), 0);

		funcionarios[0] = criaFuncionario("Funcionario 0", 100);
		funcionarios[1] = criaFuncionario("Funcionario 1", 100);
		
		empresa.adiciona(funcionarios[0]);
		assertEquals("Somente o funcionario 0 pode ser demitido, entao deve retornar 1", empresa.demiteFuncionarios(funcionarios), 1);

		empresa.adiciona(funcionarios[1]);
		assertEquals("Os dois funcionarios foram adicionados e podem ser demitidos", empresa.demiteFuncionarios(funcionarios), 2);
		
		assertNotSame("N�o pode retornar 3, pois tem apenas 2 funcionarios", empresa.demiteFuncionarios(funcionarios), 3);

	}

	private Funcionario criaFuncionario(String nome, double quantidade) {
		Funcionario f = new Funcionario();
		f.nome = nome;
		f.bonifica(quantidade);
		return f;
	}
	
}
