import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ventana {
    private JPanel principal;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton agregarButton;
    private JButton pagarButton;
    private JButton consultaButton;
    private JTextArea textArea1;

    private PatioAutos patio=new PatioAutos();

    public Ventana() {
        textArea1.setEditable(false);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patio.encolar(comboBox1.getSelectedItem().toString(), Integer.parseInt(textField1.getText()));
                textArea1.setText(patio.listarAutos());
                vaciarCampos();
            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Auto x=patio.desencolar();
                    JOptionPane.showMessageDialog(null, "Se ha apagado: "+x.pago() +" vehiculo: "+x);
                    textArea1.setText(patio.listarAutos());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No hay elementos!");
                    throw new RuntimeException(ex);
                }
            }
        });
        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marca=comboBox1.getSelectedItem().toString();
                if(patio.listarMarca(marca).length()!=0)
                {
                    textArea1.setText(patio.listarMarca(comboBox1.getSelectedItem().toString()));
                }else{
                    textArea1.setText("NO hay vehículos de dicha marca");
                }


            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        frame.setVisible(true);

    }
    public void vaciarCampos()
    {
        comboBox1.setSelectedIndex(0);
        textField1.setText("");
    }
}
