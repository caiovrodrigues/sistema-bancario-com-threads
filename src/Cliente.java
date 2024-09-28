import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cliente extends Thread{

    private String nome;
    private List<Loja> lojas;
    private Conta conta;
    private List<HistoricoCompra> historicoCompras = new ArrayList<>();

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
                historicoCompras.add(new HistoricoCompra(this, loja, valorCompra));
                Banco.realizarTransacaoClienteParaLoja(loja, this, valorCompra);

                try { Thread.sleep(Main.TEMPO_SLEEP_ENTRE_COMPRAS); } catch (Exception e) { throw new RuntimeException(e); }
            }
        });
    }

    public void diminuirSaldo(Double valor){
        if(conta.getSaldo() - valor < 0){
            throw new RuntimeException(nome + " não possui saldo suficiente. saldo = " + conta.getSaldo() + "; valorCompra = " + valor);
        }
        conta.descontar(valor);
    }

    @Override
    public String toString() {
        return "%-20s%-27s%-15s"
                .formatted(
                        nome,
                        NumberFormat.getCurrencyInstance().format(conta.getSaldo()),
                        historicoCompras.stream()
                                .map(compra -> compra.getLoja().getNome() + " - " + NumberFormat.getCurrencyInstance().format(compra.getValor()))
                                .collect(Collectors.joining(" | "))) + " || Total Compras: " +
                        NumberFormat.getCurrencyInstance().format(historicoCompras.stream().reduce(0.0, (acc, atual) -> acc + atual.getValor(), (a,b) -> a + b));
    }
}
