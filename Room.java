import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap <String,Room> salidas;
    private ArrayList <Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
        items = new ArrayList();
    }

    public void setExit(String description,Room sala){
        salidas.put(description,sala);

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction){

        return salidas.get(direction);

    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */

    public String getExitString(){
        Set <String> direcciones = salidas.keySet();
        String salida = "Salidas: ";

        for (String direction : direcciones){
            salida += direction + " ";

        }
        return salida;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String itemsLongDescription = (items.isEmpty())?"":"Hay " + items.size() + " objetos en la sala:\n";
        
        
        for(Item item : items){
            itemsLongDescription += item.DescripcionEntera() + "\n";
       }
        
        return "Tu estas " + getDescription() + ".\n" + getExitString() + ".\n" + itemsLongDescription;
    }

    /**
     * a�ade el objeto al listado de objetos que tiene la sala
     */

    public void addItem(String descripcion, int peso ){
        items.add(new Item(descripcion,peso));

    }

}

