package com.breakingthebasics.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.breakingthebasics.R;
import com.breakingthebasics.common.MyPref;
import com.breakingthebasics.model.Classes_Model;

import java.util.List;


public class CoursesCLassesAdapter extends RecyclerView.Adapter <CoursesCLassesAdapter.MyViewHolder>{
    private List<Classes_Model> catemodels ;
    private Context context;
    MyPref myPref;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtname;
        public ImageView imgclass;

        public MyViewHolder(View view) {
            super(view);
            txtname =  view.findViewById(R.id.txtname);
            imgclass=view.findViewById(R.id.img_class);
            myPref = new MyPref(context);
        }
    }


    public CoursesCLassesAdapter(Context context, List<Classes_Model> modelCategories) {
        this.catemodels = modelCategories;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_classes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtname.setText(""+position);
//        Picasso.get().load(imgUrl+catemodels.get(position).getImage()).into(holder.imgclass);

        if(catemodels.get(position).getIs_free().equalsIgnoreCase("1")||(myPref.getSubscription().toString()).equalsIgnoreCase("1")){
            holder.imgclass.setImageResource(R.drawable.ic_play);
        }else{
            holder.imgclass.setImageResource(R.drawable.ic_lock);
        }
    }
    @Override
    public int getItemCount() {
        return catemodels.size();
    }
}



