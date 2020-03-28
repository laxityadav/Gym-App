package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements AskForDetailsDialog.GetDetails {

    private TextView trainingName, trainingLongDesc;
    private Button btnAddToPlan;
    private ImageView trainingImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();
        Intent intent = getIntent();
        try {
            final GymTraining incomingTraining = intent.getParcelableExtra("training");
            trainingName.setText(incomingTraining.getName());
            trainingLongDesc.setText(incomingTraining.getLongDesc());
            Glide.with(this)
                    .asBitmap()
                    .load(incomingTraining.getImageUrl())
                    .into(trainingImage);

            btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     AskForDetailsDialog askForDetailsDialog = new AskForDetailsDialog();
                     Bundle bundle = new Bundle();
                     bundle.putParcelable("training", incomingTraining);
                     askForDetailsDialog.setArguments(bundle);
                     askForDetailsDialog.show(getSupportFragmentManager(), "ask for details");
                }
            });
        }catch (NullPointerException e){
            e.getMessage();
        }

    }

    private void initViews() {
        trainingImage = findViewById(R.id.trainingImage);
        trainingLongDesc = findViewById(R.id.trainingLongDesc);
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        trainingName = findViewById(R.id.trainingName);
    }

    @Override
    public void onGettingDetailsResult(Plan plan) {
        Utils.addToUserPlan(plan);
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("plan", plan);
        startActivity(intent);
    }
}
