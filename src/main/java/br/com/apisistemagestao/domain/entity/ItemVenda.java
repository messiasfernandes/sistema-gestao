package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;
@Entity
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Iditem;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtdeItem;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal subTotal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal desconto;
	@ManyToOne()
	@JoinColumn(nullable = false)
	private Venda venda;

	public ItemVenda() {
		produto = new Produto();
		qtdeItem = BigDecimal.ZERO;
		subTotal = BigDecimal.ZERO;
		desconto= BigDecimal.ZERO;
	}

	public Long getIditem() {
		return Iditem;
	}

	public void setIditem(Long iditem) {
		Iditem = iditem;
	}

	public BigDecimal getQtdeItem() {
		return qtdeItem;
	}

	public void setQtdeItem(BigDecimal qtdeItem) {
		this.qtdeItem = qtdeItem;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
   
	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Iditem == null) ? 0 : Iditem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (Iditem == null) {
			if (other.Iditem != null)
				return false;
		} else if (!Iditem.equals(other.Iditem))
			return false;
		return true;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
