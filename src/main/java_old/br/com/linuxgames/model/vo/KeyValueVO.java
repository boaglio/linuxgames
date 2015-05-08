package br.com.linuxgames.model.vo;

import java.io.Serializable;

/**
 * Classe utilizada para transportar pares chave-valor genericos
 * @author Fernando Boaglio
 * @version $Revision: 1.1 $ $Date: 2006/08/01 19:04:50 $ $Author: cfboagli $
 */
public class KeyValueVO implements Serializable {

	private static final long serialVersionUID = -7323691027858993309L;
	private String key;
    private String value;


    /**
     * construtor default do KeyValue
     */
    public KeyValueVO()
    {
        super();
    }

    /**
     * construtor do KeyValue
     * @param key
     * @param value
     */
    public KeyValueVO(String key,String value)
    {
     setKey(key);
     setValue(value);
    }

    public KeyValueVO(String key, Integer valueStr) {
        setKey(key);
        setValue(String.valueOf(valueStr));
	}

	/**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		final KeyValueVO other = (KeyValueVO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "KeyValueVO ( "
	        + "key = " + this.key + TAB
	        + "value = " + this.value + TAB
	        + " )";

	    return retValue;
	}
}
