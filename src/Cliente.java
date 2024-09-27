import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cliente extends Thread{

    private String nome;
    private List<Loja> lojas;
    private Conta conta;
    private final Object saldoLock = new Object();

    public Cliente(String nome, List<Loja> lojas){
        this.nome = nome;
        this.lojas = lojas;
        conta = new ContaSalario(2000.0);
    }

    @Override
    public void run() {

        lojas.forEach(loja -> {
            for (int i = 0; i < 2; i++) {
                double valorCompra = new Random().nextDouble(200.0, 500.0);
                System.out.println(nome + " comprou " + NumberFormat.getCurrencyInstance().format(valorCompra) + " na " + loja.getNome());
                Banco.realizarTransacaoClienteParaLoja(loja, this, valorCompra);
//                System.out.println("Compra realizada, dormindo por 3 segundos... zzz");

//                try { Thread.sleep(3000); } catch (Exception e) { throw new RuntimeException(e); }
            }
        });
    }

    public void diminuirSaldo(Double valor){
        synchronized (saldoLock){
            if(conta.getSaldo() - valor < 0){
                throw new RuntimeException(nome + " não possui saldo suficiente. saldo = " + conta.getSaldo() + "; valorCompra = " + valor);
            }
            conta.descontar(valor);
        }
    }

    @Override
    public String toString() {
        return "%-20s%-27s".formatted(nome, NumberFormat.getCurrencyInstance().format(conta.getSaldo()));
//        return "Cliente{" +
//                "nome='" + nome + '\'' +
//                ", conta=" + conta +
//                '}';
    }
}
