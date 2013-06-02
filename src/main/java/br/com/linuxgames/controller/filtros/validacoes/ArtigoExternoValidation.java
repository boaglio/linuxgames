package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class ArtigoExternoValidation  extends ValidationFilter {
 
	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("titulo", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("link", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
