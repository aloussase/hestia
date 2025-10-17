package io.github.aloussase.hestia.panel;

final public class PostgresPanel implements IPanel {
    @Override
    public String title() {
        return "PostgreSQL";
    }

    @Override
    public String render() {
        return "Postgres info";
    }
}
