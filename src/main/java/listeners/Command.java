package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * This is your bread and butter right here. See PingCommand.java as an example of this being used.
 */
public abstract class Command extends CommandListener {
    public Command(String command, String... aliases) {
        super(command, aliases);
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event, String command) {
        command(event);
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event);
}
