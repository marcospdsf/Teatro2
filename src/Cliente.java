import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cliente {

	/* Variaáveis Globais */
	public String cliente;
	public static boolean saiu = false, ok = false, cancelar = false, fezContrato = false;;
	public String check;
	Object[] options = { "Gerar Contrato", "Sair" };
	static Object[] escolha = { "Sim", "Não" };
	static NumberFormat f = NumberFormat.getCurrencyInstance();

	/* Mostra o contrato através de entrada do usuário */
	public static void mostraContrato() {
		/* Entrada do usuário */
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o CPF do cliente:\n");

		try {
			/* if para se o usuário digitou algo */
			if (nomeArq != null && nomeArq.equals("") == false) {
				/* Cria uma janela de texto 20 x 20 */
				JTextArea ta = new JTextArea(20, 20);
				/* Lê o arquivo ".txt" */
				ta.read(new FileReader(nomeArq + ".txt"), null);
				/* Torna o arquivo não editável */
				ta.setEditable(false);
				/* Mostra o contrato na Janela de Texto */

				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Teatro", 2, null);

			}
			/*
			 * Joga a exceção personalizada "DigitouNada" se o usuário não
			 * digitou nada
			 */
			else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new DigitouNada();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato não existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Digito invalido!");
		} catch (DigitouNada e) {
		}

	}

	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws DigitouNada
	 * 
	 */
	public Usuario consultarUsuario() throws ClassNotFoundException {

		String nomeArq = JOptionPane.showInputDialog("Informe o CPF");

		try {
			if (nomeArq != null && nomeArq.equals("") == false) {

				String nomeArquivoSerializado = nomeArq + ".Usuario.bin";
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivoSerializado));
				Usuario usuarioConsultado = (Usuario) ois.readObject();
				ois.close();
				return usuarioConsultado;
			} else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new DigitouNada();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo de contrato não existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Digito inválido!");
		} catch (DigitouNada e) {
		}

		return null;

	}

	/* Mostra todos os cadastros de Usuario */
	public static void lerCadastro() {

		/* Strings para armazenar os dados de Usuario */
		String dadosPF = null;

		try {
			/* Filtro para arquivos "PessoaFisica.bin" */
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith("Usuario.bin");
				}
			};

			/* Abre diretório aonde o programa está localizado */
			File dir = new File("./");
			/* Usa o filtro no diretório */
			File[] files = dir.listFiles(filter);

			/*
			 * Arraylists para armazenar Strings de dados de Usuario
			 */
			List<String> listaClientesPF = new ArrayList<>();

			/* For pra ler arquivos no diretório */
			for (int i = 0; i < files.length; i++) {
				/* Lê arquivos */
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Usuario lerPF = (Usuario) ois.readObject();

				/* Transforma dados lidos de PessoaFisica em String */
				dadosPF = ("\n\nCliente\n\nNome : " + lerPF.getCliente() + "\nCPF : " + lerPF.getCpf());
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);

				/* Checa se os usuários cadastrados tem contrato */
				if (lerPF.isFezContrato() == false) {
					try {

						/* Pergunta se o usuário quer gerar o contrato */
						int opcao = JOptionPane.showOptionDialog(null,
								"Cliente " + lerPF.getCliente() + " de CPF : " + lerPF.getCpf()
										+ " não tem contrato...\nDeseja gerar um?",
								"Operação", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, escolha,
								escolha[0]);
						/* if salva arquivo */
						if (opcao == 0) {

							/*
							 * Pega os dados do arquivo e coloca em Arrays
							 * temporários
							 */
							String[] temp1 = { lerPF.getCliente(), lerPF.getCpf(), };

							/* Deleta arquivo */
							files[i].delete();

							Usuario c = new Usuario(temp1[0], temp1[1], true);

							/* Cria novo arquivo */
							try {

								FileOutputStream fos = new FileOutputStream(temp1[1] + ".PessoaFisica.bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(c);

								oos.close();
								fos.close();
							} catch (IOException e) {
							}

							/* Gera contrato do Usuario */
							FileWriter arq = new FileWriter(temp1[1] + ".txt");
							PrintWriter gravarArq = new PrintWriter(arq);
							gravarArq.printf("**CADASTRO**%n%nNome do cliente: " + temp1[0] + "%nCPF: " + temp1[1]);

							JOptionPane.showMessageDialog(null,
									"Contrato salvo com sucesso como " + temp1[1] + ".txt !");

							arq.close();

						} else if (opcao == 1) {
							continue;
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
					}
				}

				fis.close();
				ois.close();

			}

			/* String contendo a ArrayList */
			String TextArea = ("CLIENTES:" + listaClientesPF);
			/* Janela de texto 20 x 25 */
			JTextArea ta = new JTextArea(20, 25);
			/* Adiciona conteúdo da string "TextArea" na Janela de texto */
			ta.append(TextArea);
			/* Torna o arquivo não editável */
			ta.setEditable(false);
			/* Mostra o contrato na Janela de Texto */
			JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Teatro", 2, null);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		}

	}

	public void lerPoltrona() {

		String nomeArq = JOptionPane.showInputDialog(null, "Informe a data do arquivo:\n");

		/* Strings para armazenar os dados de Usuario */
		String dadosPF = null;

		try {
			if (nomeArq != null && nomeArq.equals("") == false) {
				/* Filtro para arquivos "Poltrona.bin" */
				FileFilter filter = new FileFilter() {
					public boolean accept(File file) {
						return file.getName().endsWith(".Poltrona.bin");
					}
				};

				/* Abre diretório aonde o programa está localizado */
				File dir = new File("./");
				/* Usa o filtro no diretório */
				File[] files = dir.listFiles(filter);

				/*
				 * Arraylists para armazenar Strings de dados de Usuario
				 */
				List<String> listaClientesPF = new ArrayList<>();
				
				/* Lê arquivos */
				
				FileInputStream fis = new FileInputStream(nomeArq + ".Poltrona.bin");
				ObjectInputStream ois = new ObjectInputStream(fis);

				Teatro lerPF = (Teatro) ois.readObject();

				/* Transforma dados lidos de Poltrona em String */
				dadosPF = ("Poltronas Ocupadas: " + lerPF.listarCadeirasOcupadas() + "Poltronas vazias: "
						+ lerPF.listarCadeirasVazias());
				/* Se os dados lidos não forem nulos, adiciona ao ArrayList */
				if (dadosPF != null)
					listaClientesPF.add(dadosPF);
				// }

			}
			/*
			 * Joga a exceção personalizada "DigitouNada" se o usuário não
			 * digitou nada
			 */
			else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new DigitouNada();
			}

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Não há cadastros ainda...");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir arquivos!");
		} catch (DigitouNada e) {

		}

	}

}
