package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class BannerVO implements Serializable  {

	private static final long serialVersionUID = 325672066745278247L;
	private int id;
    private String imagem;
    private Date dtInicio;
    private Date dtFim;
    private FabricanteVO fabricanteVO=new FabricanteVO();

	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public FabricanteVO getFabricanteVO() {
		return fabricanteVO;
	}
	public void setFabricanteVO(FabricanteVO fabricanteVO) {
		this.fabricanteVO = fabricanteVO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BannerVO other = (BannerVO) obj;
		if (id != other.id)
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+imagem;
	}

}
