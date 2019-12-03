package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * a Classe tela Atendido cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao atendido.
 * 
 * @author hcand
 *
 */
public class TelaAtendido extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeAtendido;
	public JTextField txtNisAtendido;
	public JTextField txtNascimentoAtendido;
	public JTextField txtNumeroAtendido;
	public JTextField txtRuaAtendido;
	public JTextField txtBairroAtendido;
	public JTextField txtCidadeAtendido;
	public JTextField txtEmailAtendido;
	public JTextField txtTelefoneAtendido;
	public JTextField txtRaAtendido;
	public JComboBox cbResponsavelAtendido = new JComboBox();
	public String codigoResponsavel;
	public int tipoDoSalvarAtendido;
	public Object codigoAtendido;
	
	
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
	public TelaAtendido() {
		setTitle("CADASTRO ATENDIDO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("* Nome:");
		label.setFont(new Font("Arial", Font.PLAIN, 11));
		label.setBounds(51, 11, 48, 14);
		contentPane.add(label);
		
		txtNomeAtendido = new JTextField();
		txtNomeAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeAtendido.setColumns(10);
		txtNomeAtendido.setBounds(51, 28, 301, 20);
		contentPane.add(txtNomeAtendido);
		
		txtNisAtendido = new JTextField();
		txtNisAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNisAtendido.setColumns(10);
		txtNisAtendido.setBounds(51, 87, 301, 20);
		contentPane.add(txtNisAtendido);
		
		JLabel lblNissomente = new JLabel("NIS (Somente os Digitos):\r\n");
		lblNissomente.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNissomente.setBounds(51, 70, 288, 14);
		contentPane.add(lblNissomente);
		
		txtNascimentoAtendido = new JTextField();
		txtNascimentoAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNascimentoAtendido.setColumns(10);
		txtNascimentoAtendido.setBounds(377, 87, 301, 20);
		contentPane.add(txtNascimentoAtendido);
		
		JLabel lblDataDe = new JLabel("* Data de Nascimento (dd/mm/aaaa):");
		lblDataDe.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDataDe.setBounds(377, 70, 263, 14);
		contentPane.add(lblDataDe);
		
		JLabel lblNmero = new JLabel("* N\u00FAmero:");
		lblNmero.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNmero.setBounds(377, 132, 301, 14);
		contentPane.add(lblNmero);
		
		txtNumeroAtendido = new JTextField();
		txtNumeroAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNumeroAtendido.setColumns(10);
		txtNumeroAtendido.setBounds(377, 149, 301, 20);
		contentPane.add(txtNumeroAtendido);
		
		txtRuaAtendido = new JTextField();
		txtRuaAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRuaAtendido.setColumns(10);
		txtRuaAtendido.setBounds(51, 149, 301, 20);
		contentPane.add(txtRuaAtendido);
		
		JLabel lblRua = new JLabel("* Rua:");
		lblRua.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRua.setBounds(51, 132, 288, 14);
		contentPane.add(lblRua);
		
		txtBairroAtendido = new JTextField();
		txtBairroAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtBairroAtendido.setColumns(10);
		txtBairroAtendido.setBounds(51, 212, 301, 20);
		contentPane.add(txtBairroAtendido);
		
		JLabel lblBairro = new JLabel("* Bairro:");
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBairro.setBounds(51, 195, 48, 14);
		contentPane.add(lblBairro);
		
		txtCidadeAtendido = new JTextField();
		txtCidadeAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtCidadeAtendido.setColumns(10);
		txtCidadeAtendido.setBounds(377, 212, 301, 20);
		contentPane.add(txtCidadeAtendido);
		
		JLabel lblCidade = new JLabel("* Cidade:");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCidade.setBounds(377, 195, 89, 14);
		contentPane.add(lblCidade);
		
		txtEmailAtendido = new JTextField();
		txtEmailAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtEmailAtendido.setColumns(10);
		txtEmailAtendido.setBounds(377, 278, 301, 20);
		contentPane.add(txtEmailAtendido);
		
		JLabel lblEmail = new JLabel("* E-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmail.setBounds(377, 261, 301, 14);
		contentPane.add(lblEmail);
		
		txtTelefoneAtendido = new JTextField();
		txtTelefoneAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTelefoneAtendido.setColumns(10);
		txtTelefoneAtendido.setBounds(51, 278, 301, 20);
		contentPane.add(txtTelefoneAtendido);
		
		JLabel lblTelefonesomente = new JLabel("* Telefone (Somente os N\u00FAmeros):");
		lblTelefonesomente.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTelefonesomente.setBounds(51, 261, 301, 14);
		contentPane.add(lblTelefonesomente);
		
		JLabel lblResponsvelpara = new JLabel("* Respons\u00E1vel (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblResponsvelpara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblResponsvelpara.setBounds(51, 334, 528, 14);
		contentPane.add(lblResponsvelpara);
		
		
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
		btCancelar.setBounds(366, 399, 89, 23);
		contentPane.add(btCancelar);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarAtendido) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeAtendido.getText().trim().equals("") || 
						   txtNascimentoAtendido.getText().trim().equals("") || 
						   txtNumeroAtendido.getText().trim().equals("") || 
						   txtRuaAtendido.getText().trim().equals("") || 
						   txtBairroAtendido.getText().trim().equals("") || 
						   txtCidadeAtendido.getText().trim().equals("") || 
						   txtEmailAtendido.getText().trim().equals("") || 
						   txtTelefoneAtendido.getText().trim().equals("") ||
						   txtRaAtendido.getText().trim().equals("") ||
						   cbResponsavelAtendido.getSelectedItem().toString().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into atendido values "
				            			+ "('"+txtNomeAtendido.getText()+"',"
				            			+ "'"+txtRaAtendido.getText()+"',"
				            			+ "'"+txtNisAtendido.getText()+"',"
				            			+ "'"+txtNascimentoAtendido.getText()+"',"
				            			+ "'"+txtRuaAtendido.getText()+"',"
				            			+ "'"+txtNumeroAtendido.getText()+"',"
				            			+ "'"+txtBairroAtendido.getText()+"',"
				            			+ "'"+txtCidadeAtendido.getText()+"',"
				            			+ "'"+txtTelefoneAtendido.getText()+"',"
				            			+ "'"+txtEmailAtendido.getText()+"',"
				            			+ "'"+cbResponsavelAtendido.getSelectedItem().toString().substring(0, 3)+"');";
				            	
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
					
					if(txtNomeAtendido.getText().trim().equals("") || 
							   txtNascimentoAtendido.getText().trim().equals("") || 
							   txtNumeroAtendido.getText().trim().equals("") || 
							   txtRuaAtendido.getText().trim().equals("") || 
							   txtBairroAtendido.getText().trim().equals("") || 
							   txtCidadeAtendido.getText().trim().equals("") || 
							   txtEmailAtendido.getText().trim().equals("") || 
							   txtTelefoneAtendido.getText().trim().equals("") ||
							   txtRaAtendido.getText().trim().equals("") ||
							   cbResponsavelAtendido.getSelectedItem().toString().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
					}	
					
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE atendido "
					            			+ "SET "
					            			+ "nome_atendido = '"+txtNomeAtendido.getText()+"',"
					            			+ "ra_atendido = '"+txtRaAtendido.getText()+"',"
					            			+ "nis_atendido = '"+txtNisAtendido.getText()+"',"
					            			+ "nascimento_atendido = '"+txtNascimentoAtendido.getText()+"',"
					            			+ "rua_atendido = '"+txtRuaAtendido.getText()+"',"
					            			+ "numero_atendido = '"+txtNumeroAtendido.getText()+"',"
					            			+ "bairro_atendido = '"+txtBairroAtendido.getText()+"',"
					            			+ "cidade_atendido = '"+txtCidadeAtendido.getText()+"',"
					            			+ "telefone_atendido = '"+txtTelefoneAtendido.getText()+"',"
					            			+ "email_atendido = '"+txtEmailAtendido.getText()+"',"
					            			+ "cod_responsavel = '"+cbResponsavelAtendido.getSelectedItem().toString().substring(0, 3)+"'"
					            			+ " WHERE cod_atendido = "+codigoAtendido+";";
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
		btSalvar.setBounds(267, 399, 89, 23);
		contentPane.add(btSalvar);
		
		txtRaAtendido = new JTextField();
		txtRaAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRaAtendido.setColumns(10);
		txtRaAtendido.setBounds(377, 28, 301, 20);
		contentPane.add(txtRaAtendido);
		
		JLabel lblRasomente = new JLabel("* RA (Somente os Digitos):");
		lblRasomente.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRasomente.setBounds(377, 11, 263, 14);
		contentPane.add(lblRasomente);
		cbResponsavelAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		
		
		
	//	JComboBox cbResponsavelAtendido = new JComboBox();
		cbResponsavelAtendido.setBounds(51, 350, 528, 22);
		contentPane.add(cbResponsavelAtendido);
		
		
		JButton btVerificar = new JButton("Verificar");
		btVerificar.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaResponsavel responsavel = new TelaResponsavel();
				
				String dados2[] = new String[12];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbResponsavelAtendido.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            	System.out.println(linha3);
		         

		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM responsavel WHERE cod_responsavel = "+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados2 [0] =rs.getString(1);
		            			dados2 [1] =rs.getString(2);
		            			dados2 [2] =rs.getString(3);
		            			dados2 [3] =rs.getString(4);
		            			dados2 [4] =rs.getString(5);
		            			dados2 [5] =rs.getString(6);
		            			dados2 [6] =rs.getString(7);
		            			dados2 [7] =rs.getString(8);
		            			dados2 [8] =rs.getString(9);
		            			dados2 [9] =rs.getString(10);
		            			dados2 [10] =rs.getString(11);
		            			dados2 [11] =rs.getString(12);
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        responsavel.txtNomeResponsavel.setText(dados2[1]);
		        responsavel.txtRgResponsavel.setText(dados2[2]);
		        responsavel.txtNisResponsavel.setText(dados2[3]);
		        responsavel.txtCpfResponsavel.setText(dados2[4]);
		        responsavel.txtNascimentoResponsavel.setText(dados2[5]);
		        responsavel.txtRuaResponsavel.setText(dados2[6]);
		        responsavel.txtNumeroResponsavel.setText(dados2[7]);
		        responsavel.txtBairroResponsavel.setText(dados2[8]);
		        responsavel.txtCidadeResponsavel.setText(dados2[9]);
		        responsavel.txtTelefoneResponsavel.setText(dados2[10]);
		        responsavel.txtEmailResponsavel.setText(dados2[11]);
		      
		        responsavel.txtNomeResponsavel.setEnabled(false);
		        responsavel.txtRgResponsavel.setEnabled(false);
		        responsavel.txtCpfResponsavel.setEnabled(false);
		        responsavel.txtRuaResponsavel.setEnabled(false);
		        responsavel.txtBairroResponsavel.setEnabled(false);
		        responsavel.txtNisResponsavel.setEnabled(false);
		        responsavel.txtNascimentoResponsavel.setEnabled(false);
		        responsavel.txtNumeroResponsavel.setEnabled(false);
		        responsavel.txtCidadeResponsavel.setEnabled(false);
		        responsavel.txtTelefoneResponsavel.setEnabled(false);
		        responsavel.txtEmailResponsavel.setEnabled(false);
				
			
				responsavel.setVisible(true);
				
				
			}
		});
		btVerificar.setBounds(589, 350, 89, 23);
		contentPane.add(btVerificar);
	}
	
	
}
