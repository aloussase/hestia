package io.github.aloussase.hestia.config;

import io.github.aloussase.hestia.panel.DockerPanel;
import io.github.aloussase.hestia.panel.IPanel;
import io.github.aloussase.hestia.panel.PostgresPanel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
public class PanelConfig {

    private final List<IPanel> panels = new ArrayList<>();

    public PanelConfig(
            @Value("${dashboard.panels.docker.enabled}")
            boolean dockerPanelEnabled,
            @Value("${dashboard.panels.postgres.enabled}")
            boolean postgresPanelEnabled,
            ApplicationContext applicationContext
    ) {
        if (dockerPanelEnabled)
            panels.add(applicationContext.getBean(DockerPanel.class));
        if (postgresPanelEnabled)
            panels.add(applicationContext.getBean(PostgresPanel.class));
    }

}
