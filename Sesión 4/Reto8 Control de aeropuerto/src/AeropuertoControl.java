import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {
    private static final Random random = new Random();

    // Simula latencia de 2-3 segundos y condiciones aleatorias
    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2)); // 2-3 segundos
                boolean resultado = random.nextDouble() < 0.8; // 80% de probabilidad
                System.out.println("🛣️ Pista disponible: " + resultado);
                return resultado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en pista");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
                boolean resultado = random.nextDouble() < 0.85;
                System.out.println("🌦️ Clima favorable: " + resultado);
                return resultado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en clima");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
                boolean resultado = random.nextDouble() < 0.9;
                System.out.println("🚦 Tráfico aéreo despejado: " + resultado);
                return resultado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en tráfico aéreo");
            }
        });
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2 + random.nextInt(2));
                boolean resultado = random.nextDouble() < 0.95;
                System.out.println("👷‍♂️ Personal disponible: " + resultado);
                return resultado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en personal de tierra");
            }
        });
    }
}
