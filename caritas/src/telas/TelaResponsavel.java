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
 *  * a Classe tela Responsavel cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao responsavel.
 * @author hcand
 *
 */
public class TelaResponsavel extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeResponsavel;
	public JTextField txtRgResponsavel;
	public JTextField txtCpfResponsavel;
	public JTextField txtRuaResponsavel;
	public JTextField txtBairroResponsavel;
	public JTextField txtNisResponsavel;
	public JTextField txtNascimentoResponsavel;
	public JTextField txtNumeroResponsavel;
	public JTextField txtCidadeResponsavel;
	public JTextField txtTelefoneResponsavel;
	public JTextField txtEmailResponsavel;
	public int tipoDoSalvarResponsavel;
	public Object codigoResponsavel;
	
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
	public TelaResponsavel() {
		setTitle("CADASTRO RESPONS\u00C1VEL");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 492);
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
				
				switch(tipoDoSalvarResponsavel) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeResponsavel.getText().trim().equals("") || 
						   txtRgResponsavel.getText().trim().equals("") || 
						   txtCpfResponsavel.getText().trim().equals("") || 
						   txtRuaResponsavel.getText().trim().equals("") || 
						   txtBairroResponsavel.getText().trim().equals("") || 
						   txtNascimentoResponsavel.getText().trim().equals("") || 
						   txtNumeroResponsavel.getText().trim().equals("") || 
						   txtCidadeResponsavel.getText().trim().equals("") || 
						   txtTelefoneResponsavel.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into responsavel values "
				            			+ "('"+txtNomeResponsavel.getText()+"',"
				            			+ "'"+txtRgResponsavel.getText()+"',"
				            			+ "'"+txtNisResponsavel.getText()+"',"
				            			+ "'"+txtCpfResponsavel.getText()+"',"
				            			+ "'"+txtNascimentoResponsavel.getText()+"',"
				            			+ "'"+txtRuaResponsavel.getText()+"',"
				            			+ "'"+txtNumeroResponsavel.getText()+"',"
				            			+ "'"+txtBairroResponsavel.getText()+"',"
				            			+ "'"+txtCidadeResponsavel.getText()+"',"
				            			+ "'"+txtTelefoneResponsavel.getText()+"',"
				            			+ "'"+txtEmailResponsavel.getText()+"');";
				            	
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
					
					if(txtNomeResponsavel.getText().trim().equals("") || 
							   txtRgResponsavel.getText().trim().equals("") || 
							   txtCpfResponsavel.getText().trim().equals("") || 
							   txtRuaResponsavel.getText().trim().equals("") || 
							   txtBairroResponsavel.getText().trim().equals("") || 
							   txtNascimentoResponsavel.getText().trim().equals("") || 
							   txtNumeroResponsavel.getText().trim().equals("") || 
							   txtCidadeResponsavel.getText().trim().equals("") || 
							   txtTelefoneResponsavel.getText().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE responsavel "
					            			+ "SET "
					            			+ "nome_responsavel = '"+txtNomeResponsavel.getText()+"',"
					            			+ "rg_responsavel = '"+txtRgResponsavel.getText()+"',"
					            			+ "nis_responsavel = '"+txtNisResponsavel.getText()+"',"
					            			+ "cpf_responsavel = '"+txtCpfResponsavel.getText()+"',"
					            			+ "nascimento_responsavel = '"+txtNascimentoResponsavel.getText()+"',"
					            			+ "rua_responsavel = '"+txtRuaResponsavel.getText()+"',"
					            			+ "numero_responsavel = '"+txtNumeroResponsavel.getText()+"',"
					            			+ "bairro_responsavel = '"+txtBairroResponsavel.getText()+"',"
					            			+ "cidade_responsavel = '"+txtCidadeResponsavel.getText()+"',"
					            			+ "telefone_responsavel = '"+txtTelefoneResponsavel.getText()+"',"
					            			+ "email_responsavel = '"+txtEmailResponsavel.getText()+"'"
					            			+ " WHERE cod_responsavel = "+codigoResponsavel+";";
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
		btSalvar.setBounds(240, 409, 89, 23);
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
		btCancelar.setBounds(339, 409, 89, 23);
		contentPane.add(btCancelar);
		
		JLabel lblNome = new JLabel("* Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNome.setBounds(24, 21, 48, 14);
		contentPane.add(lblNome);
		
		txtNomeResponsavel = new JTextField();
		txtNomeResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeResponsavel.setBounds(24, 38, 627, 20);
		contentPane.add(txtNomeResponsavel);
		txtNomeResponsavel.setColumns(10);
		
		txtRgResponsavel = new JTextField();
		txtRgResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRgResponsavel.setColumns(10);
		txtRgResponsavel.setBounds(24, 97, 301, 20);
		contentPane.add(txtRgResponsavel);
		
		JLabel lblRgsomenteOs = new JLabel("* RG (Somente os Digitos):");
		lblRgsomenteOs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRgsomenteOs.setBounds(24, 80, 288, 14);
		contentPane.add(lblRgsomenteOs);
		
		txtCpfResponsavel = new JTextField();
		txtCpfResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCpfResponsavel.setColumns(10);
		txtCpfResponsavel.setBounds(24, 159, 301, 20);
		contentPane.add(txtCpfResponsavel);
		
		JLabel lblCpfsomenteOs = new JLabel("* CPF (Somente os Digitos):");
		lblCpfsomenteOs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCpfsomenteOs.setBounds(24, 142, 288, 14);
		contentPane.add(lblCpfsomenteOs);
		
		txtRuaResponsavel = new JTextField();
		txtRuaResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRuaResponsavel.setColumns(10);
		txtRuaResponsavel.setBounds(24, 222, 301, 20);
		contentPane.add(txtRuaResponsavel);
		
		JLabel lblRua = new JLabel("* Rua:");
		lblRua.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRua.setBounds(24, 205, 48, 14);
		contentPane.add(lblRua);
		
		txtBairroResponsavel = new JTextField();
		txtBairroResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairroResponsavel.setColumns(10);
		txtBairroResponsavel.setBounds(24, 288, 301, 20);
		contentPane.add(txtBairroResponsavel);
		
		JLabel lblBairro = new JLabel("* Bairro:");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBairro.setBounds(24, 271, 48, 14);
		contentPane.add(lblBairro);
		
		txtNisResponsavel = new JTextField();
		txtNisResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNisResponsavel.setColumns(10);
		txtNisResponsavel.setBounds(350, 97, 301, 20);
		contentPane.add(txtNisResponsavel);
		
		JLabel lblNissomenteOs = new JLabel("NIS (Somente os Digitos):");
		lblNissomenteOs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNissomenteOs.setBounds(350, 80, 263, 14);
		contentPane.add(lblNissomenteOs);
		
		txtNascimentoResponsavel = new JTextField();
		txtNascimentoResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNascimentoResponsavel.setColumns(10);
		txtNascimentoResponsavel.setBounds(350, 159, 301, 20);
		contentPane.add(txtNascimentoResponsavel);
		
		JLabel lblDataDeNascimento = new JLabel("* Data de Nascimento (dd/mm/aaaa):");
		lblDataDeNascimento.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDataDeNascimento.setBounds(350, 142, 301, 14);
		contentPane.add(lblDataDeNascimento);
		
		txtNumeroResponsavel = new JTextField();
		txtNumeroResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNumeroResponsavel.setColumns(10);
		txtNumeroResponsavel.setBounds(350, 222, 301, 20);
		contentPane.add(txtNumeroResponsavel);
		
		JLabel lblNmero = new JLabel("* N\u00FAmero:");
		lblNmero.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNmero.setBounds(350, 205, 89, 14);
		contentPane.add(lblNmero);
		
		txtCidadeResponsavel = new JTextField();
		txtCidadeResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCidadeResponsavel.setColumns(10);
		txtCidadeResponsavel.setBounds(350, 288, 301, 20);
		contentPane.add(txtCidadeResponsavel);
		
		JLabel lblCidade = new JLabel("* Cidade:");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCidade.setBounds(350, 271, 48, 14);
		contentPane.add(lblCidade);
		
		txtTelefoneResponsavel = new JTextField();
		txtTelefoneResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTelefoneResponsavel.setColumns(10);
		txtTelefoneResponsavel.setBounds(24, 361, 301, 20);
		contentPane.add(txtTelefoneResponsavel);
		
		JLabel lblTelefonesomenteOs = new JLabel("* Telefone (Somente os n\u00FAmeros): ");
		lblTelefonesomenteOs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTelefonesomenteOs.setBounds(24, 344, 211, 14);
		contentPane.add(lblTelefonesomenteOs);
		
		txtEmailResponsavel = new JTextField();
		txtEmailResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmailResponsavel.setColumns(10);
		txtEmailResponsavel.setBounds(350, 361, 301, 20);
		contentPane.add(txtEmailResponsavel);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmail.setBounds(350, 344, 48, 14);
		contentPane.add(lblEmail);
	}

}
