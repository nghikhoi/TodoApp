package com.example.todoapp;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.database.TodoTask;

import java.util.Calendar;
import java.util.Date;

public class EditFragment extends Fragment {

    private Button btnSave;
    private EditText etTitle, etDescription;
    private EditText etStartTime, etEndTime;
    private DatePickerDialog.OnDateSetListener onStartDateSet, onEndDateSet;
    private Calendar startDate, endDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_fragment, container, false);

        etTitle = v.findViewById(R.id.etTitle);
        etDescription = v.findViewById(R.id.etDescription);
        etStartTime = v.findViewById(R.id.etToDoStartTime);
        etEndTime = v.findViewById(R.id.etToDoEndTime);
        btnSave = v.findViewById(R.id.btnSave);

        onStartDateSet = (datePicker, year, month, dayOfMonth) -> {
            month += 1;
            startDate = Calendar.getInstance();
            startDate.set(year, month, dayOfMonth, 0, 0);
            etStartTime.setText(startDate.get(Calendar.DATE) + "/" + startDate.get(Calendar.MONTH) + "/" + startDate.get(Calendar.YEAR));
        };

        onEndDateSet = (datePicker, year, month, dayOfMonth) -> {
            month += 1;
            endDate = Calendar.getInstance();
            endDate.set(year, month, dayOfMonth, 0, 0);
            etEndTime.setText(endDate.get(Calendar.DATE) + "/" + endDate.get(Calendar.MONTH) + "/" + endDate.get(Calendar.YEAR));
        };

        etStartTime.setOnClickListener(view -> Utils.pickDate(getActivity(), onStartDateSet));

        etEndTime.setOnClickListener(view -> Utils.pickDate(getActivity(), onEndDateSet));

        btnSave.setOnClickListener(view -> {
            save();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, ListFragment.class, null).commit();
        });

        return v;
    }

    private void save() {
        String title = etTitle.getText().toString();
        String desc = etDescription.getText().toString();
        Date start = startDate.getTime();
        Date end = endDate.getTime();
        TodoTask task = new TodoTask(title, start, end, desc);

        ((MainActivity) getActivity()).getTodoManager().save(task);
    }

}
