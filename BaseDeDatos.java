/**Esta clase lo que hace es crear los id y relacionarlos
 * con valores que otra clase el va a dar u
 *
 * @param <T> ES una clase generica para facilitar el relacionar el
 *  id con los otros elementossin tener que hacer mas clases
 */
public class BaseDeDatos<T> {
// es un arreglo por lo que se tiene que definir cuantos usuarios va atener nuestro banco antes la idea es despues en alguna otra clase una forma de aumentarlo
    private int maxUsuario = 100;
    private int maximoIdUsado = -1;
    private Object[] datos = new Object[maxUsuario];
    /**revisa que el id sea valido nada de negativo ni numeros mayores al limiti impuesto como 10 al 9999999999999999999999

     * @param id id a revisar
     * @return true si esta entre 0 y maxUsuario - 1
     */
    private boolean idValido(int id) {
        return id >= 0 && id < maxUsuario;
    }
    /**
     * Guarda un dato en la posicion del id. Si ya habia uno, lo reemplaza.
     *
     * @param id    id al que se asocia el dato
     * @param valor dato a guardar
     * @throws IllegalArgumentException si el id esta fuera de rango
     */

    public void guardar(int id, T valor) {
        if (!idValido(id)) {
            throw new IllegalArgumentException("Id fuera de rango: " + id);
        }
        datos[id] = valor;
        if (id > maximoIdUsado) {
            maximoIdUsado = id;
        }
    }
    /**
     * Devuelve el dato asociado a un id.
     *
     * @param id id a consultar
     * @return el dato guardado, o null si ese id no tiene datos
     * @throws IllegalArgumentException si el id esta fuera de rango
     */
    @SuppressWarnings("unchecked")
    public T obtener(int id) {
        if (!idValido(id)) {
            throw new IllegalArgumentException("Id fuera de rango: " + id);
        }
        return (T) datos[id];
    }

    public int getMaximoIdUsado() {
        return maximoIdUsado;
    }
//para no cargar mas de lo nesesario en el archivo se usa el mayor id usado no el mayor id pposible asi se evita que un sitema con solo 10 revise un millon
    public void setMaxUsuario(int nuevoMax) {
        if (nuevoMax <= maximoIdUsado) {
            throw new IllegalArgumentException(
                "El nuevo maximo debe ser mayor al id mas alto usado: " + maximoIdUsado);
        }
        maxUsuario = nuevoMax;
        datos = java.util.Arrays.copyOf(datos, nuevoMax);
    }

    public void guardarCSV(String ruta) {
        try (java.io.BufferedWriter escritor =
                new java.io.BufferedWriter(new java.io.FileWriter(ruta))) {
            escritor.write("id,valor");
            escritor.newLine();
            for (int i = 0; i <= maximoIdUsado; i++) {
                escritor.write(i + "," + (datos[i] == null ? "" : datos[i]));
                escritor.newLine();
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
