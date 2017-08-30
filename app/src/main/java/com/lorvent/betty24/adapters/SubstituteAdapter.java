package com.lorvent.betty24.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lorvent.betty24.R;
import com.lorvent.betty24.model.Substitute;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 5/6/2017.
 */

public class SubstituteAdapter extends RecyclerView.Adapter<SubstituteAdapter.ViewHoldeer> {

    public Context context;
    public ArrayList<Substitute> arrayList;
    int[] images={R.drawable.subs2,R.drawable.subs1};
    public SubstituteAdapter(Context context, ArrayList<Substitute> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SubstituteAdapter.ViewHoldeer onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_substitute, parent, false);
        return new ViewHoldeer(v);

    }

    @Override
    public void onBindViewHolder(SubstituteAdapter.ViewHoldeer holder, int position) {
       Substitute substitute=arrayList.get(position);
        holder.name.setText(substitute.getName());
        holder.address.setText(substitute.getAddrees());
        holder.image.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoldeer extends RecyclerView.ViewHolder {
        TextView name,address;
        CircleImageView image;
        public ViewHoldeer(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            address=(TextView)itemView.findViewById(R.id.txt);
            image=(CircleImageView)itemView.findViewById(R.id.contact_image);
        }
    }
}
