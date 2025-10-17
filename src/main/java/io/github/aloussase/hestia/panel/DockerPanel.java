package io.github.aloussase.hestia.panel;

final public class DockerPanel implements IPanel {
    @Override
    public String title() {
        return "Docker";
    }

    @Override
    public String render() {
        return "Docker info";
    }
}
