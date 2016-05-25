package app.views.simulations;

import app.controllers.FixedRateSimulationControllerClient;
import app.models.FixedRateSimulation;
import app.models.Insurance;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

public class FixedRateSimulationView {

    // JPanel that contains all the components
    JPanel panel;
    
    // components of the simulation frame
    private JComboBox cbLoanType;
    private JComboBox cbInsurance;
    private JSpinner spinnerAmount;
    private JTextField txtFieldDuration;
    private JTextField txtFieldRate;
    private JButton btnOk;
    private JButton btnSimulate;
    private JButton btnCancel;

    // controller for the fixed rate credit simulation
    FixedRateSimulationControllerClient controller;

    public FixedRateSimulationView(FixedRateSimulationControllerClient c, JPanel p) {
        // set the panel layout
        //super(new GridBagLayout());
        panel = p;
        
        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }*/
        
        // assign a controller to the view
        controller = c;
        
        // prepare the JPanel to the addition of the components
        panel.setVisible(false);
        panel.removeAll();
        panel.setLayout(new GridBagLayout());

        // initialisation of the components
        cbLoanType = new JComboBox(controller.getLoanTypes().toArray()); // fill the JComboBox with the loan types list
        cbLoanType.insertItemAt("", 0); // add blank first item in JComboBox
        cbLoanType.setSelectedIndex(0); // select the JComboBox blank field
        //btnOk = new JButton("OK");
        btnCancel = new JButton("Annuler");

        // add the action listeners to the components
        cbLoanType.addItemListener(new cbLoanTypeItemListener());
        //btnOk.addActionListener(new BtnOkListener());
        btnCancel.addActionListener(new BtnCancelListener());

        // add components to the panel using the GridBagLayout and GridBagConstraints
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(new JLabel("Type de prêt :"), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel.add(cbLoanType, gc);

        //gc.gridx = 0;
        //gc.gridy = 1;
        //panel.add(btnOk, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(btnCancel, gc);
        
        // display the JPanel
        panel.setVisible(true);
    }
    
    public void displayInsurances(String loanType) {
        System.out.println("appel de displayInsurances");
        // hide the JPanel
        panel.setVisible(false);

        // remove components from the JPanel
        panel.remove(btnCancel);
        
        // disable the modification of the loan type
        cbLoanType.setEnabled(false);
        
        // initilisation of the new component
        cbInsurance = new JComboBox();
        ArrayList<Insurance> insurancesList = controller.getInsurances(cbLoanType.getSelectedItem().toString());
        //System.out.println("arraylist tostring :");
        //insurancesList.toString();
        //for (Insurance i : insurancesList) {
            //cbInsurance.addItem(i);
        //}
        
        // add the action listeners to the components
        cbInsurance.addItemListener(new cbInsuranceItemListener());
        
        // add components to the panel using the GridBagLayout and GridBagConstraints
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(new JLabel("Assurance :"), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(cbInsurance, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        panel.add(btnCancel, gc);
        
        // perform the operations needed after the removal and the addition of components
        panel.revalidate();
        panel.repaint();

        // display the JPanel
        panel.setVisible(true);
    } 
    
    // display the fixed rate loan simulation form, adapted to the chosen loan type
    public void displayForm(int idInsurance) {
        // hide the JPanel
        panel.setVisible(false);

        // remove components from the JPanel
        panel.remove(btnCancel);
        
        // disable the modification of the insurance
        cbInsurance.setEnabled(false);
        
        // initialisation of the new components
        SpinnerNumberModel sm = new SpinnerNumberModel(0, 0, 10000, 100);
        spinnerAmount = new JSpinner(sm);
        txtFieldDuration = new JTextField(5);
        txtFieldRate = new JTextField(5);
        btnSimulate = new JButton("Simuler");
        
        // add the action listeners to the components
        btnSimulate.addActionListener(new BtnSimulateListener());

        // add components to the panel using the GridBagLayout and GridBagConstraints
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 10, 10);
        gc.anchor = GridBagConstraints.LINE_START;
        
        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(new JLabel("Taux de base :"), gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(new JLabel("4,76 %"), gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        panel.add(new JLabel("Taux :"), gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        panel.add(txtFieldRate, gc);
        
        gc.gridx = 0;
        gc.gridy = 3;
        panel.add(new JLabel("Montant :"), gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        panel.add(spinnerAmount, gc);
        
        gc.gridx = 0;
        gc.gridy = 4;
        panel.add(new JLabel("Durée :"), gc);
        
        gc.gridx = 1;
        gc.gridy = 4;
        panel.add(txtFieldDuration, gc);
        
        gc.gridx = 0;
        gc.gridy = 5;
        panel.add(btnSimulate, gc);
        
        gc.gridx = 1;
        gc.gridy = 5;
        panel.add(btnCancel, gc);
        
        // perform the operations needed after the removal and the addition of components
        panel.revalidate();
        panel.repaint();
        
        // display the JPanel
        panel.setVisible(true);
    }
    
    // ######################
    // inner listener classes
    // ######################
    
    // item listener for the cbLoanType JComboBox
    class cbLoanTypeItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // get the chosen loan type and display the alvailable insurances
                String type = cbLoanType.getSelectedItem().toString();
                if (!type.isEmpty()) {
                    displayInsurances(type);
                }
            }
            
        }
    }

    // item listener for the cbInsurance JComboBox
    class cbInsuranceItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            // get the insurance type and display the loan form
            String type = cbInsurance.getSelectedItem().toString();
            int insuranceId;
            if (!type.isEmpty()) {
                insuranceId = Integer.parseInt(type);
                displayForm(insuranceId);
            }
        }
    }
    
    // listener for the btnOk JButton
    /*class BtnOkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get the chosen loan type and display the simulation form
            String type = cbLoanType.getSelectedItem().toString();
            displayForm(type);
        }
    }*/

    // listener for the btnCancel JButton
    class BtnCancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    // listener for the btnSimulate JButton
    class BtnSimulateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    /*public static void main(String[] args) {
        javax.swing.JFrame f = new javax.swing.JFrame("test");
        app.models.FixedRateSimulation m = new app.models.FixedRateSimulation();
        JComboBox cb = new JComboBox();
        FixedRateSimulation s = new FixedRateSimulation();
        //FixedRateSimulationControllerClient c = new FixedRateSimulationControllerClient(m);
        //FixedRateSimulationView v = new FixedRateSimulationView(c);
        //f.add(v);
        f.setSize(500,500);
        f.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }*/
}
