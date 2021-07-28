package com.example.drawer.ui.home;

import android.os.Binder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.drawer.ItemOnClickListener;
import com.example.drawer.R;
import com.example.drawer.adapter.TaskAdapter;
import com.example.drawer.databinding.FragmentHomeBinding;
import com.example.drawer.model.TaskModel;
import com.example.drawer.room.MyApp;

import org.jetbrains.annotations.NotNull;

import java.nio.BufferUnderflowException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemOnClickListener {

    private static final String BUNDLE_KEY = "dd";
    private FragmentHomeBinding binding;
    private TaskAdapter adapter;
    private boolean isLinearLayout = true;
    private boolean isList = true;
    private int position;

    private List<TaskModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
        getNotesFromDB();
        initSearch();
        deleteData();
    }

    private void deleteData() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                TaskModel mtaskModel = adapter.getWordAtPosition(position);
                MyApp.getInstance().taskDao().delete(mtaskModel);

            }
        });
        touchHelper.attachToRecyclerView(binding.rvRecycler);
    }

    private void getNotesFromDB() {
        MyApp.getInstance().taskDao().getAll().observe(requireActivity(), taskModels -> {
            adapter.setList(taskModels);
            list = taskModels;
        });
    }

    private void initSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<TaskModel> filteredList = new ArrayList<>();
        for (TaskModel item : list) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void setupRecycler() {
        binding.rvRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvRecycler.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter(isList, HomeFragment.this);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            if (isLinearLayout) {
                item.setIcon(R.drawable.ic_baseline_format_list_bulleted_24);
                binding.rvRecycler.setLayoutManager(new StaggeredGridLayoutManager(
                        2, StaggeredGridLayoutManager.VERTICAL));
                binding.rvRecycler.setAdapter(adapter);
                isLinearLayout = false;
            } else {
                item.setIcon(R.drawable.ic_dashboard);
                binding.rvRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.rvRecycler.setAdapter(adapter);
                isLinearLayout = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemClick(int position, TaskModel taskModel) {
        this.position = position;
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, taskModel);
        NavController navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_nav_home_to_formFragment, bundle);
    }
}