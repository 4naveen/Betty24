package com.lorvent.betty24.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lorvent.betty24.R;
import com.lorvent.betty24.model.News;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by User on 4/28/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Viewholder> {
    public Context context;
    public ArrayList<News> newsArrayList;

   public NewsAdapter(Context context, ArrayList<News> movies) {
        this.context = context;
        this.newsArrayList = movies;
    }

    @Override
    public NewsAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_news, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.Viewholder holder, int position) {
        News news=newsArrayList.get(position);
        holder.heading.setText(news.getHeading());
        holder.expTv1.setText(news.getContent());
       /* Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/roboto_regular.ttf");
         holder.expTv1.setTypeface(tf);*/
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView heading,content;
        public final ExpandableTextView expTv1;

        public Viewholder(View itemView) {
            super(itemView);
            expTv1 = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);
            heading=(TextView)itemView.findViewById(R.id.heading);
            //content=(TextView)itemView.findViewById(R.id.content);

        }
    }
}
