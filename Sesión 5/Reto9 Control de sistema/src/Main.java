import java.util.Random;
import java.util.concurrent.*;

public class Main {

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        // 1. Sensores de tráfico - cada 500ms
        scheduler.scheduleAtFixedRate(() -> {
            int congestion = random.nextInt(101);
            String evento = "🚗 Congestión: " + congestion + "% en Avenida Solar";
            if (congestion > 70) {
                System.out.println("🚗 Alerta crítica: " + evento);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

        // 2. Contaminación del aire - cada 600ms
        scheduler.scheduleAtFixedRate(() -> {
            int pm25 = random.nextInt(101);
            String evento = "🌫️ Contaminación PM2.5: " + pm25 + " ug/m3";
            if (pm25 > 50) {
                System.out.println("🌫️ Alerta crítica: " + evento);
            }
        }, 0, 600, TimeUnit.MILLISECONDS);

        // 3. Accidentes viales - cada 800ms
        String[] prioridades = {"Baja", "Media", "Alta"};
        scheduler.scheduleAtFixedRate(() -> {
            String prioridad = prioridades[random.nextInt(prioridades.length)];
            String evento = "🚑 Accidente con prioridad " + prioridad;
            if (prioridad.equals("Alta")) {
                System.out.println("🚑 Emergencia vial: " + evento);
            }
        }, 0, 800, TimeUnit.MILLISECONDS);

        // 4. Trenes maglev - cada 700ms
        scheduler.scheduleAtFixedRate(() -> {
            int retraso = random.nextInt(11);
            String evento = "🚝 Tren maglev retrasado " + retraso + " minutos";
            if (retraso > 5) {
                System.out.println("🚝 Retraso crítico: " + evento);
            }
        }, 0, 700, TimeUnit.MILLISECONDS);

        // 5. Semáforos inteligentes - cada 400ms
        String[] estados = {"Verde", "Amarillo", "Rojo"};
        // Para detectar 3 rojos seguidos, guardamos estado por cruce
        ConcurrentHashMap<String, Integer> rojoContador = new ConcurrentHashMap<>();

        String[] cruces = {"Norte", "Sur", "Este", "Oeste"};
        scheduler.scheduleAtFixedRate(() -> {
            String estado = estados[random.nextInt(estados.length)];
            String cruce = cruces[random.nextInt(cruces.length)];
            String evento = "🚦 Semáforo en " + estado + " en cruce " + cruce;

            if (estado.equals("Rojo")) {
                rojoContador.merge(cruce, 1, Integer::sum);
                if (rojoContador.get(cruce) == 3) {
                    System.out.println("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce " + cruce);
                    rojoContador.put(cruce, 0); // resetear contador
                }
            } else {
                rojoContador.put(cruce, 0);
            }
        }, 0, 400, TimeUnit.MILLISECONDS);

        // Dejamos correr 5 segundos para ver resultados
        Thread.sleep(5000);

        scheduler.shutdown();
        System.out.println("🏆 Fin de la simulación.");
    }
}
