import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Teatro implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cadeira[] cadeiras = new Cadeira[100];
	


	public Teatro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo utilizado para ocupar lugar no teatro
	 * 
	 * @param cadeira
	 * @return
	 */
	public boolean ocupar(int posicao, Cadeira cadeira) {

		try {
			
			this.cadeiras[cadeira.getPosicao() - 1] = cadeira;


		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * Metodo utilizado para desocupar lugar no teatro
	 * 
	 * @param cadeira
	 * @return
	 */
	public boolean desocupar(int posicao) {

		try {

			this.cadeiras[posicao - 1] = null;

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * Verifica se a posição esta ocupada
	 * 
	 * @param posicao
	 *            contendo a posição a ser ocupada
	 * @return <boolean> true se estiver livre ou false se estiver ocupado
	 */
	public boolean isDisponivel(int posicao) {

		int posicaoVetor = posicao - 1;
		if (this.cadeiras[posicaoVetor] == null) {
			return true;
		}

		return false;
	}

	/**
	 * Consulta CPF nas cadeiras
	 * 
	 * @param cpf
	 * @return
	 */
	public Cadeira consultarLugarPorCpf(String cpf) {

		for (Cadeira cadeira : this.cadeiras) {
			if (cadeira != null) {
				if (cadeira.getUsuario().getCpf().equalsIgnoreCase(cpf)) {
					return cadeira;
				}
			}
		}

		return null;
	}

	public Cadeira listarCadeirasVazias() {
		String textArea = null;
		/* Janela de texto 20 x 25 */
		JTextArea ta = new JTextArea(20, 25);
		for (int posicao = 0; posicao < this.cadeiras.length; posicao++) {
			if (this.cadeiras[posicao] == null) {
				/* String contendo a ArrayList */
				textArea = ("[" + (posicao + 1) + "] - Vazia\n");
				/* Adiciona conteúdo da string "TextArea" na Janela de texto */
				ta.append(textArea);
			}
		}

		/* Torna o arquivo não editável */
		ta.setEditable(false);
		/* Mostra o contrato na Janela de Texto */
		JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Teatro", 2, null);

		return null;
	}

	public Cadeira listarCadeirasOcupadas() {
		
//		Cliente cliente = new Cliente();
//		cliente.lerPoltrona();
		
		String textArea = null;
		/* Janela de texto 20 x 25 */
		JTextArea ta = new JTextArea(20, 25);

		for (int posicao = 0; posicao < this.cadeiras.length; posicao++) {
			if (this.cadeiras[posicao] != null) {
				/* String contendo a ArrayList */
				textArea = ("[" + (posicao + 1) + "] - Ocupado\n");
				/* Adiciona conteúdo da string "TextArea" na Janela de texto */
				ta.append(textArea);
			}
		}
		
		/* Torna o arquivo não editável */
		ta.setEditable(false);
		/* Mostra o contrato na Janela de Texto */
		JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Teatro", 2, null);

		return null;
	}

}
