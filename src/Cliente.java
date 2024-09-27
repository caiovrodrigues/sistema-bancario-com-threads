import java.util.Random;

public class Cliente extends Thread{

    private Loja loja;
    private Double saldo;

    public Cliente(Loja loja){
        saldo = 2000.0;
        this.loja = loja;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            loja.realizarVenda(700.0);
            saldo -= 700.0;
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "saldo=" + saldo +
                '}';
    }
}
