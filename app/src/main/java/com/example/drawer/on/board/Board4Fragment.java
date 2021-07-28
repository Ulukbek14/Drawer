package com.example.drawer.on.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawer.R;
import com.example.drawer.databinding.FragmentBoard4Binding;
import com.example.drawer.unitt.PreferenceHelper;

public class Board4Fragment extends Fragment {

    FragmentBoard4Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoard4Binding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickStart();
    }

    private void setClickStart() {
        binding.tvStart.setOnClickListener(v -> {
            PreferenceHelper prefHelper = new PreferenceHelper();
            prefHelper.unit(requireContext());
            prefHelper.onSaveOnBoardState();
            close();
        });
    }

    public void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }
}