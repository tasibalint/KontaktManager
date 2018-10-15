import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {

	private JFrame frame;
	private JTextField txbFullname;
	private JTextField txbBirthsday;
	Manager _Manager;
	DefaultListModel<String> model;
	JList<String> list;
	private JTextField txbSearcher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		_Manager = new Manager();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblFullname = new JLabel("Fullname:");
		lblFullname.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(lblFullname);
		
		txbFullname = new JTextField();
		txbFullname.setText("Max Mustermann");
		txbFullname.setBounds(10, 36, 123, 20);
		frame.getContentPane().add(txbFullname);
		txbFullname.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(10, 67, 89, 14);
		frame.getContentPane().add(lblGender);
		
		JRadioButton rbtMale = new JRadioButton("Male");
		rbtMale.setBounds(6, 88, 61, 23);
		frame.getContentPane().add(rbtMale);
		
		JRadioButton rbtFemale = new JRadioButton("Female");
		rbtFemale.setBounds(69, 88, 79, 23);
		frame.getContentPane().add(rbtFemale);
		
		rbtMale.setSelected(true);
		rbtFemale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rbtMale.setSelected(false);
				
			}
		});
		rbtMale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rbtFemale.setSelected(false);
				
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(rbtMale);
		group.add(rbtFemale);
		
		JLabel lblBirthsday = new JLabel("Birthsday dd/mm/yyyy");
		lblBirthsday.setBounds(10, 118, 123, 14);
		frame.getContentPane().add(lblBirthsday);
		
		txbBirthsday = new JTextField();
		txbBirthsday.setText("21/10/1997");
		txbBirthsday.setBounds(10, 137, 123, 20);
		frame.getContentPane().add(txbBirthsday);
		txbBirthsday.setColumns(10);
		
		JButton btnAdd = new JButton("Add Person");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String fullname;
				String gender = "Nonebinary";
				String dateOfBirth;
				try {
						fullname = txbFullname.getText();
						dateOfBirth = txbBirthsday.getText();
						if(rbtMale.isSelected())
						{
							gender= rbtMale.getText();
						}
						if(rbtFemale.isSelected())
						{
							gender=rbtFemale.getText();
						}
						String[] name = fullname.split(" ");
						_Manager.Add(name[0]+";"+name[1] +";"+gender+";"+dateOfBirth);
						
					}
				catch(Exception ex)
				{
					System.out.println("Please Fill out every Information...");
				}
				finally
				{
					DisplayItems(_Manager._ListOfPersons);
				}
				
			}
		});
		btnAdd.setBounds(10, 168, 123, 23);
		frame.getContentPane().add(btnAdd);
		
		txbSearcher = new JTextField();
		txbSearcher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String input = txbSearcher.getText();
                Find(input);
			}
		});
		txbSearcher.setBounds(174, 8, 221, 20);
		frame.getContentPane().add(txbSearcher);
		txbSearcher.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_Manager.Save();
			}
		});
		btnSave.setBounds(0, 238, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_Manager.Load();
				DisplayItems(_Manager._ListOfPersons);
			}
		});
		btnLoad.setBounds(99, 238, 89, 23);
		frame.getContentPane().add(btnLoad);
		
		JButton btnDelete = new JButton("Delete Person");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = list.getSelectedValue();
				_Manager.DeletePerson(input);
				DisplayItems(_Manager._ListOfPersons);
			}
		});
		btnDelete.setBounds(10, 204, 123, 23);
		frame.getContentPane().add(btnDelete);
		
		model = new DefaultListModel<>();
		list = new JList<>( model );
		list.setBounds(174, 38, 200, 200);
		frame.getContentPane().add(list);
	}
	public void Find(String input)
	{
		List<Person> result = new ArrayList<>();
		result = _Manager.FindAll(input);
		DisplayItems(result);
	}
	public void MakeNewList()
	{
		DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
		listModel.removeAllElements();
	}
	public void DisplayItems(List<Person> Persons)
	{
		MakeNewList();
		for(Person item:Persons)
		{
			model.addElement(item.ToString());
		}
	}
}
