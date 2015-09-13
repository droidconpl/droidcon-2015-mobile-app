package pl.droidcon.app.ui.fragment;


import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @StringRes
    public abstract int getTitle();

    public abstract String getFragmentTag();
}
