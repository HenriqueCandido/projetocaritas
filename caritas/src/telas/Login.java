package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;


/**
 * Classe Login recebe usuario e senha atraves dos txtfield
 * e verifica se existe no banco de dados. Se existe, dá acesso ao sistema
 * se nao, mostra mensagem dizendo que nao existe.
 * @author hcand
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	
	 // Cria a variavel para a string de conexão
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=caritas1;integratedSecurity=true;";
		
	// Declara os objetos do JDBC
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtUsuario.setBounds(72, 55, 265, 48);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		txtSenha.setBounds(72, 123, 265, 48);
		contentPane.add(txtSenha);
		
		
		/**
		 * BtAcessar busca os dados inseridos no banco de dados, se existe
		 * abre o sistema, se nao, mostra mensagem dizendo que informações sao inválidas.
		 */
		JButton btAcessar = new JButton("Acessar");
		btAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dados[] = new String[2];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT nome_usuario, senha_usuario "
		            		    	+ "FROM usuario "
		            		    	+ "WHERE nome_usuario='"+txtUsuario.getText()+"' AND "
		            		    	+ "senha_usuario='"+txtSenha.getText()+"';";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados [0] =rs.getString(1);
		            			dados [1] =rs.getString(2);
		            	}
		            	
		            	if(dados[0]==null || dados[1]==null) {
		            		JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
		            	}
		            	else {
		            		TelaPrincipalAdministrador menu = new TelaPrincipalAdministrador();
		    				menu.setVisible(true);
		    				dispose();
		            	}
		            }

					// Handle any errors that may have occurred.
					catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro!");
					}
				
				
				
			}
		});
		
		btAcessar.setFont(new Font("Arial", Font.PLAIN, 11));
		btAcessar.setBounds(160, 183, 89, 32);
		contentPane.add(btAcessar);
		
		JLabel lblInsiraUsurioE = new JLabel("Usu\u00E1rio:");
		lblInsiraUsurioE.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInsiraUsurioE.setBounds(72, 41, 197, 14);
		contentPane.add(lblInsiraUsurioE);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSenha.setBounds(72, 108, 197, 14);
		contentPane.add(lblSenha);
	}
}
