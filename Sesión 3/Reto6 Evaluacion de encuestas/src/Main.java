import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Crear encuestas
        List<Encuesta> encuestasCentro = List.of(
                new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                new Encuesta("Luis", null, 4)
        );

        List<Encuesta> encuestasNorte = List.of(
                new Encuesta("Pedro", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Laura", null, 5)
        );

        // Crear sucursales
        Sucursal centro = new Sucursal("Centro", encuestasCentro);
        Sucursal norte = new Sucursal("Norte", encuestasNorte);

        List<Sucursal> sucursales = List.of(centro, norte);

        // Procesar encuestas con flatMap y Optional
        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(e -> e.getCalificacion() <= 3)
                                .map(e -> Map.entry(sucursal.getNombre(), e))
                )
                .map(entry ->
                        entry.getValue().getComentario()
                                .map(comentario -> "Sucursal " + entry.getKey() + ": Seguimiento a paciente con comentario: \"" + comentario + "\"")
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }
}
