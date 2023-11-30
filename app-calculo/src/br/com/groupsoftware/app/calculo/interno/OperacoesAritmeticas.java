package br.com.groupsoftware.app.calculo.interno;

import java.util.Arrays;

public class OperacoesAritmeticas {

	public double soma(double... nums) {

		return Arrays.stream(nums).reduce(0.0, (total, v_atual) -> total + v_atual);
	}
}
