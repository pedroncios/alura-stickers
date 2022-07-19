import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    
    public void criar(InputStream stream, String nomeArquivo) throws Exception {

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(stream);

        // cria uma nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT); 

        // copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte
        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 0, novaAltura - 100);
        
        // escrever a nova imagem em um arquivo
        Files.createDirectories(Paths.get("saida"));
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }
}
