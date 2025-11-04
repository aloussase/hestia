package io.github.aloussase.hestia.panel;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Slf4j
final public class CmdPanel extends AbstractPanel implements IPanel {

    private final String cmdline;

    public CmdPanel(String cmdline) {
        this.cmdline = cmdline;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public String render() {
        final var runtime = Runtime.getRuntime();
        BufferedReader reader = null;
        try {
            final var process = runtime.exec(new String[]{"/bin/sh", "-c", cmdline});
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            final var output = reader.lines().collect(Collectors.joining("\n"));
            log.debug("Executed command {}: {}", cmdline, output);
            return verbatim(output);
        } catch (IOException e) {
            return "Failed to execute the command: " + cmdline;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
