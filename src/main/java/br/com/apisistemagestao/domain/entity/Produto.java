package br.com.apisistemagestao.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apisistemagestao.serivces.exeption.NegocioExeption;




@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long idproduto;
	@NotBlank
	@Column(length = 90)
	private String nomeproduto;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal precocusto;
	@Column(precision=9, scale=3) 
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal qteestoque;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	@JsonFormat(pattern = "#,##0.000")
	private BigDecimal precodevenda;
	@Column(columnDefinition = "DECIMAL(9,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal customedio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataultimacmpra;
	private Boolean ativo;
	@Column(length = 8)
	private String  unidede;
	@Column(length = 15)
	private String codigoEan13;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length  = 15)
	private TipoProduto tipoproduto;
	@Column(length = 25)
	private String codigofabricante;
    @Column(length = 50)	
	private String marca;
    @Column
    private String caminhodafoto; 
	@Column(precision = 18, scale = 3)
	@NumberFormat(pattern = "#,##0.0000")
    private  BigDecimal peso;
	@Column(columnDefinition = "DECIMAL(18,2) DEFAULT 0.00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal ultiMocusto_pago;
	@NotNull
	@ManyToOne()
	@JoinColumn
	private Categoria categoria;
	public Produto() {
		categoria = new Categoria();
	
		qteestoque=BigDecimal.ZERO;
		precocusto=BigDecimal.ZERO;
		precodevenda=BigDecimal.ZERO;
		customedio=BigDecimal.ZERO;
		peso=BigDecimal.ZERO;
		ultiMocusto_pago=BigDecimal.ZERO;
	}
	public Long getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	public String getNomeproduto() {
		return nomeproduto;
	}
	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto.toUpperCase();
	}
	public BigDecimal getPrecocusto() {
		return precocusto;
	}
	public void setPrecocusto(BigDecimal precocusto) {
		this.precocusto = precocusto;
	}
	public BigDecimal getQteestoque() {
	
		return qteestoque;
	}
	public void setQteestoque(BigDecimal qteestoque) {
		
		this.qteestoque=qteestoque;

	}
	public BigDecimal getPrecodevenda() {
		 
		return precodevenda;
	}
	public void setPrecodevenda(BigDecimal precodevenda) {

		this.precodevenda = precodevenda;
	}
	public BigDecimal getCustomedio() {
		return customedio;
	}
	public void setCustomedio(BigDecimal customedio) {
		this.customedio = customedio;
	}
	public LocalDate getDataultimacmpra() {
		return dataultimacmpra;
	}
	public void setDataultimacmpra(LocalDate dataultimacmpra) {
		this.dataultimacmpra = dataultimacmpra;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public TipoProduto getTipoproduto() {
		return tipoproduto;
	}
	public void setTipoproduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getCodigoEan13() {
		return codigoEan13;
	}
	public void setCodigoEan13(String codigoEan13) {
		this.codigoEan13 = codigoEan13;
	}
	
	public String getUnidede() {
		return unidede;
	}
	public void setUnidede(String unidede) {
		this.unidede = unidede;
	}
	
	
	public String getCodigofabricante() {
		return codigofabricante;
	}
	public void setCodigofabricante(String codigofabricante) {
		this.codigofabricante = codigofabricante;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public BigDecimal getUltiMocusto_pago() {
		return ultiMocusto_pago;
	}
	public void setUltiMocusto_pago(BigDecimal ultiMocusto_pago) {
		this.ultiMocusto_pago = ultiMocusto_pago;
	}
	@Transient
	public BigDecimal baixarEstoque(BigDecimal quantidade) {
		BigDecimal novaQuantidade = this.getQteestoque().subtract( quantidade);
		System.out.println();
		if ((quantidade.compareTo(getQteestoque())>0)&&(getTipoproduto().equals(TipoProduto.FISÍCO))){
			throw new NegocioExeption("Não há disponibilidade no estoque de "
					+ quantidade + " itens do produto " + this.getIdproduto()+ ".");
		}
		this.setQteestoque(novaQuantidade);
	return	this.getQteestoque();
	}

	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
			
	}
	public BigDecimal converter(BigDecimal pqte){
		
	System.out.println(   pqte= pqte.multiply(peso));
	    System.out.println(pqte  +"  estoque");
 		this.setQteestoque(pqte);
	
		return this.getQteestoque();
	}
	
	public BigDecimal covetervalorvenda(BigDecimal pvalorvenda) {
  System.out.println(		pvalorvenda= pvalorvenda.divide(new BigDecimal(1000),3, RoundingMode.DOWN).multiply(new BigDecimal(100)));
		this.setPrecodevenda(pvalorvenda);
		return this.getPrecodevenda();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idproduto == null) ? 0 : idproduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idproduto == null) {
			if (other.idproduto != null)
				return false;
		} else if (!idproduto.equals(other.idproduto))
			return false;
		return true;
	}
	public String getCaminhodafoto() {
		return caminhodafoto;
	}
	public void setCaminhodafoto(String caminhodafoto) {
		this.caminhodafoto = caminhodafoto;
	}
	
	

}
