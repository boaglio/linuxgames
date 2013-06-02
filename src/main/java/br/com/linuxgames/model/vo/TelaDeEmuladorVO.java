package br.com.linuxgames.model.vo;



public class TelaDeEmuladorVO extends TelaVO implements ImageFile   {

	private static final long serialVersionUID = -12024142113538L;

    private EmuladorVO emulador;
    private String fileName;
    private String fileMimeType;
    private String filePath;
    private Object fileContent;
    private long fileSize;

    // construtor padrao
    public TelaDeEmuladorVO () {
     emulador=new EmuladorVO();
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TelaDeEmuladorVO other = (TelaDeEmuladorVO) obj;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() +" emulador="+emulador;
	}

    // getters e setters
	public EmuladorVO getEmulador() {
		return emulador;
	}

	public void setEmulador(EmuladorVO jogo) {
		this.emulador = jogo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((emulador == null) ? 0 : emulador.hashCode());
		return result;
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
	
}