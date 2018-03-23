package net.thiago.mc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento() {
	}

	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo != null) {
			for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
				if (codigo.equals(estadoPagamento.getCodigo())) {
					return estadoPagamento;
				}
			}
		}

		throw new IllegalArgumentException("Código inválido para o Estado do Pagamento");
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
