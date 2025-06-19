import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Creamos un pool de 4 hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Creamos tareas que representan profesionales médicos
        Runnable draSanchez = () -> salaCirugia.usar("Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("Dr. Gómez");
        Runnable enfLopez = () -> salaCirugia.usar("Enf. López");
        Runnable drRamirez = () -> salaCirugia.usar("Dr. Ramírez");
        Runnable draJimenez = () -> salaCirugia.usar("Dra. Jiménez");

        // Ejecutamos las tareas
        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfLopez);
        executor.execute(drRamirez);
        executor.execute(draJimenez);

        executor.shutdown(); // Cerramos el executor después de lanzar las tareas
    }
}
