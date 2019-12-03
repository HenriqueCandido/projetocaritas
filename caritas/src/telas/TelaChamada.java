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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * A Classe tela chamada abre uma tela com um combo box que o usuario pode escolher a turma, pesquisar e escolher o aluno
 * da turma para abrir a tela de chamadas e colocar presença ou nao no aluno ou fechar no botao fechar.
 * @author hcand
 *
 */
public class TelaChamada extends JFrame {

	private JPanel contentPane;
	private JTable tbAlunos;
    public  JComboBox cbTurmaChamada = new JComboBox();
    
    
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
	public TelaChamada() {
		setTitle("CHAMADA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 646, 183);
		contentPane.add(scrollPane);
		
		JLabel lblEscolhaATurma = new JLabel("Escolha a turma e clique em pesquisar:");
		lblEscolhaATurma.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEscolhaATurma.setBounds(10, 11, 629, 14);
		contentPane.add(lblEscolhaATurma);
		cbTurmaChamada.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbTurmaChamada = new JComboBox();
		cbTurmaChamada.setBounds(10, 36, 529, 22);
		contentPane.add(cbTurmaChamada);
		
		JButton btPesquisar = new JButton("Pesquisar");
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String colunas[]={"Cod. Atendido:","Nome Atendido:","Cod. Matricula.:","Cod. Turma:","Nome Turma:"};
			    String dados[][] = new String[100][5];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            

		            	Object linha1;
		            	String linha2;
						String linha3;
		            	
		            	linha1 = cbTurmaChamada.getSelectedItem();
		            	linha2 = linha1.toString();// + linha2.charAt(1) + linha2.charAt(2);
		            	linha3 = linha2.substring(0, 3);
		            	System.out.println(linha3);
		            	
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT turma_atendido.cod_atendido,atendido.nome_atendido,"
		            			+ "turma_atendido.cod_turma_atendido, turma_atendido.cod_turma, turma.nome_turma"
		            			+ " FROM atendido, turma_atendido, turma"
		            			+ " WHERE atendido.cod_atendido = turma_atendido.cod_atendido AND "
		            			+ " turma.cod_turma = turma_atendido.cod_turma AND "
		            			+ "turma_atendido.cod_turma = '"+linha3+"';";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            			dados[x][3]= rs.getString(4);
		            			dados[x][4]= rs.getString(5);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
		        tbAlunos = new JTable(dados,colunas);
				scrollPane.setViewportView(tbAlunos);
				
				
				
			}
		});
		btPesquisar.setFont(new Font("Arial", Font.PLAIN, 11));
		btPesquisar.setBounds(550, 36, 106, 23);
		contentPane.add(btPesquisar);
		
	//	JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(10, 94, 646, 183);
	//	contentPane.add(scrollPane);
		// Pegar aqui
		//tbAlunos = new JTable();
		//scrollPane.setViewportView(tbAlunos);
		
		JLabel lblSelecioneOAluno = new JLabel("Selecione o aluno e clique em abrir:");
		lblSelecioneOAluno.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelecioneOAluno.setBounds(10, 69, 629, 14);
		contentPane.add(lblSelecioneOAluno);
		
		JButton btAbrir = new JButton("Abrir ");
		btAbrir.setFont(new Font("Arial", Font.PLAIN, 11));
		btAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				if(tbAlunos == null) {
					JOptionPane.showMessageDialog(null, "Escolha uma turma e clique em pesquisar!");
					
				}
				else {
				int linhaTabela = tbAlunos.getSelectedRow();
				
				if(linhaTabela == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Registro");
				}
				else {
					
						Object linha = tbAlunos.getValueAt(linhaTabela,0);
				
						
					
						TelaChamada02 chamada02 = new TelaChamada02();
						
						chamada02.txtAtendidoChamada02.setText(tbAlunos.getValueAt(linhaTabela,1).toString());
						chamada02.txtTurmaChamada02.setText(tbAlunos.getValueAt(linhaTabela,4).toString());
						
						chamada02.codigoMatriculaChamada = Integer.parseInt(tbAlunos.getValueAt(linhaTabela, 2).toString());
						
						chamada02.cbPresenteChamada02.addItem("Sim");
						chamada02.cbPresenteChamada02.addItem("Não");
						
						String dataatual, ultimadatabanco;
						
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
						
						dataatual = formatador.format(cal.getTime());
						chamada02.txtChamadaChamada02.setText(dataatual);
						
						
						
						
						/*
						String dados[] = new String[1];
					
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT data_chamada "
				            		    	+ "FROM chamada order by data_chamada;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	while(rs.next()) {
				            			dados [0] =rs.getString(1);
				            	}
				            	
				            	
				            	
				            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				        
				        ultimadatabanco = AjustarData.ajustar(dados[0]);
				        
				        System.out.println(dados[0]);
				        System.out.println(ultimadatabanco);
				        System.out.println(dataatual);
				        
				        if (dataatual.equals(ultimadatabanco)) {
				        	JOptionPane.showMessageDialog(null, "igual");
				        	chamada02.txtChamadaChamada02.setText(dataatual);
				        }
				        else {
				        	JOptionPane.showMessageDialog(null, "Diferente");
				        	chamada02.txtChamadaChamada02.setText(dataatual);
				        }
				     */
						chamada02.setVisible(true);
						
					
				}
				}
				
				
				
			}
		});
		btAbrir.setBounds(276, 284, 89, 23);
		contentPane.add(btAbrir);
		
		JButton btCancelar = new JButton("Fechar Tela de Chamada");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btCancelar.setBounds(203, 318, 225, 23);
		contentPane.add(btCancelar);
	}
}
