public class PagamentoPIX extends Pagamento {

    @Override
    public void processar() {
        System.out.println("Processando pagamento PIX");
    }
}