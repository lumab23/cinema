import java.util.Scanner;
import java.time.LocalDateTime;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static ControladorVendas controlador = new ControladorVendas();
    private static Cliente cliente;
    private static Sessao sessaoSelecionada;

    public static void main(String[] args) {
        // Dados iniciais de exemplo
        Filme filme = new Filme("Interestelar", "Ficção Científica", "Viagem no espaço", "Christopher Nolan", 169);
        Sala sala = new Sala(1, new boolean[5][10]); // 5 fileiras, 10 assentos cada
        sessaoSelecionada = new Sessao(sala, filme, LocalDateTime.now().plusDays(1), 0, 50); // Capacidade: 50

        // Menu principal
        while (true) {
            System.out.println("\n=== CINEMA ===\n1. Cadastrar Cliente\n2. Comprar Ingresso\n3. Cancelar Venda\n4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    comprarIngresso();
                    break;
                case 3:
                    cancelarVenda();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();

        cliente = new Cliente(nome, cpf, email, idade);
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    private static void comprarIngresso() {
        if (cliente == null) {
            System.out.println("Cadastre um cliente primeiro!");
            return;
        }

        System.out.println("\n--- COMPRA DE INGRESSO ---");
        System.out.println("Sessão: " + sessaoSelecionada.getFilme().getNome() + 
                          " | Horário: " + sessaoSelecionada.getHorario());
        System.out.println("Assentos disponíveis: " + sessaoSelecionada.getIngressosDisponiveis());

        System.out.print("Quantidade de ingressos: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        if (qtd > sessaoSelecionada.getIngressosDisponiveis()) {
            System.out.println("Ingressos insuficientes!");
            return;
        }

        // Selecionar tipo de ingresso
        System.out.println("Tipo (1-Inteira | 2-Meia | 3-VIP): ");
        int tipoOpcao = scanner.nextInt();
        TipoIngresso tipo = switch (tipoOpcao) {
            case 1 -> TipoIngresso.INTEIRA;
            case 2 -> TipoIngresso.MEIA;
            case 3 -> TipoIngresso.VIP;
            default -> TipoIngresso.INTEIRA;
        };

        System.out.println("Tipo de ingresso selecionado: " + tipo);

        // Selecionar pagamento
        System.out.println("Pagamento (1-Cartão | 2-PIX | 3-Dinheiro): ");
        int pagamentoOpcao = scanner.nextInt();
        Pagamento pagamento = switch (pagamentoOpcao) {
            case 1 -> new PagamentoCartao("1234-5678", "123");
            case 2 -> new PagamentoPIX("chave@email.com");
            case 3 -> new PagamentoDinheiro();
            default -> new PagamentoCartao("0000-0000", "000");
        };

        // Efetuar venda
        Venda venda = controlador.realizarVenda(sessaoSelecionada, cliente, qtd);
        venda.setPagamento(pagamento);
        venda.efetuarPagamento();

        System.out.println("Venda realizada! Total: R$" + venda.getPrecoTotal());
    }

    private static void cancelarVenda() {
        System.out.println("Funcionalidade não implementada neste exemplo.");
    }
}