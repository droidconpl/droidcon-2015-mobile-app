package pl.droidcon.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.droidcon.app.R;

public class MapAndInfoFragment extends BaseFragment {

    public static final String TAG = MapAndInfoFragment.class.getSimpleName();

    public static MapAndInfoFragment newInstance() {
        Bundle args = new Bundle();
        MapAndInfoFragment fragment = new MapAndInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_fragment, container, false);
    }

    @Override
    public int getTitle() {
        return R.string.map_and_info;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
