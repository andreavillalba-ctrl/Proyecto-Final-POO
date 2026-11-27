public class Main {

    public static void main(String[] args) {
        BaseDeDatos<Double> saldos = new BaseDeDatos<>();

        saldos.guardar(0, 100.0);
        saldos.guardar(1, 250.0);
        saldos.guardar(2, 1500.5);

        saldos.guardarCSV("cuentas.csv");
        System.out.println("Archivo cuentas.csv generado");

        BaseDeDatos<Double> cargados = new BaseDeDatos<>();
            CargarCSV archivo = new CargarCSV();
        archivo.cargarSaldos("cuentas.csv", cargados);

        for (int i = 0; i <= cargados.getMaximoIdUsado(); i++) {
            System.out.println("Id " + i + ": " + cargados.obtener(i));
        }
    }
}