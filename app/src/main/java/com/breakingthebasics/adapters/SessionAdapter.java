package com.breakingthebasics.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.breakingthebasics.R;
import com.breakingthebasics.model.SessionData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SessionAdapter extends RecyclerView.Adapter <SessionAdapter.MyViewHolder>{
    private List<SessionData> catemodels ;
    private Context context;
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd, yyyy");
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtname;

        public MyViewHolder(View view) {
            super(view);

            txtname =  view.findViewById(R.id.txtname);
        }
    }


    public SessionAdapter(Context context, List<SessionData> modelCategories) {
        this.catemodels = modelCategories;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_session, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String dates="";
        try {
            if (!catemodels.get(position).getDate().equalsIgnoreCase("")) {

                Date date = fmt.parse(catemodels.get(position).getDate());
                dates = fmtOut.format(date);
            }
        } catch (Exception e) {

        }

        holder.txtname.setText(dates+" | "+catemodels.get(position).getClass_name());
    }
    @Override
    public int getItemCount() {
        return catemodels.size();
    }
}



