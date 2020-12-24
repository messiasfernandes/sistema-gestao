package br.com.apisistemagestao.dto;

;

public class CidadeDTO {
	private Long codigo_cidade;
   
  	private String codigoibge;
 
	private String cidadenome;
  private EstadoDTO estado = new EstadoDTO();
	public Long getCodigo_cidade() {
		return codigo_cidade;
	}

	public void setCodigo_cidade(Long codigo_cidade) {
		this.codigo_cidade = codigo_cidade;
	}

	public String getCodigoibge() {
		return codigoibge;
	}

	public void setCodigoibge(String codigoibge) {
		this.codigoibge = codigoibge;
	}

	public String getCidadenome() {
		return cidadenome;
	}

	public void setCidadenome(String cidadenome) {
		this.cidadenome = cidadenome;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}
	
	
}
