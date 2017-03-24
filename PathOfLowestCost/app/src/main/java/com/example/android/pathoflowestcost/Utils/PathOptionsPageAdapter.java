package com.example.android.pathoflowestcost.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.pathoflowestcost.Fragments.PathCostCustomFragment;
import com.example.android.pathoflowestcost.Fragments.PathCostExampleFragment;

/**
 * Created by rampr on 3/19/2017.
 */
public class PathOptionsPageAdapter extends FragmentPagerAdapter {

    public PathOptionsPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PathCostExampleFragment();
            case 1:
                return new PathCostCustomFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Example Grids";
            case 1:
                return "Custom Grid";
            default:
                return null;
        }
    }


}
