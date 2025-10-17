package io.github.aloussase.hestia.panel;

public sealed interface IPanel
        permits IGraphicPanel, DockerPanel, PostgresPanel {

    /**
     * Get the panel's title.
     *
     * @return The panel's title.
     */
    String title();


    /**
     * Render this panel's content into a string.
     *
     * @return A string representation of this panel's content.
     */
    String render();

}
