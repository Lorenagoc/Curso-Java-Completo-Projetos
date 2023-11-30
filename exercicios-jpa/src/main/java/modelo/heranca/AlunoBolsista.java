package modelo.heranca;

import javax.persistence.Entity;

@Entity
public class AlunoBolsista extends Aluno {

	private double valorBolsa;

	public AlunoBolsista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlunoBolsista(Long matricula, String nome) {
		super(matricula, nome);
		// TODO Auto-generated constructor stub
	}

	public double getValorBolsa() {
		return valorBolsa;
	}

	public void setValorBolsa(double valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

}
