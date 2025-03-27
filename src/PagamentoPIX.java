public class PagamentoPIX extends Pagamento {
    private String chavePIX;

    public PagamentoPIX(String chavePIX) {
        this.chavePIX = chavePIX;
    }

    @Override
    public void processar() {
        // Implementação específica para PIX
    }

    public String getChavePIX() {
        return chavePIX;
    }

    public void setChavePIX(String chavePIX) {
        this.chavePIX = chavePIX;
    }

    
}