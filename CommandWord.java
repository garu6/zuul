
/**
 * Write a description of class CommandWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum CommandWord
{
    // un objeto para cada comando mas otro para los comandos no reconocidos
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"),BACK("back"),
    TAKE("take"), DROP("drop"), ITEMS("items"),UNKNOWN(""), EQUIP("equip");

    private String command;
    /**
     * Constructor for objects of class CommandWord
     */
    CommandWord(String command)
    {
        this.command = command;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getCommand()
    {
        return command;
    }
}
