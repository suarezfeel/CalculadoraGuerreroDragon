package calculadorav1;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CalculadoraV1
{
    // Panel
    private JPanel pnlCalculadora;

    // Campo de texto
    private JTextField txtOperacion;

    // Números
    private JButton btnCero;
    private JButton btnUno;
    private JButton btnDos;
    private JButton btnTres;
    private JButton btnCuatro;
    private JButton btnCinco;
    private JButton btnSeis;
    private JButton btnSiete;
    private JButton btnOcho;
    private JButton btnNueve;

    // Operadores básicos
    private JButton btnSuma;
    private JButton btnResta;
    private JButton btnMultiplicacion;
    private JButton btnDivision;
    private JButton btnIgual;

    // Funciones y auxiliares
    private JButton btnAC;
    private JButton btnMasMenos;
    private JButton btnPorcentaje;
    private JButton btnComa;
    private JButton btnCalculadora;

    private void createUIComponents()
    {
        btnCero = new BotonRedondeado(25);
        btnUno = new BotonRedondeado(25);
        btnDos = new BotonRedondeado(25);
        btnTres = new BotonRedondeado(25);
        btnCuatro = new BotonRedondeado(25);
        btnCinco = new BotonRedondeado(25);
        btnSeis = new BotonRedondeado(25);
        btnSiete = new BotonRedondeado(25);
        btnOcho = new BotonRedondeado(25);
        btnNueve = new BotonRedondeado(25);

        btnSuma = new BotonRedondeado(25);
        btnResta = new BotonRedondeado(25);
        btnMultiplicacion = new BotonRedondeado(25);
        btnDivision = new BotonRedondeado(25);
        btnIgual = new BotonRedondeado(25);

        btnAC = new BotonRedondeado(25);
        btnMasMenos = new BotonRedondeado(25);
        btnPorcentaje = new BotonRedondeado(25);
        btnComa = new BotonRedondeado(25);
        btnCalculadora = new BotonRedondeado(25);
    }

    public class BotonRedondeado extends JButton
    {
        private final int radio;
        private Shape forma;

        public BotonRedondeado()
        {
            this(25); // Radio por defecto para el diseñador visual
        }

        public BotonRedondeado(int radio)
        {
            this.radio = radio;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }

            // Toma el lado menor entre ancho y alto para que siempre sea un círculo y lo centra
            int diametro = Math.min(getWidth(), getHeight()) - 1;
            int x = (getWidth() - diametro) / 2;
            int y = (getHeight() - diametro) / 2;

            forma = new Ellipse2D.Float(x, y, diametro, diametro);
            g2.fill(forma);
            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            int diametro = Math.min(getWidth(), getHeight()) - 1;
            int posX = (getWidth() - diametro) / 2;
            int posY = (getHeight() - diametro) / 2;

            if (forma == null || !forma.getBounds().equals(new java.awt.Rectangle(posX, posY, diametro, diametro))) {
                forma = new Ellipse2D.Float(posX, posY, diametro, diametro);
            }
            return forma.contains(x, y);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CalculadoraV1");
        frame.setContentPane(new CalculadoraV1().pnlCalculadora);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}