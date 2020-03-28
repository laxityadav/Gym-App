package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    private RecyclerView mondayRecView, tuesdayRecView, wednesdayRecView, thursdayRecView, fridayRecView, saturdayRecView, sundayRecView;
    private NestedScrollView nestedScrollView;
    private TextView mondayEdit, tuesdayEdit, wednesdayEdit, thursdayEdit, fridayEdit, saturdayEdit, sundayEdit;
    private Button btnAddPlan;
    private RelativeLayout notAddedPlanRelLayout;

    private PlanRecViewAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter, saturdayAdapter, sundayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initialise();
        initAdapter();
        initRecViews();

        if(Utils.getUsersPlan().size()>0){
            notAddedPlanRelLayout.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
        }
        else{
            notAddedPlanRelLayout.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
        }

        setOnClickListeners();
    }

    private void setOnClickListeners(){
        mondayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Monday");
                startActivity(intent);
            }
        });
        tuesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Tuesday");
                startActivity(intent);
            }
        });
        wednesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Wednesday");
                startActivity(intent);
            }
        });
        thursdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Thursday");
                startActivity(intent);
            }
        });
        fridayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Friday");
                startActivity(intent);
            }
        });
        saturdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Saturday");
                startActivity(intent);
            }
        });
        sundayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, EditActivity.class);
                intent.putExtra("day", "Sunday");
                startActivity(intent);
            }
        });

        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.this, AllTrainingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void initRecViews(){
        mondayRecView.setAdapter(mondayAdapter);
        mondayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> mondayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Monday")){
                mondayPlans.add(plan);
            }
        }
        mondayAdapter.setPlans(mondayPlans);

        tuesdayRecView.setAdapter(tuesdayAdapter);
        tuesdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> tuesdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Tuesday")){
                tuesdayPlans.add(plan);
            }
        }
        tuesdayAdapter.setPlans(tuesdayPlans);

        wednesdayRecView.setAdapter(wednesdayAdapter);
        wednesdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> wednesdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Wednesday")){
                wednesdayPlans.add(plan);
            }
        }
        wednesdayAdapter.setPlans(wednesdayPlans);

        thursdayRecView.setAdapter(thursdayAdapter);
        thursdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> thursdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Thursday")){
                thursdayPlans.add(plan);
            }
        }
        thursdayAdapter.setPlans(thursdayPlans);

        fridayRecView.setAdapter(fridayAdapter);
        fridayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> fridayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Friday")){
                fridayPlans.add(plan);
            }
        }
        fridayAdapter.setPlans(fridayPlans);

        saturdayRecView.setAdapter(saturdayAdapter);
        saturdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> saturdayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Saturday")){
                saturdayPlans.add(plan);
            }
        }
        saturdayAdapter.setPlans(saturdayPlans);

        sundayRecView.setAdapter(sundayAdapter);
        sundayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<Plan> sundayPlans = new ArrayList<>();
        for(Plan plan:Utils.getUsersPlan()){
            if(plan.getDay().equals("Sunday")){
                sundayPlans.add(plan);
            }
        }
        sundayAdapter.setPlans(sundayPlans);
    }

    private void initAdapter() {
        mondayAdapter = new PlanRecViewAdapter(this);
        tuesdayAdapter = new PlanRecViewAdapter(this);
        wednesdayAdapter = new PlanRecViewAdapter(this);
        thursdayAdapter = new PlanRecViewAdapter(this);
        fridayAdapter = new PlanRecViewAdapter(this);
        saturdayAdapter = new PlanRecViewAdapter(this);
        sundayAdapter = new PlanRecViewAdapter(this);
    }

    private void initialise() {
        mondayEdit = findViewById(R.id.editMondayPlan);
        tuesdayEdit = findViewById(R.id.editTuesdayPlan);
        wednesdayEdit = findViewById(R.id.editWednesdayPlan);
        thursdayEdit = findViewById(R.id.editThursdayPlan);
        fridayEdit = findViewById(R.id.editFridayPlan);
        saturdayEdit = findViewById(R.id.editSaturdayPlan);
        sundayEdit = findViewById(R.id.editSundayPlan);

        btnAddPlan = findViewById(R.id.btnAddPlan);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        notAddedPlanRelLayout = findViewById(R.id.notAddedPlanRelLayout);

        mondayRecView = findViewById(R.id.mondayRecView);
        tuesdayRecView = findViewById(R.id.tuesdayRecView);
        wednesdayRecView = findViewById(R.id.wednesdayRecView);
        thursdayRecView = findViewById(R.id.thursdayRecView);
        fridayRecView = findViewById(R.id.fridayRecView);
        saturdayRecView = findViewById(R.id.saturdayRecView);
        sundayRecView = findViewById(R.id.sundayRecView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
