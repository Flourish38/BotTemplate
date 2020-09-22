package managers;

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

        // NOTE: Code below this point is very sensitive to changes, and if you aren't careful, your bot will hang
        // indefinitely when you try to shut it down. I have tested this part rigorously, it probably works good enough
        // for your use case without any changes.
        int count = 0;
        try{
            // I set this to time out after 1000ms (100*10ms). From my testing, the slowest it has ever shutdown properly
            // was 120ms. I set it this high because I could see someone having a ping of over 300ms, and it doesn't really matter anyways.
            while(!shutdown && count < 100){
                count++;
                // I picked this because it had to be something, and this seems to be about the right amount of time to wait.
                // plus, nice round number.
                Thread.sleep(10);
            }
        }
        catch (InterruptedException e){
            // I have never seen this happen, but it shouldn't cause much of a problem anyways.
            System.out.println("Shutdown interrupted after " + count + " tries.");
            return;
        }
        if(!shutdown){
            System.out.println("Shutdown process terminated after 1000ms elapsed.");
            return;
        }
        System.out.println("Shutdown properly after " + count + " tries.");
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        shutdown = true;
    }
}
