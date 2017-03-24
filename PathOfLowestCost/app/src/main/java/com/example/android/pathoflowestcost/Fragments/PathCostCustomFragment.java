package com.example.android.pathoflowestcost.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.pathoflowestcost.Utils.Grid;
import com.example.android.pathoflowestcost.Utils.GridVisitor;
import com.example.android.pathoflowestcost.Utils.PathState;
import com.example.android.pathoflowestcost.R;
import com.example.android.pathoflowestcost.Utils.UtilsOfGrid;

import java.util.List;

/**
 * Created by rampr on 3/19/2017.
 */
public class PathCostCustomFragment extends android.support.v4.app.Fragment
{
    public PathCostCustomFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragmet_custom_path, container, false);

        Button goButton = (Button) fragmentView.findViewById(R.id.go_button_go);
        goButton.setOnClickListener(new GoOnClickListener());

         EditText customGridContents = (EditText) fragmentView.findViewById(R.id.custom_grid_contents);
        customGridContents.addTextChangedListener(new GridContentsWatcher());

        return fragmentView;
    }

    private String formatPath(PathState path) {
        StringBuilder builder = new StringBuilder();
        List<Integer> rows = path.getRowsTraversed();

        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }

        return builder.toString();
    }

    private boolean gridContentsAreValid(int[][] contents) {
        if (contents.length < 1 || contents.length > 10 || contents[0].length < 1 || contents[0].length > 100) {
            return false;
        } else {
            return true;
        }
    }

    private void loadGrid(int[][] contents) {
        Grid validGrid = new Grid(contents);
        GridVisitor visitor = new GridVisitor(validGrid);
        PathState bestPath = visitor.getBestPathForGrid();

        if (bestPath.isSuccessful()) {
            ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
        } else {
            ((TextView) getView().findViewById(R.id.results_success)).setText("No");
        }
        ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
        ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        ((TextView) getView().findViewById(R.id.grid_contents)).setText(validGrid.asDelimitedString("\t"));
    }

    class GridContentsWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Button goButton = (Button) getView().findViewById(R.id.go_button_go);
            goButton.setEnabled(!s.toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String gridString = ((EditText) getView().findViewById(R.id.custom_grid_contents)).getText().toString();
            int[][] potentialGridContents = UtilsOfGrid.gridArrayFromString(gridString);
            if (gridContentsAreValid(potentialGridContents)) {
                loadGrid(potentialGridContents);
            } else {
                new AlertDialog.Builder(getContext())
                        .setTitle("Invalid Grid")
                        .setMessage(R.string.invalid_grid_message)
                        .setPositiveButton("OK", null)
                        .show();
            }
        }
    }


}
