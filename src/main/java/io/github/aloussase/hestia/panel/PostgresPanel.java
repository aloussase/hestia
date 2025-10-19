package io.github.aloussase.hestia.panel;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "dashboard.panels.postgres.enabled", havingValue = "true")
@Component
final public class PostgresPanel extends AbstractPanel implements IPanel {

    private final JdbcTemplate jdbcTemplate;

    public PostgresPanel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String title() {
        return "PostgreSQL";
    }

    @Override
    public String render() {
        return joinVertically(
                table(
                        tableEntry("Version", version()),
                        tableEntry("Active connections", activeConnections())
                ),
                tableInfo()
        );
    }

    private String tableInfo() {
        final var sql = """
                SELECT
                    schemaname || '.' || relname AS table_name,
                    pg_size_pretty(pg_total_relation_size(relid)) AS total_size
                FROM pg_catalog.pg_statio_user_tables
                ORDER BY pg_total_relation_size(relid) DESC;
                """;
        final var entries = jdbcTemplate.query(sql, (rs, rowNum) -> {
            final var tableName = rs.getString("table_name");
            final var size = rs.getString("total_size");
            return new TableEntry2D(tableName, size);
        });
        return section(
                "Tables",
                table(
                        entries
                )
        );
    }

    private String version() {
        return getSingleColumnResultFor(
                "select version();",
                "???"
        ).split(" ")[1];
    }

    private String activeConnections() {
        return getSingleColumnResultFor(
                "SELECT count(*) FROM pg_stat_activity WHERE state = 'active'",
                "No active connections"
        );
    }

    private String getSingleColumnResultFor(
            String sql,
            String errorMsg) {
        final var result = jdbcTemplate.queryForObject(sql, String.class);
        if (result == null) {
            return errorMsg;
        }
        return result;
    }
}
