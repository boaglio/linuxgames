package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class ImagemVO implements Serializable, ImageFile  {

	private static final long serialVersionUID = 325677512282647L;
	

    private int id;
    private String fileName;
    private String fileMimeType;
    private String filePath;
    private Object fileContent;
    private int tipo;
    private int tipoId;
    
    private Jogo jogo;
    private EmuladorVO emu;
    private long fileSize;

    public ImagemVO() {
    	jogo = new Jogo();
    	emu = new EmuladorVO();
    }

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#getFileName()
	 */
	public String getFileName() {
		return fileName;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#setFileName(java.lang.String)
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#getFileMimeType()
	 */
	public String getFileMimeType() {
		return fileMimeType;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#setFileMimeType(java.lang.String)
	 */
	public void setFileMimeType(String fileMimeType) {
		this.fileMimeType = fileMimeType;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#getFileContent()
	 */
	public Object getFileContent() {
		return fileContent;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#setFileContent(java.lang.Object)
	 */
	public void setFileContent(Object fileContent) {
		this.fileContent = fileContent;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public EmuladorVO getEmu() {
		return emu;
	}

	public void setEmu(EmuladorVO emu) {
		this.emu = emu;
	}
 
	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#getFileSize()
	 */
	public long getFileSize() {
		return fileSize;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#setFileSize(long)
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#getFilePath()
	 */
	public String getFilePath() {
		return filePath;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.model.vo.Image#setFilePath(java.lang.String)
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipoId() {
		return tipoId;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		final ImagemVO other = (ImagemVO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ImagemVO[");
		buffer.append(" id = ").append(id);
		buffer.append(" emu = ").append(emu);
		buffer.append(" fileContent = ").append(fileContent);
		buffer.append(" fileMimeType = ").append(fileMimeType);
		buffer.append(" fileName = ").append(fileName);
		buffer.append(" jogo = ").append(jogo);
		buffer.append("]");
		return buffer.toString();
	}
     
}
