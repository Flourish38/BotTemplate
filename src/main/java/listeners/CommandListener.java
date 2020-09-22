package listeners;

import managers.BotConfig;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is not meant to be used to make commands, it is meant to be used to make more command interfaces.
 * This literally only exists so I could make parameter commands with aliases. That is all.
 */
public abstract class CommandListener extends ListenerAdapter {
    protected final List<String> commands;

    // This parameter setup requires at least one name for the command and has nice compile-time enforcing.
    public CommandListener(String command, String... aliases){
        // This sorts the commands so that you always check the longest commands first to avoid "!nickname" being called as "!nick name"
        this.commands = Stream.concat(Stream.of(command), Arrays.stream(aliases))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        // This method just calls command() once if the message is calling the command.
        // "Calling the command" is seen as using either the prefix or mentioning the bot directly followed by an alias of the command.

        String raw = event.getMessage().getContentRaw();
        String mention =  "<@!" + event.getJDA().getSelfUser().getId() + ">"; // event.getJDA().getSelfUser().getAsMention() does not work because it does not include the ! in <@! at time of writing
        for(String command : commands)
        {
            if((raw.startsWith(mention + " " + command))
                    || raw.startsWith(BotConfig.get("prefix") + command))
            {
                command(event, command);
                break;
            }
        }
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event, String command);
}
