package com.hardziyevich.gateway.groomer;

import com.hardziyevich.gateway.command.CommandProvider;
import com.hardziyevich.gateway.command.CommandRequestProvider;
import com.hardziyevich.gateway.groomer.CommandRequestGroomer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommandRequestProviderImpl implements CommandRequestProvider {

    private final List<CommandRequestGroomer> commandRequestGroomers;

    private final Map<CommandProvider, CommandRequestGroomer> warehouse = new HashMap<>();

    public CommandRequestProviderImpl(List<CommandRequestGroomer> commandRequestGroomers) {
        this.commandRequestGroomers = commandRequestGroomers;
    }

    @PostConstruct
    public void init() {
        commandRequestGroomers.forEach(c -> warehouse.put(c.showTypeRequest(),c));
    }

    @Override
    public CommandRequestGroomer findCommand(CommandProvider commandProvider) {
        return warehouse.get(commandProvider);
    }
}
