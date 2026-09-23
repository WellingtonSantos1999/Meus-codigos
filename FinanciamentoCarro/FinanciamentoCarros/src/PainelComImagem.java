import javax.swing.*;
import java.awt.*;

public class PainelComImagem extends JPanel {

    private Image imagem;

    public PainelComImagem(String caminho) {

        imagem = new ImageIcon(
                getClass().getResource(caminho)
        ).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(
                imagem,
                0,
                0,
                getWidth(),
                getHeight(),
                this
        );
    }
}