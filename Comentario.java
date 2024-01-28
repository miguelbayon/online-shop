
/**
 * Esta clase representa un comentario dejado para un artículo de venta en un sitio de ventas online
 * del estilo de Amazon.
 * 
 * Un comentario consta de un texto de comentario, una valoración y un nombre de autor. Otros usuarios
 * pueden indicar si el comentario fue útil o no (llamado 'upvoting' o 'downvoting'). 
 * 
 * El balance entre upvotes y downvotes se almacena con los comentarios.
 * 
 * Un balance negativo significa que el comentario ha recibido más votos negativos que positivos.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Comentario
{
    private String autor;
    private String texto;
    private int valoracion;
    private int balanceVotos;

    /**
     * Crea un comentario con todos los datos necesarios.
     */
    public Comentario(String autor, String texto, int valoracion)
    {
        this.autor = autor;
        this.texto = texto;
        this.valoracion = valoracion;
        balanceVotos = 0;
    }

    /**
     * Indica que este comentario es útil ('upvote'). Se utiliza cuando un lector pulsa
     * el botón "Sí" después de la pregunta "¿Ha sido útil este comentario?"
     */
    public void upvote()
    {
        balanceVotos++;
    }

    /**
     * Indica que este comentario no es útil ('downvote'). Se utiliza cuando un lector pulsa
     * el botón "No" después de la pregunta "¿Ha sido útil este comentario?"
     */
    public void downvote()
    {
        balanceVotos--;
    }
    
    /**
     * Devuelve el autor de un comentario
     */
    public String getAutor()
    {
        return autor;
    }
    
    /**
     * Devuelve la valoracion de un comentario
     */
    public int getValoracion()
    {
        return valoracion;
    }
    
    /**
     * Devuelve el balance entre upvotes y downvotes
     */
    public int getBalanceVotos()
    {
        return balanceVotos;
    }
    
    /**
     * Devuelve un texto con todos los detalles del comentario incluyendo
     * el texto del comentario, el autor y el balance de votos.
     */
    public String getDetalles()
    {
        String detalles = "Valoracion: " + "*****".substring(0,valoracion) + "    "
                         + "By: " + autor + "\n"
                         + "    " + texto + "\n";
        // Nota: 'balanceVotos' se incluye en este momento solo a 
        // efectos de prueba. En la aplicación final, este valor no se mostrará. 
        // En su lugar, el recuento de votos se utilizará para seleccionar 
        // y ordenar los comentarios en pantalla
        detalles += "(Votado como util: " + balanceVotos + ")\n";
        return detalles;
    }
}
