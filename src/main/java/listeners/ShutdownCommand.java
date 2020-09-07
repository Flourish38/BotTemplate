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
        handle(event);
        event.getJDA().shutdown();
    }

    /**
     * This method is for any cleanup that the shutdown command might need to do. By default,
     * it doesn't need to do anything, but if you're doing stuff like creating temporary text channels,
     * this is where you should be deleting them.
     *
     * I don't care how you get the channel IDs here, you're clever, you figure it out.
     *
     * @param event the shutdown command.
     */
    private static void handle(GuildMessageReceivedEvent event) {

    }
}

