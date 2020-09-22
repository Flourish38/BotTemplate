package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class PingCommand extends Command {
    public PingCommand(){
        // if you like, you could add command aliases by doing something like this:
        // super("ping", "p", "latency");
        // this would make the command work with any of those aliases
        super("ping");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        long startTime = System.currentTimeMillis();
        // sends a message, then edits the message to tell you how long it took to send the message
        event.getChannel().sendMessage("Pong!").queue((a) -> a.editMessage((System.currentTimeMillis() - startTime) + "ms").queue());
    }
}
