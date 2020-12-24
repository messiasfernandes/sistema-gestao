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
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class ItemCompra implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_itemcompra;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.000")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qtde;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal Subtotal;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal custototal;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Compra compra;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Produto produto;
	@Transient
    private Integer numItem;
	public ItemCompra() {
		compra = new Compra();
		produto = new Produto();
	}

	public Long getId_itemcompra() {
		return id_itemcompra;
	}

	public void setId_itemcompra(Long id_itemcompra) {
		this.id_itemcompra = id_itemcompra;
	}

	public BigDecimal getQtde() {
		return qtde;
	}

	public void setQtde(BigDecimal qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getSubtotal() {
		return Subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		Subtotal = subtotal;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public BigDecimal getCustototal() {
		return custototal;
	}

	public void setCustototal(BigDecimal custototal) {
		this.custototal = custototal;
	}

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id_itemcompra == null) ? 0 : id_itemcompra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCompra other = (ItemCompra) obj;
		if (id_itemcompra == null) {
			if (other.id_itemcompra != null)
				return false;
		} else if (!id_itemcompra.equals(other.id_itemcompra))
			return false;
		return true;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
