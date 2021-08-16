package com.shpresarta.audiosteganography.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.shpresarta.audiosteganography.R;
import com.shpresarta.audiosteganography.databinding.FragmentHomeBinding;

import java.util.Locale;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NavController navController;
    private static Button english, albanian;
    private static TextView chooseText;
    private static Locale myLocale;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        navController = Navigation.findNavController(view);

        binding.btnEmbedMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_embed));
        binding.btnCompressMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_compress));
        binding.btnDecompressMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_decompress));
        binding.btnExtractMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_extract));
//        binding.btnAboutMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_about));
        binding.btnAboutMenu.setOnClickListener(v -> navController.navigate(R.id.action_home_to_about));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
