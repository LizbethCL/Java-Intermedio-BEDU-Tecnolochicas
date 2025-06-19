import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Lista de pedidos
        List<Pedido> pedidos = List.of(
                new Pedido("Ana", "domicilio", "555-1234"),
                new Pedido("Luis", "local", null),
                new Pedido("Pedro", "domicilio", "555-5678"),
                new Pedido("Laura", "domicilio", null),
                new Pedido("Carlos", "local", "555-9999")
        );

        // Usar Stream API para procesar
        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // solo domicilio
                .map(Pedido::getTelefono) // Optional<String>
                .filter(Optional::isPresent) // solo los que tienen teléfono
                .map(Optional::get) // obtener el valor del Optional
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .forEach(System.out::println);
    }
}
