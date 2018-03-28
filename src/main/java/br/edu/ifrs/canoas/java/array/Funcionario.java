package br.edu.ifrs.canoas.java.array;

public class Funcionario {
	String nome;
	double salario = 0;
	boolean estaNaEmpresa = true;

	public void bonifica(double aumento) {
		salario += aumento;
	}

	public void demite() {
		estaNaEmpresa = false;
	}

	public boolean igual(Funcionario func) {
		if (this == func) {
			return true;
		} else {
			return false;
		}
	}
	
}
