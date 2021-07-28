package com.example.drawer.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.drawer.on.board.Board1Fragment;
import com.example.drawer.on.board.Board2Fragment;
import com.example.drawer.on.board.Board4Fragment;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Board1Fragment();
            case 1:
                return new Board2Fragment();
            case 2:
                return new Board4Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}