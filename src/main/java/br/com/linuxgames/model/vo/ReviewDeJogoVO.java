package br.com.linuxgames.model.vo;



public class ReviewDeJogoVO extends ReviewVO {

	private static final long serialVersionUID = -12024142111351438L;

	/*

	mysql> describe REVIEWS_JOGOS;
	+------------+--------------+------+-----+---------+----------------+
	| Field      | Type         | Null | Key | Default | Extra          |
	+------------+--------------+------+-----+---------+----------------+
	| id         | int(11)      | NO   | PRI | NULL    | auto_increment |
	| jogo_id    | int(10)      | NO   | MUL |         |                |
	| usuario_id | int(11)      | NO   | MUL |         |                |
	| comentario | varchar(100) | NO   |     |         |                |
	| nota       | tinyint(2)   | NO   |     |         |                |
	+------------+--------------+------+-----+---------+----------------+

	 */

	private Jogo jogo;

	public ReviewDeJogoVO() {
		jogo = new Jogo();
	}


	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
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
		final ReviewDeJogoVO other = (ReviewDeJogoVO) obj;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" jogo="+jogo;
	}
}