package com.example.realm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public RecyclerViewAdapter(List<DataModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }

    List<DataModel>dataModelList;
    Context context;



    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_course_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        DataModel model = dataModelList.get(position);

        holder.vh_coursetrack.setText(model.getCourseTracks());
        holder.vh_coursedesc.setText(model.getCourseDescription());
        holder.vh_courseduration.setText(model.getCourseDuration());
        holder.vh_coursename.setText(model.getCourseName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(context,CourseUpdate.class);
                update.putExtra("CourseName",model.getCourseName());
                update.putExtra("CourseDesc",model.getCourseDescription());
                update.putExtra("CourseTrack",model.getCourseTracks());
                update.putExtra("CoursDuration",model.getCourseDuration());
                update.putExtra("id",model.getId());
                context.startActivity(update);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,CourseUpdate.class);
                i.putExtra("courseName",model.getCourseName());
                i.putExtra("courseDescription",model.getCourseDescription());
                i.putExtra("courseDuration",model.getCourseDuration());
                i.putExtra("courseTracks",model.getCourseTracks());
                i.putExtra("id",model.getId());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView vh_coursename;
        private  TextView vh_courseduration;
        private  TextView vh_coursetrack;
        private  TextView vh_coursedesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vh_coursename = itemView.findViewById(R.id.cv_coursename);
            vh_courseduration = itemView.findViewById(R.id.cv_courseDuration);
            vh_coursetrack = itemView.findViewById(R.id.cv_coursetrack);
            vh_coursedesc = itemView.findViewById(R.id.cv_courseDesc);

        }


    }


}
