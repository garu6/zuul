
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescripcion;
    private int itemPeso; 
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcionObjeto, int pesoObjeto)
    {
        itemDescripcion = descripcionObjeto;
        itemPeso = pesoObjeto;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getItemDescripcion()
    {
       return itemDescripcion;
    }
    
    
    public int getitemPeso(){
        return itemPeso;
    }
    
    public String DescripcionEntera(){
        String resultado = " --> " + itemDescripcion +  " " + itemPeso +  " " + "kg";
        return resultado;
    }
}
