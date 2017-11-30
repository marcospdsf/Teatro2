import javax.swing.JOptionPane;

public class DigitouNada extends Exception{

	private static final long serialVersionUID = 2628380054499696578L;
	
	public DigitouNada(){
		JOptionPane.showMessageDialog(null, "Você não digitou nada!");
	}

}
