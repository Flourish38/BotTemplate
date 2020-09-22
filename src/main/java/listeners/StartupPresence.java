package listeners;

import managers.BotConfig;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class StartupPresence extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        // This is mostly useful as a reminder of what the prefix is. Also a good indication that the bot is working properly.
        event.getJDA().getPresence().setActivity(Activity.playing("\"" + BotConfig.get("prefix") + "help\""));
    }
}
