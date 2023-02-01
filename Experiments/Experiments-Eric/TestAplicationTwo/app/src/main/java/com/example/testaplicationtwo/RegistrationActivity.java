package com.example.testaplicationtwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

    public class RegistrationActivity extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            //ImageView usersImage = (ImageView) container.findViewById(R.id.UsersImage);
            return getView();
        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }
    }
