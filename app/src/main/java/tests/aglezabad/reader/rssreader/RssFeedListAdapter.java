package tests.aglezabad.reader.rssreader;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by angel on 12/03/18.
 */

public class RssFeedListAdapter
        extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedElement> mRssFeedElements;

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rss_feed, parent,false);

        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final RssFeedElement rssFeedElement = mRssFeedElements.get(position);
        ((TextView) holder.rssFeedView.findViewById(R.id.titleText))
                .setText(rssFeedElement.getTitle());
        ((TextView) holder.rssFeedView.findViewById(R.id.descriptionText))
                .setText(rssFeedElement.getDescription());
        ((TextView) holder.rssFeedView.findViewById(R.id.linkText))
                .setText(rssFeedElement.getLink());

    }

    @Override
    public int getItemCount() {
        return mRssFeedElements.size();
    }

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedListAdapter(List<RssFeedElement> rssFeedElements) {
        mRssFeedElements = rssFeedElements;
    }



}
