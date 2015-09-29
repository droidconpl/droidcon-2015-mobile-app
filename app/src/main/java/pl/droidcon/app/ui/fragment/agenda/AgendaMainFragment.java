package pl.droidcon.app.ui.fragment.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidcon.app.R;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.ui.fragment.BaseFragment;


public class AgendaMainFragment extends BaseFragment {

    public static final String TAG = AgendaMainFragment.class.getSimpleName();

    public static AgendaMainFragment newInstance() {
        Bundle args = new Bundle();
        AgendaMainFragment fragment = new AgendaMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agenda_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());
        viewPager.setAdapter(new AgendaFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getTitle() {
        return R.string.agenda;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    private class AgendaFragmentAdapter extends FragmentPagerAdapter {

        public AgendaFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return AgendaFragment.newInstance(SessionDay.values()[position]);
        }

        @Override
        public int getCount() {
            return SessionDay.values().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int humanReadable = SessionDay.values()[position].humanReadableDateStringId;
            return getString(humanReadable);
        }
    }
}
