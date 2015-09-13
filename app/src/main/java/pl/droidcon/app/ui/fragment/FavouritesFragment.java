package pl.droidcon.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.droidcon.app.R;

public class FavouritesFragment extends BaseFragment {

    public static final String TAG = FavouritesFragment.class.getSimpleName();

    public static FavouritesFragment newInstance() {
        Bundle args = new Bundle();
        FavouritesFragment fragment = new FavouritesFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favourites_fragment, container, false);
    }

    @Override
    public int getTitle() {
        return R.string.favourites;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }
}

