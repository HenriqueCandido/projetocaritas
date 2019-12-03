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
 * a Classe tela instrutor cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao instrutor.
 * @author hcand
 *
 */
public class TelaInstrutor extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeInstrutor;
	public JTextField txtEspecialidadeInstrutor;
	public JTextField txtRgInstrutor;
	public JTextField txtNascimentoInstrutor;
	public JTextField txtCpfInstrutor;
	public JTextField txtRuaInstrutor;
	public JTextField txtNumeroInstrutor;
	public JTextField txtCidadeInstrutor;
	public JTextField txtBairroInstrutor;
	public JTextField txtTelefoneInstrutor;
	public JTextField txtEmailInstrutor;
	public int tipoDoSalvarInstrutor;
	public Object codigoInstrutor;

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
	public TelaInstrutor() {
		setTitle("CADASTRO INSTRUTOR");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("* Nome:");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(31, 23, 48, 14);
		contentPane.add(label);
		
		txtNomeInstrutor = new JTextField();
		txtNomeInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeInstrutor.setColumns(10);
		txtNomeInstrutor.setBounds(31, 40, 627, 20);
		contentPane.add(txtNomeInstrutor);
		
		JLabel lblEspecialidade = new JLabel("* Especialidade:\r\n");
		lblEspecialidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEspecialidade.setBounds(31, 82, 288, 14);
		contentPane.add(lblEspecialidade);
		
		txtEspecialidadeInstrutor = new JTextField();
		txtEspecialidadeInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEspecialidadeInstrutor.setColumns(10);
		txtEspecialidadeInstrutor.setBounds(31, 99, 301, 20);
		contentPane.add(txtEspecialidadeInstrutor);
		
		JLabel lblRgsomente = new JLabel("* RG (Somente os Digitos):");
		lblRgsomente.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRgsomente.setBounds(357, 82, 263, 14);
		contentPane.add(lblRgsomente);
		
		txtRgInstrutor = new JTextField();
		txtRgInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRgInstrutor.setColumns(10);
		txtRgInstrutor.setBounds(357, 99, 301, 20);
		contentPane.add(txtRgInstrutor);
		
		JLabel label_3 = new JLabel("* Data de Nascimento (dd/mm/aaaa):");
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		label_3.setBounds(357, 144, 301, 14);
		contentPane.add(label_3);
		
		txtNascimentoInstrutor = new JTextField();
		txtNascimentoInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNascimentoInstrutor.setColumns(10);
		txtNascimentoInstrutor.setBounds(357, 161, 301, 20);
		contentPane.add(txtNascimentoInstrutor);
		
		txtCpfInstrutor = new JTextField();
		txtCpfInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCpfInstrutor.setColumns(10);
		txtCpfInstrutor.setBounds(31, 161, 301, 20);
		contentPane.add(txtCpfInstrutor);
		
		JLabel label_4 = new JLabel("* CPF (Somente os Digitos):");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(31, 144, 288, 14);
		contentPane.add(label_4);
		
		txtRuaInstrutor = new JTextField();
		txtRuaInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRuaInstrutor.setColumns(10);
		txtRuaInstrutor.setBounds(31, 224, 301, 20);
		contentPane.add(txtRuaInstrutor);
		
		JLabel label_5 = new JLabel("* Rua:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(31, 207, 48, 14);
		contentPane.add(label_5);
		
		txtNumeroInstrutor = new JTextField();
		txtNumeroInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNumeroInstrutor.setColumns(10);
		txtNumeroInstrutor.setBounds(357, 224, 301, 20);
		contentPane.add(txtNumeroInstrutor);
		
		JLabel label_6 = new JLabel("* N\u00FAmero:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(357, 207, 89, 14);
		contentPane.add(label_6);
		
		txtCidadeInstrutor = new JTextField();
		txtCidadeInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCidadeInstrutor.setColumns(10);
		txtCidadeInstrutor.setBounds(357, 290, 301, 20);
		contentPane.add(txtCidadeInstrutor);
		
		JLabel label_7 = new JLabel("* Cidade:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 11));
		label_7.setBounds(357, 273, 48, 14);
		contentPane.add(label_7);
		
		txtBairroInstrutor = new JTextField();
		txtBairroInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairroInstrutor.setColumns(10);
		txtBairroInstrutor.setBounds(31, 290, 301, 20);
		contentPane.add(txtBairroInstrutor);
		
		JLabel label_8 = new JLabel("* Bairro:");
		label_8.setFont(new Font("Arial", Font.PLAIN, 11));
		label_8.setBounds(31, 273, 48, 14);
		contentPane.add(label_8);
		
		txtTelefoneInstrutor = new JTextField();
		txtTelefoneInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTelefoneInstrutor.setColumns(10);
		txtTelefoneInstrutor.setBounds(31, 363, 301, 20);
		contentPane.add(txtTelefoneInstrutor);
		
		JLabel label_9 = new JLabel("* Telefone (Somente os n\u00FAmeros): ");
		label_9.setFont(new Font("Arial", Font.PLAIN, 11));
		label_9.setBounds(31, 346, 211, 14);
		contentPane.add(label_9);
		
		txtEmailInstrutor = new JTextField();
		txtEmailInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmailInstrutor.setColumns(10);
		txtEmailInstrutor.setBounds(357, 363, 301, 20);
		contentPane.add(txtEmailInstrutor);
		
		JLabel lblEmail = new JLabel("* E-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmail.setBounds(357, 346, 48, 14);
		contentPane.add(lblEmail);
		
		
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
		btCancelar.setBounds(346, 411, 89, 23);
		contentPane.add(btCancelar);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarInstrutor) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeInstrutor.getText().trim().equals("") || 
						   txtEspecialidadeInstrutor.getText().trim().equals("") || 
						   txtRgInstrutor.getText().trim().equals("") || 
						   txtNascimentoInstrutor.getText().trim().equals("") || 
						   txtCpfInstrutor.getText().trim().equals("") || 
						   txtRuaInstrutor.getText().trim().equals("") || 
						   txtNumeroInstrutor.getText().trim().equals("") || 
						   txtCidadeInstrutor.getText().trim().equals("") ||
						   txtBairroInstrutor.getText().trim().equals("") ||
						   txtTelefoneInstrutor.getText().trim().equals("") ||
						   txtEmailInstrutor.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into instrutor values "
				            			+ "('"+txtNomeInstrutor.getText()+"',"
				            			+ "'"+txtEspecialidadeInstrutor.getText()+"',"
				            			+ "'"+txtRgInstrutor.getText()+"',"
				            			+ "'"+txtCpfInstrutor.getText()+"',"
				            			+ "'"+txtNascimentoInstrutor.getText()+"',"
				            			+ "'"+txtRuaInstrutor.getText()+"',"
				            			+ "'"+txtNumeroInstrutor.getText()+"',"
				            			+ "'"+txtBairroInstrutor.getText()+"',"
				            			+ "'"+txtCidadeInstrutor.getText()+"',"
				            			+ "'"+txtTelefoneInstrutor.getText()+"',"
				            			+ "'"+txtEmailInstrutor.getText()+"');";
				            	
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
					
					if(txtNomeInstrutor.getText().trim().equals("") || 
							   txtEspecialidadeInstrutor.getText().trim().equals("") || 
							   txtRgInstrutor.getText().trim().equals("") || 
							   txtNascimentoInstrutor.getText().trim().equals("") || 
							   txtCpfInstrutor.getText().trim().equals("") || 
							   txtRuaInstrutor.getText().trim().equals("") || 
							   txtNumeroInstrutor.getText().trim().equals("") || 
							   txtCidadeInstrutor.getText().trim().equals("") ||
							   txtBairroInstrutor.getText().trim().equals("") ||
							   txtTelefoneInstrutor.getText().trim().equals("") ||
							   txtEmailInstrutor.getText().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE instrutor "
					            			+ "SET "
					            			+ "nome_instrutor = '"+txtNomeInstrutor.getText()+"',"
					            			+ "especialidade_instrutor = '"+txtEspecialidadeInstrutor.getText()+"',"
					            			+ "rg_instrutor = '"+txtRgInstrutor.getText()+"',"
					            			+ "cpf_instrutor = '"+txtCpfInstrutor.getText()+"',"
					            			+ "nascimento_instrutor = '"+txtNascimentoInstrutor.getText()+"',"
					            			+ "rua_instrutor = '"+txtRuaInstrutor.getText()+"',"
					            			+ "numero_instrutor = '"+txtNumeroInstrutor.getText()+"',"
					            			+ "bairro_instrutor = '"+txtBairroInstrutor.getText()+"',"
					            			+ "cidade_instrutor = '"+txtCidadeInstrutor.getText()+"',"
					            			+ "telefone_instrutor = '"+txtTelefoneInstrutor.getText()+"',"
					            			+ "email_instrutor = '"+txtEmailInstrutor.getText()+"'"
					            			+ " WHERE cod_instrutor = "+codigoInstrutor+";";
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
		btSalvar.setBounds(247, 411, 89, 23);
		contentPane.add(btSalvar);
	}

}
