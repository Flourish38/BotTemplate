package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class ShutdownCommand extends Command {
    public ShutdownCommand(){
        super("shutdown");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        // if(event.getAuthor().getIdLong() != BotConfig.BOT_ADMIN_ID) return; // Note: You probably want this if you're using a shutdown command. I'm using another bot to manage my bots, so I don't need one.
        event.getChannel().sendMessage("Shutting down...").queue();
        System.exit(0); // This triggers the shutdown hook in ShutdownHandler.java
    }
}

