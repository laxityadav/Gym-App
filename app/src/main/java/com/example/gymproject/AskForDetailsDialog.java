package com.example.gymproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class AskForDetailsDialog extends DialogFragment {

    private EditText timeEditText;
    private Button btnAdd, btnCancel;
    private Spinner spinnerDay;
    private TextView trainingName;

    public interface GetDetails{
        public void onGettingDetailsResult(Plan plan);
    }

    private GetDetails getDetails;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_ask_for_details, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Enter Details")
                .setView(view);

        initViews(view);
        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, days);
        spinnerDay.setAdapter(adapter);
        Bundle bundle = getArguments();
        GymTraining training = bundle.getParcelable("training");
        trainingName.setText(training.getName());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        try {
            getDetails = (GetDetails)getActivity();             //?????? not clear
        }catch (ClassCastException e){
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                GymTraining training = bundle.getParcelable("training");
                if(null != training){
                    Plan plan = new Plan();
                    plan.setAccomplished(false);
                    plan.setDay(spinnerDay.getSelectedItem().toString());
                    plan.setMinutes(Integer.valueOf(timeEditText.getText().toString()));
                    plan.setTraining(training);
                    getDetails.onGettingDetailsResult(plan);
                }
            }
        });
        return builder.create();
    }

    private void initViews(View view) {
        btnAdd = view.findViewById(R.id.btnAdd);
        btnCancel = view.findViewById(R.id.btnCancel);
        spinnerDay = view.findViewById(R.id.spinnerDay);
        timeEditText = view.findViewById(R.id.edtTxtTime);
        trainingName = view.findViewById(R.id.trainingName);
    }
}
