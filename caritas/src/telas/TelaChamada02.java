package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * Essa classe Tela Chamada 02 abre uma tela que permite o usuario colocar presença ou nao no aluno de determinada turma.
 * O botao salvar grava no banco de dados e cancelar nao salva, os dois fechando a tela.
 * @author hcand
 *
 */
public class TelaChamada02 extends JFrame {

	private JPanel contentPane;
	public  JTextField txtAtendidoChamada02;
	public  JTextField txtChamadaChamada02;
	public  JTextField txtTurmaChamada02;
	public  JComboBox cbPresenteChamada02 = new JComboBox();
	public int codigoMatriculaChamada;
	public int presente;
	
	
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
	public TelaChamada02() {
		setTitle("CHAMADA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAtendido = new JLabel("Atendido:");
		lblAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAtendido.setBounds(24, 21, 301, 14);
		contentPane.add(lblAtendido);
		
		txtAtendidoChamada02 = new JTextField();
		txtAtendidoChamada02.setFont(new Font("Arial", Font.PLAIN, 11));
		txtAtendidoChamada02.setEditable(false);
		txtAtendidoChamada02.setColumns(10);
		txtAtendidoChamada02.setBounds(24, 38, 301, 20);
		contentPane.add(txtAtendidoChamada02);
		
		JLabel lblChamada = new JLabel("Chamada:");
		lblChamada.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChamada.setBounds(24, 80, 288, 14);
		contentPane.add(lblChamada);
		
		txtChamadaChamada02 = new JTextField();
		txtChamadaChamada02.setFont(new Font("Arial", Font.PLAIN, 11));
		txtChamadaChamada02.setEditable(false);
		txtChamadaChamada02.setColumns(10);
		txtChamadaChamada02.setBounds(24, 97, 301, 20);
		contentPane.add(txtChamadaChamada02);
		
		JLabel lblTurma = new JLabel("Turma:\r\n");
		lblTurma.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTurma.setBounds(24, 142, 288, 14);
		contentPane.add(lblTurma);
		
		txtTurmaChamada02 = new JTextField();
		txtTurmaChamada02.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTurmaChamada02.setEditable(false);
		txtTurmaChamada02.setColumns(10);
		txtTurmaChamada02.setBounds(24, 159, 301, 20);
		contentPane.add(txtTurmaChamada02);
		
		JLabel lblPesente = new JLabel("* Presente?");
		lblPesente.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPesente.setBounds(24, 205, 301, 14);
		contentPane.add(lblPesente);
		cbPresenteChamada02.setFont(new Font("Arial", Font.PLAIN, 11));
		
		//JComboBox cbPresenteChamada02 = new JComboBox();
		cbPresenteChamada02.setBounds(24, 224, 301, 22);
		contentPane.add(cbPresenteChamada02);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		

				
				String ultimadatabanco;		
				
				String dados[] = new String[2];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_chamada, data_chamada "
		            		    	+ "FROM chamada order by data_chamada;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	while(rs.next()) {
		            			dados [0] =rs.getString(1);
		            			dados [1] =rs.getString(2);
		            	}
		            	
		            	
		            	
		            }

					// Handle any errors that may have occurred.
					catch (Exception e1) {
						e1.printStackTrace();
					}
		        
		        ultimadatabanco = AjustarData.ajustar(dados[1]);
		        
		       
		        
		        if (txtChamadaChamada02.getText().equals(ultimadatabanco)) {
		        	
		        }
		        else {
		        	 try {
			        		// Establish the connection.
			        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			            	con = DriverManager.getConnection(connectionUrl);

			            	// Create and execute an SQL statement that returns some data.
			            	String SQL = "insert into chamada values "
			            			+ "('"+txtChamadaChamada02.getText()+"');";
			            	
			            	stmt = con.createStatement();
			            	stmt.execute(SQL);
			            	
			            	SQL = "SELECT cod_chamada, data_chamada "
		            		    	+ "FROM chamada order by data_chamada;";
			            	stmt = con.createStatement();
			            	rs = stmt.executeQuery(SQL);
		            		
			            	// Iterate through the data in the result set and display it. 	
		            	
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
			            	while(rs.next()) {
			            		dados [0] =rs.getString(1);
			            		dados [1] =rs.getString(2);
			            	}
			            	
			            	
			            }

		        	 	// Handle any errors that may have occurred.
		        	 	catch (Exception e1) {
		        	 		e1.printStackTrace();
		        	 		JOptionPane.showMessageDialog(null, "Erro ao Salvar");
		        	 	}
		        	 
		        	 	ultimadatabanco = AjustarData.ajustar(dados[1]);
		        }
				
		        if(cbPresenteChamada02.getSelectedItem().toString().equals("Sim")) {
		        	presente = 1;
		        }
		        else {
		        	presente = 0;
		        }
		        
				
				
				
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into turma_atendido_chamada values "
				            			+ "('"+codigoMatriculaChamada+"',"
				            			+ "'"+dados[0]+"',"
				            			+ "'"+presente+"');";
				            	
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
				
				
				
			}
		});
		btSalvar.setFont(new Font("Arial", Font.PLAIN, 11));
		btSalvar.setBounds(86, 281, 99, 23);
		contentPane.add(btSalvar);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setBounds(195, 281, 89, 23);
		contentPane.add(btCancelar);
	}

}
