package com.hardziyevich.gateway.command;

import com.hardziyevich.gateway.groomer.CommandRequestGroomer;

public interface CommandRequestProvider {

    CommandRequestGroomer findCommand(CommandProvider commandProvider);

}
