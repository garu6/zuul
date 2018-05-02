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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room volver;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        volver = null;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room recibidor, cocina, baño, comedor, Habitacion, Despensa, Sotano;
      
        // create the rooms
        recibidor = new Room("en el recibidor de la casa");
        cocina = new Room("en la cocina de la casa");
        baño = new Room("en el baño de la casa");
        comedor = new Room("en el  comedor de la casa");
        Habitacion = new Room("en la habitacion de la casa");
        Despensa = new Room ("en la despensa de la casa");
        Sotano = new Room ("en el sotano de la casa");
        
        // initialise room exits
        recibidor.setExit("east",cocina);
        recibidor.setExit("south",comedor);
        recibidor.setExit("west",baño);
        
        cocina.setExit("west",recibidor);
        baño.setExit("atajo",Habitacion);
        baño.setExit("east",recibidor);
        
        comedor.setExit("north",recibidor);
        comedor.setExit("east",Habitacion);
        comedor.setExit("southeast",Despensa);
        comedor.setExit("northeast",Sotano);
        
        Habitacion.setExit("west",comedor);
        
        Sotano.setExit("south",cocina);
        Sotano.setExit("southeast",comedor);
        
        
        //añadimos los objetos a las salas
        
        Habitacion.addItem("hacha",15);
        Habitacion.addItem("cama",50);
        
        Sotano.addItem("vela",5);     
        Sotano.addItem("botella",1);
        
        comedor.addItem("casco",20);
        comedor.addItem("mesa",50);
        comedor.addItem("silla",10);
        
        currentRoom = recibidor;  // start game outside
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
        printLocationInfo();
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
            look();
            
        }
        else if (commandWord.equals("eat")){
            eat();
            
        }else if (commandWord.equals("back")){
            back();
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

        // Try to leave current room.
        
        
        Room nextRoom = currentRoom.getExit(direction);
        
        
        if (nextRoom == null) {
            System.out.println("Aqui no hay puerta estas atrapado");
        }
        else {
            volver = currentRoom;
            currentRoom = nextRoom;
            printLocationInfo();
        }
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
    
    
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        
        System.out.println();
        
        
    }
    
    
    private void look(){
        System.out.println(currentRoom.getLongDescription());
        
    }
    
    
    private void eat(){
        System.out.println("acabas de comer y ya no tienes hambre");
        
    }
    
    
     /**
     * Metodo que devuelve al jugador a la ultima sala en la que estuvo.
     * Al iniciar la partida y cuando intenta invocarse dos veces seguidas
     * avisa de que no es posible llevar a cabo la accion.
     */
    private void back() 
    {
        if(volver == null){
            System.out.println("¡No puedes volver atrás!"); 
        }
        else{
            currentRoom = volver;
            volver = null;
            printLocationInfo();
        }
    }
}
