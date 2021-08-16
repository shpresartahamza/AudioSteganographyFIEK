package com.shpresarta.audiosteganography.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.shpresarta.audiosteganography.R;
import com.shpresarta.audiosteganography.databinding.FragmentCompareBinding;
import com.shpresarta.audiosteganography.model.FileData;
import com.shpresarta.audiosteganography.model.FileData1;
import com.shpresarta.audiosteganography.utils.FileHelper;
import com.shpresarta.audiosteganography.utils.FileHelper1;
import com.shpresarta.audiosteganography.viewmodel.CompareViewModel;
import com.shpresarta.audiosteganography.viewmodel.CompareViewModel1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class CompareFragment extends BaseFragment {

    private FragmentCompareBinding binding;
    private FragmentCompareBinding binding1;
    private CompareViewModel viewModel;
    private CompareViewModel1 viewModel1;
    private FileHelper fileHelperAudio;
    private FileHelper1 fileHelperAudio1;
    private FileData fileData;
    private FileData1 fileData1;
    private byte[] resultBytes;
    private byte[] resultBytes1;
    private byte[] initBytes;
    private byte[] initBytes1;
    String fileName;
    private double runningTime;
    double filesize1;
    double filesize2;

    public CompareFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(CompareViewModel.class);
        viewModel1 = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(CompareViewModel1.class);
        fileHelperAudio = new FileHelper(requireActivity());
        fileHelperAudio1 = new FileHelper1(requireActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompareBinding.inflate(inflater, container, false);
        binding.btnCompare.setOnClickListener((this));
        binding.btnSelectFileAudio1.setOnClickListener(this);
        binding.btnSelectFileAudio2.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel.getFileData().observe(getViewLifecycleOwner(), data -> {
            if (data != null) {
                this.fileData = data;
                this.initBytes = data.getFileBytes();
                binding.tvFilePath.setText(String.format("%s.%s", fileData.getFileName(), fileData.getFileExt()));

            }
        });
        viewModel1.getFileData1().observe(getViewLifecycleOwner(), data1 -> {
            if (data1 != null) {
                this.fileData1 = data1;
                this.initBytes1 = data1.getFileBytes1();
                binding.tvFilePath2.setText(String.format("%s.%s", fileData1.getFileName1(), fileData1.getFileExt1()));

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_select_file_audio_1:
                selectContent("audio/*");
                if(initBytes != null) {
                    FileSize();
                }
                break;
            case R.id.btn_select_file_audio_2:
                    selectContentModified("audio/*");
                   if(initBytes1 != null) {
                    FileSize1();
                }
                break;
            case R.id.btn_compare:
                if((initBytes != null) && (initBytes1 != null))
                {
                    CompareFiles();
                }
                break;
        }
    }


    @Override
    public void selectAudioFileCallback(Uri result) throws FileNotFoundException {
        fileHelperAudio.setPick(result, Build.VERSION.SDK_INT);
        viewModel.setFileData(requireContext().getContentResolver().openInputStream(result), fileHelperAudio.getFilePath());
        showSnackbar(R.string.read_audio_success);
    }
    @Override
    public void selectAudioFileCallback1(Uri result1) throws FileNotFoundException {
        fileHelperAudio1.setPick1(result1, Build.VERSION.SDK_INT);
        viewModel1.setFileData1(requireContext().getContentResolver().openInputStream(result1), fileHelperAudio1.getFilePath1());
        showSnackbar1(R.string.read_audio_success);
    }

    @Override
    public void saveAudioFileCallback(Uri result) {
        try (OutputStream outputStream = requireContext().getContentResolver().openOutputStream(result)) {
            if (outputStream != null) {
                outputStream.write(resultBytes);
            }
            showSnackbar(R.string.success_save_file);
        } catch (IOException e) {
            showSnackbar(R.string.failed_save_file);
        }
    }

    @Override
    public void saveAudioFileCallback1(Uri result1) {
        try (OutputStream outputStream = requireContext().getContentResolver().openOutputStream(result1)) {
            if (outputStream != null) {
                outputStream.write(resultBytes1);
            }
            showSnackbar1(R.string.success_save_file);
        } catch (IOException e) {
            showSnackbar1(R.string.failed_save_file);
        }
    }

    private void FileSize() {
        long startTime = System.nanoTime();
        resultBytes = viewModel.getFileSize(initBytes);
        long totalTime = System.nanoTime() - startTime;

        if (resultBytes != null) {
            runningTime = (double) totalTime / 1_000_000_000;
            showResult();
        } else {
            showSnackbar(R.string.process_failed_warning);
        }
    }

    private void showResult() {
        filesize1 = (double) resultBytes.length / 1000000;
        binding.tvStatus.setText(String.format(getString(R.string.get_file_size_to_mb), filesize1));
    }

    private void FileSize1() {
        long startTime = System.nanoTime();
        resultBytes1 = viewModel1.getFileSize(initBytes1);
        long totalTime = System.nanoTime() - startTime;

        if (resultBytes1 != null) {
            runningTime = (double) totalTime / 1_000_000_000;
            showResult1();
        } else {
            showSnackbar(R.string.process_failed_warning);
        }
    }


    private void showResult1() {
        filesize2 = (double) resultBytes1.length / 1000000;
        binding.tvStatus1.setText(String.format(getString(R.string.get_file_size_to_mb1), filesize2));
    }

    private void CompareFiles(){
        if(filesize1 < filesize2){
            binding.tvStatus2.setText(String.format(getString(R.string.compare_file_modified)));
        }else if(filesize1 > filesize2){
            binding.tvStatus2.setText(String.format(getString(R.string.compare_file_modified)));
        }else if(filesize1 == filesize2){
            binding.tvStatus2.setText(String.format(getString(R.string.compare_file_original)));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}