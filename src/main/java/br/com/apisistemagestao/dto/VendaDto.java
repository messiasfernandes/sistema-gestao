package br.com.apisistemagestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;




import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.domain.entity.StatusVenda;
import br.com.apisistemagestao.domain.entity.TipoVenda;



public class VendaDto {

	private Long idvenda;

	private TipoVenda tipovenda;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal toTalVenda;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate datavenda;

	private StatusVenda statusVenda;
    private ClienteDTO cliente =new ClienteDTO();
	public Long getIdvenda() {
		return idvenda;
	}

	public void setIdvenda(Long idvenda) {
		this.idvenda = idvenda;
	}

	public TipoVenda getTipovenda() {
		return tipovenda;
	}

	public void setTipovenda(TipoVenda tipovenda) {
		this.tipovenda = tipovenda;
	}

	public BigDecimal getToTalVenda() {
		return toTalVenda;
	}

	public void setToTalVenda(BigDecimal toTalVenda) {
		this.toTalVenda = toTalVenda;
	}



	public LocalDate getDatavenda() {
		return datavenda;
	}

	public void setDatavenda(LocalDate datavenda) {
		this.datavenda = datavenda;
	}

	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

}
