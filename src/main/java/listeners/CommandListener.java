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

    public CommandListener(String command, String... commands){
        this.commands = Stream.concat(Stream.of(command), Arrays.stream(commands))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String raw = event.getMessage().getContentRaw();
        String mention =  "<@!" + event.getJDA().getSelfUser().getId() + ">"; // bro I stg why did discord change how they store pings internally AAAAA JDA hasn't updated to match it so this is life ig
        for(String command : commands)
        {
            if((raw.startsWith(mention + " " + command)) // ping as prefix
                    || raw.startsWith(BotConfig.PREFIX + command)) // msg starts with prefix
            {
                command(event, command);
                break;
            }
        }
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event, String command);
}
