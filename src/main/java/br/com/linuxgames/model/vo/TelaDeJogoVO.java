package br.com.linuxgames.model.vo;



public class TelaDeJogoVO extends TelaVO implements ImageFile {

	private static final long serialVersionUID = -12024142113519438L;

    private Jogo jogo;
    private Usuario usuario;
    private String fileName;
    private String fileMimeType;
    private String filePath;
    private Object fileContent;
    private long fileSize;
    private int Id;
    

    // construtor padrao
    public TelaDeJogoVO () {
     jogo=new Jogo();
     usuario=new Usuario();
    }

    // getters e setters
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileMimeType() {
		return fileMimeType;
	}

	public void setFileMimeType(String fileMimeType) {
		this.fileMimeType = fileMimeType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Object getFileContent() {
		return fileContent;
	}

	public void setFileContent(Object fileContent) {
		this.fileContent = fileContent;
	}
	
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Id;
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		final TelaDeJogoVO other = (TelaDeJogoVO) obj;
		if (Id != other.Id)
			return false;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
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
		return super.toString()+" Id="+Id+" jogo="+jogo+" usuario="+usuario;
	}
}