package pl.droidcon.app.ui.fragment.factory;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

import pl.droidcon.app.R;
import pl.droidcon.app.ui.fragment.AboutFragment;
import pl.droidcon.app.ui.fragment.BaseFragment;
import pl.droidcon.app.ui.fragment.MapAndInfoFragment;
import pl.droidcon.app.ui.fragment.agenda.AgendaMainFragment;
import pl.droidcon.app.ui.fragment.schedule.ScheduleMainFragment;

public class DrawerFragmentFactory {

    private static final String CURRENT_FRAGMENT = "current_fragment";

    private static final int FRAGMENTS_SIZE = 5;

    private Map<String, BaseFragment> baseFragmentMap = new HashMap<>(FRAGMENTS_SIZE);

    @IdRes
    private int currentFragmentMenuId = -1;

    public void saveInstanceState(Bundle outState, FragmentManager fragmentManager) {
        outState.putInt(CURRENT_FRAGMENT, currentFragmentMenuId);
        BaseFragment fragmentByMenuItemId = getFragmentByMenuItemId(currentFragmentMenuId);
        fragmentManager.putFragment(outState, fragmentByMenuItemId.getFragmentTag(), fragmentByMenuItemId);
    }

    public void restoreState(@Nullable Bundle savedInstanceState, FragmentManager fragmentManager) {
        if (savedInstanceState != null) {
            currentFragmentMenuId = savedInstanceState.getInt(CURRENT_FRAGMENT);
            createFragments(savedInstanceState, fragmentManager);
        } else {
            createFragments();
        }
    }

    public void setCurrentFragmentMenuId(@IdRes int currentFragmentMenuId) {
        this.currentFragmentMenuId = currentFragmentMenuId;
    }

    @IdRes
    public int getLastFragmentOrDefault() {
        if (currentFragmentMenuId == -1) {
            return R.id.drawer_agenda;
        }
        return currentFragmentMenuId;
    }

    @NonNull
    public BaseFragment getFragmentByMenuItemId(@IdRes int menuItemId) {
        BaseFragment fragment;

        switch (menuItemId) {
            case R.id.drawer_agenda:
                fragment = getFragment(AgendaMainFragment.TAG);
                break;
            case R.id.drawer_favourites:
                fragment = getFragment(ScheduleMainFragment.TAG);
                break;
            case R.id.drawer_map_and_info:
                fragment = getFragment(MapAndInfoFragment.TAG);
                break;
            case R.id.drawer_about:
                fragment = getFragment(AboutFragment.TAG);
                break;
            default:
                throw new UnsupportedOperationException("Unsupported fragment menuId=" + menuItemId);
        }
        return fragment;
    }

    private void createFragments() {
        putFragment(AgendaMainFragment.TAG, AgendaMainFragment.newInstance());
        putFragment(ScheduleMainFragment.TAG, ScheduleMainFragment.newInstance());
        putFragment(MapAndInfoFragment.TAG, MapAndInfoFragment.newInstance());
        putFragment(AboutFragment.TAG, AboutFragment.newInstance());
    }

    private void createFragments(@NonNull Bundle savedState, @NonNull FragmentManager fragmentManager) {
        BaseFragment savedAgendaMainFragment = getSavedFragment(fragmentManager, savedState, AgendaMainFragment.TAG);
        BaseFragment agendaMainFragment = savedAgendaMainFragment == null ? AgendaMainFragment.newInstance() : savedAgendaMainFragment;

        BaseFragment savedFavouritesFragment = getSavedFragment(fragmentManager, savedState, ScheduleMainFragment.TAG);
        BaseFragment favouritesFragment = savedFavouritesFragment == null ? ScheduleMainFragment.newInstance() : savedFavouritesFragment;

        BaseFragment savedMapAndInfoFragment = getSavedFragment(fragmentManager, savedState, MapAndInfoFragment.TAG);
        BaseFragment mapAndInfoFragment = savedMapAndInfoFragment == null ? MapAndInfoFragment.newInstance() : savedMapAndInfoFragment;

        BaseFragment savedAboutFragment = getSavedFragment(fragmentManager, savedState, AboutFragment.TAG);
        BaseFragment aboutFragment = savedAboutFragment == null ? AboutFragment.newInstance() : savedAboutFragment;

        putFragment(AgendaMainFragment.TAG, agendaMainFragment);
        putFragment(ScheduleMainFragment.TAG, favouritesFragment);
        putFragment(MapAndInfoFragment.TAG, mapAndInfoFragment);
        putFragment(AboutFragment.TAG, aboutFragment);
    }

    private BaseFragment getSavedFragment(@NonNull FragmentManager fragmentManager, @NonNull Bundle savedState, @NonNull String tag) {
        return (BaseFragment) fragmentManager.getFragment(savedState, tag);
    }

    private void putFragment(@NonNull String tag, @NonNull BaseFragment baseFragment) {
        baseFragmentMap.put(tag, baseFragment);
    }

    private BaseFragment getFragment(@NonNull String tag) {
        return baseFragmentMap.get(tag);
    }


}
