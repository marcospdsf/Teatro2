import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class CadastroUsuario extends Cliente implements Interface {
	ChecarEntrada checar = new ChecarEntrada();

	private String cpf;
	int i;

	public void cadastro() {
		do {
			cancelar = false;
			ok = false;
			saiu = false;
			/* Entrada do nome do cliente */
			do {
				try {
					cliente = JOptionPane.showInputDialog("Digite o nome do cliente:");

					if (cliente != null && cliente.length() > 0) {
						ok = true;
					} else if (cliente.length() == 0 && cliente != null) {
						throw new DigitouNada();
					}
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DigitouNada ex) {

				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			/* Entrada do CPF do cliente */
			do {
				try {
					cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

					File check = new File(cpf + ".txt");
					if (check.exists() == true) {
						throw new IOException();
					} else if (checar.isValidCPF(cpf) == true && cpf != null && cpf.length() > 0) {
						ok = true;
					} else if (cpf != null && checar.isValidCPF(cpf) == false) {
						throw new CPFinvalido();
					} else if (cpf.length() == 0) {
						throw new DigitouNada();
					}
				} catch (CPFinvalido ex) {
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Já existe um contrato para esse CPF!");
				} catch (NullPointerException ex) {
					cancelar = true;
					saiu = true;
					break;
				} catch (DigitouNada e) {
				}
			} while (ok == false);

			if (cancelar == true) {
				break;
			}

			ok = false;

			break;

		} while (cancelar == false);

	}
	
	/* Salva cadastro em ".bin" */
	public void salvarCadastro() {
		Usuario c = new Usuario(cliente, cpf, fezContrato);

		try {

			FileOutputStream fos = new FileOutputStream(cpf + ".Usuario.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	/* Gerador de contrato */
	public void gerarContrato() {

		try {

			/* Pergunta se o usuário quer gerar o contrato */
			int opcao = JOptionPane.showOptionDialog(null, "Clique na operação a qual deseja realizar:", "Operação",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			/* if salva arquivo */
			if (opcao == 0) {

				FileWriter arq = new FileWriter(cpf + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CADASTRO**%n%nNome do cliente: " + cliente + "%nCPF: " + cpf);

				JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso como " + cpf + ".txt !");
				fezContrato = true;
				arq.close();

			} else if (opcao == 1) {/*
									 * if não salva arquivo e retorna ao menu
									 * principal
									 */
				JOptionPane.showMessageDialog(null, "Você não gerou o cadastro!\nClique em OK para retornar ao menu");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar contrato!");
		}
	}

}
