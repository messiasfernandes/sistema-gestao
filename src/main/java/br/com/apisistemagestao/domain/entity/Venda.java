package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.NumberFormat;


import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Venda implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idvenda;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Usuario usario;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemVenda> itens = new ArrayList<>();
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)  
	private TipoVenda tipovenda;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal toTalVenda;
	 @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datavenda;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime datahoravenda;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 25)  
	private StatusVenda statusVenda;
	public Venda() {
		  toTalVenda=BigDecimal.ZERO;
		  idvenda=0l;
	}
	public Long getIdvenda() {
		return idvenda;
	}
	public void setIdvenda(Long idvenda) {
		this.idvenda = idvenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
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
	
	public LocalDateTime getDatahoravenda() {
		return datahoravenda;
	}
	public void setDatahoravenda(LocalDateTime datahoravenda) {
		this.datahoravenda = datahoravenda;
	}
	public StatusVenda getStatusVenda() {
		return statusVenda;
	}
	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}
	public LocalDate getDatavenda() {
		return datavenda;
	}
	public void setDatavenda(LocalDate datavenda) {
		this.datavenda = datavenda;
	}
	public Usuario getUsario() {
		return usario;
	}
	public void setUsario(Usuario usario) {
		this.usario = usario;
	}
	
	

}
