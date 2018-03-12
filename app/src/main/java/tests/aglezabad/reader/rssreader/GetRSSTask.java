package tests.aglezabad.reader.rssreader;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.ContentValues.TAG;

/**
 * Created by angel on 11/03/18.
 */

class GetRSSTask extends AsyncTask<Void,Void,Boolean> {
    private MainActivity main;
    private String rssUrl;

    public GetRSSTask(MainActivity act) {
        super();
        main = act;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (TextUtils.isEmpty(rssUrl))
            return false;

        try {
            if (!rssUrl.startsWith("http://") && !rssUrl.startsWith("https://"))
                rssUrl = "http://" + rssUrl;

            URL url = new URL(rssUrl);
            InputStream inputStream = url.openConnection().getInputStream();
            main.setmRssFeedList(RSSParser.parse(inputStream));

            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error", e);
        } catch (XmlPullParserException e) {
            Log.e(TAG, "Error", e);
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        main.getmSwipeLayout().setRefreshing(false);

        if (success) {
            main.getmRecyclerView().setAdapter(new RssFeedListAdapter(main.getmRssFeedList()));
        } else {
            Toast.makeText(main,
                    "This url is not a RSS feed.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        main.getmSwipeLayout().setRefreshing(true);
        rssUrl = main.getmEditText().getText().toString();
    }
}
