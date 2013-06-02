package br.com.linuxgames.model.vo;



public class ReviewDeArtigoVO extends ReviewVO {

	private static final long serialVersionUID = -12024142111351438L;

	/*
	  
	mysql> describe REVIEWS_ARTIGOS;
	+------------+--------------+------+-----+---------+-------+
	| Field      | Type         | Null | Key | Default | Extra |
	+------------+--------------+------+-----+---------+-------+
	| id         | int(11)      | NO   | PRI |         |       |
	| artigo_id  | int(10)      | NO   | MUL |         |       |
	| usuario_id | int(11)      | NO   | MUL |         |       |
	| comentario | varchar(100) | NO   |     |         |       |
	| nota       | tinyint(2)   | NO   |     |         |       |
	+------------+--------------+------+-----+---------+-------+
   
   */


	private ArtigoVO artigo;

	public ReviewDeArtigoVO() {
		artigo = new ArtigoVO();
	}

	public ArtigoVO getArtigo() {
		return artigo;
	}

	public void setArtigo(ArtigoVO artigo) {
		this.artigo = artigo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((artigo == null) ? 0 : artigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ReviewDeArtigoVO other = (ReviewDeArtigoVO) obj;
		if (artigo == null) {
			if (other.artigo != null)
				return false;
		} else if (!artigo.equals(other.artigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" artigo="+artigo;
	}

}