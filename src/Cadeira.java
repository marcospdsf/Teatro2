import java.io.Serializable;

public class Cadeira implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6725174431053268970L;
	private int posicao;
	private Usuario usuario;
	
	
	public Cadeira(int posicao, Usuario usuario) {
		super();
		this.posicao = posicao;
		this.usuario = usuario;
	}
	
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cadeira [posicao=" + posicao + ", usuario=" + usuario + "]";
	}


	
}
