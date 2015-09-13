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
import pl.droidcon.app.ui.fragment.FavouritesFragment;
import pl.droidcon.app.ui.fragment.MapAndInfoFragment;
import pl.droidcon.app.ui.fragment.SettingsFragment;
import pl.droidcon.app.ui.fragment.agenda.AgendaMainFragment;

public class DrawerFragmentFactory {

    private static final String CURRENT_FRAGMENT = "current_fragment";

    private static final int FRAGMENTS_SIZE = 5;

    private Map<String, BaseFragment> baseFragmentMap = new HashMap<>(FRAGMENTS_SIZE);

    @IdRes
    private int currentFragmentMenuId = -1;

    public void saveInstanceState(Bundle outState, FragmentManager fragmentManager) {
        outState.putInt(CURRENT_FRAGMENT, currentFragmentMenuId);
        BaseFragment fragmentByMenuItemId = getFragmentByMenuItemId(currentFragmentMenuId);
        if (fragmentByMenuItemId != null) {
            fragmentManager.putFragment(outState, fragmentByMenuItemId.getFragmentTag(), fragmentByMenuItemId);
        }
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

    @Nullable
    public BaseFragment getFragmentByMenuItemId(@IdRes int menuItemId) {
        BaseFragment fragment = null;

        switch (menuItemId) {
            case R.id.drawer_agenda:
                fragment = getFragment(AgendaMainFragment.TAG);
                break;
            case R.id.drawer_favourites:
                fragment = getFragment(FavouritesFragment.TAG);
                break;
            case R.id.drawer_map_and_info:
                fragment = getFragment(MapAndInfoFragment.TAG);
                break;
            case R.id.drawer_about:
                fragment = getFragment(AboutFragment.TAG);
                break;
            case R.id.drawer_settings:
                fragment = getFragment(SettingsFragment.TAG);
                break;
        }
        return fragment;
    }

    private void createFragments() {
        putFragment(AgendaMainFragment.TAG, AgendaMainFragment.newInstance());
        putFragment(FavouritesFragment.TAG, FavouritesFragment.newInstance());
        putFragment(MapAndInfoFragment.TAG, MapAndInfoFragment.newInstance());
        putFragment(AboutFragment.TAG, AboutFragment.newInstance());
        putFragment(SettingsFragment.TAG, SettingsFragment.newInstance());
    }

    private void createFragments(@NonNull Bundle savedState, @NonNull FragmentManager fragmentManager) {
        BaseFragment savedAgendaMainFragment = getSavedFragment(fragmentManager, savedState, AgendaMainFragment.TAG);
        BaseFragment agendaMainFragment = savedAgendaMainFragment == null ? AgendaMainFragment.newInstance() : savedAgendaMainFragment;

        BaseFragment savedFavouritesFragment = getSavedFragment(fragmentManager, savedState, FavouritesFragment.TAG);
        BaseFragment favouritesFragment = savedFavouritesFragment == null ? FavouritesFragment.newInstance() : savedFavouritesFragment;

        BaseFragment savedMapAndInfoFragment = getSavedFragment(fragmentManager, savedState, MapAndInfoFragment.TAG);
        BaseFragment mapAndInfoFragment = savedMapAndInfoFragment == null ? MapAndInfoFragment.newInstance() : savedMapAndInfoFragment;

        BaseFragment savedAboutFragment = getSavedFragment(fragmentManager, savedState, AboutFragment.TAG);
        BaseFragment aboutFragment = savedAboutFragment == null ? AboutFragment.newInstance() : savedAboutFragment;

        BaseFragment savedSettingsFragment = getSavedFragment(fragmentManager, savedState, SettingsFragment.TAG);
        BaseFragment settingsFragment = savedSettingsFragment == null ? SettingsFragment.newInstance() : savedSettingsFragment;

        putFragment(AgendaMainFragment.TAG, agendaMainFragment);
        putFragment(FavouritesFragment.TAG, favouritesFragment);
        putFragment(MapAndInfoFragment.TAG, mapAndInfoFragment);
        putFragment(AboutFragment.TAG, aboutFragment);
        putFragment(SettingsFragment.TAG, settingsFragment);
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
