public class Pagamento {

    // ver sobre o padrão strategy

    public String tipoPagamento(String tipo) {
        switch (tipo.toLowerCase()) {
            case "pix":
                return "Pagamento realizado com PIX";
            case "cartao":
                return "Pagamento realizado com Cartão";
            case "espécie":
                return "Pagamento realizado com espécie";
            default:
                return "Tipo de pagamento inválido";
        }
    }

}
