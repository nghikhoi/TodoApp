package com.example.todoapp;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Page2 extends Fragment {

    private Page2ViewModel mViewModel;
    private Button btnSave;
    private EditText etStartTime,etEndTime;
    private DatePickerDialog.OnDateSetListener onStartDateSet, onEndDateSet;
    private Calendar startDate, endDate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page2_fragment, container, false);

        etStartTime =v.findViewById(R.id.etToDoStartTime);
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

        etStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.pickDate(getActivity(),onStartDateSet);
            }
        });

        etEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.pickDate(getActivity(),onEndDateSet);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView,Page1.class,null).commit();
            }
        });

        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Page2ViewModel.class);
        // TODO: Use the ViewModel
    }

}
