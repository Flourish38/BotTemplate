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
        // This will create a file, "config.txt", that has the token field empty and the prefix field prefilled,
        // provided one does not already exist. If one does exist, it asserts that config.txt has all the fields
        // specified by setKeys(), adding more if it does not find any.
        BotConfig.setKeys(
                "token",
                "prefix: !" // Change this from "!" if you want obviously
        );
        if(!BotConfig.init())
            return;
        // By this point, every field in config.txt is guaranteed to have an assigned value.

        JDA jda = JDABuilder.createDefault(BotConfig.get("token"))
                .addEventListeners(new ShutdownHandler()) // Avoid removing this one. See managers/ShutdownHandler.java for more information.
                .addEventListeners( // TODO: Put all of your commands here
                        new StartupPresence(), // Feel free to tweak the startup presence, but for most use cases this is probably what you want
                        // TODO: don't forget to update the help command to include any commands you add!
                        new HelpCommand(),
                        new PingCommand()
                ).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> ShutdownHandler.handle(jda)));

    }
}
