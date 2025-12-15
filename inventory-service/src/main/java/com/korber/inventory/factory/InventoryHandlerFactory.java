package com.korber.inventory.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InventoryHandlerFactory {

    private final Map<String, InventoryHandler> handlers;

    public InventoryHandler getHandler(String type) {
        return handlers.getOrDefault(type, handlers.get("DEFAULT"));
    }

}

