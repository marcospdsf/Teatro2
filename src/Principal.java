import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

/*Classe principal(Menu)*/
public class Principal extends Cliente {

	/* Menu */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
		Calendar data = Calendar.getInstance();
		CadastroUsuario cadastro = new CadastroUsuario();
		Usuario usuario = null;
		Teatro teatro = new Teatro();
		Cliente cliente = new Cliente();
		Object[] menu = { "Usuario", "Cadeiras", "Exibir",	"Gerenciar Arquivos", "\nSair" };
		Object[] pessoas = { "Cadastrar Usuario", "Consultar Usuarios", "Consultar Usuarios por CPF", "Cancelar" };
		Object[] cadeiras = { "Ocupar Cadeira", "Desocupar Cadeira"};
		Object[] exibir = { "Exibir Cadeiras Ocupadas", "Exibir Cadeiras Vazias" };
		Object[] arquivos = { "Salvar Poltronas", "Carregar Poltronas" };


		int opcao = 0;

		do {/* Inicio do laço de repetição que só para de "opcao" = 3 */

			/* Menu de opções */
			opcao = JOptionPane.showOptionDialog(null, "*****************TEATRO*****************", "Teatro",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, menu, menu[0]);

			switch (opcao) {/* Início do Switch do Menu */
			case 0:/* Usuario */
				int opcaoPessoaCadastro = JOptionPane.showOptionDialog(null, "Escolha a opção:", "Teatro",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pessoas, pessoas[0]);

				/* Cadastro de cliente */
				if (opcaoPessoaCadastro == 0) {
					cadastro.cadastro();
					/* if para se o usuário não saiu do programa */
					if (saiu == false) {
						cadastro.gerarContrato();
						cadastro.salvarCadastro();
					}
				} else if (opcaoPessoaCadastro == 1) {
					lerCadastro();
				} else if (opcaoPessoaCadastro == 2) {
					mostraContrato();
				} else {
					break;
				}

				break;

			case 1:/* Mostra ações com cadeiras através de entrada do usuário */
				int opcaoCadeira = JOptionPane.showOptionDialog(null, "Opções para Cadeiras:", "Teatro",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, cadeiras, cadeiras[0]);

				if (opcaoCadeira == 0) {
					
					
					
					try {
					
						usuario = cliente.consultarUsuario();

						if (usuario != null && usuario.equals("") == false) {

							String posicao = JOptionPane.showInputDialog("Informe o N da poltrona");
							int nPosicao = new Integer(posicao);

							Cadeira cadeira = new Cadeira(nPosicao, usuario);
							teatro.ocupar(nPosicao, cadeira);

							FileOutputStream fos = new FileOutputStream(formatoData.format(data.getTime()) + ".Poltrona.bin");
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(teatro);
							oos.flush();

							oos.close();
							fos.close();

						} else if (usuario != null && usuario.equals("") == true) {
							throw new DigitouNada();
						}
					} catch (NullPointerException ex) {

					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Arquivo de cadastro não existe!");
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, "Digito invalido!");
					} catch (DigitouNada e) {
					}

				} else if (opcaoCadeira == 1) {
					try {
						String posicao = JOptionPane.showInputDialog("Informe o N da poltrona");
						if (usuario != null && usuario.equals("") == false){
						int nPosicao = new Integer(posicao);
						teatro.desocupar(nPosicao);
						
						
						} else if (usuario != null && usuario.equals("") == true) {
							throw new DigitouNada();
						}  else {
							break;
						}
						
					} catch (NullPointerException ex) {

					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, "Digito invalido!");
					} catch (DigitouNada e) {
					}

				} else {
					break;
				}
				break;

			case 2:/* Lê cadastros já realizados no programa */
				int opcaoPessoa = JOptionPane.showOptionDialog(null, "Listar cadeiras:", "Teatro",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, exibir, exibir[0]);
				if (opcaoPessoa == 0) {
					teatro.listarCadeirasOcupadas();

				} else if (opcaoPessoa == 1) {
					teatro.listarCadeirasVazias();

				} else {
					break;
				}
				break;
			case 3:/* Salvar ou Carregar dados antigos */
				int arquivosPoltrona = JOptionPane.showOptionDialog(null, "Gerenciar arquivos:", "Teatro",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, arquivos, arquivos[0]);
				if (arquivosPoltrona == 0) {
					try {
					FileOutputStream fos = new FileOutputStream(formatoData.format(data.getTime()) + ".Poltrona.bin");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(teatro);
					oos.flush();

					oos.close();
					fos.close();
					} catch (NullPointerException ex) {

					}

				} else if (arquivosPoltrona == 1) {
					cliente.lerPoltrona();

				} else {
					break;
				}
				break;


			case 4:/* Sair do programa */
				break;

			}/* Fim do Switch do Menu */
		} while (opcao != 3);/*
								 * Fim do laço de repetição que só para de
								 * "opcao" = 3
								 */
	}
}
