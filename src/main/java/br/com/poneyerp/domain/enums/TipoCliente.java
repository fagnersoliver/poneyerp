package br.com.poneyerp.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Fisíca"), PESSOAJURIDICA(2, "Pessoa Juridica");

	private Integer cod;
	private String descrição;

	private TipoCliente(Integer cod, String descrição) {
		this.cod = cod;
		this.descrição = descrição;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescrição() {
		return descrição;
	}

	/*
	 * Converte o código passado para Enum
	 */
	public static TipoCliente toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {

			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + cod);
	}

}
