import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = AeropuertoControl.verificarPista();
        CompletableFuture<Boolean> clima = AeropuertoControl.verificarClima();
        CompletableFuture<Boolean> trafico = AeropuertoControl.verificarTraficoAereo();
        CompletableFuture<Boolean> personal = AeropuertoControl.verificarPersonalTierra();

        // Esperar todos y combinar resultados
        CompletableFuture<Void> resultadoFinal = CompletableFuture.allOf(pista, clima, trafico, personal)
                .thenRun(() -> {
                    try {
                        boolean okPista = pista.get();
                        boolean okClima = clima.get();
                        boolean okTrafico = trafico.get();
                        boolean okPersonal = personal.get();

                        if (okPista && okClima && okTrafico && okPersonal) {
                            System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                        } else {
                            System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                        }
                    } catch (Exception e) {
                        System.out.println("\n❌ Error en verificación: " + e.getMessage());
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n❌ Error inesperado: " + ex.getMessage());
                    return null;
                });

        resultadoFinal.join(); // Bloquea hasta que termine
    }
}
