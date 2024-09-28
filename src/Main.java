import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    //Tempo de sleep entre uma compra e outra dos clientes (em millisegundos)
    public static final Integer TEMPO_SLEEP_ENTRE_COMPRAS = 0;
    private static final Integer QTD_LOJAS = 2;
    private static final Integer QTD_FUNCIONARIOS = 4;

    public static void main(String[] args) throws Exception{

        List<Funcionario> funcionarios = instanciaListaFuncionarios(QTD_FUNCIONARIOS);
        funcionarios.forEach(funcionario -> funcionario.start());

        List<Loja> lojas = instanciaListaLojas(QTD_LOJAS, funcionarios);

        Cliente[] clientes = new Cliente[10];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente("Cliente " + (i + 1), lojas);
            clientes[i].start();
        }

        for (int i = 0; i < clientes.length; i++) {
            clientes[i].join();
        }

        System.out.printf("%n%n-----------------------FUNCIONÁRIOS-----------------------%n%n");
        System.out.println("%-20s%-27s%-30s%-13s".formatted("NOME", "SALDO (CONTA SALÁRIO) ", "SALDO (CONTA INVESTIMENTO)", "TOTAL"));
        funcionarios.forEach(System.out::println);

        System.out.printf("%n----------------------------LOJAS----------------------------%n%n");
        System.out.println("%-20s%-27s%-13s".formatted("NOME", "SALDO", "FUNCIONÁRIOS"));
        lojas.forEach(System.out::println);

        System.out.printf("%n---------------------------CLIENTES---------------------------%n%n");
        System.out.println("%-20s%-27s%-15s".formatted("NOME", "SALDO (CONTA SALÁRIO)", "HISTÓRICO COMPRAS"));
        Arrays.stream(clientes).forEach(System.out::println);
    }

    private static List<Funcionario> instanciaListaFuncionarios(int qtdFuncionarios){
        return IntStream.rangeClosed(1, qtdFuncionarios).mapToObj(numero -> new Funcionario("Funcionário " + numero)).toList();
    }

    private static List<Loja> instanciaListaLojas(int qtdLojas, List<Funcionario> funcionarios){
        List<Loja> lojas = new ArrayList<>();
        int qtdFuncionariosPorLoja = funcionarios.size() / qtdLojas;
        for (int i = 0; i < funcionarios.size();) {
            int inicioSubLista = i;
            i = i + qtdFuncionariosPorLoja;
            Loja loja = new Loja("Loja " + (lojas.size() + 1), funcionarios.subList(inicioSubLista, i));
            lojas.add(loja);
        }
        return lojas;
    }

}