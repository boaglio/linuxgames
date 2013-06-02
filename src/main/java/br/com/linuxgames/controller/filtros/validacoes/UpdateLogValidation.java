package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class UpdateLogValidation  extends ValidationFilter {
 
	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("descricao", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("data", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
