package pl.droidcon.app.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.reminder.SessionReminder;
import pl.droidcon.app.dagger.DroidconInjector;

public class SettingsActivity extends AppCompatActivity {

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ButterKnife.bind(this);
        setupToolbarBack(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(R.string.settings);
        }
        getFragmentManager().beginTransaction().replace(R.id.settings, PreferencesFragment.newInstance()).commit();
    }

    private void setupToolbarBack(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public static class PreferencesFragment extends PreferenceFragment {

        public static PreferencesFragment newInstance() {
            Bundle args = new Bundle();
            PreferencesFragment fragment = new PreferencesFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Inject
        SessionReminder sessionReminder;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            DroidconInjector.get().inject(this);
            addPreferencesFromResource(R.xml.preferences_fragment);
            CheckBoxPreference notifySessionsPreference = (CheckBoxPreference) findPreference(getString(R.string.preference_notify_sessions));
            notifySessionsPreference.setChecked(sessionReminder.isReminding());
            notifySessionsPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    sessionReminder.setReminding((Boolean) newValue);
                    return true;
                }
            });
        }
    }

}
