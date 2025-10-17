package io.github.aloussase.hestia.config;

import io.github.aloussase.hestia.panel.AbstractPanelFactory;
import io.github.aloussase.hestia.panel.DockerPanel;
import io.github.aloussase.hestia.panel.IPanel;
import io.github.aloussase.hestia.panel.PostgresPanel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
public class PanelConfig {

    private final List<IPanel> panels = new ArrayList<>();

    public PanelConfig(
            @Value("${dashboard.panels.docker.enabled}")
            boolean dockerPanelEnabled,
            @Value("${dashboard.panels.postgres.enabled}")
            boolean postgresPanelEnabled
    ) {
        if (dockerPanelEnabled)
            panels.add(AbstractPanelFactory.instantiate(DockerPanel.class));
        if (postgresPanelEnabled)
            panels.add(AbstractPanelFactory.instantiate(PostgresPanel.class));
    }

}
