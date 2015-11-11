package pl.droidcon.app.ui.fragment;

import android.content.Intent;
import android.net.Uri;
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
        View inflate = inflater.inflate(R.layout.map_fragment, container, false);

        inflate.findViewById(R.id.location_open_venue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("1st & Pike, Seattle"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        return inflate;
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
