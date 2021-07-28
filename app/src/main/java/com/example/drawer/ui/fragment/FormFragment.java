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
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.drawer.R;
import com.example.drawer.databinding.FragmentFormBinding;
import com.example.drawer.model.TaskModel;
import com.example.drawer.room.MyApp;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private TaskModel taskModel;
    private TaskModel model;
    public final static String REQUEST_KEY = "res";
    public final static String BUNDLE_KEY = "done";
    public final static String BUNDLE_UPDATE_KEY = "updateDone";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickListener();
        getDataForEdit();
        setFocusForEditText();
    }

    private void getDataForEdit() {
        if (getArguments() != null) {
            taskModel = (TaskModel) getArguments().getSerializable(BUNDLE_KEY);
            if (taskModel != null) {
                binding.etTitle.setText(taskModel.getTitle());
            }
        }
    }

    private void setFocusForEditText() {
        binding.etTitle.post(() -> {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    binding.etTitle.getApplicationWindowToken(),
                    InputMethodManager.SHOW_IMPLICIT, 0);
            binding.etTitle.requestFocus();
        });
    }

    private void initClickListener() {
        NavController navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host_fragment_content_main);
        binding.tvDone.setOnClickListener(v -> {
            if (binding.etTitle.getText().toString().trim().equals("")) {
                binding.etTitle.setError("Введите текст");
                YoYo.with(Techniques.SlideInDown  )
                        .duration(1008)
                        .repeat(1)
                        .playOn(binding.tvDone);
                return;
            }
            String title = binding.etTitle.getText().toString();
            Bundle bundle = new Bundle();
            model = new TaskModel(title, "19.07.2021");
            if (taskModel == null) {
                MyApp.getInstance().taskDao().insertTask(model);
                bundle.putSerializable(BUNDLE_KEY, model);
            } else {
                taskModel.setTitle(title);
                MyApp.getInstance().taskDao().update(taskModel);
                bundle.putSerializable(BUNDLE_UPDATE_KEY, taskModel);
            }
            getParentFragmentManager().setFragmentResult(REQUEST_KEY, bundle);
            close();
        });
        binding.ivArrowBack.setOnClickListener(v -> navController.navigateUp());
    }

    public void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }
}