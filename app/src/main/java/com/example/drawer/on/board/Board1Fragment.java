package com.example.drawer.on.board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawer.R;
import com.example.drawer.databinding.FragmentBoard1Binding;
import com.example.drawer.unitt.PreferenceHelper;


public class Board1Fragment extends Fragment {

    private FragmentBoard1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoard1Binding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickSkip1();
    }

    private void setClickSkip1() {
        binding.tvSkip1.setOnClickListener(v -> {
            PreferenceHelper helper = new PreferenceHelper();
            helper.unit(requireContext());
            helper.onSaveOnBoardState();
            close();
        });
    }

    public void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }
}