import exceptions.CategoriaInvalidaException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LeituraArquivo leitura = new LeituraArquivo();
        LDE<Veiculo> veiculosDisponiveis = leitura.lerArqVeiculos();
        LDE<Veiculo> veiculosLocados = new LDE<>();
        LDE<Categoria> categorias = leitura.lerArqCategorias();
        LDE<Cliente> clientes = new LDE<>();
        LDE<Locacao> locacoes = new LDE<>();
        String opcao;
        boolean fim = false;

        do {
            System.out.println("ALEGRETE LOCAÇÕES\nO que você deseja acessar?");
            System.out.println("""
                    1 - Cadastrar
                    2 - Excluir
                    3 - Editar
                    4 - Listar
                    5 - Finalizar programa
                    ->\s""");
            opcao = s.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.println("\nO que deseja cadastrar?");
                    System.out.println("""
                            1 - Cliente
                            2 - Veículo
                            3 - Categoria
                            4 - Locação
                            5 - Finalizar programa
                            Sua resposta: \s""");
                    String cadastrar = s.nextLine();
                    switch (cadastrar) {
                        case "1" -> {
                            clientes.insereInicio(Cadastro.cadastroCliente());
                            System.out.println("Cliente cadastrado com sucesso!\n");
                        }
                        case "2" -> {
                            try {
                                veiculosDisponiveis.insereInicio(Cadastro.cadastroVeiculo(categorias));
                                System.out.println("Veículo cadastrado com sucesso!\n");
                            } catch (CategoriaInvalidaException e) {
                                System.out.println(e.getMessage() + "\n");
                            }
                        }
                        case "3" -> {
                            Categoria cat = Cadastro.cadastroCategoria();
                            categorias.insereInicio(cat);
                            System.out.println("Categoria cadastrada com sucesso!\n");
                        }
                        case "4" -> {
                            try {
                                System.out.println("Para visualizar os carros disponíveis para locação, você deseja: ");
                                System.out.println("""
                                        1 - Filtrar veiculos
                                        2 - Visualizar em ordem crescente (Por categoria)
                                        3 - Visualizar em ordem decrescente (Por Categoria)
                                        -> \s""");
                                String opcaoVisualizacao = s.nextLine();
                                switch (opcaoVisualizacao) {
                                    case "1" -> {
                                        // vai filtrar de acordo com as preferencias
                                        System.out.println("Informe a potência mínima desejada: ");
                                        int potenciaMinima = s.nextInt();
                                        s.nextLine();
                                        System.out.println("Informe o número mínimo de lugares desejado: ");
                                        int lugaresMinimos = s.nextInt();
                                        s.nextLine();
                                        System.out.println("Deseja filtrar por categoria? S/N: ");
                                        String opcaoCategoria = s.nextLine();
                                        if (opcaoCategoria.equalsIgnoreCase("s")) {
                                            System.out.println("Informe o ID da categoria desejada: ");
                                            int categoria = s.nextInt();
                                            s.nextLine();

                                            LDE<Veiculo> veiculosFiltrados = filtrarVeiculos(veiculosDisponiveis, potenciaMinima, lugaresMinimos, categoria);
                                            veiculosFiltrados.imprimir();
                                        } else if (opcaoCategoria.equalsIgnoreCase("n")) {
                                            LDE<Veiculo> veiculosFiltrados = filtrarVeiculos(veiculosDisponiveis, potenciaMinima, lugaresMinimos, null);
                                            veiculosFiltrados.imprimir();
                                        }
                                    }
                                    case "2" ->
                                        // como a lista ja esta ordenada(Pela professora) basta printar ela normal que saira em ordem crescente
                                            veiculosDisponiveis.imprimirReverso();
                                    case "3" ->
                                        // como a lista ja esta ordenada(Pela professora) basta printar reverso que decrescente
                                            veiculosDisponiveis.imprimir();
                                }
                                Locacao loc = Cadastro.cadastroLocacao(clientes);
                                String placa = loc.getPlacaVeiculo();
                                Veiculo v = veiculosDisponiveis.busca_atributo(veiculo -> veiculo.getPlaca().equals(placa));
                                if (v == null){
                                    throw new RuntimeException("Veículo não existe, locação não cadastrada!");
                                }
                                locacoes.insereInicio(loc);
                                //remove o veiculo da lista de disponiveis e adiciona em veiculos locados
                                veiculosDisponiveis.remove(v);
                                veiculosLocados.insereInicio(v);
                                //Atualiza o cliente para ficar atrelado a uma locacao
                                String cnhCliente = loc.getCnhCliente();
                                Cliente c = clientes.busca_atributo(cliente -> cliente.getCnh().equals(cnhCliente));
                                clientes.remove(c);
                                c.setLocacaoAtiva(true);
                                clientes.insereInicio(c);

                                System.out.println("Locação cadastrada com sucesso!\n");
                            } catch (ParseException | RuntimeException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        case "5" -> {
                            System.out.println("Saindo..");
                            fim = true;
                        }
                        default -> System.out.println("Opção inválida!\n");
                    }
                }
                case "2" -> {
                    System.out.println("\nO que deseja excluir?");
                    System.out.println("""
                            1 - Cliente
                            2 - Veículo
                            3 - Categoria
                            4 - Locação
                            5 - Finalizar programa
                            Sua resposta: \s""");
                    String excluir = s.nextLine();
                    switch (excluir) {
                        case "1" -> {
                            System.out.println("Digite o CPF do cliente a ser removido: ");
                            String cpf = s.nextLine();
                            Cliente clienteEncontrado = clientes.busca_atributo(cliente -> cliente.getCpf().equals(cpf));

                            if (clienteEncontrado != null &&!clienteEncontrado.isLocacaoAtiva()) {
                                clientes.remove(clienteEncontrado);
                                System.out.println("Cliente removido com sucesso!\n");
                                break;
                            }
                            System.out.println("Cliente não existe ou está com alguma locação ativa\n!");
                        }
                        case "2" -> {
                            System.out.println("Digite a placa do veículo a ser removido: ");
                            String placa = s.nextLine();
                            Veiculo veiculoEncontrado = veiculosDisponiveis.busca_atributo(veiculo -> veiculo.getPlaca().equals(placa));

                            if (veiculoEncontrado != null) {
                                veiculosDisponiveis.remove(veiculoEncontrado);
                                System.out.println("Veículo removido com sucesso!");
                                break;
                            }
                            System.out.println("Veículo não encontrado!");
                        }
                        case "3" -> {
                            System.out.println("Digite o identificador da categoria a ser removida: ");
                            int identificador = s.nextInt();
                            Categoria categoriaEncontrada = categorias.busca_atributo(categoria1 -> categoria1.getIdentificador() == identificador);

                            if (categoriaEncontrada != null) {
                                boolean categoriaEmUso = false;
                                Noh atual = veiculosDisponiveis.getInicio();
                                while (atual != null) {
                                    Veiculo veiculo = (Veiculo) atual.getInfo();
                                    if (veiculo.getCategoria() == identificador) {
                                        categoriaEmUso = true;
                                        break;
                                    }
                                    atual = atual.getProximo();
                                }
                                if (!categoriaEmUso) {
                                    atual = veiculosLocados.getInicio();
                                    while (atual != null) {
                                        Veiculo veiculo = (Veiculo) atual.getInfo();
                                        if (veiculo.getCategoria() == identificador) {
                                            categoriaEmUso = true;
                                            break;
                                        }
                                        atual = atual.getProximo();
                                    }
                                }

                                if (categoriaEmUso) {
                                    System.out.println("Não é possível excluir a categoria pois ela está atrelada a pelo menos um veículo");
                                } else {
                                    categorias.remove(categoriaEncontrada);
                                    System.out.println("A categoria foi removida com sucesso!");
                                }
                            }else{
                                System.out.println("Categoria inválida!");
                            }
                        }

                        case "4" -> {
                            System.out.println("Digite a placa do veículo locado: ");
                            String placa = s.nextLine();
                            Locacao locacaoEncontrada = locacoes.busca_atributo(locacoes1 -> locacoes1.getPlacaVeiculo().equals(placa));

                            if (locacaoEncontrada != null){
                                locacoes.remove(locacaoEncontrada);
                                //atualizar locacaoAtiva
                                String cnhLocador = locacaoEncontrada.getCnhCliente();
                                Cliente locador = clientes.busca_atributo(cliente -> cliente.getCnh().equals(cnhLocador));
                                if (locador != null){
                                    clientes.remove(locador);
                                    locador.setLocacaoAtiva(false);
                                    clientes.insereInicio(locador);
                                }
                                System.out.println("A locação foi removida com sucesso!\n");

                            }else{
                                System.out.println("Locação não encontrada para o veículo com a placa" + placa + ". Verifique a placa informada.\n");
                            }
                        }
                        case "5" -> {
                            System.out.println("Saindo..");
                            fim = true;
                        }
                        default -> System.out.println("Opção inválida!\n");
                    }
                }
                case "3" -> {
                    System.out.println("\nO que deseja editar?");
                    System.out.println("""
                            1 - Cliente
                            2 - Veículo
                            3 - Categoria
                            4 - Finalizar programa
                            Sua resposta: \s""");
                    String editar = s.nextLine();
                    switch (editar) {
                        case "1" -> {
                            try {
                                Cliente[] clientesEdicao = Edicao.editarCliente(clientes);
                                Cliente cliente_atualizado = clientesEdicao[0];
                                Cliente cliente_antigo = clientesEdicao[1];
                                if (cliente_atualizado != null && cliente_antigo != null) {
                                    clientes.remove(cliente_antigo);
                                    clientes.insereInicio(cliente_atualizado);
                                    System.out.println("Sucesso!\n");
                                } else
                                    System.out.println("Algo deu errado!\n");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage() + "\n");
                            }
                        }
                        case "2" -> {
                            try {
                                Veiculo[] veiculosEdicao = Edicao.editarVeiculo(veiculosDisponiveis);
                                Veiculo veiculo_atualizado = veiculosEdicao[0];
                                Veiculo veiculo_antigo = veiculosEdicao[1];
                                if (veiculo_atualizado != null && veiculo_antigo != null) {
                                    veiculosDisponiveis.remove(veiculo_antigo);
                                    veiculosDisponiveis.insereInicio(veiculo_atualizado);
                                    System.out.println("Sucesso!\n");
                                } else
                                    System.out.println("Algo deu errado!\n");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage() + "\n");
                            }
                        }
                        case "3" -> {
                            try {
                                Categoria[] categoriasEdicao = Edicao.editarCategoria(categorias);
                                Categoria categoria_atualizada = categoriasEdicao[0];
                                Categoria categoria_antiga = categoriasEdicao[1];
                                if (categoria_atualizada != null && categoria_antiga != null) {
                                    categorias.remove(categoria_antiga);
                                    categorias.insereInicio(categoria_atualizada);
                                    System.out.println("Sucesso!\n");
                                } else
                                    System.out.println("Algo deu errado!\n");
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage() + "\n");
                            }
                        }

                        case "4" -> {
                            System.out.println("Saindo..");
                            fim = true;
                        }
                        default -> System.out.println("Opção inválida!\n");
                    }
                }
                case "4" -> {
                    System.out.println("\nO que deseja listar?");
                    System.out.println("""
                            1 - Clientes
                            2 - Veículos
                            3 - Categorias
                            4 - Locações
                            5 - Finalizar programa
                            -> \s""");
                    String listar = s.nextLine();
                    switch (listar) {
                        case "1" -> {
                            System.out.println("1 - Impressão normal\n2 - Impressão reversa\n3 - Sair\n-> ");
                            String opcaoClientes = s.nextLine();
                            switch (opcaoClientes) {
                                case "1" -> {
                                    if (!clientes.isVazia()) {
                                        clientes.imprimir();
                                        System.out.println();
                                    }
                                    else System.out.println("Não há clientes cadastrados!\n");
                                }
                                case "2" -> {
                                    if (!clientes.isVazia()) {
                                        clientes.imprimirReverso();
                                        System.out.println();
                                    }
                                    else System.out.println("Não há clientes cadastrados!\n");
                                }
                                case "3" -> {
                                    System.out.println("Saindo..\n");
                                    fim = true;
                                }
                                default -> System.out.println("Opção inválida!\n");
                            }
                        }
                        case "2" -> {
                            System.out.println("1 - Impressão normal\n2 - Impressão reversa\n3 - Sair\n-> ");
                            String opcaoVeiculos = s.nextLine();
                            switch (opcaoVeiculos) {
                                case "1" -> {
                                    if (!veiculosDisponiveis.isVazia()) veiculosDisponiveis.imprimir();
                                    if (!veiculosLocados.isVazia()) veiculosLocados.imprimir();
                                    if (veiculosDisponiveis.isVazia() && veiculosLocados.isVazia())
                                        System.out.println("Não há veículos para mostrar!\n");
                                    System.out.println();
                                }
                                case "2" -> {
                                    if (!veiculosDisponiveis.isVazia()) veiculosDisponiveis.imprimirReverso();
                                    if (!veiculosLocados.isVazia()) veiculosLocados.imprimirReverso();
                                    if (veiculosDisponiveis.isVazia() && veiculosLocados.isVazia())
                                        System.out.println("Não há veículos para mostrar!\n");
                                    System.out.println();
                                }
                                case "3" -> {
                                    System.out.println("Saindo..\n");
                                    fim = true;
                                }
                                default -> System.out.println("Opção inválida!\n");
                            }
                        }
                        case "3" -> {
                            if (!categorias.isVazia()) {
                                categorias.imprimir();
                                System.out.println();
                            } else {
                                System.out.println("Não há categorias cadastradas!\n");
                            }
                        }
                        case "4" -> {
                            if (!locacoes.isVazia()) {
                                locacoes.imprimir();
                                System.out.println();
                            } else {
                                System.out.println("Não há nenhuma locação ativa!\n");
                            }
                        }
                        case "5" -> {
                            System.out.println("Saindo..");
                            fim = true;
                        }
                        default -> System.out.println("Opção inválida!\n");
                    }
                }
                case "5" -> {
                    System.out.println("Saindo..");
                    fim = true;
                }
                default -> System.out.println("Opção inválida!\n");
            }
        } while (!fim);


    }

    public static LDE<Veiculo> filtrarVeiculos(LDE<Veiculo> veiculos, int potenciaMinima, int lugaresMinimos, Integer categoria){
        LDE<Veiculo> veiculosFiltrados = new LDE<>();

        Noh atual = veiculos.getInicio();
        while (atual != null) {
            Veiculo veiculo = (Veiculo) atual.getInfo();

            if (veiculo.getPotencia() >= potenciaMinima &&
                    veiculo.getLugares() >= lugaresMinimos &&
                    (categoria == null || veiculo.getCategoria() == categoria)) {
                veiculosFiltrados.insereFim(veiculo);
            }
            atual = atual.getProximo();
        }
        return veiculosFiltrados;
    }

}
