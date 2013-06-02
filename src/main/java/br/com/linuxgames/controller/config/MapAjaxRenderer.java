package br.com.linuxgames.controller.config;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.mentawai.ajax.AjaxConsequence;
import org.mentawai.ajax.AjaxRenderer;
import org.mentawai.ajax.DOMUtils;

public class MapAjaxRenderer implements AjaxRenderer {

	public static final String FATHER = "map";
	public static final String CHILD = "entry";
	public static final String KEY = "key";

	private String root;
	private String child;
	private String key;

	public MapAjaxRenderer(String root, String child, String key) {
		this.root = root;
		this.child = child;
		this.key = key;
	}

	public MapAjaxRenderer() {
		this.root = FATHER;
		this.child = CHILD;
		this.key = KEY;
	}

	public String encode(Object obj, Locale loc, boolean pretty)
			throws Exception {

		if (!(obj instanceof Map))
			throw new IllegalArgumentException("Value is not a Map: " + obj);

		Map<?, ?> map = (Map<?, ?>) obj;

		Document document = new Document();

		Element mapElement = new Element(root);

		for (Object element : map.entrySet()) {

			Content child = new Element(this.child);

			Entry<?, ?> entry = (Entry<?, ?>) element;

			String key = entry.getKey().toString();

			String value = entry.getValue().toString();

			((Element) child).setAttribute(this.key, key);

			((Element) child).setText(value);

			mapElement.addContent(child);
		}

		document.setRootElement(mapElement);

		return DOMUtils.getDocumentAsString(document, pretty);
	}

	public String getContentType() {
		return TEXT_XML;
	}

	public String getCharset() {
		return AjaxConsequence.DEFAULT_CHARSET;
	}
}
