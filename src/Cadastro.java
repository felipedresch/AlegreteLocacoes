import exceptions.CategoriaInvalidaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Cadastro {
    static Scanner s = new Scanner(System.in);

    public static Cliente cadastroCliente(){
        System.out.println("Informe o nome do cliente: ");
        String nome = s.nextLine();
        System.out.println("Número da CNH: ");
        String cnh = s.nextLine();
        System.out.println("Telefone: ");
        String telefone = s.nextLine();
        System.out.println("CPF: ");
        String cpf = s.nextLine();
        return new Cliente(nome, cnh, telefone, cpf, false);
    }

    /***
     * Cria um novo objeto do tipo veiculo com as informações completas. Será armazenado na LDE veiculos
     * @param categorias LDE de categorias
     * @return Objeto a ser armazenado na LDE de veiculos
     * @throws CategoriaInvalidaException se a categoria informada não estiver cadastrada
     */
    public static Veiculo cadastroVeiculo(LDE <Categoria> categorias){
        System.out.println("Informe o número da placa do veículo: ");
        String placa = s.nextLine();
        System.out.println("Modelo: ");
        String modelo = s.nextLine();
        System.out.println("Marca: ");
        String marca = s.nextLine();
        System.out.println("Ano: ");
        int ano = s.nextInt();

        System.out.println("Potência: ");
        int potencia = s.nextInt();
        System.out.println("Número de lugares: ");
        int lugares = s.nextInt();

        System.out.println("Id da categoria: ");
        int idCategoria = s.nextInt();
        //Apenas para consertar um bug do java com nextInt()
        s.nextLine();

        //busca o id da categoria na LDE de categorias
        if (categorias.busca_atributo(categoria -> categoria.getIdentificador() == idCategoria) == null)
            throw new CategoriaInvalidaException("A categoria informada não existe!");

        return new Veiculo(placa, modelo, marca, ano, potencia, lugares, idCategoria);
    }

    public static Categoria cadastroCategoria(){
        System.out.println("Nome da categoria: ");
        String nome = s.nextLine();
        System.out.println("Id: ");
        int id = s.nextInt();
        //Apenas para consertar um bug do java com nextInt()
        s.nextLine();
        return new Categoria(id, nome);
    }

    /***
     * Constrói um objeto do tipo locação a partir da entrada do usuário.
     * @param clientes LDE de clientes
     * @return um objeto do tipo locacao com as informações completas
     * @throws RuntimeException caso o locador não esteja cadastrado
     */
    public static Locacao cadastroLocacao(LDE <Cliente> clientes) throws ParseException {
        System.out.println("Informe o número da CNH do locador: ");
        String cnhLocador = s.nextLine();
        System.out.println("Placa do veículo locado: ");
        String placa = s.nextLine();

        System.out.println("Data de retirada: ");
        String retirada = s.nextLine();
        System.out.println("Data de devolução: ");
        String devolucao = s.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        //variáveis formatadas para o tipo Date
        Date retiradaF = formatter.parse(retirada);
        Date devolucaoF = formatter.parse(devolucao);
        System.out.println("Valor a ser pago: ");
        double valor = s.nextDouble();
        //Apenas para consertar um bug do java com nextInt()
        s.nextLine();

        //Verifica se o cliente existe
        Cliente clienteEncontrado = clientes.busca_atributo(cliente -> cliente.getCnh().equals(cnhLocador));
        if (clienteEncontrado == null)
            throw new RuntimeException("Cliente não existente!");

        return new Locacao(cnhLocador, placa, retiradaF, devolucaoF, valor);
    }
}
