package io.github.aloussase.hestia.panel;

import java.util.List;

/**
 * A panel that holds data points to be graphed.
 */
public non-sealed interface IGraphicPanel extends IPanel {

    record DataPoint(int x, int y) {
    }

    /**
     * Return a list of the data points that should be graphed.
     *
     * @return A list of data points to be graphed.
     */
    List<DataPoint> getDataPoints();

}
