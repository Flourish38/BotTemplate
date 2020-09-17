import listeners.*;
import managers.BotConfig;
import managers.ShutdownHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws LoginException {
        BotConfig.setKeys(new String[]{
                "token",
                "prefix: !"
        });
        if(!BotConfig.init())
            return;

        JDA jda = JDABuilder.createDefault(BotConfig.get("token"))
                .addEventListeners( // TODO: Put all of your commands here
                        new StartupPresence(),
                        new ShutdownHandler(),
                        new HelpCommand(),
                        new PingCommand()
                ).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> ShutdownHandler.handle(jda)));

    }
}
