import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception{

        Funcionario funcionario = new Funcionario("Funcionario 1");
        funcionario.start();

        Loja supermarket = new Loja("SUPERMARKET", funcionario);

        Cliente[] clientes = new Cliente[1];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(supermarket);
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }

        for (Cliente cliente : clientes) {
            cliente.join();
        }

        System.out.println(supermarket);
        Arrays.stream(clientes).forEach(System.out::println);

    }
}