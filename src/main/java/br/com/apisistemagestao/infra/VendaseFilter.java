package br.com.apisistemagestao.infra;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

public class VendaseFilter {
    private Long codigo;
    private Long idvenda;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private  LocalDate datavenda;
    private String nomePessoa;
    private String cpfouCnpj;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getIdvenda() {
		return idvenda;
	}
	public void setIdvenda(Long idvenda) {
		this.idvenda = idvenda;
	}
	public LocalDate getDatavenda() {
		return datavenda;
	}
	public void setDatavenda(LocalDate datavenda) {
		this.datavenda = datavenda;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public String getCpfouCnpj() {
		return cpfouCnpj;
	}
	public void setCpfouCnpj(String cpfouCnpj) {
		this.cpfouCnpj = cpfouCnpj;
	}

    
}
