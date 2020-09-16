package listeners;

import managers.BotConfig;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends Command {
    private final String message;

    public HelpCommand() {
        super("help");

        // TODO: Put a full list of commands here

        String[] commands = new String[]{
                "help",
                "ping"
        };
        message = "List of commands:\n" + BotConfig.PREFIX + String.join("\n" + BotConfig.PREFIX, commands);
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(message).queue();
    }
}
