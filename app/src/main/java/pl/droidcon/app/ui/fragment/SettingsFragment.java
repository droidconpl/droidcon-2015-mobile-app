package pl.droidcon.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.droidcon.app.R;
import pl.droidcon.app.ui.view.SettingButton;

public class SettingsFragment extends BaseFragment {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.settings_notify_about_my_sessions)
    SettingButton mySessionsNotify;

    @Bind(R.id.settings_notify_about_feedback)
    SettingButton feedbackNotify;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public int getTitle() {
        return R.string.settings;
    }


    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @OnClick(R.id.settings_notify_about_my_sessions)
    void onNotifyAboutSelectedSessionsClicked() {
        mySessionsNotify.toggle();
    }

    @OnClick(R.id.settings_notify_about_feedback)
    void onNotifyAboutFeedbackClicked() {
        feedbackNotify.toggle();
    }
}
