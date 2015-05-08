package br.com.linuxgames.model.vo;

public interface ImageFile {

	public abstract String getFileName();

	public abstract void setFileName(String fileName);

	public abstract String getFileMimeType();

	public abstract void setFileMimeType(String fileMimeType);

	public abstract Object getFileContent();

	public abstract void setFileContent(Object fileContent);

	public abstract long getFileSize();

	public abstract void setFileSize(long fileSize);

	public abstract String getFilePath();

	public abstract void setFilePath(String filePath);

}