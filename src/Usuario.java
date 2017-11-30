import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 4042322851451794298L;
	
	private String cliente;
	private String cpf;
	private boolean fezContrato = false;

	/**
	 * @param cliente
	 * @param cpf
	 * @param fezContrato
	 */
	public Usuario(String cliente, String cpf, boolean fezContrato) {
		super();
		this.cliente = cliente;
		this.cpf = cpf;
		this.fezContrato = fezContrato;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * @return the fezContrato
	 */
	public boolean isFezContrato() {
		return fezContrato;
	}
	/**
	 * @param fezContrato the fezContrato to set
	 */
	public void setFezContrato(boolean fezContrato) {
		this.fezContrato = fezContrato;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

