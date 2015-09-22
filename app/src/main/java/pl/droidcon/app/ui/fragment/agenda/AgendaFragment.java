package pl.droidcon.app.ui.fragment.agenda;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.droidcon.app.R;
import pl.droidcon.app.model.api.AgendaAndSpeakersResponse;
import pl.droidcon.app.rx.AgendaFragmentSubscription;
import pl.droidcon.app.ui.adapter.AgendaAdapter;
import rx.functions.Action1;


public class AgendaFragment extends Fragment {

    private RecyclerView agendaList;

    private AgendaFragmentSubscription agendaFragmentSubscription;

    public static AgendaFragment newInstance() {
        Bundle args = new Bundle();
        AgendaFragment fragment = new AgendaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agendaFragmentSubscription = new AgendaFragmentSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agenda_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        agendaList = (RecyclerView) view.findViewById(R.id.agenda_view);
        agendaList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        agendaList.setLayoutManager(mLayoutManager);
        agendaList.addItemDecoration(new SpacesItemDecoration(view.getContext().getResources().getDimension(R.dimen.list_element_margin)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        agendaFragmentSubscription.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        agendaFragmentSubscription.unSubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        agendaFragmentSubscription.bind(new Action1<AgendaAndSpeakersResponse>() {
            @Override
            public void call(AgendaAndSpeakersResponse agendaResponse) {
                update(agendaResponse);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        agendaFragmentSubscription.bind(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void update(AgendaAndSpeakersResponse agendaResponse) {
        AgendaAdapter mAdapter = new AgendaAdapter(agendaResponse.agendaAndSpeakers);
        agendaList.setAdapter(mAdapter);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(float space) {
            this.space = (int) space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int childPosition = parent.getChildPosition(view);

            if (childPosition % 2 == 0) {
                // left element
                outRect.right = (int) (space * 0.5f);
                outRect.left = space;
            } else {
                // right element
                outRect.right = space;
                outRect.left = (int) (space * 0.5f);
            }

            outRect.bottom = space;
        }
    }
}
