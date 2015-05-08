package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class IndexVO implements Serializable  {

	private static final long serialVersionUID = 325672067452247L;

    private InfoDoDiaVO infoDoDiaVO=new InfoDoDiaVO();
    private LinuxGamesVO versaoVO=new LinuxGamesVO();
    private EnqueteVO enqueteVO=new EnqueteVO();
    private Jogo jogoDoDia=new Jogo();
    private String imagemDoJogoDoDia;
    private EmuladorVO emuladorDoDia=new EmuladorVO();
    private String imagemDoEmuladorDoDia;
    private int totalDeJogos;
    private int totalDeEmuladores;

	public InfoDoDiaVO getInfoDoDiaVO() {
		return infoDoDiaVO;
	}
	public void setInfoDoDiaVO(InfoDoDiaVO infoDoDiaVO) {
		this.infoDoDiaVO = infoDoDiaVO;
	}
	public LinuxGamesVO getVersaoVO() {
		return versaoVO;
	}
	public void setVersaoVO(LinuxGamesVO versaoVO) {
		this.versaoVO = versaoVO;
	}
	public EnqueteVO getEnqueteVO() {
		return enqueteVO;
	}
	public void setEnqueteVO(EnqueteVO enqueteVO) {
		this.enqueteVO = enqueteVO;
	}
	public Jogo getJogoDoDia() {
		return jogoDoDia;
	}
	public void setJogoDoDia(Jogo jogoDoDia) {
		this.jogoDoDia = jogoDoDia;
	}
	public EmuladorVO getEmuladorDoDia() {
		return emuladorDoDia;
	}
	public void setEmuladorDoDia(EmuladorVO emuladorDoDia) {
		this.emuladorDoDia = emuladorDoDia;
	}
	public String getImagemDoJogoDoDia() {
		return imagemDoJogoDoDia;
	}
	public void setImagemDoJogoDoDia(String imagemDoJogoDoDia) {
		this.imagemDoJogoDoDia = imagemDoJogoDoDia;
	}
	public String getImagemDoEmuladorDoDia() {
		return imagemDoEmuladorDoDia;
	}
	public void setImagemDoEmuladorDoDia(String imagemDoEmuladorDoDia) {
		this.imagemDoEmuladorDoDia = imagemDoEmuladorDoDia;
	}
	public int getTotalDeJogos() {
		return totalDeJogos;
	}
	public void setTotalDeJogos(int totalDeJogos) {
		this.totalDeJogos = totalDeJogos;
	}
	public int getTotalDeEmuladores() {
		return totalDeEmuladores;
	}
	public void setTotalDeEmuladores(int totalDeEmuladores) {
		this.totalDeEmuladores = totalDeEmuladores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emuladorDoDia == null) ? 0 : emuladorDoDia.hashCode());
		result = prime * result
				+ ((infoDoDiaVO == null) ? 0 : infoDoDiaVO.hashCode());
		result = prime * result
				+ ((jogoDoDia == null) ? 0 : jogoDoDia.hashCode());
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
		final IndexVO other = (IndexVO) obj;
		if (emuladorDoDia == null) {
			if (other.emuladorDoDia != null)
				return false;
		} else if (!emuladorDoDia.equals(other.emuladorDoDia))
			return false;
		if (infoDoDiaVO == null) {
			if (other.infoDoDiaVO != null)
				return false;
		} else if (!infoDoDiaVO.equals(other.infoDoDiaVO))
			return false;
		if (jogoDoDia == null) {
			if (other.jogoDoDia != null)
				return false;
		} else if (!jogoDoDia.equals(other.jogoDoDia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return emuladorDoDia+" "+infoDoDiaVO+" "+jogoDoDia;
	}
}
