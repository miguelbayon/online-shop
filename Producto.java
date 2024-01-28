import java.util.ArrayList;
import java.util.Iterator;

/**
 * La clase representa artículos de venta en un sitio de comercio electrónico como Amazon.com.
 * 
 * Los objetos Producto almacenan toda la información relevante a un artículo, incluyendo la descripción,
 * precio, comentarios de los clientes, etc.
 * 
 * NOTA: ¡La versión actual está incompleta! Actualmente, sólo incluye el código que se ocupa de los 
 * comentarios de los clientes.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1 (2016.02.29)
 */
public class Producto
{
    private String nombre;
    private int precio;  // en centimos
    private ArrayList<Comentario> comentarios;
    
    /**
     * Crea un nuevo producto
     */
    public Producto(String nombre, int precio)
    {
        this.nombre = nombre;
        this.precio = precio;
        comentarios = new ArrayList<>();
    }

    /**
     * Devuelve el nombre del producto
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Devuelve el precio del producto.
     */
    public int getPrecio()
    {
        return precio;
    }
    
    /**
     * Devuelve el numero de comentarios sobre el producto
     */
    public int getNumeroDeComentarios()
    {
        return comentarios.size();
    }
    
    /**
     * Añade un comentario a la lista de comentarios de este producto. 
     * Devuelve true si se ha realizado correctamente;
     * false si el comentario fue rechazado.
     * 
     * El comentario será rechazado si el mismo autor ya ha dejado un comentario, o
     * si la valoración no es válida. Las valoraciones válidas son números entre 1 y 5 (ambos inclusive).
     */
    public boolean agregarComentario(String autor, String texto, int valoracion)
    {
        boolean agregado = true;
        
        if(esValoracionInvalida(valoracion)) {
            agregado = false;
        }        
        else if(encontrarComentarioPorAutor(autor) != null) { 
            agregado = false;
        }
        else {
            comentarios.add(new Comentario(autor, texto, valoracion));    
        }
        
        return agregado;
    }
    
    /**
     * Elimina el comentario almacenado en el índice dado. Si el índice no es válido, no se hace nada.
     */
    public void eliminarComentario(int index)
    {
        if(index >= 0 && index < comentarios.size()) {
            comentarios.remove(index);
        }
    }
    
    /**
     * Upvote el comentario en 'index'. Es decir: cuenta este comentario como útil.
     * Si el índice no es válido, no hace nada.
     */
    public void upvoteComentario(int index)
    {
        if(index >= 0 && index < comentarios.size()) { 
            comentarios.get(index).upvote();
        }
    }
    
    /**
     * Downvote el comentario en 'index'. Es decir: cuenta este comentario como no útil.
     * Si el índice no es válido, no hace nada.
     */
    public void downvoteComentario(int index)
    {
        if(index >= 0 && index < comentarios.size()) { 
            comentarios.get(index).downvote();
        }
    }
    
    /**
     * Muestra todos los detalles del producto incluyendo todos sus comentarios.
     * Actualmente, a efectos de prueba, se imprime la informacion en la terminal.
     * Modificar más tarde para la visualización web.
     */
    public void mostrarDetalles()
    {
        System.out.println("*** " + nombre + " ***");
        System.out.println("Precio: " + precioAString(precio));
        System.out.println();
        System.out.println("Comentarios de compradores:");
        for(Comentario comentario : comentarios) {
            System.out.println("-------------------------------------------");
            System.out.println(comentario.getDetalles());
        }
        System.out.println();
        System.out.println("===========================================");
    }
    
    /**
     * Devuelve el comentario más útil. El comentario más útil es el que tiene el balance de votos mas alto.
     * Si hay varios comentarios con igual saldo más alto, devuelve cualquiera de de ellos.
     */
    public Comentario getComentarioMasUtil()
    {
        Iterator<Comentario> it = comentarios.iterator();
        Comentario mejorComentario = it.next();
        while(it.hasNext()) {
            Comentario comentarioActual = it.next();
            if(comentarioActual.getBalanceVotos() > mejorComentario.getBalanceVotos()) {
                mejorComentario = comentarioActual;
            }
        }
        return mejorComentario;
    }
    
    /**
     * Comprueba si la calificación dada no es válida. Devuelve true si no es válida.
     * Las valoraciones válidas están en el rango [1..5].
     */
    private boolean esValoracionInvalida(int valoracion)
    {
        return valoracion < 0 || valoracion > 5;
    }
    
    /**
     * Encuentra el comentario del autor con el nombre dado.
     * 
     * @return El comentario si existe; null si no existe.
     */
    private Comentario encontrarComentarioPorAutor(String autor)
    {
        Comentario comentarioADevolver = null;
    
        for(Comentario comentario : comentarios) {
            if(comentario.getAutor().equals(autor)) {
                comentarioADevolver = comentario;
            }
        }

        return comentarioADevolver;
    }
    
    /**
     * Para un precio dado como int, devuelve una cadena legible que representa el mismo precio.
     * El precio se indica en céntimos enteros. Por ejemplo, para precio==12345, se devuelve la siguiente cadena
     * se devuelve: 123.45€
     */
    private String precioAString(int precio)
    {
        String precioADevolver = "";
        
        int euros = precio / 100;
        int centimos = precio - (euros * 100);
        if(centimos <= 9) {
            precioADevolver += euros + ".0" + centimos +  "€";
        }
        else {
            precioADevolver += euros + "." + centimos + "€";
        }
        
        return precioADevolver;
    }
}
