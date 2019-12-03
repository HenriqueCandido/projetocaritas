package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * a Classe tela Espaco cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao espaco.
 * @author hcand
 *
 */
public class TelaEspaco extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeEspaco;
	public JTextField txtLugaresEspaco;
	public int tipoDoSalvarEspaco;
	public Object codigoEspaco;

	
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
	public TelaEspaco() {
		setTitle("CADASTRO ESPA\u00C7O");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeEspaco = new JTextField();
		txtNomeEspaco.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeEspaco.setColumns(10);
		txtNomeEspaco.setBounds(26, 44, 296, 20);
		contentPane.add(txtNomeEspaco);
		
		JLabel lblNomeDo = new JLabel("* Nome do Espa\u00E7o:");
		lblNomeDo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNomeDo.setBounds(26, 22, 296, 23);
		contentPane.add(lblNomeDo);
		
		txtLugaresEspaco = new JTextField();
		txtLugaresEspaco.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLugaresEspaco.setColumns(10);
		txtLugaresEspaco.setBounds(26, 110, 296, 20);
		contentPane.add(txtLugaresEspaco);
		
		JLabel lblQuantidadeDe = new JLabel("* Quantidade de Lugares:");
		lblQuantidadeDe.setFont(new Font("Arial", Font.PLAIN, 11));
		lblQuantidadeDe.setBounds(26, 88, 296, 23);
		contentPane.add(lblQuantidadeDe);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarEspaco) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeEspaco.getText().trim().equals("") || 
						   txtLugaresEspaco.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into espaco values "
				            			+ "('"+txtNomeEspaco.getText()+"',"
				            			+ "'"+txtLugaresEspaco.getText()+"');";
				            	
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
					
					if(txtNomeEspaco.getText().trim().equals("") || 
							   txtLugaresEspaco.getText().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE espaco "
					            			+ "SET "
					            			+ "nome_espaco = '"+txtNomeEspaco.getText()+"',"
					            			+ "lugares_espaco = '"+txtLugaresEspaco.getText()+"'"
					            			+ " WHERE cod_espaco = "+codigoEspaco+";";
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
		btSalvar.setBounds(75, 167, 89, 23);
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
		btCancelar.setBounds(175, 167, 89, 23);
		contentPane.add(btCancelar);
	}

}
