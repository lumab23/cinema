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

    public boolean cancelarVenda(Venda venda) {
        if (venda != null) {
            venda.getSessao().setIngressosVendidos(venda.getSessao().getIngressosVendidos() - venda.getQtdIngressos());
            return true;
        }
        return false;
    }

    public int consultarDisponibilidade(Sessao sessao) {
        return sessao.getIngressosDisponiveis();
    }
}
