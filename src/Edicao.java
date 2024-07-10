import java.util.Scanner;

public class Edicao {
    static Scanner s = new Scanner(System.in);

    /***
     * Recebe o CPF do cliente e o busca da lista para podermos alterar seus atributos. Depois de
     * alterados, adicionamos o cliente novo e o antigo em um vetor que será retornado, tornando
     * possível a exclusão do cliente antigo e a adição do novo na lista de clientes da main.
     * @param clientes lista de clientes
     * @return um vetor com o cliente atualizado na primeira posição e o cliente antigo na segunda
     */
    public static Cliente[] editarCliente(LDE <Cliente> clientes){
        System.out.println("Informe o CPF do cliente que deseja editar: ");
        String cpf = s.nextLine();
        Cliente cliente = clientes.busca_atributo(cliente1 -> cliente1.getCpf().equals(cpf));
        //TODO: custom exception
        if (cliente == null) throw new RuntimeException("Cliente nao encontrado!");

        System.out.println("Qual informação de cliente você deseja atualizar?");
        System.out.println("1 - CPF\n2 - CNH\n3 - Nome\n4 - Telefone\n-> ");
        int info = s.nextInt();
        //Apenas para consertar um bug do java com nextInt()
        s.nextLine();

        Cliente[] clientesReturn = new Cliente[2];
        clientesReturn[1] = cliente;
        Cliente clienteNovo;

        switch (info){
            case 1 -> {
                System.out.println("Informe o novo CPF: ");
                String cpfNovo = s.nextLine();
                clienteNovo = new Cliente(cliente.getNome(), cliente.getCnh(), cliente.getTelefone(), cpfNovo, cliente.isLocacaoAtiva());
                clientesReturn[0] = clienteNovo;
                return clientesReturn;
            }
            case 2 -> {
                System.out.println("Informe a nova CNH: ");
                String cnhNova = s.nextLine();
                clienteNovo = new Cliente(cliente.getNome(), cnhNova, cliente.getTelefone(), cliente.getCpf(), cliente.isLocacaoAtiva());
                clientesReturn[0] = clienteNovo;
                return clientesReturn;
            }
            case 3 -> {
                System.out.println("Informe o novo nome: ");
                String nome = s.nextLine();
                clienteNovo = new Cliente(nome, cliente.getCnh(), cliente.getTelefone(), cliente.getCpf(), cliente.isLocacaoAtiva());
                clientesReturn[0] = clienteNovo;
                return clientesReturn;
            }
            case 4 -> {
                System.out.println("Informe o novo telefone: ");
                String telefone = s.nextLine();
                clienteNovo = new Cliente(cliente.getNome(), cliente.getCnh(), telefone, cliente.getCpf(), cliente.isLocacaoAtiva());
                clientesReturn[0] = clienteNovo;
                return clientesReturn;
            }
            default -> System.out.println("Opção inválida!");
        }

        return new Cliente[2];
    }

    public static Veiculo[] editarVeiculo(LDE <Veiculo> veiculos){
        System.out.println("Informe a placa do veículo que deseja editar: ");
        String placa = s.nextLine();
        Veiculo veiculo = veiculos.busca_atributo(veiculo1 -> veiculo1.getPlaca().equals(placa));

        if (veiculo == null) throw new RuntimeException("Veículo não encontrado!");

        System.out.println("Qual informação do veículo você deseja atualizar?");
        System.out.println("1 - Placa\n2 - Modelo\n3 - Ano\n4 - Potência\n5 - Lugares\n6 - Marca\n7 - Categoria\n-> ");

        int info = s.nextInt();
        s.nextLine();

        Veiculo[] veiculosReturn = new Veiculo[2];
        veiculosReturn[1] = veiculo;
        Veiculo veiculoNovo;

        switch (info){
            case 1 -> {
                System.out.println("Informe a nova Placa: ");
                String placaNova = s.nextLine();
                veiculoNovo = new Veiculo(placaNova, veiculo.getModelo(), veiculo.getMarca(), veiculo.getAno(), veiculo.getPotencia(), veiculo.getLugares(), veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 2 -> {
                System.out.println("Informe o novo modelo: ");
                String modeloNovo = s.nextLine();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), modeloNovo, veiculo.getMarca(), veiculo.getAno(), veiculo.getPotencia(), veiculo.getLugares(), veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 3 -> {
                System.out.println("Informe a nova marca: ");
                String marcaNova = s.nextLine();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), veiculo.getModelo(), marcaNova, veiculo.getAno(), veiculo.getPotencia(), veiculo.getLugares(), veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 4 -> {
                System.out.println("Informe o novo ano: ");
                int novoAno = s.nextInt();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca(), novoAno, veiculo.getPotencia(), veiculo.getLugares(), veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 5 -> {
                System.out.println("Informe a nova potência : ");
                int novaPotencia = s.nextInt();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getAno(), novaPotencia, veiculo.getLugares(), veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 6 -> {
                System.out.println("Informe o novo número de lugares : ");
                int novoLugares = s.nextInt();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getAno(), veiculo.getPotencia(), novoLugares, veiculo.getCategoria());
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            case 7 -> {
                System.out.println("Informe a nova categoria: ");
                int novaCategoria = s.nextInt();
                veiculoNovo = new Veiculo(veiculo.getPlaca(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getAno(), veiculo.getPotencia(), veiculo.getLugares(), novaCategoria);
                veiculosReturn[0] = veiculoNovo;
                return veiculosReturn;
            }
            default ->{
                System.out.println("Opção inválida!");
            }

        }
        return new Veiculo[2];
    }

    public static Categoria[] editarCategoria(LDE <Categoria> categorias){
        System.out.println("Informe o identificador da categoria que deseja editar: ");
        int identificador = s.nextInt();
        Categoria categoria = categorias.busca_atributo(categoria1 -> categoria1.getIdentificador() == identificador);

        if (categoria == null) throw new RuntimeException("Categoria não encontrada!");

        System.out.println("Qual informação da categoria você deseja atualizar?");
        System.out.println("1 - Identificador\n2 - Nome\n-> ");

        int info = s.nextInt();
        s.nextLine();

        Categoria[] categoriasReturn = new Categoria[2];
        categoriasReturn[1] = categoria;
        Categoria categoriaNova;

        switch (info){
            case 1 -> {
                System.out.println("Informe o novo Identificador: ");
                int identificadorNovo = s.nextInt();
                categoriaNova = new Categoria(identificadorNovo, categoria.getNome());
                categoriasReturn[0] = categoriaNova;
                return categoriasReturn;
            }
            case 2 -> {
                System.out.println("Informe o novo nome: ");
                String nomeNovo = s.nextLine();
                categoriaNova = new Categoria(categoria.getIdentificador(), nomeNovo);
                categoriasReturn[0] = categoriaNova;
                return categoriasReturn;
            }
            default ->{
                System.out.println("Opção inválida!");
            }
        }
        return new Categoria[2];
    }

}