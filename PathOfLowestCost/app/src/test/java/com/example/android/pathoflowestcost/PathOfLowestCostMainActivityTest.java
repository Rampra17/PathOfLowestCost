package com.example.android.pathoflowestcost;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.android.pathoflowestcost.Activity.PathOfLowestCostMainActivity;
import com.example.android.pathoflowestcost.Utils.PathOptionsPageAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by rampr on 3/20/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class PathOfLowestCostMainActivityTest {

    private PathOfLowestCostMainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(PathOfLowestCostMainActivity.class);
    }

    @Test
    public void viewPagerIsConfiguredWithAPathOptionsPagerAdapter() {
        PagerAdapter pagerAdapter = ((ViewPager) activity.findViewById(R.id.container)).getAdapter();
        assertThat(pagerAdapter, instanceOf(PathOptionsPageAdapter.class));
    }

    @Test
    public void tabLayoutIsConfiguredAndStartsAtTheZerothTab() {
        TabLayout tabLayout = ((TabLayout) activity.findViewById(R.id.tabs));
        assertThat(tabLayout.getSelectedTabPosition(), equalTo(0));
    }
}
