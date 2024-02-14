import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ventana {
    private JPanel principal;
    private JComboBox cboPrioridad;
    private JTextField txtNombre;
    private JTextField txtSintoma;
    private JButton btnRegistrar;
    private JList lstListaPacientes;
    private JButton btnAtender;
    DefaultListModel dlm=new DefaultListModel();
    Clinica clinica=new Clinica();

    public void llenarJlist(){
        dlm.removeAllElements();
        Stream<Paciente> ordenada= clinica.listarPacientes().stream().sorted();
        for(Paciente p: ordenada.collect(Collectors.toList())){
            dlm.addElement(p);
        }
        lstListaPacientes.setModel(dlm);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p=Integer.parseInt(cboPrioridad.getSelectedItem().toString());
                String nombre=txtNombre.getText();
                String sintomas=txtSintoma.getText();
                Paciente nuevo=new Paciente(p,nombre,sintomas);
                clinica.encolar(nuevo);
                llenarJlist();
            }
        });


        btnAtender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Paciente p=clinica.atender();
                    JOptionPane.showMessageDialog(null,"Paciente atendido: "+p.toString());
                    llenarJlist();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }
}
