package com.example.testaplicationtwo;

package com.example.myapplication;

import android.app.Activity;
import android.media.MediaDrm;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MainScreenActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.values.activity_main_screen, null, false);
        Log.d("help", "THIS HAS BEEN REACHED 2-1");
        View rootView = inflater.inflate(R.layout.activity_main_screen, container, false);

        Log.d("help", "THIS HAS BEEN REACHED 2-2");
        //getView().inflate(inflater, container, false);
        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState, ViewGroup container) {
        public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);

//            Button button = (Button) container.findViewById(R.id.main_registerButton);
//            Log.d("help", "THIS HAS BEEN REACHED 3");
//            Button button = (Button) view.findViewById(R.id.main_registerButton);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    NavHostFragment.findNavController(MainScreenActivity.this)
//                            .navigate(R.id.action_activity_main_screen_to_activity_registration);
//                }
//            });
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }

    }
}
