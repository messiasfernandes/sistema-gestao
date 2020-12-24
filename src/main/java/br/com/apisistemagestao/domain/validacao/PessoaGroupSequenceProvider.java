package br.com.apisistemagestao.domain.validacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.apisistemagestao.domain.entity.Pessoa;
import br.com.apisistemagestao.domain.entity.TipoPessoa;
import br.com.apisistemagestao.domain.grupos.PessoaFisica;
import br.com.apisistemagestao.domain.grupos.PessoaJuridica;



public class PessoaGroupSequenceProvider implements DefaultGroupSequenceProvider<Pessoa> {

	@Override
	public List<Class<?>> getValidationGroups(Pessoa objeto) {
		List<Class<?>> grupos = new ArrayList<>();

		grupos.add(Pessoa.class);
		if (objeto != null) {
			if (objeto.getTipessoa().equals(TipoPessoa.JURÍDiCA)) {
				grupos.add(PessoaJuridica.class);
			} else if (objeto.getTipessoa().equals(TipoPessoa.FÍSICA)) {
				grupos.add(PessoaFisica.class);
			}
		}
		return grupos;
	}
}
