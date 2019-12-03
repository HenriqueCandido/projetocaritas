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
 * a Classe tela periodo cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes ao periodo.
 * @author hcand
 *
 */
public class TelaPeriodo extends JFrame {

	private JPanel contentPane;
	public JTextField txtDiaPeriodo;
	public JTextField txtPeriodoPeriodo;
	public JTextField txtHorarioPeriodo;
	public int tipoDoSalvarPeriodo;
	public Object codigoPeriodo;
	
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
	public TelaPeriodo() {
		setTitle("CADASTRO PERIODO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtDiaPeriodo = new JTextField();
		txtDiaPeriodo.setFont(new Font("Arial", Font.PLAIN, 11));
		txtDiaPeriodo.setColumns(10);
		txtDiaPeriodo.setBounds(24, 46, 296, 20);
		contentPane.add(txtDiaPeriodo);
		
		JLabel lblDiaex = new JLabel("* Dia (ex: Segunda-feira):");
		lblDiaex.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDiaex.setBounds(24, 24, 296, 23);
		contentPane.add(lblDiaex);
		
		txtPeriodoPeriodo = new JTextField();
		txtPeriodoPeriodo.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPeriodoPeriodo.setColumns(10);
		txtPeriodoPeriodo.setBounds(24, 112, 296, 20);
		contentPane.add(txtPeriodoPeriodo);
		
		JLabel lblPeriodomanh = new JLabel("* Periodo (Manh\u00E3, Tarde ou Noite):");
		lblPeriodomanh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPeriodomanh.setBounds(24, 90, 296, 23);
		contentPane.add(lblPeriodomanh);
		
		txtHorarioPeriodo = new JTextField();
		txtHorarioPeriodo.setFont(new Font("Arial", Font.PLAIN, 11));
		txtHorarioPeriodo.setColumns(10);
		txtHorarioPeriodo.setBounds(24, 179, 296, 20);
		contentPane.add(txtHorarioPeriodo);
		
		JLabel lblHorrioex = new JLabel("* Hor\u00E1rio (ex: das 8 as 10):");
		lblHorrioex.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHorrioex.setBounds(24, 157, 296, 23);
		contentPane.add(lblHorrioex);
		
		
		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarPeriodo) {
				case 1: // colocar codigo de insert aqui
				
						if(txtDiaPeriodo.getText().trim().equals("") || 
						   txtPeriodoPeriodo.getText().trim().equals("") || 
						   txtHorarioPeriodo.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into periodo values "
				            			+ "('"+txtDiaPeriodo.getText()+"',"
				            			+ "'"+txtPeriodoPeriodo.getText()+"',"
				            			+ "'"+txtHorarioPeriodo.getText()+"');";
				            	
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
					
					if(txtDiaPeriodo.getText().trim().equals("") || 
						txtPeriodoPeriodo.getText().trim().equals("") || 
						txtHorarioPeriodo.getText().trim().equals("")) {  
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
						break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE periodo "
					            			+ "SET "
					            			+ "dia_periodo = '"+txtDiaPeriodo.getText()+"',"
					            			+ "periodo_periodo = '"+txtPeriodoPeriodo.getText()+"',"
					            			+ "horario_periodo = '"+txtHorarioPeriodo.getText()+"'"
					            			+ " WHERE cod_periodo = "+codigoPeriodo+";";
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
		btSalvar.setBounds(78, 227, 89, 23);
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
		btCancelar.setBounds(177, 227, 89, 23);
		contentPane.add(btCancelar);
	}

}
