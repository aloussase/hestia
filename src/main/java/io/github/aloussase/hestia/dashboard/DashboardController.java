package io.github.aloussase.hestia.dashboard;

import io.github.aloussase.hestia.config.Config;
import io.github.aloussase.hestia.config.PanelConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {

    private final Config config;
    private final PanelConfig panelConfig;

    public DashboardController(Config config, PanelConfig panelConfig) {
        this.config = config;
        this.panelConfig = panelConfig;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("config", config);
        model.addAttribute("panels", panelConfig.getPanels());
        return "index";
    }

    @PostMapping("filter")
    public String filter(Model model, @RequestParam String search) {
        final var panels = panelConfig.getPanels().stream()
                .filter(panel -> panel.title().toLowerCase().contains(search.toLowerCase()))
                .toList();
        model.addAttribute("config", config);
        model.addAttribute("panels", panels);
        return "index";
    }

}
