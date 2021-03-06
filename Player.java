
import java.util.Stack;
import java.util.ArrayList;

public class Player
{   
    //  Variables de control de posicion
    private Room currentRoom;   //  La sala en la que se encuentra el jugador
    private Stack<Room> enteredRooms;   //  Stack de las salas visitadas por el jugador durante la partida
    private ArrayList<Item> mochila;
    private int maximo;
    private int pesoActual;
    //poder especialque hace que el jugador pueda equipar cualquier objeto que se encuentre en la sala
    // se activa una vez cojes un objeto especial en una habitacion
    private boolean poder;
    /**
     * Constructor de la clase Player
     */    
    public Player(int maximo){
        enteredRooms = new Stack<>();
        mochila = new ArrayList<>();
        this.maximo = maximo;
        poder=false;
    }

    /**
     * Getter de la sala actual
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Setter de la sala actual
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Metodo que simula el movimiento del jugador de una sala a otra del mapa
     * a traves de la salida indicada por parametro. Si no hay salida en esa direccion
     * avisa por pantalla.
     * 
     * @param direction La direccion de la salida indicada
     */
    public void goRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("�No hay salida posible en esa direcci�n!");
        }
        else {
            enteredRooms.push(currentRoom);
            currentRoom = nextRoom;
            look();
        }
    }

    /**
     * Muestra por pantalla la informacion detallada de la sala actual y sus 
     * salidas.
     */
    public void look(){
        System.out.println(currentRoom.getLongDescription());

        System.out.println();

    }

    /**
     * Metodo que devuelve al jugador a la ultima sala en la que estuvo. Puede
     * invocarse repetidamente hasta volver a la posicion inicial de la partida.
     * Avisa por pantalla en caso de estar en la sala inicial.
     */
    public void back() 
    {
        if(enteredRooms.empty()){
            System.out.println("�No puedes volver atr�s, est�s en el comienzo del juego!"); 
        }
        else{
            currentRoom = enteredRooms.pop();
            look();
        }
    }

    /**
     * Metodo que simula la ingesta de comida por parte del jugador
     * por pantalla.
     */
    public void eat(){
        System.out.println("Has comido algo y ya no est�s hambriento.");
    }

    public void take(String itemDescriptionFirstWord) 
    {        
        Item objeto = null;

        for(Item item : currentRoom.getItems()){
            if(item.getItemDescripcion().contains(itemDescriptionFirstWord)){
                objeto = item;
            }
        }

        if(objeto != null){
            if(objeto.getCoger() || poder) {
                if (objeto.getitemPeso() + pesoActual < maximo){
                    mochila.add(objeto);
                    currentRoom.getItems().remove(objeto);
                    pesoActual += objeto.getitemPeso();
                    System.out.println("Has cogido el objeto: " + objeto.getItemDescripcion() + ".");            
                }else{
                    System.out.println("�El objeto no cabe en tu inventario!");
                }

            }else{
                System.out.println("�No puedes coger el objeto!");
            }

        }else{
            System.out.println("Ese objeto no existe en esta habitacion");

        }

    }   

    /**
     * permite dejar en la sala un objeto de nuestra mochila 
     */

    public void drop (String itemDescriptionFirstWord){
        Item objeto = null;

        for(Item item : mochila){
            if(item.getItemDescripcion().contains(itemDescriptionFirstWord)){
                objeto = item;
            }
        }

        if(objeto != null){

            currentRoom.getItems().add(objeto);
            pesoActual -= objeto.getitemPeso();
            mochila.remove(objeto);
            System.out.println("Has dejado el objeto: " + objeto.getItemDescripcion() + ".");
        }
        else{
            System.out.println("El objeto que quieres dejar no se encuentra en tu inventario");
        }

    }

    public void items() 
    {
        if(mochila.isEmpty()){
            System.out.println("Tu inventario est� vac�o.");
        }
        else{
            System.out.println("Inventario: ");

            for(Item item : mochila){
                System.out.println(item. DescripcionEntera());
            }            

            System.out.println("Peso total: " + pesoActual + " kg");
        }
        
        
    }
    
    /**
     * metodo que al equiparte la chaqueta de bombero permite poder coger todos los objetos que te 
     * encuentres en las salas
     */
    
    
    public void equip(String itemDescriptionFirstWord){
        

        if(itemDescriptionFirstWord != null){
            if (itemDescriptionFirstWord.equals("chaqueta")){
                if (mochila.isEmpty()){
                System.out.println("no tienes nada en tu inventario");
            }else if (poder){
                System.out.println("ya lo tienes equipado loco");
            }
            else{
            boolean searching = true;
            
            for (int i =0; i < mochila.size() && searching; i++){
                if (mochila.get(i).getItemDescripcion().equals("chaqueta")){
                    mochila.remove(mochila.get(i));
                    poder = true;
                    System.out.println("Has cogido el objeto especial, ahora puedes coger cualquier objeto que necuentres en las salas");            
                }else{
                    System.out.println("�El objeto no cabe en tu inventario!");
                }

            }
        }   
        }
    
        }else{
            System.out.println("Ese objeto no existe en esta habitacion");

        }

        
        
        
    }
} 