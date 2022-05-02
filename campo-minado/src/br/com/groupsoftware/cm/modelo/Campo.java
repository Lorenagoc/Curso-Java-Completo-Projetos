package br.com.groupsoftware.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.groupsoftware.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean minado = false;
	private boolean aberto = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<Campo>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVizinho(Campo supostoVizinho) {

		boolean linhaDiferente = linha != supostoVizinho.linha;
		boolean colunaDiferente = linha != supostoVizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int diferencaLinhas = Math.abs(linha - supostoVizinho.linha);
		int diferencaColunas = Math.abs(coluna - supostoVizinho.coluna);
		int somatorio = diferencaLinhas + diferencaColunas;

		if (somatorio == 1 && !diagonal) {
			vizinhos.add(supostoVizinho);
			return true;
		} else if (somatorio == 2 && diagonal) {
			vizinhos.add(supostoVizinho);
			return true;
		} else {
			return false;
		}
	}

	void alternarMarcacao() {

		if (!aberto) {
			marcado = !marcado;
		}
	}

	boolean abrir() {

		if (!aberto && !marcado) {
			aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;

		} else {

			return false;
		}
	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void minar() {
		minado = true;
	}

	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
}