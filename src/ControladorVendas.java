public class ControladorVendas {
    public Venda realizarVenda(Sessao sessao, Cliente cliente, int qtdIngressos) {
        if (sessao.reservarIngressos(qtdIngressos)) {
            Venda venda = new Venda(sessao, cliente, qtdIngressos);
            GerenteDB db = new GerenteDB();
            db.salvarVenda(venda);
            return venda;
        }
        return null;
    }

    public int consultarDisponibilidade(Sessao sessao) {
        return sessao.getIngressosDisponiveis();
    }
}
