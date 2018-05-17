package com.example.intern.myapplication;

import android.content.res.Resources;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThemeSettingsActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_theme_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        RadioButton Turquoise = (RadioButton) getActivity().findViewById(R.id.radio1);
        RadioButton Blue = (RadioButton) getActivity().findViewById(R.id.radio2);
        RadioButton Marsala = (RadioButton) getActivity().findViewById(R.id.radio3);
        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radiog);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch (checkedId){
                    case R.id.radio1:
                        //getActivity().setTheme(R.style.AppThemeTurquoise);
                        getActivity().getApplicationContext().setTheme(R.style.AppThemeTurquoise);
                        break;
                    case R.id.radio2:
                        //getActivity().setTheme(R.style.AppThemeBlue);
                        getActivity().getApplicationContext().setTheme(R.style.AppThemeTurquoise);
                        break;
                    case R.id.radio3:
                        //getActivity().setTheme(R.style.AppThemeMarsala);
                        getActivity().getApplicationContext().setTheme(R.style.AppThemeTurquoise);
                        break;
                }
            }

        });


    }

}
