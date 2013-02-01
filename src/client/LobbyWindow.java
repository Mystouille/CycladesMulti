package client;

import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LobbyWindow extends JFrame{
	public Client uplink;
	public JTextField field;
	JButton bouton;
	
	public LobbyWindow(Client uplink){
		super();
		this.uplink=uplink;
		build();
	}
	
	private void build(){
		setTitle("Lobby");
		setSize(320,240);
		setLocationRelativeTo(null); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		field = new JTextField();
		field.setName("server IP:");
		field.setColumns(10);
		panel.add(field);


		bouton = new JButton(new ActionValidateServerIP(this));
		panel.add(bouton);
		
		return panel;
	}
}
