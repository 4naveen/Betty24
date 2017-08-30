package com.lorvent.betty24.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lorvent.betty24.R;
import com.lorvent.betty24.model.Holiday;
import com.lorvent.betty24.model.Substitute;

import java.util.ArrayList;

/**
 * Created by User on 4/28/2017.
 */

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Holiday> holidayArrayList;
    private ArrayList<Substitute> substituteArrayList;

    public HolidayAdapter(Context context, ArrayList<Holiday> holidayArrayList,ArrayList<Substitute> substituteArrayList) {
        this.context = context;
        this.holidayArrayList = holidayArrayList;
        this.substituteArrayList=substituteArrayList;
    }

    @Override
    public HolidayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_holidays, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(HolidayAdapter.ViewHolder holder, int position) {
        Holiday holiday=holidayArrayList.get(position);
        holder.date.setText(holiday.getDate());
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        holder.itemView.setAnimation(animAnticipateOvershoot);
        holder.substitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .title("Substitute Doctor")
                        .titleGravity(GravityEnum.CENTER)
                        // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                        .adapter(new SubstituteAdapter(context,substituteArrayList), null)
                        .titleColor(context.getResources().getColor(R.color.colorPrimaryDark))
                        .positiveText("ok")
                        .show();
            }
        });
       /* if (position%2==0)
        {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.card_back));
            holder.date.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
        }
         else {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));
        }
*/
    }
    @Override
    public int getItemCount() {
        return holidayArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,substitute;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date);
            substitute=(TextView)itemView.findViewById(R.id.substitute);
            cardView=(CardView)itemView.findViewById(R.id.cv);
            substitute.setPaintFlags(substitute.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        }
    }
}
