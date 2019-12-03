package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * a Classe tela usuario cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao usuario.
 * 
 * @author hcand
 *
 */
public class TelaUsuario extends JFrame {

	private JPanel contentPane;
	public  JTextField txtNomeUsuario;
	public  JTextField txtSenhaUsuario;
	public  JTextField txtTipoUsuario;
	public int tipoDoSalvarUsuario;
	public Object codigoUsuario;

	// Create a variable for the connection string.
	String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
		"databaseName=caritas1;integratedSecurity=true;";

	// Declare the JDBC objects.
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	/**
	 * Create the frame.
	 */
	public TelaUsuario() {
		setTitle("CADASTRO USU\u00C1RIO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 * 
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarUsuario) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeUsuario.getText().trim().equals("") || 
						   txtSenhaUsuario.getText().trim().equals("") || 
						   txtTipoUsuario.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into usuario values ('"+txtNomeUsuario.getText()+"','"+txtSenhaUsuario.getText()+"','"+txtTipoUsuario.getText()+"');";
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            	JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
				            	dispose();
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Erro ao Salvar");
						}
					
					//JOptionPane.showMessageDialog(null, "Usuario Um Criar");
					break;
				case 2: // colocar codigo alterar aqui
					
					if(txtNomeUsuario.getText().trim().equals("") || 
					   txtSenhaUsuario.getText().trim().equals("") || 
					   txtTipoUsuario.getText().trim().equals("")) {  
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
						break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE usuario "
					            			+ "SET nome_usuario = '"+txtNomeUsuario.getText()+"',"
					            			+ "senha_usuario = '"+txtSenhaUsuario.getText()+"',"
					            			+ "tipo_usuario = '"+txtTipoUsuario.getText()+"'"
					            			+ " WHERE cod_usuario = "+codigoUsuario+";";
					            	stmt = con.createStatement();
					            	stmt.execute(SQL);
					            	JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					            	dispose();
					            	
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Erro ao Salvar");
							}
					
	
					break;
				case 3: // nao fazer nada
					//JOptionPane.showMessageDialog(null, "Usuario Tres Visualizar");
					dispose();
					break;
				default: 
					JOptionPane.showMessageDialog(null, "Erro ao Salvar");
					dispose();
					break;
				}
			
			}
		});
		btSalvar.setFont(new Font("Arial", Font.PLAIN, 11));
		btSalvar.setBounds(76, 213, 89, 23);
		contentPane.add(btSalvar);
		
		/**
		 * O botao cancelar fecha a tela do formulário 
		 */
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setBounds(175, 213, 89, 23);
		contentPane.add(btCancelar);
		
		JLabel lblUsurio = new JLabel("* Nome:");
		lblUsurio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUsurio.setBounds(24, 11, 49, 23);
		contentPane.add(lblUsurio);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeUsuario.setBounds(24, 33, 296, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		txtSenhaUsuario = new JTextField();
		txtSenhaUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtSenhaUsuario.setColumns(10);
		txtSenhaUsuario.setBounds(24, 99, 296, 20);
		contentPane.add(txtSenhaUsuario);
		
		JLabel lblSenha = new JLabel("* Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSenha.setBounds(24, 77, 49, 23);
		contentPane.add(lblSenha);
		
		txtTipoUsuario = new JTextField();
		txtTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTipoUsuario.setColumns(10);
		txtTipoUsuario.setBounds(24, 170, 296, 20);
		contentPane.add(txtTipoUsuario);
		
		JLabel lblTipo = new JLabel("* Tipo (Administrador, Normal, Instrutor):");
		lblTipo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTipo.setBounds(24, 148, 296, 23);
		contentPane.add(lblTipo);
	}
}
