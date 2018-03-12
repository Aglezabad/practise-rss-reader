package tests.aglezabad.reader.rssreader;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by angel on 11/03/18.
 */

class RSSParser {

    public static List<RssFeedElement> parse(InputStream inputStream)
            throws XmlPullParserException, IOException {

        String title = null;
        String link = null;
        String description = null;
        boolean isElement = false;
        List<RssFeedElement> elements = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isElement = false;
                    }
                    continue;
                }

                if(eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isElement = true;
                        continue;
                    }
                }

                Log.d("RSSParser", "Parsing name ==> " + name);
                String result = "";

                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (isElement && name.equalsIgnoreCase("title")) {
                    title = result;
                } else if(isElement && name.equalsIgnoreCase("link")) {
                    link = result;
                } else if(isElement && name.equalsIgnoreCase("description")) {
                    description = result;
                }

                if (title != null && link != null && description != null) {
                    if (isElement) {
                        RssFeedElement element = new RssFeedElement(title, link, description);
                        elements.add(element);
                    }

                    title = null;
                    link = null;
                    description = null;
                    isElement = false;
                }
            }

            return elements;
        } finally {
            inputStream.close();
        }

    }
}
