package pl.droidcon.app.ui.fragment.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.droidcon.app.R;
import pl.droidcon.app.service.FetchDataService;


public class AgendaFragment extends Fragment {


    public static AgendaFragment newInstance() {
        Bundle args = new Bundle();

        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent msgIntent = new Intent(getContext(), FetchDataService.class);
        getContext().startService(msgIntent);
        return inflater.inflate(R.layout.agenda_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
