package com.example.android.pathoflowestcost.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class PathCostExampleFragment extends android.support.v4.app.Fragment {
    private Grid loadedGrid;

    public PathCostExampleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_example_path, container, false);

        Button gridOneButton = (Button) fragmentView.findViewById(R.id.grid_1_button);
        gridOneButton.setOnClickListener(new GridOnClickListener());
        Button gridTwoButton = (Button) fragmentView.findViewById(R.id.grid_2_button);
        gridTwoButton.setOnClickListener(new GridOnClickListener());
        Button gridThreeButton = (Button) fragmentView.findViewById(R.id.grid_3_button);
        gridThreeButton.setOnClickListener(new GridOnClickListener());
        Button goButton = (Button) fragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new GoOnClickListener());

        return fragmentView;
    }

    private Grid getGridForButton(View view) {
        switch (view.getId()) {
            case R.id.grid_1_button:
                return UtilsOfGrid.EXAMPLE_GRID_1;
            case R.id.grid_2_button:
                return UtilsOfGrid.EXAMPLE_GRID_2;
            case R.id.grid_3_button:
                return UtilsOfGrid.EXAMPLE_GRID_3;
            default:
                return null;
        }
    }

    private void clearResults() {
        ((TextView) getView().findViewById(R.id.results_success)).setText("");
        ((TextView) getView().findViewById(R.id.results_total_cost)).setText(getResources().getText(R.string.no_results));
        ((TextView) getView().findViewById(R.id.results_path_taken)).setText("");
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

    class GridOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Grid selectedGrid = getGridForButton(view);
            if (!selectedGrid.equals(loadedGrid)) {
                clearResults();
            }

            loadedGrid = selectedGrid;
            ((TextView) getView().findViewById(R.id.grid_contents)).setText(loadedGrid.asDelimitedString("\t"));
            getView().findViewById(R.id.go_button).setEnabled(true);
        }
    }

    class GoOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            GridVisitor visitor = new GridVisitor(loadedGrid);
            PathState bestPath = visitor.getBestPathForGrid();

            if (bestPath.isSuccessful()) {
                ((TextView) getView().findViewById(R.id.results_success)).setText("Yes");
            } else {
                ((TextView) getView().findViewById(R.id.results_success)).setText("No");
            }
            ((TextView) getView().findViewById(R.id.results_total_cost)).setText(Integer.toString(bestPath.getTotalCost()));
            ((TextView) getView().findViewById(R.id.results_path_taken)).setText(formatPath(bestPath));
        }

    }
}
