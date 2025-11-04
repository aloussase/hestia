package io.github.aloussase.hestia.config;

import io.github.aloussase.hestia.panel.CmdPanel;
import io.github.aloussase.hestia.panel.DockerPanel;
import io.github.aloussase.hestia.panel.IPanel;
import io.github.aloussase.hestia.panel.PostgresPanel;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
public class PanelConfig {

    private final List<IPanel> panels = new ArrayList<>();

    @ConfigurationProperties(prefix = "dashboard.panels")
    @Component
    @Data
    public static class CmdPanelsConfig {
        private List<String> cmds;
    }

    public PanelConfig(
            @Value("${dashboard.panels.docker.enabled}")
            boolean dockerPanelEnabled,
            @Value("${dashboard.panels.postgres.enabled}")
            boolean postgresPanelEnabled,
            CmdPanelsConfig cmdPanelsConfig,
            ApplicationContext applicationContext
    ) throws Exception {
        for (final var cmd : cmdPanelsConfig.getCmds()) {
            panels.add(CmdPanel.class.getDeclaredConstructor(String.class).newInstance(cmd));
        }
        if (dockerPanelEnabled)
            panels.add(applicationContext.getBean(DockerPanel.class));
        if (postgresPanelEnabled)
            panels.add(applicationContext.getBean(PostgresPanel.class));
    }

}
