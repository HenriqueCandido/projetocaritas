package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
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
 * a Classe tela Turmas cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes as turmas.
 * 
 * @author hcand
 *
 */
public class TelaTurmas extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeTurma;
	public JComboBox cbAtividadeTurmas = new JComboBox();
	public int tipoDoSalvarTurmas;
	public Object codigoTurmas;
	
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
	public TelaTurmas() {
		setTitle("CADASTRO TURMAS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cbAtividadeTurmas.setFont(new Font("Arial", Font.PLAIN, 11));
		
	//	JComboBox cbAtividadeTurmas = new JComboBox();
		cbAtividadeTurmas.setBounds(29, 91, 355, 22);
		contentPane.add(cbAtividadeTurmas);
		
		JButton btVerificarAtividade = new JButton("Verificar");
		btVerificarAtividade.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAtividades atividades = new TelaAtividades();
				
				String dados8[] = new String[11];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            
		            	
		            	linha1 = cbAtividadeTurmas.getSelectedItem();
		            	
		            
		            	
		            	
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "select atividade.cod_atividade, atividade.cod_instrutor, nome_instrutor, atividade.cod_oficina," + 
		            			  "nome_oficina, espaco_atividade.cod_espaco, nome_espaco," +
		            			  "periodo_atividade.cod_periodo, dia_periodo, periodo_periodo, horario_periodo" +
		            			  " FROM  atividade, instrutor, oficina, espaco_atividade, espaco, periodo_atividade, periodo" +
		            			  " WHERE atividade.cod_instrutor = instrutor.cod_instrutor AND " +
		            					" atividade.cod_oficina = oficina.cod_oficina AND " +
		            					" atividade.cod_atividade = espaco_atividade.cod_atividade AND " +
		            					" espaco_atividade.cod_espaco = espaco.cod_espaco AND " +
		            					" atividade.cod_atividade = periodo_atividade.cod_atividade AND " +
		            					" periodo_atividade.cod_periodo = periodo.cod_periodo AND " +
		            					" atividade.cod_atividade = "+linha1+"; ";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados8 [0] =rs.getString(1);
		            			dados8 [1] =rs.getString(2);
		            			dados8 [2] =rs.getString(3);
		            			dados8 [3] =rs.getString(4);
		            			dados8 [4] =rs.getString(5);
		            			dados8 [5] =rs.getString(6);
		            			dados8 [6] =rs.getString(7);
		            			dados8 [7] =rs.getString(8);
		            			dados8 [8] =rs.getString(9);
		            			dados8 [9] =rs.getString(10);
		            			dados8 [10] =rs.getString(11);
		            		
		            		
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		      
		        atividades.cbInstrutorAtividade.addItem(dados8[1]+"  -  "+dados8[2]);
		        atividades.cbOficinaAtividade.addItem(dados8[3]+"  -  "+dados8[4]);
		        atividades.cbEspacoAtividade.addItem(dados8[5]+"  -  "+dados8[6]);
		        atividades.cbPeriodoAtividade.addItem(dados8[7]+"  -  "+dados8[8]+" - "+dados8[9]+" - "+dados8[10]);
		       
		      //  atividades.codigoInstrutor = dados8[1];
		       // atividades.codigoOficina = dados8[3];
		      //  atividades.codigoEspaco = dados8[5];
		      //  atividades.codigoPeriodo = dados8[7];
		       
		        
		        atividades.cbInstrutorAtividade.setEnabled(false);
		        atividades.cbOficinaAtividade.setEnabled(false);
		        atividades.cbEspacoAtividade.setEnabled(false);
		        atividades.cbPeriodoAtividade.setEnabled(false);
			
			
				atividades.setVisible(true);
				
				
				
			}
		});
		btVerificarAtividade.setBounds(398, 91, 89, 23);
		contentPane.add(btVerificarAtividade);
		
		JLabel lblAtividadepara = new JLabel("* Atividade (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblAtividadepara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAtividadepara.setBounds(29, 75, 528, 14);
		contentPane.add(lblAtividadepara);
		
		JLabel lblNomeDa = new JLabel("* Nome da Turma:");
		lblNomeDa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNomeDa.setBounds(29, 27, 355, 14);
		contentPane.add(lblNomeDa);
		
		txtNomeTurma = new JTextField();
		txtNomeTurma.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeTurma.setColumns(10);
		txtNomeTurma.setBounds(29, 44, 458, 20);
		contentPane.add(txtNomeTurma);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarTurmas) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeTurma.getText().trim().equals("") ||
						   cbAtividadeTurmas.getSelectedItem().toString().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into turma values "
				            			+ "('"+txtNomeTurma.getText()+"',"
				            			+ "'"+cbAtividadeTurmas.getSelectedItem().toString()+"');";
				            	
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
					
							if(txtNomeTurma.getText().trim().equals("") ||
							   cbAtividadeTurmas.getSelectedItem().toString().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
							}
					
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE turma "
					            			+ "SET "
					            			+ "nome_turma = '"+txtNomeTurma.getText()+"',"
					            			+ "cod_atividade = '"+cbAtividadeTurmas.getSelectedItem().toString()+"'"
					            			+ " WHERE cod_turma = "+codigoTurmas+";";
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
		btSalvar.setBounds(159, 146, 89, 23);
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
		btCancelar.setBounds(258, 146, 89, 23);
		contentPane.add(btCancelar);
	}
}
