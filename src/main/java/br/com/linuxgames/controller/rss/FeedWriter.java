package br.com.linuxgames.controller.rss;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.vo.Novidade;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class FeedWriter extends HttpServlet {

	private static Logger logger = Logger.getLogger(FeedWriter.class);

	private static final long serialVersionUID = 726132868996606603L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");
		OutputStream os = response.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		create("rss_2.0", pw);
		pw.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);

	}

	@SuppressWarnings("unchecked")
	protected void create(String feedType, Writer writer) {
		try {
			SyndFeed feed = new SyndFeedImpl();
			feed.setFeedType(feedType);

			feed.setTitle("Noticias de LinuxGames.com.br");
			feed.setLink("http://www.linuxgames.com.br");
			feed.setDescription("O maior portal de jogos para Linux do Brasil");

			List<SyndEntry> entries = new ArrayList<SyndEntry>();
			SyndEntry entry;
			SyndContent description;

			ArrayList<Novidade> novidades = (ArrayList<Novidade>) CacheManager
					.getCacheDeNovidades().getCache();

			for (Novidade noticia : novidades) {
				entry = new SyndEntryImpl();
				entry.setTitle(noticia.getTexto());
				entry.setLink(noticia.getLink());
				entry.setPublishedDate(noticia.getDataPublic());
				description = new SyndContentImpl();
				description.setType("text/plain");
				description.setValue(noticia.getTexto());
				entry.setDescription(description);
				entries.add(entry);
			}

			feed.setEntries(entries);

			SyndFeedOutput output = new SyndFeedOutput();
			output.output(feed, writer);
		} catch (Exception e) {
			logger.error("RSS ERROR: ",e);
		}
	}
}