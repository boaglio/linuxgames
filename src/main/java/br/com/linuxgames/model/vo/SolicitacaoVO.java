package br.com.linuxgames.model.vo;


public class SolicitacaoVO   {

	private static final long serialVersionUID = -12024142113519438L;

    private int id;
    private String nome;
    private Usuario usuario;
    private TextoVO texto;
    private int tipoDeSolicitacao;
    private int categoria;
    
    // construtor padrao
    public SolicitacaoVO () {
     usuario=new Usuario();
     texto=new TextoVO();
    }

    // getters e setters
 
	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public TextoVO getTexto() {
		return texto;
	}

	public void setTexto(TextoVO texto) {
		this.texto = texto;
	}

	public int getTipoDeSolicitacao() {
		return tipoDeSolicitacao;
	}

	public void setTipoDeSolicitacao(int tipoDeSolicitacao) {
		this.tipoDeSolicitacao = tipoDeSolicitacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + tipoDeSolicitacao;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		final SolicitacaoVO other = (SolicitacaoVO) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoDeSolicitacao != other.tipoDeSolicitacao)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  id +" "+nome+" "+usuario+" "+texto;
	}
}