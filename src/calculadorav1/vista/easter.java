package calculadorav1.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class easter {
    public JPanel pnlEaster;
    private JButton btnRegresar;
    private JLabel txtEasterEgg;
    private void createUIComponents()
    {
        btnRegresar = new CalculadoraV1.BotonRedondeado(25);
        txtEasterEgg = new JLabel();
    }
    public easter() {
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(pnlEaster);
                Dimension tamanoActual = frameActual.getSize();
                frameActual.dispose();

                JFrame frameCalc = new JFrame("Calculadora");
                frameCalc.setContentPane(new CalculadoraV1().getPnlCalculadora());
                frameCalc.setSize(tamanoActual);
                frameCalc.setResizable(false);
                frameCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameCalc.setLocationRelativeTo(null);
                frameCalc.setVisible(true);
            }
        });
    }
}

