package com.example.drawer.on.board;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawer.R;
import com.example.drawer.adapter.ViewPagerAdapter;
import com.example.drawer.databinding.FragmentBoard1Binding;
import com.example.drawer.databinding.FragmentBoard2Binding;


public class Board2Fragment extends Fragment {

    FragmentBoard2Binding binding;

//getActivity().getSupportFragmentManager()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        binding = FragmentBoard2Binding.inflate(getLayoutInflater(), container, false);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager, true);
        return binding.getRoot();
    }
}