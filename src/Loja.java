import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Loja {

    private String nome;
    private Double saldo;
    private List<Funcionario> funcionarios;

    public Loja(String nome, List<Funcionario> funcionarios){
        saldo = 0.0;
        this.nome = nome;
        this.funcionarios = funcionarios;
    }

    public void realizarVenda(Double valorCompra){
        saldo += valorCompra;
        verificaSaldoParaPagarFuncionarios();
    }

    private void verificaSaldoParaPagarFuncionarios(){
        if(saldo >= 1400){
            Optional<Funcionario> funcionarioAPagar = funcionarios.stream().filter(funcionario -> !funcionario.pagamentoRecebido()).findFirst();
            if(funcionarioAPagar.isPresent()){
                System.out.println(nome + " conseguiu dinheiro suficiente para pagar um funcionário;");
                Banco.realizarTransacaoLojaParaFuncionario(this, funcionarioAPagar.get(), 1400.0);
                System.out.println("""
                        Pagamento de %.2f realizado para %s
                        """.formatted(1400.0, funcionarioAPagar.get().getNome()));
            }else{
//                System.out.println("Todos os funcionários já foram pagos!");
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void diminuirSaldo(Double valor){
        saldo -= valor;
    }

    @Override
    public String toString() {
        return "%-20s%-27s%-13s%n".formatted(nome, NumberFormat.getCurrencyInstance().format(getSaldo()), funcionarios.stream().map(Funcionario::getNome).collect(Collectors.joining(", ")));
    }
}
