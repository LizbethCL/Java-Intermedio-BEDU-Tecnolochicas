import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        // Iniciar operaciones asincrónicas
        CompletableFuture<String> rutaFuture = MovilidadApp.calcularRuta();
        CompletableFuture<Double> tarifaFuture = MovilidadApp.estimarTarifa();

        // Combinar ambas operaciones
        CompletableFuture<Void> resultado = rutaFuture
                .thenCombine(tarifaFuture, (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa)
                .exceptionally(error -> "❌ Error al calcular viaje: " + error.getMessage())
                .thenAccept(System.out::println);

        // Esperar a que termine
        resultado.join(); // Bloquea el hilo principal hasta que termine
    }
}
