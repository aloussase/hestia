package io.github.aloussase.hestia.panel;

import java.util.Arrays;
import java.util.List;

public abstract non-sealed class AbstractPanel implements IPanel {

    public record TableEntry2D(
            Object a,
            Object b
    ) {
    }

    public TableEntry2D tableEntry(Object a, Object b) {
        return new TableEntry2D(a, b);
    }

    protected String table(TableEntry2D... entries) {
        return table(Arrays.asList(entries));
    }

    protected String table(List<TableEntry2D> entries) {
        final var sb = new StringBuilder();
        sb.append("<div class='table'>");
        for (var entry : entries) {
            sb
                    .append("<div class='table-row'>")
                    .append("<span class='table-col'>").append(entry.a()).append("</span>")
                    .append("<span class='table-col'>").append(entry.b()).append("</span>")
                    .append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }

    protected String joinVertically(String... components) {
        return String.join("<br>", components);
    }

    protected String section(
            String sectionTitle,
            String component
    ) {
        return "<div class='section'>" +
                "<h3 class='title'>" + sectionTitle + "</h3>" +
                "<div class='section-component'>" + component + "</div>" +
                "</div>";
    }

    protected String verbatim(String text) {
        return "<pre>" + text + "</pre>";
    }

    protected String image(String url) {
        return "<img src='" + url + "'/>";
    }

}
