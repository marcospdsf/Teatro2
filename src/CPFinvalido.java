import javax.swing.JOptionPane;

public class CPFinvalido extends Exception {

	private static final long serialVersionUID = -6346794817253321355L;

	public CPFinvalido(){
		JOptionPane.showMessageDialog(null, "Voc� digitou um CPF inv�lido!");
    }
}

