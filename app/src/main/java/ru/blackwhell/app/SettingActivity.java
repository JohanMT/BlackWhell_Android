package ru.blackwhell.app;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public final class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}
