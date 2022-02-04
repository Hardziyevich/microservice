package com.hardziyevich.gateway.command;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandRequestProviderImpl implements CommandRequestProvider {

    private final List<CommandRequest> commandRequests;

    private final Map<CommandProvider, CommandRequest> warehouse = new HashMap<>();

    public CommandRequestProviderImpl(List<CommandRequest> commandRequests) {
        this.commandRequests = commandRequests;
    }

    @PostConstruct
    public void init() {
        commandRequests.forEach(c -> warehouse.put(c.showTypeRequest(),c));
    }

    @Override
    public CommandRequest findCommand(CommandProvider commandProvider) {
        return warehouse.get(commandProvider);
    }
}
