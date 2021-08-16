package com.shpresarta.audiosteganography.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.shpresarta.audiosteganography.R;
import com.shpresarta.audiosteganography.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button english, albanian;
    private static TextView chooseText;
    private static Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        initViews();
        setListeners();
        loadLocale();

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;



    //Initiate all views
    private void initViews() {
        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        english = (Button) findViewById(R.id.changeMyLangEng);
        albanian = (Button) findViewById(R.id.changeMyLangAlb);
    }

    //Set Click Listener
    private void setListeners() {
        english.setOnClickListener(this);
        albanian.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String lang = "en";//Default Language
        switch (view.getId()) {
            case R.id.changeMyLangEng:
                lang = "en";
                break;
            case R.id.changeMyLangAlb:
                lang = "sq-rAL";
                break;
        }

        changeLocale(lang);//Change Locale on selection basis
    }


    //Change Locale
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
        updateTexts();//Update texts according to locale
    }

    //Save locale method in preferences
    public void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }

    //Get locale method in preferences
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        changeLocale(language);
    }

    //Update text methods
    private void updateTexts() {
        english.setText(R.string.changeLAng_Eng);
        albanian.setText(R.string.changeLang_alb);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp();
    }
}