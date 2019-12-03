package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * a Classe tela Atividades cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes a atividade.
 * 
 * @author hcand
 *
 */
public class TelaAtividades extends JFrame {

	private JPanel contentPane;
	public JComboBox cbInstrutorAtividade = new JComboBox();
	public JComboBox cbOficinaAtividade = new JComboBox();
	public JComboBox cbEspacoAtividade = new JComboBox();
	public JComboBox cbPeriodoAtividade = new JComboBox();
	public String codigoInstrutor;
	public String codigoOficina;	
	public String codigoEspaco;
	public String codigoPeriodo;
	public int tipoDoSalvarAtividades;
	public Object codigoAtividades;
	
	
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
	public TelaAtividades() {
		setTitle("CADASTRO ATIVIDADES");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstrutorpara = new JLabel("* Instrutor (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblInstrutorpara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInstrutorpara.setBounds(53, 43, 528, 14);
		contentPane.add(lblInstrutorpara);
		cbInstrutorAtividade.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbInstrutorAtividade = new JComboBox();
		cbInstrutorAtividade.setBounds(53, 59, 528, 22);
		contentPane.add(cbInstrutorAtividade);
		
		JButton btVerificarInstrutor = new JButton("Verificar");
		btVerificarInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarInstrutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaInstrutor instrutor = new TelaInstrutor();
				
				String dados3[] = new String[12];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbInstrutorAtividade.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            //	System.out.println(linha3);
		            	
		         
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM instrutor WHERE cod_instrutor = "+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados3 [0] =rs.getString(1);
		            			dados3 [1] =rs.getString(2);
		            			dados3 [2] =rs.getString(3);
		            			dados3 [3] =rs.getString(4);
		            			dados3 [4] =rs.getString(5);
		            			dados3 [5] =rs.getString(6);
		            			dados3 [6] =rs.getString(7);
		            			dados3 [7] =rs.getString(8);
		            			dados3 [8] =rs.getString(9);
		            			dados3 [9] =rs.getString(10);
		            			dados3 [10] =rs.getString(11);
		            			dados3 [11] =rs.getString(12);
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        instrutor.txtNomeInstrutor.setText(dados3[1]);
		        instrutor.txtEspecialidadeInstrutor.setText(dados3[2]);
		        instrutor.txtRgInstrutor.setText(dados3[3]);
		        instrutor.txtCpfInstrutor.setText(dados3[4]);
		        instrutor.txtNascimentoInstrutor.setText(dados3[5]);
		        instrutor.txtRuaInstrutor.setText(dados3[6]);
		        instrutor.txtNumeroInstrutor.setText(dados3[7]);
		        instrutor.txtBairroInstrutor.setText(dados3[8]);
		        instrutor.txtCidadeInstrutor.setText(dados3[9]);
		        instrutor.txtTelefoneInstrutor.setText(dados3[10]);
		        instrutor.txtEmailInstrutor.setText(dados3[11]);
		        
		        instrutor.txtNomeInstrutor.setEnabled(false);
		        instrutor.txtEspecialidadeInstrutor.setEnabled(false);
		        instrutor.txtRgInstrutor.setEnabled(false);
		        instrutor.txtCpfInstrutor.setEnabled(false);
		        instrutor.txtNascimentoInstrutor.setEnabled(false);
		        instrutor.txtRuaInstrutor.setEnabled(false);
		        instrutor.txtNumeroInstrutor.setEnabled(false);
		        instrutor.txtBairroInstrutor.setEnabled(false);
		        instrutor.txtCidadeInstrutor.setEnabled(false);
		        instrutor.txtTelefoneInstrutor.setEnabled(false);
		        instrutor.txtEmailInstrutor.setEnabled(false);
		      
		        
			
			
				instrutor.setVisible(true);

			}
		});
		btVerificarInstrutor.setBounds(591, 59, 89, 23);
		contentPane.add(btVerificarInstrutor);
		
		JLabel lblOficinapara = new JLabel("* Oficina (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblOficinapara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblOficinapara.setBounds(53, 111, 528, 14);
		contentPane.add(lblOficinapara);
		cbOficinaAtividade.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbOficinaAtividade = new JComboBox();
		cbOficinaAtividade.setBounds(53, 127, 528, 22);
		contentPane.add(cbOficinaAtividade);
		
		JButton btVerificarOficina = new JButton("Verificar");
		btVerificarOficina.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaOficina oficina = new TelaOficina();
				
				String dados4[] = new String[5];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbOficinaAtividade.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM oficina WHERE cod_oficina = "+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados4 [0] =rs.getString(1);
		            			dados4 [1] =rs.getString(2);
		            			dados4 [2] =rs.getString(3);
		            			dados4 [3] =rs.getString(4);
		            			dados4 [4] =rs.getString(5);
		            		
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        oficina.txtNomeOficina.setText(dados4[1]);
		        oficina.txtDescricaoOficina.setText(dados4[2]);
		        oficina.txtObjetivoOficina.setText(dados4[3]);
		        oficina.txtPublicoOficina.setText(dados4[4]);
		      
		        oficina.txtNomeOficina.setEnabled(false);
		        oficina.txtDescricaoOficina.setEnabled(false);
		        oficina.txtObjetivoOficina.setEnabled(false);
		        oficina.txtPublicoOficina.setEnabled(false);

				oficina.setVisible(true);
				
				
				
				
				
			}
		});
		btVerificarOficina.setBounds(591, 127, 89, 23);
		contentPane.add(btVerificarOficina);
		
		JLabel lblEspaopara = new JLabel("* Espa\u00E7o (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblEspaopara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEspaopara.setBounds(53, 186, 528, 14);
		contentPane.add(lblEspaopara);
		cbEspacoAtividade.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbEspacoAtividade = new JComboBox();
		cbEspacoAtividade.setBounds(53, 202, 528, 22);
		contentPane.add(cbEspacoAtividade);
		
		JButton btVerificarEspaco = new JButton("Verificar");
		btVerificarEspaco.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarEspaco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaEspaco espaco = new TelaEspaco();
				
				String dados5[] = new String[3];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbEspacoAtividade.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM espaco WHERE cod_espaco = "+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados5 [0] =rs.getString(1);
		            			dados5 [1] =rs.getString(2);
		            			dados5 [2] =rs.getString(3);
		            
		            		
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        espaco.txtNomeEspaco.setText(dados5[1]);
		        espaco.txtLugaresEspaco.setText(dados5[2]);
		       
		        espaco.txtNomeEspaco.setEnabled(false);
		        espaco.txtLugaresEspaco.setEnabled(false);

				espaco.setVisible(true);
				
				
				
				
				
			}
		});
		btVerificarEspaco.setBounds(591, 202, 89, 23);
		contentPane.add(btVerificarEspaco);
		
		JLabel lblPeriodopara = new JLabel("* Periodo (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblPeriodopara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPeriodopara.setBounds(53, 257, 528, 14);
		contentPane.add(lblPeriodopara);
		cbPeriodoAtividade.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbPeriodoAtividade = new JComboBox();
		cbPeriodoAtividade.setBounds(53, 273, 528, 22);
		contentPane.add(cbPeriodoAtividade);
		
		JButton btVerificarPeriodo = new JButton("Verificar");
		btVerificarPeriodo.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaPeriodo periodo = new TelaPeriodo();
				
				String dados6[] = new String[4];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbPeriodoAtividade.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM periodo WHERE cod_periodo = "+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados6 [0] =rs.getString(1);
		            			dados6 [1] =rs.getString(2);
		            			dados6 [2] =rs.getString(3);
		            			dados6 [3] =rs.getString(4);
		            
		            		
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        periodo.txtDiaPeriodo.setText(dados6[1]);
		        periodo.txtPeriodoPeriodo.setText(dados6[2]);
		        periodo.txtHorarioPeriodo.setText(dados6[3]);
		        
		        periodo.txtDiaPeriodo.setEnabled(false);
		        periodo.txtPeriodoPeriodo.setEnabled(false);
		        periodo.txtHorarioPeriodo.setEnabled(false);
		        
				
			
				periodo.setVisible(true);
				
			}
		});
		btVerificarPeriodo.setBounds(591, 273, 89, 23);
		contentPane.add(btVerificarPeriodo);
		
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
		btCancelar.setBounds(361, 346, 89, 23);
		contentPane.add(btCancelar);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarAtividades) {
				case 1: // colocar codigo de insert aqui
				
						String[] cod_atividadeCriado = new String[3];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into atividade values "
				            			+ "('"+cbInstrutorAtividade.getSelectedItem().toString().substring(0, 3)+"',"
				            			+ "'"+cbOficinaAtividade.getSelectedItem().toString().substring(0, 3)+"');";
				            	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            	
				            	System.out.println(cbInstrutorAtividade.getSelectedItem().toString().substring(0, 3));
				            	System.out.println(cbOficinaAtividade.getSelectedItem().toString().substring(0, 3));
				            	//
				            	
				            	SQL = "SELECT * "
			            		    	+ "FROM atividade "
			            		    	+ "WHERE cod_instrutor = '"+cbInstrutorAtividade.getSelectedItem().toString().substring(0, 3)+"' AND"
			            		    	+ " cod_oficina = '"+cbOficinaAtividade.getSelectedItem().toString().substring(0, 3)+"';";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            	
				            	while(rs.next()) {
			            			cod_atividadeCriado[0] = rs.getString(1);
			            			cod_atividadeCriado[1] = rs.getString(2);
			            			cod_atividadeCriado[2] = rs.getString(3);
			            			
				            	}
				            	
				            	
				            	System.out.println(cod_atividadeCriado[0]);
				            	
				            	//
				            	
				            	SQL = "insert into espaco_atividade values "
				            			+ "('"+cbEspacoAtividade.getSelectedItem().toString().substring(0, 3)+"',"
				            			+ "'"+cod_atividadeCriado[0]+"');";
				            	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            	
				            	SQL = "insert into periodo_atividade values "
				            			+ "('"+cbPeriodoAtividade.getSelectedItem().toString().substring(0, 3)+"',"
				            			+ "'"+cod_atividadeCriado[0]+"');";
				            	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            	
				            	
				            	
				            	JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
				            	dispose();
		
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Erro ao Salvar.");
							JOptionPane.showMessageDialog(null, "Verifique se a combinação instrutor | oficina já existe.");
						}
				        
				     

					//JOptionPane.showMessageDialog(null, "Usuario Um Criar");
					break;
				case 2: // colocar codigo alterar aqui
					
			
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE atividade "
					            			+ "SET "
					            			+ "cod_instrutor = '"+cbInstrutorAtividade.getSelectedItem().toString().substring(0, 3)+"',"
					            			+ "cod_oficina = '"+cbOficinaAtividade.getSelectedItem().toString().substring(0, 3)+"'"
					            			+ " WHERE cod_atividade = "+codigoAtividades+";";
					            	
					            	stmt = con.createStatement();
					            	stmt.execute(SQL);
					            	
					            	SQL = "UPDATE espaco_atividade "
					            			+ "SET "
					            			+ "cod_espaco = '"+cbEspacoAtividade.getSelectedItem().toString().substring(0, 3)+"'"
					            			+ " WHERE cod_atividade = "+codigoAtividades+";";
					            	
					            	stmt = con.createStatement();
					            	stmt.execute(SQL);
					            	
					              	SQL = "UPDATE periodo_atividade "
					            			+ "SET "
					            			+ "cod_periodo = '"+cbPeriodoAtividade.getSelectedItem().toString().substring(0, 3)+"'"
					            			+ " WHERE cod_atividade = "+codigoAtividades+";";
					            	
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
		btSalvar.setBounds(262, 346, 89, 23);
		contentPane.add(btSalvar);
	}
}
