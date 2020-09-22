package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParameterCommand extends CommandListener {
    public ParameterCommand(String command, String... aliases) {
        super(command, aliases);
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, String command) {
        // Calls command() with either an empty list if no parameters were specified or a list of parameters, separated by spaces
        String raw = event.getMessage().getContentRaw();
        raw = raw.substring(raw.indexOf(command) + command.length()).trim();
        command(event, (raw.equals("") ? new ArrayList<>() : Arrays.asList(raw.split(" "))));
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event, List<String> params);
}