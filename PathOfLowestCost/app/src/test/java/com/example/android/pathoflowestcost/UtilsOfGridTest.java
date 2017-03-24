package com.example.android.pathoflowestcost;

import com.example.android.pathoflowestcost.Utils.UtilsOfGrid;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by rampr on 3/19/2017.
 */
public class UtilsOfGridTest {
    @Test
    public void createsEmptyGridArrayWithNullInput() {
        assertThat(UtilsOfGrid.gridArrayFromString(null), equalTo(new int[0][0]));
    }

    @Test
    public void createsEmptyGridArrayWithAnyNonNumericInput() {
        assertThat(UtilsOfGrid.gridArrayFromString("1 2 3 a 5"), equalTo(new int[0][0]));
    }

    @Test
    public void createsOneLineGridArrayWithOneLineOfStringData() {
        assertThat(UtilsOfGrid.gridArrayFromString("1  2   3  4 5"), equalTo(new int[][]{ { 1, 2, 3, 4, 5 } }));
    }

    @Test
    public void createsMultiLineGridArrayWithMultipleLinesOfStringData() {
        assertThat(UtilsOfGrid.gridArrayFromString("1  2   3  4 5\n6 7 8  9\t10"),
                equalTo(new int[][]{ { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 } }));
    }

    @Test
    public void lengthOfFirstLineDeterminesRowLengthAndExtraNumbersInLaterLinesAreDiscarded() {
        assertThat(UtilsOfGrid.gridArrayFromString("1 2 3\n6 7 8 9 10"),
                equalTo(new int[][]{ { 1, 2, 3 }, { 6, 7, 8 } }));
    }

    @Test
    public void lengthOfFirstLineDeterminesRowLengthAndMissingNumbersInLaterLinesAreZero() {
        assertThat(UtilsOfGrid.gridArrayFromString("1 2 3 4 5\n6 7 8   "),
                equalTo(new int[][]{ { 1, 2, 3, 4, 5 }, { 6, 7, 8, 0, 0 } }));
    }


}
