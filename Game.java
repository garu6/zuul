import java.util.Stack;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    private ArrayList<Item> mochila;
    private static final int maximo = 65;
    private int pesoActual;
    private Player jugador;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
 
        mochila = new ArrayList<>();
        jugador = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room recibidor, cocina, ba�o, comedor, Habitacion, Despensa, Sotano;

        // create the rooms
        recibidor = new Room("en el recibidor de la casa");
        cocina = new Room("en la cocina de la casa");
        ba�o = new Room("en el ba�o de la casa");
        comedor = new Room("en el  comedor de la casa");
        Habitacion = new Room("en la habitacion de la casa");
        Despensa = new Room ("en la despensa de la casa");
        Sotano = new Room ("en el sotano de la casa");

        // initialise room exits
        recibidor.setExit("east",cocina);
        recibidor.setExit("south",comedor);
        recibidor.setExit("west",ba�o);

        cocina.setExit("west",recibidor);
        ba�o.setExit("atajo",Habitacion);
        ba�o.setExit("east",recibidor);

        comedor.setExit("north",recibidor);
        comedor.setExit("east",Habitacion);
        comedor.setExit("southeast",Despensa);
        comedor.setExit("northeast",Sotano);

        Habitacion.setExit("west",comedor);

        Sotano.setExit("south",cocina);
        Sotano.setExit("southeast",comedor);

        //a�adimos los objetos a las salas
        Habitacion.addItem("hacha",15,true);
        Habitacion.addItem("cama",50,false);

        Sotano.addItem("vela",5,true);     
        Sotano.addItem("botella",1,true);

        comedor.addItem("casco",20,true);
        comedor.addItem("mesa",50,true);
        comedor.addItem("silla",10,false);

        jugador.setCurrentRoom(recibidor); // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar.  Hasta la proxima.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al mundo de zuul");
        System.out.println("El mundo de zuul es un nuevo, juego de aventuras increiblemente aburrido");
        System.out.println("Escribe 'help' si necesitas ayuda");
        System.out.println();
        jugador.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        String segundWord = command.getSecondWord();

        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            jugador.look();

        }
        else if (commandWord.equals("eat")){
            jugador.eat();

        }else if (commandWord.equals("back")){
            jugador.back();
        }else if (commandWord.equals("take")){
            jugador.take(segundWord);
        }else if (commandWord.equals("drop")){
            jugador.drop(segundWord);
        }else if (commandWord.equals("items")){
            jugador.items();
                    }
        return wantToQuit;
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estas perdido,estas solo y asustado");
        System.out.println("En una casa en llamas");
        System.out.println();
        System.out.println("Tus comandos de palabras son:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        jugador.goRoom(direction);
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    
    
}
