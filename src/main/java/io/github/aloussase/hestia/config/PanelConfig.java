package io.github.aloussase.hestia.config;

import io.github.aloussase.hestia.panel.*;
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
    public static class StringsPanelsConfig {
        private List<String> cmds;
        private List<String> images;
    }

    public PanelConfig(
            @Value("${dashboard.panels.docker.enabled}")
            boolean dockerPanelEnabled,
            @Value("${dashboard.panels.postgres.enabled}")
            boolean postgresPanelEnabled,
            StringsPanelsConfig stringsPanelsConfig,
            ApplicationContext applicationContext
    ) throws Exception {
        for (final var cmd : stringsPanelsConfig.getCmds())
            panels.add(CmdPanel.class.getDeclaredConstructor(String.class).newInstance(cmd));
        for (final var cmd : stringsPanelsConfig.getImages())
            panels.add(RemoteImagePanel.class.getDeclaredConstructor(String.class).newInstance(cmd));
        if (dockerPanelEnabled)
            panels.add(applicationContext.getBean(DockerPanel.class));
        if (postgresPanelEnabled)
            panels.add(applicationContext.getBean(PostgresPanel.class));
    }

}
