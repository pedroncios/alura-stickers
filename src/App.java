import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // fazer a conexão HTTP com a API de filmes (top 250 filmes)
        // https://imdb-api.com/en/API/Top250Movies/ + SUA-API-KEY
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        URI endereco = URI.create(url);        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // extrair os dados do retorno (titulo, poster e avaliação)
        JsonParser parser = new JsonParser();
        //List<Map<String, String>> listaDeFilmes = parser.parseUsingRegex(body);
        ListaDeItens listaDeFilmes = parser.parseUsingGson(body);

        GeradorDeFigurinhas gerador = new GeradorDeFigurinhas();
        // exibir os dados
        for (Map<String,String> filme : listaDeFilmes.items) {
            
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            String rank = filme.get("rank");

            System.out.println("Gerando figurinha do filme: " + titulo + "...");

            InputStream stream = new URL(urlImagem).openStream();
            String nomeArquivo = rank + ".png";
            
            gerador.criar(stream, nomeArquivo);
        }
    }
}
