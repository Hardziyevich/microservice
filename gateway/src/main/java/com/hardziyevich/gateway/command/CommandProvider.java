package com.hardziyevich.gateway.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hardziyevich.gateway.command.Field.*;

public enum CommandProvider {

    FIND_ALL(List.of()),
    FIND_DAY(List.of(DAY)),
    FIND_SERVICE(List.of(SERVICE)),
    FIND_GROOMER(List.of(GROOMER)),
    FIND_DAY_AND_SERVICE(List.of(DAY, SERVICE));

    private final List<Field> fields;

    CommandProvider(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getValueFromField(Field field) {
        final String replace = "";
        return this.getFields().stream()
                .filter(x -> x.equals(field))
                .map(x -> {
                    Optional<String> value = Optional.ofNullable(x.getValue());
                    if(value.isPresent()) {
                        x.setValue(null);
                    }
                    return value.orElse(replace);
                })
                .findFirst()
                .orElse(replace);
    }

    public static Optional<CommandProvider> findRequestType(List<Field> fields) {
        return Arrays.stream(CommandProvider.values())
                .filter(r -> r.fields.containsAll(fields))
                .findFirst();
    }

}
