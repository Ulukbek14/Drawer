package com.example.drawer.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.view.inputmethod.InputMethodManager;

import com.example.drawer.R;
import com.example.drawer.databinding.FragmentFormBinding;
import com.example.drawer.model.TaskModel;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    TaskModel taskModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        initClickListener(navController);
        setFocusForEditText();
        return binding.getRoot();
    }

    private void setFocusForEditText() {
        binding.etTitle.post(() -> {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    binding.etTitle.getApplicationWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
            binding.etTitle.requestFocus();
        });
    }

    private void initClickListener(NavController navController) {
        binding.tvDone.setOnClickListener(v -> {
            if (binding.etTitle.getText().toString().trim().equals("")) {
                binding.etTitle.setError("Введите текст");
            } else {
                String title = binding.etTitle.getText().toString();
                taskModel = new TaskModel(title);
                Bundle bundle = new Bundle();
                bundle.putSerializable("done", taskModel);
                getParentFragmentManager().setFragmentResult("res", bundle);
                navController.navigateUp();
            }
        });

        binding.ivArrowBack.setOnClickListener(v -> navController.navigate(R.id.nav_home));

    }
}