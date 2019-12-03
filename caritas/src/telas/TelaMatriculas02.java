package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
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
 * A Classe Tela Matricula abre o formulário para que o usuario possa efetuar a matricula
 * escolhendo o atendido e a turma que deseja matricula-lo.
 * @author hcand
 *
 */
public class TelaMatriculas02 extends JFrame {

	private JPanel contentPane;
	public JComboBox cbPessoaMatricula = new JComboBox();
	public JComboBox cbTurmaMatricula = new JComboBox();
	
	
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
	public TelaMatriculas02() {
		setTitle("MATRICULA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cbPessoaMatricula.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbPessoaMatricula = new JComboBox();
		cbPessoaMatricula.setBounds(36, 77, 355, 22);
		contentPane.add(cbPessoaMatricula);
		
		JLabel lblPessoapara = new JLabel("* Atendido: (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblPessoapara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPessoapara.setBounds(36, 61, 453, 14);
		contentPane.add(lblPessoapara);
		
		JButton btVerificarPessoa = new JButton("Verificar");
		btVerificarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAtendido atendido = new TelaAtendido();
				
				
				String dados7[] = new String[13];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbPessoaMatricula.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            	System.out.println(linha3);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "select cod_atendido,nome_atendido," + 
		            			"ra_atendido,nis_atendido,nascimento_atendido,rua_atendido," + 
		            			"numero_atendido,bairro_atendido,cidade_atendido,telefone_atendido," + 
		            			"email_atendido,atendido.cod_responsavel,nome_responsavel " + 
		            			" FROM atendido, responsavel" + 
		            			" WHERE atendido.cod_responsavel = responsavel.cod_responsavel AND " + 
		            			" cod_atendido ="+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados7 [0] =rs.getString(1);
		            			dados7 [1] =rs.getString(2);
		            			dados7 [2] =rs.getString(3);
		            			dados7 [3] =rs.getString(4);
		            			dados7 [4] =rs.getString(5);
		            			dados7 [5] =rs.getString(6);
		            			dados7 [6] =rs.getString(7);
		            			dados7 [7] =rs.getString(8);
		            			dados7 [8] =rs.getString(9);
		            			dados7 [9] =rs.getString(10);
		            			dados7 [10] =rs.getString(11);
		            			dados7 [11] =rs.getString(12);
		            			dados7 [12] =rs.getString(13);
		            		
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		        
		        atendido.txtNomeAtendido.setText(dados7[1]);
		        atendido.txtRaAtendido.setText(dados7[2]);
		        atendido.txtNisAtendido.setText(dados7[3]);
		        atendido.txtNascimentoAtendido.setText(AjustarData.ajustar(dados7[4]));
		        atendido.txtRuaAtendido.setText(dados7[5]);
		        atendido.txtNumeroAtendido.setText(dados7[6]);
		        atendido.txtBairroAtendido.setText(dados7[7]);
		        atendido.txtCidadeAtendido.setText(dados7[8]);
		        atendido.txtTelefoneAtendido.setText(dados7[9]);
		        atendido.txtEmailAtendido.setText(dados7[10]);
		        
		       // atendido.codigoResponsavel = dados7[11];
		        
		        atendido.cbResponsavelAtendido.addItem(dados7[11]+"  -  "+dados7[12]);
		        
		        atendido.txtNomeAtendido.setEnabled(false);
		        atendido.txtRaAtendido.setEnabled(false);
		        atendido.txtNisAtendido.setEnabled(false);
		        atendido.txtNascimentoAtendido.setEnabled(false);
		        atendido.txtRuaAtendido.setEnabled(false);
		        atendido.txtNumeroAtendido.setEnabled(false);
		        atendido.txtBairroAtendido.setEnabled(false);
		        atendido.txtCidadeAtendido.setEnabled(false);
		        atendido.txtTelefoneAtendido.setEnabled(false);
		        atendido.txtEmailAtendido.setEnabled(false);

		        atendido.cbResponsavelAtendido.setEnabled(false);
		       
		        
				atendido.setVisible(true);
				
				
				
				
				
			}
		});
		btVerificarPessoa.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarPessoa.setBounds(402, 77, 89, 23);
		contentPane.add(btVerificarPessoa);
		cbTurmaMatricula.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbTurmaMatricula = new JComboBox();
		cbTurmaMatricula.setBounds(36, 142, 355, 22);
		contentPane.add(cbTurmaMatricula);
		
		JButton btVerificarTurma = new JButton("Verificar");
		btVerificarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaTurmas turmas = new TelaTurmas();
				
				String dados9[] = new String[3];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            	
		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbTurmaMatricula.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            	System.out.println(linha3);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM turma"
		            			+ " WHERE cod_turma ="+linha3+";";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados9 [0] =rs.getString(1);
		            			dados9 [1] =rs.getString(2);
		            			dados9 [2] =rs.getString(3);
		    
		            	}
		            	
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
		      
		        turmas.txtNomeTurma.setText(dados9[1]);
		        turmas.cbAtividadeTurmas.addItem(dados9[2]);

		       
		        turmas.txtNomeTurma.setEnabled(false);
		        turmas.cbAtividadeTurmas.setEnabled(false);
		  
			
				turmas.setVisible(true);
			
				
				
			}
		});
		btVerificarTurma.setFont(new Font("Arial", Font.PLAIN, 11));
		btVerificarTurma.setBounds(402, 142, 89, 23);
		contentPane.add(btVerificarTurma);
		
		JLabel lblTurmapara = new JLabel("* Turma: (Para ver mais informa\u00E7\u00F5es, escolha e clique em VERIFICAR):");
		lblTurmapara.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTurmapara.setBounds(36, 126, 453, 14);
		contentPane.add(lblTurmapara);
		
		
		/**
		 * Salva no banco de dados a matricula e fecha a tela.
		 */
		JButton btMatricular = new JButton("Matricular");
		btMatricular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);

		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "insert into turma_atendido values ("
		            			+ "'"+cbPessoaMatricula.getSelectedItem().toString().substring(0, 3)+"',"
		            			+ "'"+cbTurmaMatricula.getSelectedItem().toString().substring(0, 3)+"');";
		            	
		            	stmt = con.createStatement();
		            	stmt.execute(SQL);
		            	JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		            	dispose();
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao Salvar");
					JOptionPane.showMessageDialog(null, "Verifique se atendido já está matriculado na turma!");
				}
				
				
				
				
			}
		});
		btMatricular.setFont(new Font("Arial", Font.PLAIN, 11));
		btMatricular.setBounds(158, 206, 99, 23);
		contentPane.add(btMatricular);
		
		/**
		 * Fecha a tela sem salvar.
		 */
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setBounds(267, 206, 89, 23);
		contentPane.add(btCancelar);
	}

}
