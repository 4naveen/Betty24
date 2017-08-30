package com.lorvent.betty24.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lorvent.betty24.DoctorDetailsActivity;
import com.lorvent.betty24.R;
import com.lorvent.betty24.model.Doctor;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by User on 4/27/2017.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>{

    private Context context;
    public ArrayList<Doctor> doctorArrayList;
    public static final int MY_PERMISSIONS_REQUEST_CALL = 101;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorArrayList) {
        this.context = context;
        this.doctorArrayList = doctorArrayList;
    }
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indi_view_doctor, parent, false);
        return new ViewHolder(v);

    }
    @Override
    public void onBindViewHolder(DoctorAdapter.ViewHolder holder, int position) {
        Doctor doctor=doctorArrayList.get(position);
        holder.name.setText(doctor.getDoctor_name());
        holder.txt1.setText(doctor.getAddress());
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,txt1,send,more;
        ImageView contact_image;
        LinearLayout layout,layout2;
        ViewHolder(View itemView) {
            super(itemView);
            layout=(LinearLayout)itemView.findViewById(R.id.layout1);
            layout2=(LinearLayout)itemView.findViewById(R.id.layout2);

            name=(TextView)itemView.findViewById(R.id.name);
            txt1=(TextView)itemView.findViewById(R.id.txt1);
            send=(TextView)itemView.findViewById(R.id.send);
            more=(TextView)itemView.findViewById(R.id.call);
            send.setPaintFlags(send.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            more.setPaintFlags(more.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            contact_image=(ImageView)itemView.findViewById(R.id.contact_image);
            more.setOnClickListener(this);
            send.setOnClickListener(this);
            layout.setOnClickListener(this);
            layout2.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (v.getId() ==R.id.layout1)
            {
                Intent i = new Intent(context, DoctorDetailsActivity.class);
                context.startActivity(i);
            }
            if (v.getId() ==R.id.layout2)
            {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                context.startActivity(callIntent);
/*
                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AppSession.mActivity,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL);
                }
                else {

                }*/

            }
           if (v.getId()==R.id.send)
           {send.setVisibility(View.INVISIBLE);
               new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                       .setTitleText("Success!")
                       .setContentText("Your details has been successfully sent to your doctor")

                       .show();
           }
        }
    }
}
