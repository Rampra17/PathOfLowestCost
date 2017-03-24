package com.example.android.pathoflowestcost.Utils;

/**
 * Created by rampr on 3/19/2017.
 */
public class PathStateCollector {

    private PathState bestPath;
    private PathStateComparator comparator;

    public PathStateCollector()
    {
        this.comparator = new PathStateComparator();
    }

    public PathState getBestPath() {
        return this.bestPath;
    }

    public void addPath(PathState newPath) {
        if (this.comparator.compare(newPath, this.bestPath) < 0)
            this.bestPath = newPath;
    }
}
