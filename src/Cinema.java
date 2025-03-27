import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Sessao> sessoes = new ArrayList<>();
    private static ControladorFilmes controladorFilmes = new ControladorFilmes();
    private static ControladorVendas controladorVendas = new ControladorVendas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;
        
        while (!sair) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarFilme();
                    break;
                case 2:
                    listarFilmes();
                    break;
                case 3:
                    criarSessao();
                    break;
                case 4:
                    listarSessoes();
                    break;
                case 5:
                    venderIngresso();
                    break;
                case 6:
                    consultarDisponibilidade();
                    break;
                case 0:
                    sair = true;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Reserva de Ingressos de Cinema ---");
        System.out.println("1. Cadastrar Filme");
        System.out.println("2. Listar Filmes");
        System.out.println("3. Criar Sessão");
        System.out.println("4. Listar Sessões");
        System.out.println("5. Vender Ingresso");
        System.out.println("6. Consultar Disponibilidade de Sessão");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarFilme() {
        System.out.print("Nome do Filme: ");
        String nome = scanner.nextLine();
        
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        
        System.out.print("Sinopse: ");
        String sinopse = scanner.nextLine();
        
        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();
        
        System.out.print("Duração (minutos): ");
        int duracaoMin = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        if (controladorFilmes.adicionarFilme(nome, genero, sinopse, diretor, duracaoMin)) {
            Filme filme = new Filme(nome, genero, sinopse, diretor, duracaoMin);
            filmes.add(filme);
            System.out.println("Filme cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar filme.");
        }
    }

    private static void listarFilmes() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }
        
        System.out.println("--- Filmes Cadastrados ---");
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            System.out.printf("%d. %s - %s (%d min)\n", 
                i+1, filme.getNome(), filme.getGenero(), filme.getDuracaoMin());
        }
    }

    private static void criarSessao() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado. Cadastre um filme primeiro.");
            return;
        }
        
        listarFilmes();
        System.out.print("Escolha o número do filme: ");
        int escolhaFilme = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer
        
        if (escolhaFilme < 0 || escolhaFilme >= filmes.size()) {
            System.out.println("Filme inválido.");
            return;
        }
        
        Filme filme = filmes.get(escolhaFilme);
        
        System.out.print("Número da Sala: ");
        int numSala = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        // Criando uma sala de exemplo 10x10
        boolean[][] assentos = new boolean[10][10];
        for (boolean[] fila : assentos) {
            for (int i = 0; i < fila.length; i++) {
                fila[i] = true; // todos os assentos inicialmente disponíveis
            }
        }
        Sala sala = new Sala(numSala, assentos);
        
        System.out.print("Data e Hora da Sessão (AAAA-MM-DD HH:MM): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime horario = LocalDateTime.parse(dataHoraStr);
        
        System.out.print("Capacidade Máxima: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        Sessao sessao = new Sessao(sala, filme, horario, 0, capacidade);
        sessoes.add(sessao);
        System.out.println("Sessão criada com sucesso!");
    }

    private static void listarSessoes() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão cadastrada.");
            return;
        }
        
        System.out.println("--- Sessões Disponíveis ---");
        for (int i = 0; i < sessoes.size(); i++) {
            Sessao sessao = sessoes.get(i);
            System.out.printf("%d. %s - Sala %d - %s - Ingressos: %d/%d\n", 
                i+1, 
                sessao.getFilme().getNome(), 
                sessao.getSala().getNumero(), 
                sessao.getHorario(), 
                sessao.getIngressosVendidos(), 
                sessao.getCapacidadeMaxima());
        }
    }

    private static void venderIngresso() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão disponível.");
            return;
        }
        
        listarSessoes();
        System.out.print("Escolha o número da sessão: ");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer
        
        if (escolhaSessao < 0 || escolhaSessao >= sessoes.size()) {
            System.out.println("Sessão inválida.");
            return;
        }
        
        Sessao sessao = sessoes.get(escolhaSessao);
        
        System.out.print("Quantidade de Ingressos: ");
        int qtdIngressos = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        
        // Criando um cliente temporário
        Cliente cliente = new Cliente(nomeCliente, "000.000.000-00", "email@exemplo.com", 25);
        
        // Escolher tipo de ingresso
        System.out.println("Tipo de Ingresso:");
        System.out.println("1. Inteira");
        System.out.println("2. Meia");
        System.out.println("3. VIP");
        System.out.print("Escolha o tipo: ");
        int tipoIngressoEscolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        TipoIngresso tipoIngresso;
        switch (tipoIngressoEscolha) {
            case 2:
                tipoIngresso = TipoIngresso.MEIA;
                break;
            case 3:
                tipoIngresso = TipoIngresso.VIP;
                break;
            default:
                tipoIngresso = TipoIngresso.INTEIRA;
        }
        
        // Definir preço base
        float precoBase = 20.0f; // Exemplo de preço base
        
        // Criar ingressos
        List<Ingresso> ingressos = new ArrayList<>();
        for (int i = 0; i < qtdIngressos; i++) {
            // Gerar número de cadeira (exemplo simples)
            String numeroCadeira = "A" + (i + 1);
            
            // Criar ingresso com o tipo selecionado
            Ingresso ingresso = new Ingresso(precoBase, sessao, tipoIngresso, numeroCadeira);
            
            // Calcular preço com base no tipo de ingresso
            float precoFinal = ingresso.calcularPreco();
            ingresso.setPreco(precoFinal);
            
            ingressos.add(ingresso);
        }
        
        // Calcular preço total
        float precoTotal = ingressos.stream()
            .map(Ingresso::getPreco)
            .reduce(0f, Float::sum);
        
        // Escolher método de pagamento
        System.out.println("Método de Pagamento:");
        System.out.println("1. Cartão");
        System.out.println("2. Dinheiro");
        System.out.println("3. PIX");
        System.out.print("Escolha o método: ");
        int metodoPagamentoEscolha = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        
        Pagamento pagamento;
        switch (metodoPagamentoEscolha) {
            case 2:
                pagamento = new PagamentoDinheiro();
                break;
            case 3:
                pagamento = new PagamentoPIX();
                break;
            default:
                pagamento = new PagamentoCartao();
        }
        
        Venda venda = controladorVendas.realizarVenda(sessao, cliente, qtdIngressos);
        
        if (venda != null) {
            venda.setPrecoTotal(precoTotal);
            venda.setPagamento(pagamento);
            venda.efetuarPagamento();
            System.out.println("Venda realizada com sucesso!");
            System.out.printf("Preço total: R$ %.2f\n", precoTotal);
        } else {
            System.out.println("Não foi possível realizar a venda. Verifique a disponibilidade.");
        }
    }

    private static void consultarDisponibilidade() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão disponível.");
            return;
        }
        
        listarSessoes();
        System.out.print("Escolha o número da sessão: ");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer
        
        if (escolhaSessao < 0 || escolhaSessao >= sessoes.size()) {
            System.out.println("Sessão inválida.");
            return;
        }
        
        Sessao sessao = sessoes.get(escolhaSessao);
        int disponibilidade = controladorVendas.consultarDisponibilidade(sessao);
        System.out.printf("Ingressos disponíveis: %d\n", disponibilidade);
    }
}