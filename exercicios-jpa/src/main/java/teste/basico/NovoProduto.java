package teste.basico;

import infra.DAO;
import modelo.basico.Produto;

public class NovoProduto {

	public static void main(String[] args) {

		Produto produto = new Produto("Monitor 23", 789.98);

		DAO<Produto> dao = new DAO<>(Produto.class);
		
//		dao.abrirTransacao().incluirTransacao(produto).commitTransacao().fecharDAO();
		dao.incluirAtomicamente(produto).fecharDAO();
		System.out.println("Id do " + produto.getNome() + " é: " + produto.getId());
	}
}