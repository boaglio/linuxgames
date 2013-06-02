package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class BannerValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("imagem", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("fabricante_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("dtInicio", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
