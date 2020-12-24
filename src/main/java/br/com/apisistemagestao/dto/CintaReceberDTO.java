package br.com.apisistemagestao.dto;

import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonFormat;



public class CintaReceberDTO {
    private Long idcontareceber;
	
		private ClienteDTO  cliente = new ClienteDTO();
		@JsonFormat(pattern = "dd/MM/yyyy")
		private LocalDate datalancamento;
		
		private String descricao;

		public Long getIdcontareceber() {
			return idcontareceber;
		}

		public void setIdcontareceber(Long idcontareceber) {
			this.idcontareceber = idcontareceber;
		}

		public ClienteDTO getCliente() {
			return cliente;
		}

		public void setCliente(ClienteDTO cliente) {
			this.cliente = cliente;
		}

		public LocalDate getDatalancamento() {
			return datalancamento;
		}

		public void setDatalancamento(LocalDate datalancamento) {
			this.datalancamento = datalancamento;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		
}
