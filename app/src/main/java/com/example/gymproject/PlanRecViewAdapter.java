package com.example.gymproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlanRecViewAdapter extends RecyclerView.Adapter<PlanRecViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Plan> plans = new ArrayList<>();
    private String type="";
    private DeletePlan deletePlan;

    public interface DeletePlan{
        public void onDeletingPlan(String day);
    }

    public PlanRecViewAdapter(Context context) {
        this.context = context;
    }

    public PlanRecViewAdapter() {
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.activityName.setText(plans.get(position).getTraining().getName());
        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.activityImage);
        holder.activityTime.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.activityShortDescription.setText(plans.get(position).getTraining().getShortDesc());
        if(plans.get(position).isAccomplished()){
            holder.emptyCheckBox.setVisibility(View.GONE);
            holder.filledCheckBox.setVisibility(View.VISIBLE);
        }
        else{
            holder.emptyCheckBox.setVisibility(View.VISIBLE);
            holder.filledCheckBox.setVisibility(View.GONE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra("training", plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if(type.equals("edit")){
            holder.emptyCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Accomplished?")
                            .setMessage("Have you finished "+plans.get(position).getTraining().getName())
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    plans.get(position).setAccomplished(true);
                                    for(Plan plan:Utils.getUsersPlan()){
                                        if(plan.equals(plans.get(position))){
                                            plan.setAccomplished(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
            });

            try{
                deletePlan = (DeletePlan)context;
            }catch (Exception e){
                e.printStackTrace();
            }
            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Delete")
                            .setMessage("are you sure you want to delete "+plans.get(position).getTraining().getName())
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Utils.removeUserPlan(plans.get(position));
                            deletePlan.onDeletingPlan(plans.get(position).getDay());
                        }
                    });
                    builder.create().show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView activityImage, emptyCheckBox, filledCheckBox;
        private TextView activityName, activityTime, activityShortDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            activityImage = itemView.findViewById(R.id.activityImage);
            activityName = itemView.findViewById(R.id.activityName);
            activityTime = itemView.findViewById(R.id.txtTimeAmount);
            activityShortDescription = itemView.findViewById(R.id.txtShortDescription);
            emptyCheckBox = itemView.findViewById(R.id.emptyCheckBox);
            filledCheckBox = itemView.findViewById(R.id.filledCheckBox);
        }
    }

    public void setType(String type) {
        this.type = type;
    }
}
