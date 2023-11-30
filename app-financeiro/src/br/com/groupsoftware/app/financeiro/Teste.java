package br.com.groupsoftware.app.financeiro;

import br.com.groupsoftware.app.calculo.Calculadora;

public class Teste {

	public static void main(String[] args) {

		Calculadora calc = new Calculadora();
		System.out.println("Resultado = " + calc.soma(2, 3, 4));
	}
}
