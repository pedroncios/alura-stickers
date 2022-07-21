import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        // API de filmes IMDB (top 250 filmes)
        // https://imdb-api.com/en/API/Top250Movies/ + SUA-API-KEY
        //String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        //ExtratorDeConteudoIMDB extrator = new ExtratorDeConteudoIMDB();

        // API da NASA
        // https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        ExtratorDeConteudoNASA extrator = new ExtratorDeConteudoNASA();

        // fazer a conexão HTTP com a API
        ClienteHttp http = new ClienteHttp();
        // Resgata o json (retorno da API)
        String json = http.buscaDados(url);        
        // Extrai as informações do JSON transformando em objeto (List<Conteudo>)
        List<Conteudo> conteudos = extrator.extrair(json);

        // Cria o gerador de figurinhas
        GeradorDeFigurinhas gerador = new GeradorDeFigurinhas();
        
        // Gera as figurinhas
        for (Conteudo conteudo : conteudos) {
            
            String titulo = conteudo.getTitulo();
            String urlImagem = conteudo.getUrlImagem();
            
            System.out.println("Gerando figurinha: " + titulo + "...");

            InputStream stream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";            
            gerador.criar(stream, nomeArquivo);
        }
    }
}
