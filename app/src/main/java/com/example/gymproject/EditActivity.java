package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanRecViewAdapter.DeletePlan {

    private Button btnAddMorePlan;
    private TextView txtDay;
    private RecyclerView recyclerView;
    private PlanRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();
        adapter = new PlanRecViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setType("edit");

        Intent intent = getIntent();
        try {
            String day = intent.getStringExtra("day");
            if(day!=null){
                txtDay.setText(day);

                ArrayList<Plan> plans = new ArrayList<>();
                for(Plan plan:Utils.getUsersPlan()){
                    if(plan.getDay().equals(day)){
                        plans.add(plan);
                    }
                }
                adapter.setPlans(plans);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        btnAddMorePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this, AllTrainingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnAddMorePlan = findViewById(R.id.btnAddMorePlan);
        txtDay = findViewById(R.id.txtDay);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onDeletingPlan(String day) {
        txtDay.setText(day);
        ArrayList<Plan> plans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals(day)){
                plans.add(plan);
            }
        }
        adapter.setPlans(plans);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}
