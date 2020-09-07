import net.dv8tion.jda.api.JDA;

public class ShutdownHandler {
    /**
     * Go nuts. Also feel free to include properties in this class if you're having trouble
     * getting all the data you need in this method, I'm not your parents.
     *
     * @param jda the JDA instance
     */
    public static void handle(JDA jda){
        jda.shutdown();
    }
}
