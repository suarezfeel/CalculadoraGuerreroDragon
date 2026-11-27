package calculadorav1.vista;
import calculadorav1.modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculadoraV1
{

    // Boolean modo claro/oscuro
    private boolean modoOscuro = true;

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
    private JButton btnHistorial;
    private JButton btnHuevo;
    private JButton btnEstilos;
    private JLabel lblFechaHora;

    // Getter del panel (Encapsulación)
    public JPanel getPnlCalculadora(){
        return pnlCalculadora;
    }

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
    public static class BotonRedondeado extends JButton
    {
        private Shape forma;
        private int radio;
        public BotonRedondeado()
        {
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
    public void agregarOperacion(String operacion) {
        try {
            String textoActual = txtOperacion.getText();
            if (textoActual.isEmpty() || textoActual.endsWith("+") || textoActual.endsWith("-")
                    || textoActual.endsWith("/") || textoActual.endsWith("x")
                    || textoActual.endsWith("%") || textoActual.endsWith(".")) {
                throw new NumberFormatException("Operador sin operando válido.");
            }
            txtOperacion.setText(textoActual + operacion);
        } catch (NumberFormatException exception) {
            // Excepción que controla el intento de ingresar operadores consecutivos o al inicio sin número.
            JOptionPane.showMessageDialog(pnlCalculadora,
                    "Colocaste un operador matemático sin haber puesto un número válido.",
                    "Información", JOptionPane.WARNING_MESSAGE);
        }
    }

    public CalculadoraV1(){
        txtOperacion.setBorder(null);
        txtOperacion.setEditable(false);
        txtOperacion.setFocusable(false);
        // Label de fecha y hora
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        lblFechaHora.setText(java.time.LocalDateTime.now().format(formato));

        Timer reloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblFechaHora.setText(java.time.LocalDateTime.now().format(formato));
            }
        });
        reloj.start();
        // Listeners

        btnEstilos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                modoOscuro = !modoOscuro;
                if  (!modoOscuro) {
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

                    Color colorLetraFecha = new Color(110, 110, 115);
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

                    lblFechaHora.setForeground(colorLetraFecha);
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
                    Color colorLetraBotonesSuperiores = new Color(254, 254, 254);

                    Color colorLetraFecha = new Color(142, 142, 147);
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

                    lblFechaHora.setForeground(colorLetraFecha);
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
        btnComa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = txtOperacion.getText();
                String[] partes = texto.split("[+\\-x/%]");
                String ultimoNumero = "";
                if (partes.length > 0) {
                    ultimoNumero = partes[partes.length - 1];
                }
                if (!ultimoNumero.contains(".")) {
                    if (ultimoNumero.isEmpty()) {
                        txtOperacion.setText(texto + "0.");
                    }
                    else {
                        txtOperacion.setText(texto + ".");
                    }
                }
            }
        });
        btnAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (texto.isEmpty()) return;

                try {
                    // Separa números y operadores manteniendo el signo negativo unido al número
                    String[] separarOperacion = texto.split("(?<=[0-9])(?=[+\\-x/%])|(?<=[0-9][+\\-x/%])");
                    ArrayList<String> partes = new ArrayList<>(Arrays.asList(separarOperacion));

                    // 1. Primera pasada: Multiplicación, División y Residuo (mayor jerarquía)
                    for (int i = 1; i < partes.size(); i += 2) {
                        String operacion = partes.get(i);
                        if (operacion.equals("x") || operacion.equals("/") || operacion.equals("%")) {
                            double numeroA = Double.parseDouble(partes.get(i - 1));
                            double numeroB = Double.parseDouble(partes.get(i + 1));
                            OperacionMatematica op = null;

                            switch (operacion) {
                                case "x":
                                    op = new Multiplicacion(numeroA, numeroB); break;
                                case "/":
                                    op = new Division(numeroA, numeroB);       break;
                                case "%":
                                    op = new Residuo(numeroA, numeroB);        break;
                            }

                            // Reemplaza "numeroA", "operador", "numeroB" por el resultado
                            partes.set(i - 1, String.valueOf(op.ejecutar()));
                            partes.remove(i);
                            partes.remove(i);
                            i -= 2; // Retrocede el índice porque la lista se encogió
                        }
                    }

                    // 2. Segunda pasada: Suma y Resta (menor jerarquía)
                    double total = Double.parseDouble(partes.get(0));
                    for (int i = 1; i < partes.size(); i += 2) {
                        String operacion = partes.get(i);
                        double numeroB = Double.parseDouble(partes.get(i + 1));
                        OperacionMatematica op = null;

                        switch (operacion) {
                            case "+":
                                op = new Suma(total, numeroB);  break;
                            case "-":
                                op = new Resta(total, numeroB); break;
                        }

                        if (op != null) {
                            total = op.ejecutar();
                        }
                    }

                    // Evita mostrar ".0" si el resultado es entero
                    String resultadoFinal;
                    if (total % 1 == 0) {
                        resultadoFinal = String.valueOf((long) total);
                    }
                    else {
                        resultadoFinal = String.valueOf(total);
                    }
                    txtOperacion.setText(resultadoFinal);

                } catch (ArithmeticException exception) { // Excepción de división o residuo entre 0.
                    JOptionPane.showMessageDialog(pnlCalculadora, "Error! " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    txtOperacion.setText("");
                } catch (IndexOutOfBoundsException exception) { // Excepción que sale al no completar una operación.
                    JOptionPane.showMessageDialog(pnlCalculadora, "Error! Expresión incompleta.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtOperacion.setText("");
                } catch (NumberFormatException exception) { // Excepción encargada de verificar que lo ingresado sea una expresión completa
                    JOptionPane.showMessageDialog(pnlCalculadora, "Error! Expresión inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtOperacion.setText("");
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
                frameEaster.setContentPane(new easter().pnlEaster);
                frameEaster.setSize(tamanoCalculadora); // Mismo ancho y alto que la calculadora
                frameEaster.setResizable(false);         // Impide que el usuario estire la ventana
                frameEaster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameEaster.setLocationRelativeTo(null);
                frameEaster.setVisible(true);
            }
        });
        btnMasMenos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String texto = txtOperacion.getText();

                // Si el último número ya es negativo, marca su signo con '#'
                texto = texto.replaceFirst("(^|.*[+\\-x/%])-([0-9.]+)$", "$1#$2");

                // Si el último número es positivo, le antepone '-'
                texto = texto.replaceFirst("(^|.*[+\\-x/%])([0-9.]+)$", "$1-$2");

                // Elimina la marca temporal '#' dejando el número positivo
                texto = texto.replace("#", "");

                txtOperacion.setText(texto);
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("CalculadoraV1");
        frame.setContentPane(new CalculadoraV1().getPnlCalculadora());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}