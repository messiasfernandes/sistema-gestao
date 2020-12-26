package br.com.apisistemagestao.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sitemagestao")
public class SistemaGestaoApiProperty {
	private final Seguranca seguranca = new Seguranca();

	public Seguranca getSeguranca() {
		return seguranca;
	}



	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	

	}
}
