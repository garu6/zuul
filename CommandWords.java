import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
   private HashMap<String,CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
         for(CommandWord command : CommandWord.values()){
           validCommands.put(command.getCommand(),command);
       }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        
        return validCommands.keySet().contains(aString);
    }

    /**
     * Imprime por pantalla todos los comandos v�lidos
     */
    public String getCommandList()
    {
        String resultado  = "";
        String cadena = "";
        
        for (String command : validCommands.keySet()) {
            resultado = command + " ";
            cadena = cadena + resultado; 
        }
        return cadena;
    }
    
    
    /**
     * Return the CommandWord associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord corresponding to the String commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord){
        CommandWord returnedCommand = CommandWord.UNKNOWN;
        
        if(isCommand(commandWord)){
            returnedCommand = validCommands.get(commandWord);
        }
        
        return returnedCommand;
    }
}
