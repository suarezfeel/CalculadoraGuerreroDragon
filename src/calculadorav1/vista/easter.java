package calculadorav1.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class easter {
    public JPanel panel1;
    private JButton btnRegresar;
    private JLabel txtEasterEgg;

    public easter() {
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameActual = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                Dimension tamanoActual = frameActual.getSize();
                frameActual.dispose();

                JFrame frameCalc = new JFrame("Calculadora");
                frameCalc.setContentPane(new CalculadoraV1().pnlCalculadora);
                frameCalc.setSize(tamanoActual);
                frameCalc.setResizable(false);
                frameCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameCalc.setLocationRelativeTo(null);
                frameCalc.setVisible(true);
            }
        });
    }
}

