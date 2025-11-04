package io.github.aloussase.hestia.panel;

public final class RemoteImagePanel extends AbstractPanel implements IPanel {
    private final String url;

    public RemoteImagePanel(String url) {
        this.url = url;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String render() {
        return image(url);
    }
}
