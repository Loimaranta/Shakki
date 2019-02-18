package shakki.grafiikka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Poyta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image sotilasV;
	private Image sotilasM;
	private Image torniV;
	private Image torniM;
	private Image lahettiV;
	private Image lahettiM;
	private Image ratsuV;
	private Image ratsuM;
	private Image kuningasV;
	private Image kuningasM;
	private Image kuningatarV;
	private Image kuningatarM;

	private Image lauta;

	private PiirtoAlusta p;

	public Poyta() {

		lauta = Toolkit.getDefaultToolkit().getImage("tausta.png");

		p = new PiirtoAlusta(lauta);

		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.add(p, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(1800, 1800));

		this.getContentPane().add(panel);

	}

}

class PiirtoAlusta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img;

	private int y = 1;
	private int x = 1;

	private int xx = 0;
	private int yy = 0;

	public PiirtoAlusta(Image img) {

		this.img = img;

		this.setBackground(Color.WHITE);

		this.setPreferredSize(new Dimension(100, 100));

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (x >= this.getWidth() - img.getWidth(this) || x <= 0) {

			xx = -xx;

		}

		if (y >= this.getHeight() - img.getHeight(this) || y <= 0) {

			yy = -yy;

		}

		x += xx;

		y += yy;

		g.drawImage(img, x, y, this);

	}
}