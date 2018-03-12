package tests.aglezabad.reader.rssreader;

/**
 * Created by angel on 11/03/18.
 */

public class RssFeedElement {

    private String title;
    private String link;
    private String description;

    public RssFeedElement(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
