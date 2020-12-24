package br.com.apisistemagestao.infra;

import br.com.apisistemagestao.domain.entity.Ncms;

public interface NcmRepositoryQuery {
	Ncms buscarNcm(String parametro);

}
