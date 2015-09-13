package pl.droidcon.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.droidcon.app.R;


public class AboutFragment extends BaseFragment {

    public static final String TAG = AboutFragment.class.getSimpleName();

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_fragment, container, false);
    }

    @Override
    public int getTitle() {
        return R.string.about;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
