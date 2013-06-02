package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class ColaboradorValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("ativo", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("email", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("admin", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}