import java.io.BufferedReader;
import java.io.FileReader;

public class LeituraArquivo {
    BufferedReader br = null;
    String linha = "";
    String caminho;

    /**
     * Lê o arquivo csv de veículos
     * @return Uma lista duplamente encadeada, na qual cada nodo armazena um objeto veículo
     */
    public LDE <Veiculo> lerArqVeiculos() {
        caminho = "Marco1/Trabalho1/src/resources/Veiculos.csv";
        try {
            br = new BufferedReader(new FileReader(caminho));
            LDE <Veiculo> veiculos = new LDE<>();
            boolean header = true;
            while ((linha = br.readLine()) != null) {
                //pula a primeira linha do csv (cabeçalho)
                if (header){
                    header = false;
                    continue;
                }

                String[] palavras = linha.split(";");
                Veiculo veiculo = new Veiculo(
                        palavras[0],
                        palavras[1],
                        palavras[2],
                        Integer.parseInt(palavras[3]),
                        Integer.parseInt(palavras[4]),
                        Integer.parseInt(palavras[5]),
                        Integer.parseInt(palavras[6]));
                veiculos.insereInicio(veiculo);
            }
            return veiculos;

        } catch (Exception e) {
          e.getMessage();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }

    /**
     * Lê o arquivo csv de categorias
     * @return Uma lista duplamente encadeada, na qual cada nodo armazena um objeto categoria
     */
    public LDE <Categoria> lerArqCategorias() {
        caminho = "Marco1/Trabalho1/src/resources/Categorias.csv";

        try {
            br = new BufferedReader(new FileReader(caminho));
            LDE <Categoria> categorias = new LDE<>();
            boolean header = true;
            while ((linha = br.readLine()) != null) {
                //pula a primeira linha do csv (cabeçalho)
                if (header){
                    header = false;
                    continue;
                }
                Categoria categoria;
                String[] palavras = linha.split(";");
                categoria = new Categoria(Integer.parseInt(palavras[0]), palavras[1]);
                categorias.insereInicio(categoria);
            }
            return categorias;

        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
        return null;
    }
}
