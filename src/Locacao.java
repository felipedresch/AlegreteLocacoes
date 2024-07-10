import java.util.Date;

public class Locacao {
    private String cnhCliente;
    private String placaVeiculo;
    private Date dataRetirada;
    private Date dataDevolucao;
    private double valorPago;

    public Locacao(String cnhCliente, String placaVeiculo, Date dataRetirada, Date dataDevolucao, double valorPago) {
        this.cnhCliente = cnhCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "cnhCliente='" + cnhCliente + '\'' +
                ", placaVeiculo='" + placaVeiculo + '\'' +
                ", dataRetirada=" + dataRetirada +
                ", dataDevolucao=" + dataDevolucao +
                ", valorPago=" + valorPago +
                '}';
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }
    public String getCnhCliente() {
        return cnhCliente;
    }

}
