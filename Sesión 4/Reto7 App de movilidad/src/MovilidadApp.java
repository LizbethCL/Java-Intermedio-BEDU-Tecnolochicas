import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MovilidadApp {

    // Simula calcular una ruta con latencia de 2-3 segundos
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                TimeUnit.SECONDS.sleep(2 + new Random().nextInt(2)); // 2-3 seg
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta");
            }
        });
    }

    // Simula estimar una tarifa con latencia de 1-2 segundos
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                TimeUnit.SECONDS.sleep(1 + new Random().nextInt(2)); // 1-2 seg

                // Descomenta para simular una excepción aleatoria
                // if (new Random().nextBoolean()) throw new RuntimeException("Fallo en tarifa");

                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar tarifa");
            }
        });
    }
}
