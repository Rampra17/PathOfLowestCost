package com.example.android.pathoflowestcost;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.android.pathoflowestcost.Fragments.PathCostCustomFragment;
import com.example.android.pathoflowestcost.Fragments.PathCostExampleFragment;
import com.example.android.pathoflowestcost.Utils.PathOptionsPageAdapter;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by rampr on 3/20/2017.
 */
public class PathOptionsPageAdapterTest {

    private PathOptionsPageAdapter pagerAdapter;

    @Before
    public void setUp() {
        FragmentActivity fragmentActivity = Robolectric.setupActivity(FragmentActivity.class);
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        pagerAdapter = new PathOptionsPageAdapter(fragmentManager);
    }

    @Test
    public void adapterHasTwoItems() {
        assertThat(pagerAdapter.getCount(), equalTo(2));
    }

    @Test
    public void firstItemOfTheAdapterIsTheExamplesFragment() {
        assertThat(pagerAdapter.getItem(0), instanceOf(PathCostExampleFragment.class));
    }

    @Test
    public void secondItemOfTheAdapterIsTheCustomFragment() {
        assertThat(pagerAdapter.getItem(1), instanceOf(PathCostCustomFragment.class));
    }

    @Test
    public void otherItemsOfTheAdapterAreNull() {
        assertThat(pagerAdapter.getItem(2), nullValue());
    }

    @Test
    public void pageTitleOfTheFirstItemIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(0).toString(), equalTo("Example Grids"));
    }

    @Test
    public void pageTitleOfTheSecondItemIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(1).toString(), equalTo("Custom Grid"));
    }

    @Test
    public void pageTitleOfOtherItemsIsExampleGrids() {
        assertThat(pagerAdapter.getPageTitle(2), nullValue());
    }



}
