package tests.aglezabad.reader.rssreader;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * La prueba de aplicación realiza la captura de información de RSS.
 * Me he basado en este tutorial, pero haciendo modificaciones con el fin que sea más acorde.
 * https://www.androidauthority.com/simple-rss-reader-full-tutorial-733245/
 * El joven noble del mundo de los toros de España. ~Ken sobre Vega.
 */
public class MainActivity extends AppCompatActivity {

    private MainActivity self = this;

    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private Button mGetRSSBtn;
    private SwipeRefreshLayout mSwipeLayout;

    private List<RssFeedElement> mRssFeedList;

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    public SwipeRefreshLayout getmSwipeLayout() {
        return mSwipeLayout;
    }

    public EditText getmEditText() {
        return mEditText;
    }

    public List<RssFeedElement> getmRssFeedList() {
        return mRssFeedList;
    }

    public void setmRssFeedList(List<RssFeedElement> mRssFeedList) {
        this.mRssFeedList = mRssFeedList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rssRecyclerView);
        mEditText = (EditText) findViewById(R.id.rssUrl);
        mGetRSSBtn = (Button) findViewById(R.id.rssExecQuery);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mGetRSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetRSSTask(self).execute((Void) null);
            }
        });

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetRSSTask(self).execute((Void) null);
            }
        });
    }
}
