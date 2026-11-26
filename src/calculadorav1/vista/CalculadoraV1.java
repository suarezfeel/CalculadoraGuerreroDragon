package calculadorav1.vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
public class CalculadoraV1
{

    // Boolean modo claro/oscuro
    private boolean modoOscuro = false;

    // Panel
    public JPanel pnlCalculadora;

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
    private JButton btnHistorial;
    private JButton btnHuevo;
    private JButton btnEstilos;

    public double resultado=0;
    public String operacionRealizar = null;
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
        btnHistorial = new BotonRedondeado(25);

        btnEstilos = new BotonRedondeado(25);
        btnHuevo = new BotonRedondeado(25);
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
    public void agregarOperacion(String operacion){
        String textoActual = txtOperacion.getText();
        if(textoActual.isEmpty() || textoActual.endsWith("+") || textoActual.endsWith("-") || textoActual.endsWith("/") || textoActual.endsWith("x") || textoActual.endsWith("%")){
            JOptionPane.showMessageDialog(null,"colocaste un operador matematico sin haber puesto ningun numero, intenta nuevamente", "información", JOptionPane.WARNING_MESSAGE);
        }
        operacionRealizar = operacion;
        txtOperacion.setText(textoActual + operacion);
    }

    public CalculadoraV1(){
        txtOperacion.setBorder(null);
        txtOperacion.setEditable(false);
        txtOperacion.setFocusable(false);
        // Listeners

        btnEstilos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                modoOscuro = !modoOscuro;
                if  (modoOscuro) {
                    // Definición de colores Modo Claro
                    Color colorFondoPanel = new Color(242, 242, 247);

                    Color colorFondoNumerosPantalla = new Color(242,242,247);
                    Color colorLetraNumerosPantalla = new Color(28,28,30);

                    Color colorFondoNumerosBoton = new Color(254,254,254);
                    Color colorLetraNumerosBoton = new Color(28,28,30);

                    Color colorFondoFuncionesSuperiores = new Color(209,209,214);
                    Color colorLetraFuncionesSuperiores = new Color(28,28,30);

                    Color colorFondoOperadores = new Color(254,149,0);
                    Color colorLetraOperadores = new Color(254,254,254);

                    Color colorFondoBotonesSuperiores = new Color(44,44,46);
                    Color colorLetraBotonesSuperiores = new Color(254,254,254);

                    // Asignación de colores
                    pnlCalculadora.setBackground(colorFondoPanel);

                    txtOperacion.setBackground(colorFondoNumerosPantalla);
                    txtOperacion.setForeground(colorLetraNumerosPantalla);

                    JButton[] botonesNumeros = {
                            btnCero, btnUno, btnDos, btnTres, btnCuatro,
                            btnCinco, btnSeis, btnSiete, btnOcho, btnNueve,
                            btnComa, btnHistorial
                    };
                    for (JButton btn : botonesNumeros) {
                        btn.setBackground(colorFondoNumerosBoton);
                        btn.setForeground(colorLetraNumerosBoton);
                    }

                    JButton[] botonesFunciones = { btnAC, btnMasMenos, btnPorcentaje };
                    for (JButton btn : botonesFunciones) {
                        btn.setBackground(colorFondoFuncionesSuperiores);
                        btn.setForeground(colorLetraFuncionesSuperiores);
                    }

                    JButton[] botonesOperadores = {
                            btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual
                    };
                    for (JButton btn : botonesOperadores) {
                        btn.setBackground(colorFondoOperadores);
                        btn.setForeground(colorLetraOperadores);
                    }

                    JButton[] botonesSuperiores = { btnHuevo, btnEstilos };
                    for (JButton btn : botonesSuperiores) {
                        btn.setBackground(colorFondoBotonesSuperiores);
                        btn.setForeground(colorLetraBotonesSuperiores);
                    }
                }
                else {
                    // Definición de colores Modo Oscuro
                    Color colorFondoPanel = new Color(28, 28, 30);

                    Color colorFondoNumerosPantalla = new Color(28, 28, 30);
                    Color colorLetraNumerosPantalla = new Color(254, 254, 254);

                    Color colorFondoNumerosBoton = new Color(58, 58, 60);
                    Color colorLetraNumerosBoton = new Color(254, 254, 254);

                    Color colorFondoFuncionesSuperiores = new Color(0, 0, 0);
                    Color colorLetraFuncionesSuperiores = new Color(254, 254, 254);

                    Color colorFondoOperadores = new Color(255, 149, 0);
                    Color colorLetraOperadores = new Color(255, 255, 255);

                    Color colorFondoBotonesSuperiores = new Color(51, 51, 51);
                    Color colorLetraBotonesSuperiores = new Color(0, 0, 0);

                    // Asignación de colores
                    pnlCalculadora.setBackground(colorFondoPanel);

                    txtOperacion.setBackground(colorFondoNumerosPantalla);
                    txtOperacion.setForeground(colorLetraNumerosPantalla);

                    JButton[] botonesNumeros = {
                            btnCero, btnUno, btnDos, btnTres, btnCuatro,
                            btnCinco, btnSeis, btnSiete, btnOcho, btnNueve,
                            btnComa, btnHistorial
                    };
                    for (JButton btn : botonesNumeros) {
                        btn.setBackground(colorFondoNumerosBoton);
                        btn.setForeground(colorLetraNumerosBoton);
                    }

                    JButton[] botonesFunciones = { btnAC, btnMasMenos, btnPorcentaje };
                    for (JButton btn : botonesFunciones) {
                        btn.setBackground(colorFondoFuncionesSuperiores);
                        btn.setForeground(colorLetraFuncionesSuperiores);
                    }

                    JButton[] botonesOperadores = {
                            btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual
                    };
                    for (JButton btn : botonesOperadores) {
                        btn.setBackground(colorFondoOperadores);
                        btn.setForeground(colorLetraOperadores);
                    }

                    JButton[] botonesSuperiores = {btnHuevo, btnEstilos };
                    for (JButton btn : botonesSuperiores) {
                        btn.setBackground(colorFondoBotonesSuperiores);
                        btn.setForeground(colorLetraBotonesSuperiores);
                    }
                }
            }
        });
        btnUno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "1");
            }
        });
        btnDos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "2");
            }
        });
        btnTres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "3");
            }
        });
        btnCuatro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "4");
            }
        });
        btnCinco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "5");
            }
        });
        btnSeis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "6");
            }
        });
        btnSiete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "7");
            }
        });
        btnOcho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "8");
            }
        });
        btnNueve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "9");
            }
        });
        btnCero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOperacion.setText(txtOperacion.getText() + "0");
            }
        });

        btnAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operacionRealizar =null;
                txtOperacion.setText("");
            }
        });
        btnSuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOperacion("+");
            }
        });
        btnResta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOperacion("-");
            }
        });
        btnMultiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOperacion("x");
            }
        });
        btnDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOperacion("/");
            }
        });
        btnPorcentaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarOperacion("%");
            }
        });

        btnIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = txtOperacion.getText();
                try{
                    String[] separarOperacion = texto.split("(?=[+\\-x/%])|(?<=[+\\-x/%])");
                    double total = Double.parseDouble(separarOperacion[0]);

                    for(int i= 1; i<separarOperacion.length; i +=2){
                        String operacion = separarOperacion[i];
                        double numeroOperado = Double.parseDouble(separarOperacion[i+1]);

                        switch (operacion){
                            case "+":
                                total += numeroOperado;
                                break;
                            case "-":
                                total -= numeroOperado;
                                break;
                            case "x":
                                total *= numeroOperado;
                                break;
                            case "/":
                                try{
                                    if(numeroOperado == 0){
                                        throw new ArithmeticException();
                                    }
                                    total /= numeroOperado;
                                }catch( ArithmeticException exception){
                                    JOptionPane.showMessageDialog(null, "no puedes dividir entre cero", "error dividir", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            case "%":
                                try{
                                    if(numeroOperado == 0){
                                        throw new ArithmeticException();
                                    }
                                    total %= numeroOperado;
                                }catch( ArithmeticException exception){
                                    JOptionPane.showMessageDialog(null, "no se puede calcular un residuo de cero", "error residuo", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                        }
                    }
                    resultado = total;
                    txtOperacion.setText(String.valueOf(total));
                } catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "trataste de hacer una operacion sin usar ningun numero despues del operador matematico", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnHuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(pnlCalculadora);
                Dimension tamanoCalculadora = frameActual.getSize();
                frameActual.dispose();

                // 2. Crear easter con ese mismo tamaño fijo
                JFrame frameEaster = new JFrame("Easter");
                frameEaster.setContentPane(new easter().panel1);
                frameEaster.setSize(tamanoCalculadora); // Mismo ancho y alto que la calculadora
                frameEaster.setResizable(false);         // Impide que el usuario estire la ventana
                frameEaster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameEaster.setLocationRelativeTo(null);
                frameEaster.setVisible(true);
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CalculadoraV1");
        frame.setContentPane(new CalculadoraV1().pnlCalculadora);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}