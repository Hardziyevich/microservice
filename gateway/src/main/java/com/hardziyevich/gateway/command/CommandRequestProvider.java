package com.hardziyevich.gateway.command;

public interface CommandRequestProvider {

    CommandRequest findCommand(CommandProvider commandProvider);

}
