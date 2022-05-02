package br.com.groupsoftware.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.groupsoftware.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

	@Test
	void testeValorPadraoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		// Testa se a excecao correta vai ser chamada
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}

	@Test
	void testeAbrirVizinhos() {
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);

		campo22.adicionarVizinho(campo11);

		campo.adicionarVizinho(campo22);
		campo.abrir(); // abre campo atual (33)

		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirVizinhosMinados() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();

		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);

		campo.adicionarVizinho(campo22);
		campo.abrir(); // abre campo atual (33)

		assertTrue(campo22.isAberto() && campo11.isFechado());
	}

	@Test
	void testeCampoDesvendado() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	void testeCampoProtegido() {
		campo.minar();
		campo.alternarMarcacao();
		assertTrue(campo.objetivoAlcancado());
	}

	@Test
	void testeMinasNaVizinhanca() {
		Campo vizinho1 = new Campo(4, 3);
		campo.adicionarVizinho(vizinho1);
		vizinho1.minar();

		Campo vizinho2 = new Campo(3, 2);
		campo.adicionarVizinho(vizinho2);
		vizinho2.minar();

		Campo vizinho3 = new Campo(3, 4);
		campo.adicionarVizinho(vizinho3);
		vizinho3.minar();

		long resultado = campo.minasNaVizinhanca();
		assertTrue(resultado == 3);
	}

	@Test
	void testeToStringMarcado() {
		campo.alternarMarcacao();
		assertTrue("x".equals(campo.toString()));
	}

	@Test
	void testeToStringAbertoMinado() {
		campo.abrir();
		campo.minar();
		assertTrue("*".equals(campo.toString()));
	}

	@Test
	void testeToStringAbertoMinasNaVizinhanca() {
		Campo vizinho1 = new Campo(4, 3);
		campo.adicionarVizinho(vizinho1);
		vizinho1.minar();

		Campo vizinho2 = new Campo(3, 2);
		campo.adicionarVizinho(vizinho2);
		vizinho2.minar();

		Campo vizinho3 = new Campo(3, 4);
		campo.adicionarVizinho(vizinho3);
		vizinho3.minar();

		campo.abrir();
		assertTrue("3".equals(campo.toString()));

	}

	@Test
	void testeToStringAberto() {
		campo.abrir();
		assertTrue(" ".equals(campo.toString()));
	}

	@Test
	void testeToStringIncognita() {
		assertTrue("?".equals(campo.toString()));
	}

	@Test
	void testeReiniciar() {
		campo.reiniciar();
		assertFalse(campo.isAberto() && campo.isMarcado() && campo.isMinado());
	}

}
