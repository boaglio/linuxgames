package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RegexRule;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class NovidadeAgendadaValidation  extends ValidationFilter {

    public static final String LINK_PATTERN = "^[^\\s]+$";

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("texto", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("link", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("link",new RegexRule(LINK_PATTERN), "caracterInvalido");
	}
}
