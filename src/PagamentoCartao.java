public class PagamentoCartao extends Pagamento {
    private String numeroCartao;
    private String cvv;

    

    public PagamentoCartao(String numeroCartao, String cvv) {
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
    }



    @Override
    public void processar() {
        // Implementação específica para cartão
    }



    public String getNumeroCartao() {
        return numeroCartao;
    }



    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }



    public String getCvv() {
        return cvv;
    }



    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


}