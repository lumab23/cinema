public class PagamentoDinheiro extends Pagamento {

    @Override
    public void processar() {
        System.out.println("Processando pagamento em dinheiro");
    }
}