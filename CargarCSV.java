/**
 * Lee archivos CSV y llena con sus datos una {@link BaseDeDatos}.
 * Solo es para leer los archivos CSV
 */
public class CargaCSV {

    /**
     * Carga el un archivo CSV hpa5ra mostralo.
     * El archivo debe estar completo es decir que tenga un  order y no mecle los valores.
     * Si el archivo no existe no hace nada
     *
     * @param ruta   ruta del archivo CSV, por ejemplo "cuentas.csv"
     * @param saldos base de datos que se llena con los saldos leidos
     */
    public void cargarSaldos(String ruta, BaseDeDatos<Double> saldos) {
        java.io.File archivo = new java.io.File(ruta);
        //revisa que exista
        if (!archivo.exists()) {
            return;
        }
        try (java.io.BufferedReader lector =
                     new java.io.BufferedReader(new java.io.FileReader(archivo))) {
            lector.readLine();
            String linea;
            //lo lee
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                //revisa el tamaño
                if (partes.length < 2 || partes[1].isEmpty()) {
                    continue;
                }
                int id = Integer.parseInt(partes[0]);
                double valor = Double.parseDouble(partes[1]);
                saldos.guardar(id, valor);
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}