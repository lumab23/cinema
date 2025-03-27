public class ControladorVendas {
    public Venda realizarVenda(Sessao sessao, Cliente cliente, int qtdIngressos) {
        // Implementação
        return new Venda(sessao, cliente, qtdIngressos);
    }

    public boolean cancelarVenda(Venda venda) {
        // Implementação
        return true;
    }

    public int consultarDisponibilidade(Sessao sessao) {
        // Implementação
        return sessao.getIngressosDisponiveis();
    }
}