
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
     * muestra la informacion del objeto
     * @retrun la descripcion del objeto en un String
     */
    public String getItemDescripcion()
    {
       return itemDescripcion;
    }
    /**
     * muestra el peso del objeto
     * @return el peso con un entero
     */
    
    public int getitemPeso(){
        return itemPeso;
    }
    
    /**
     * devuelve la informacion del objeto y su peso
     * @return la informacion del objeto como cadena
     */
    
    public String DescripcionEntera(){
        String resultado = " --> " + itemDescripcion +  " " + itemPeso +  " " + "kg";
        return resultado;
    }
}
