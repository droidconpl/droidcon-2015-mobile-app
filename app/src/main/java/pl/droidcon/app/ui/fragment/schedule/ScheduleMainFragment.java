package pl.droidcon.app.ui.fragment.schedule;

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
import pl.droidcon.app.dagger.DroidconInjector;
import pl.droidcon.app.model.common.SessionDay;
import pl.droidcon.app.ui.fragment.BaseFragment;

public class ScheduleMainFragment extends BaseFragment {

    public static final String TAG = ScheduleMainFragment.class.getSimpleName();

    public static ScheduleMainFragment newInstance() {
        Bundle args = new Bundle();
        ScheduleMainFragment fragment = new ScheduleMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DroidconInjector.get().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favourites_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, getView());
        viewPager.setAdapter(new MyScheduleFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getTitle() {
        return R.string.favourites;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    private class MyScheduleFragmentAdapter extends FragmentPagerAdapter {

        public MyScheduleFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScheduleFragment.newInstance(SessionDay.values()[position]);
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

