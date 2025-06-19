import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> com = executor.submit(new SistemaComunicaciones());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());

        // Mostrar resultados (el orden puede variar)
        System.out.println(com.get());
        System.out.println(soporte.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        executor.shutdown();

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }
}
