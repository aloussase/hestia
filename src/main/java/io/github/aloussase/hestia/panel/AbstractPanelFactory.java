package io.github.aloussase.hestia.panel;

import org.springframework.stereotype.Service;

@Service
public abstract class AbstractPanelFactory {

    public static IPanel instantiate(Class<? extends IPanel> panelClass) {
        try {
            return panelClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
