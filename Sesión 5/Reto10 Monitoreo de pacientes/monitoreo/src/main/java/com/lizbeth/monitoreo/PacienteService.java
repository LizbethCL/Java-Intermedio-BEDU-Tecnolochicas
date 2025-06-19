import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final Random random = new Random();

    public Flux<String> generarEventos(String paciente) {
        return Flux.interval(Duration.ofMillis(300))
            .map(i -> {
                int fc = 40 + random.nextInt(100); // FC entre 40 y 140
                int paSistolica = 80 + random.nextInt(80);
                int paDiastolica = 50 + random.nextInt(50);
                int spo2 = 85 + random.nextInt(15);

                StringBuilder alertas = new StringBuilder();

                if (fc < 50 || fc > 120) alertas.append("⚠️ " + paciente + " - FC crítica: " + fc + " bpm\n");
                if (paSistolica > 140 || paDiastolica > 90 || paSistolica < 90 || paDiastolica < 60)
                    alertas.append("⚠️ " + paciente + " - PA crítica: " + paSistolica + "/" + paDiastolica + "\n");
                if (spo2 < 90) alertas.append("⚠️ " + paciente + " - SpO2 baja: " + spo2 + "%\n");

                return alertas.toString();
            })
            .filter(alerta -> !alerta.isEmpty())
            .delayElements(Duration.ofSeconds(1)); // Simula backpressure
    }
}
