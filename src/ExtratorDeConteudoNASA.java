import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoNASA implements ExtratorDeConteudo {
    
    public List<Conteudo> extrair(String json) {

        // extrair os dados do retorno
        JsonParser parser = new JsonParser();
        List<Map<String, String>> items = parser.parseUsingGson(json, List.class);

        List<Conteudo> conteudos = new ArrayList<>();
        for (Map<String, String> atributo : items) {
            
            String titulo = atributo.get("title").replaceAll("[^a-zA-Z0-9 ]", ""); // Remove os caracteres especiais
            String urlImagem = atributo.get("url");
            
            Conteudo conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
