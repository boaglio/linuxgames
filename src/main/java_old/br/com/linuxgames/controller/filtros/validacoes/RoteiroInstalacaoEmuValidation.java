package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class RoteiroInstalacaoEmuValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

		validator.add("emulador_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("distro_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("usuario_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("descricao", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("aprovado", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
