package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class EmuladorValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("nome", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("licenca_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("jogaEmRede", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("temSom", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("consoleOuX11", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("siteOficial", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("descricao", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("votos", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("notaGeral", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("fabricante_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
