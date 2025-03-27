public class PagamentoCartao extends Pagamento {

    @Override
    public void processar() {
        System.out.println("Processando pagamento com cartão");
    }

}