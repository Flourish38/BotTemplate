package listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ShutdownHandler extends ListenerAdapter {
    private static volatile boolean shutdown;

    /**
     * Go nuts. Also feel free to include properties in this class if you're having trouble
     * getting all the data you need in this method, I'm not your parents.
     *
     * @param jda the JDA instance
     */
    public static void handle(JDA jda){
        System.out.println("Shutting down...");
        jda.shutdown();
        // YOUR CODE HERE


        // YOUR CODE SHOULD PROBABLY END HERE
        int count = 0;
        try{
            while(!shutdown && count < 100){
                count++;
                Thread.sleep(10);
            }
        }
        catch (InterruptedException e){
            System.out.println("Shutdown interrupted after " + count + " tries.");
            return;
        }
        if(!shutdown){
            System.out.println("Shutdown process terminated after 1000 ms elapsed.");
            return;
        }
        System.out.println("Shutdown properly after " + count + " tries.");
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        shutdown = true;
    }
}
