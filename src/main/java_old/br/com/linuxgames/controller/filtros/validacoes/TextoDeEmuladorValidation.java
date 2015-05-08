package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class TextoDeEmuladorValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("texto", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("tipo_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("tipo", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("usuarioId", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("dataPublic", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("aprovado", RequiredFieldRule.getInstance(),"campoObrigatorio");		
	}
}
