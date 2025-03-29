import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
            scanner.nextLine(); 

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
        System.out.println("\n");
        System.out.println("================================================");
        System.out.println("   Sistema de Reserva de Ingressos de Cinema    ");
        System.out.println("================================================");
        System.out.println("  1. Cadastrar Filme");
        System.out.println("  2. Listar Filmes");
        System.out.println("  3. Criar Sessão");
        System.out.println("  4. Listar Sessões");
        System.out.println("  5. Vender Ingresso");
        System.out.println("  6. Consultar Disponibilidade de Sessão");
        System.out.println("  0. Sair");
        System.out.println("------------------------------------------------");
        System.out.print("  Escolha uma opção: ");
    }
    
    // método auxiliar para o cabeçalho da sessão 
    private static void exibirCabecalho(String titulo) {
        System.out.println("\n");
        System.out.println("================================================");
        System.out.println("   " + titulo);
        System.out.println("================================================");
    }

    private static void cadastrarFilme() {
        exibirCabecalho("CADASTRO DE FILME");
        
        System.out.print("  Nome do Filme: ");
        String nome = scanner.nextLine();
        
        System.out.print("  Gênero: ");
        String genero = scanner.nextLine();
        
        System.out.print("  Sinopse: ");
        String sinopse = scanner.nextLine();
        
        System.out.print("  Diretor: ");
        String diretor = scanner.nextLine();
        
        System.out.print("  Duração (minutos): ");
        int duracaoMin = scanner.nextInt();
        scanner.nextLine(); 
        
        if (controladorFilmes.adicionarFilme(nome, genero, sinopse, diretor, duracaoMin)) {
            Filme filme = new Filme(nome, genero, sinopse, diretor, duracaoMin);
            filmes.add(filme);
            System.out.println("\n  ✓ Filme cadastrado com sucesso!");
        } else {
            System.out.println("\n  ✗ Erro ao cadastrar filme.");
        }
        
        System.out.println("\n  Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void listarFilmes() {
        exibirCabecalho("FILMES CADASTRADOS");
        
        if (filmes.isEmpty()) {
            System.out.println("  Nenhum filme cadastrado.");
            System.out.println("\n  Pressione ENTER para continuar...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("  ID | FILME                     | GÊNERO      | DURAÇÃO");
        System.out.println("  -----------------------------------------------------------");
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            System.out.printf("  %2d | %-25s | %-12s | %d min\n", 
                i+1, 
                limitarTexto(filme.getNome(), 25), 
                limitarTexto(filme.getGenero(), 12), 
                filme.getDuracaoMin());
        }
        
        System.out.println("\n  Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static String limitarTexto(String texto, int tamanho) {
        if (texto.length() <= tamanho) {
            return texto;
        }
        return texto.substring(0, tamanho - 3) + "...";
    }

    private static void criarSessao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        exibirCabecalho("CRIAÇÃO DE SESSÃO");
        
        if (filmes.isEmpty()) {
            System.out.println("  Nenhum filme cadastrado. Cadastre um filme primeiro.");
            System.out.println("\n  Pressione ENTER para continuar...");
            scanner.nextLine();
            return;
        }
        
        listarFilmes();
        System.out.print("\n  Escolha o número do filme: ");
        int escolhaFilme = scanner.nextInt() - 1;
        scanner.nextLine(); // Limpa o buffer
        
        if (escolhaFilme < 0 || escolhaFilme >= filmes.size()) {
            System.out.println("\n  ✗ Filme inválido.");
            System.out.println("\n  Pressione ENTER para continuar...");
            scanner.nextLine();
            return;
        }
        
        Filme filme = filmes.get(escolhaFilme);
        
        System.out.print("  Número da Sala: ");
        int numSala = scanner.nextInt();
        scanner.nextLine();
        
        LocalDateTime horario = null;
        
        // loop para validar a data e hora
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("  Data e Hora da Sessão (AAAA-MM-DD HH:MM): ");
                String dataHoraStr = scanner.nextLine();
                horario = LocalDateTime.parse(dataHoraStr, formatter);
                dataValida = true; // formato válido
            } catch (Exception e) {
                System.out.println("  ✗ Formato de data e hora inválido. Use o formato AAAA-MM-DD HH:MM.");
            }
        }
        
        // erificar se já existe uma sessão na mesma sala e horário
        for (Sessao sessaoExistente : sessoes) {
            if (sessaoExistente.getSala().getNumero() == numSala &&
                sessaoExistente.getHorario().equals(horario)) {
                System.out.println("\n  ✗ Erro: Já existe uma sessão cadastrada nesta sala e horário!");
                System.out.println("\n  Pressione ENTER para continuar...");
                scanner.nextLine();
                return;
            }
        }
        
        System.out.print("  Capacidade Máxima: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine(); 
        
        int colunas = 10;
        int linhas = capacidade / colunas;
        if (capacidade % colunas != 0) {
            linhas++;
        }
        
        boolean[][] assentos = new boolean[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                assentos[i][j] = true; 
            }
        }
        
        Sala sala = new Sala(numSala, assentos);
        Sessao novaSessao = new Sessao(sala, filme, horario, 0, capacidade);
        sessoes.add(novaSessao);
        System.out.println("\n  ✓ Sessão criada com sucesso!");
        
        System.out.println("\n  Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    

    private static void listarSessoes() {
        exibirCabecalho("SESSÕES DISPONÍVEIS");
        
        if (sessoes.isEmpty()) {
            System.out.println("  Nenhuma sessão cadastrada.");
            System.out.println("\n  Pressione ENTER para continuar...");
            scanner.nextLine();
            return;
        }
        
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        
        System.out.println("  ID | FILME                | SALA | DATA       | HORA  | OCUPAÇÃO");
        System.out.println("  -----------------------------------------------------------------");
        for (int i = 0; i < sessoes.size(); i++) {
            Sessao sessao = sessoes.get(i);
            LocalDateTime horario = sessao.getHorario();
            System.out.printf("  %2d | %-20s | %4d | %s | %s | %d/%d\n", 
                i+1, 
                limitarTexto(sessao.getFilme().getNome(), 20), 
                sessao.getSala().getNumero(), 
                horario.format(formatoData),
                horario.format(formatoHora),
                sessao.getIngressosVendidos(), 
                sessao.getCapacidadeMaxima());
        }
        
        System.out.println("\n  Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void venderIngresso() {
        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão disponível.");
            return;
        }
        
        listarSessoes();
        System.out.print("Escolha o número da sessão: ");
        int escolhaSessao = scanner.nextInt() - 1;
        scanner.nextLine(); 
        
        if (escolhaSessao < 0 || escolhaSessao >= sessoes.size()) {
            System.out.println("Sessão inválida.");
            return;
        }
        
        Sessao sessao = sessoes.get(escolhaSessao);
        Sala sala = sessao.getSala();
        
        sala.exibirAssentos();
        
        System.out.print("Quantidade de Ingressos: ");
        int qtdIngressos = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();
        
        Cliente cliente = new Cliente(nomeCliente, "000.000.000-00", "email@exemplo.com", 25);
        
        List<String> assentosEscolhidos = new ArrayList<>();
        List<Ingresso> ingressos = new ArrayList<>();
        
        for (int i = 0; i < qtdIngressos; i++) {
            System.out.printf("Escolha o número do assento para o ingresso %d: ", i + 1);
            int escolhaAssento = scanner.nextInt();
            scanner.nextLine(); 
            
            int fileira = (escolhaAssento - 1) / sala.getAssentos()[0].length;
            int coluna = (escolhaAssento - 1) % sala.getAssentos()[0].length;
            
            if (!sala.isAssentoDisponivel(fileira, coluna)) {
                System.out.println("Assento já ocupado! Escolha outro.");
                i--; 
                continue;
            }
            
            sala.reservarAssento(fileira, coluna);
            String numeroCadeira = (fileira + 1) + "-" + (coluna + 1);
            assentosEscolhidos.add(numeroCadeira);
        }
        
        float precoBase = 20.0f;
        
        Venda venda = controladorVendas.realizarVenda(sessao, cliente, qtdIngressos);
        
        if (venda != null) {
            for (String cadeira : assentosEscolhidos) {
                System.out.println("Tipo de Ingresso para o assento " + cadeira + ":");
                System.out.println("1. Inteira");
                System.out.println("2. Meia");
                System.out.print("Escolha o tipo: ");
                int tipoIngressoEscolha = scanner.nextInt();
                scanner.nextLine(); 
                
                TipoIngresso tipoIngresso;
                switch (tipoIngressoEscolha) {
                    case 2:
                        tipoIngresso = TipoIngresso.MEIA;
                        break;
                    default:
                        tipoIngresso = TipoIngresso.INTEIRA;
                }
                
                Ingresso ingresso = new Ingresso(precoBase, sessao, tipoIngresso, cadeira);
                float precoFinal = ingresso.calcularPreco();
                ingresso.setPreco(precoFinal);
                
                ingressos.add(ingresso);
            }
            
            // preço total dos ingressos na venda
            float precoTotalIngressos = 0;
            for (Ingresso ingresso : ingressos) {
                precoTotalIngressos += ingresso.getPreco();
            }
            venda.setPrecoTotal(precoTotalIngressos);
            
            // obter o preço final
            float precoTotal = venda.calcularPreco();
            
            System.out.println("Método de Pagamento:");
            System.out.println("1. Cartão");
            System.out.println("2. Dinheiro");
            System.out.println("3. PIX");
            System.out.print("Escolha o método: ");
            int metodoPagamentoEscolha = scanner.nextInt();
            scanner.nextLine(); 
            
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
        scanner.nextLine(); 
        
        if (escolhaSessao < 0 || escolhaSessao >= sessoes.size()) {
            System.out.println("Sessão inválida.");
            return;
        }
        
        Sessao sessao = sessoes.get(escolhaSessao);
        int disponibilidade = controladorVendas.consultarDisponibilidade(sessao);
        System.out.printf("Ingressos disponíveis: %d\n", disponibilidade);
    }
}