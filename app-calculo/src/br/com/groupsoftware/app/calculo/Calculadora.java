package br.com.groupsoftware.app.calculo;

import br.com.groupsoftware.app.calculo.interno.OperacoesAritmeticas;
import br.com.groupsoftware.app.logging.Logger;

public class Calculadora {

	private OperacoesAritmeticas opAritmetica = new OperacoesAritmeticas();

	public double soma(double... nums) {
		Logger.info("Somando...");
		return opAritmetica.soma(nums);
	}
	
	public Class<Logger> getLoggerClass(){
		return Logger.class;
	}
}
