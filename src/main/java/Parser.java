import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private final String IMG = "img";
    private final String URL = "https://lenta.ru/";


    public List<String> parseDocument() {
        try {
            Document document = getDocument(URL);
            Elements imgSources = document.getElementsByTag(IMG);
            List<String> links = imgSources.stream().map(element -> element
                     .attr("src")
                     .contains(".jp") ? element.attr("src") : "")
                     .filter(s -> !s.isEmpty())
                     .collect(Collectors.toList());
            return links;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document getDocument(String url) throws IOException {
        Document document = null;
        document = Jsoup.connect(url).get();
        return document;
    }
}
