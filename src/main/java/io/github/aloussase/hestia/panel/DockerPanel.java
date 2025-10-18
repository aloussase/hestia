package io.github.aloussase.hestia.panel;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.okhttp.OkDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.slf4j.Slf4j;

import java.net.ConnectException;

@Slf4j
final public class DockerPanel extends AbstractPanel implements IPanel {

    private final DockerHttpClient httpClient;
    private final DefaultDockerClientConfig dockerConfig;

    public DockerPanel() {
        dockerConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        httpClient = new OkDockerHttpClient.Builder()
                .dockerHost(dockerConfig.getDockerHost())
                .connectTimeout(10000)
                .readTimeout(30000)
                .build();
    }

    @Override
    public String title() {
        return "Docker";
    }

    @Override
    public String render() {
        try {
            final var dockerClient = DockerClientImpl.getInstance(dockerConfig, httpClient);
            final var info = dockerClient.infoCmd().exec();

            final var containers = dockerClient.listContainersCmd().exec()
                    .stream()
                    .map(container -> tableEntry(container
                            .getId()
                            .substring(0, 10) + "...", container.getImage()))
                    .toList();

            return joinVertically(
                    table(
                            tableEntry("Total containers", info.getContainers()),
                            tableEntry("Running containers", info.getContainersRunning()),
                            tableEntry("Total images", info.getImages())
                    ),
                    section(
                            "System info",
                            table(
                                    tableEntry("CPUs", info.getNCPU()),
                                    tableEntry(
                                            "Memory",
                                            String.format("%.2f", info.getMemTotal() / 1024.0 / 1024 / 1024)
                                    )
                            )
                    ),
                    section(
                            "Running containers",
                            table(
                                    containers
                            )
                    )
            );
        } catch (Exception e) {
            if (e.getCause() instanceof ConnectException) {
                return "Failed to connect to Docker, is the daemon running?";
            }
            log.error("There was an error while evaluating Docker panel", e);
            return "An error occurred while evaluating the panel. See the server logs for details.";
        }
    }
}
