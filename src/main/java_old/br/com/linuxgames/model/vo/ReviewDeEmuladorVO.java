package br.com.linuxgames.model.vo;



public class ReviewDeEmuladorVO extends ReviewVO {

	private static final long serialVersionUID = -1202414211135438L;

	/*

	mysql> describe REVIEWS_EMULADORES;
	+------------+------------------+------+-----+---------+----------------+
	| Field      | Type             | Null | Key | Default | Extra          |
	+------------+------------------+------+-----+---------+----------------+
	| id         | int(11)          | NO   | PRI | NULL    | auto_increment |
	| usuario_id | int(11)          | NO   | MUL |         |                |
	| emu_id     | int(10) unsigned | NO   | MUL |         |                |
	| comentario | varchar(100)     | NO   |     |         |                |
	| nota       | tinyint(2)       | NO   |     |         |                |
	+------------+------------------+------+-----+---------+----------------+

	 */

	public ReviewDeEmuladorVO() {
		emulador = new EmuladorVO();
	}

	private EmuladorVO emulador;

	public EmuladorVO getEmulador() {
		return emulador;
	}

	public void setEmulador(EmuladorVO emulador) {
		this.emulador = emulador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((emulador == null) ? 0 : emulador.hashCode());
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
		final ReviewDeEmuladorVO other = (ReviewDeEmuladorVO) obj;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" emulador="+emulador;
	}

}