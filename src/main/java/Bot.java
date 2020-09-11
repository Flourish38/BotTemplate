import listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bot {

    public static void main(String[] args) throws IOException, LoginException {
        File file = new File("token.txt");
        if(!file.exists()){
            System.out.println("A new file \"token.txt\" has been created. Put the OAuth token of the bot in that file.");
            System.out.println(file.createNewFile());
            return;
        }
        Scanner scanner = new Scanner(file);
        if(!scanner.hasNext()){
            System.out.println("Put the OAuth token of the bot into \"token.txt\"");
            return;
        }
        JDA jda = JDABuilder.createDefault(new Scanner(file).nextLine())
                .addEventListeners(
                        new StartupPresence(),
                        new ShutdownHandler(),
                        new ShutdownCommand(),
                        new HelpCommand(),
                        new PingCommand()
                ).build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> ShutdownHandler.handle(jda)));
    }
}
