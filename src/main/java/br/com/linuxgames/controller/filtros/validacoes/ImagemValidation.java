package br.com.linuxgames.controller.filtros.validacoes;

import org.mentawai.core.Action;
import org.mentawai.filter.ValidationFilter;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.validation.Validator;

public class ImagemValidation  extends ValidationFilter {

	public void prepareValidator(Validator validator, Action action,String innerAction) {

//		validator.add("fileName", RequiredFieldRule.getInstance(),"campoObrigatorio");
//		validator.add("fileContent", RequiredFieldRule.getInstance(),"campoObrigatorio");
//		validator.add("fileMimeType", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("jogo_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
		validator.add("emu_id", RequiredFieldRule.getInstance(),"campoObrigatorio");
	}
}
