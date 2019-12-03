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
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * a Classe tela Oficina cria o formulario com os campos e botoes necessarios para cadastrar, alterar e visualizar
 * os dados referentes a oficina.
 * 
 * @author hcand
 *
 */
public class TelaOficina extends JFrame {

	private JPanel contentPane;
	public JTextField txtNomeOficina;
	public JTextField txtPublicoOficina;
	public JTextArea txtDescricaoOficina = new JTextArea();
	public JTextArea txtObjetivoOficina = new JTextArea();
	public int tipoDoSalvarOficina;
	public Object codigoOficina;
	
	
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
	public TelaOficina() {
		setResizable(false);
		setTitle("CADASTRO OFICINA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeOficina = new JTextField();
		txtNomeOficina.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNomeOficina.setColumns(10);
		txtNomeOficina.setBounds(29, 47, 296, 20);
		contentPane.add(txtNomeOficina);
		
		JLabel lblNomeDa = new JLabel("* Nome da Oficina:");
		lblNomeDa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNomeDa.setBounds(29, 25, 296, 23);
		contentPane.add(lblNomeDa);
		
		JLabel lblDescrio = new JLabel("* Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDescrio.setBounds(29, 95, 296, 23);
		contentPane.add(lblDescrio);
		
		JLabel lblObjetivo = new JLabel("* Objetivo:");
		lblObjetivo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblObjetivo.setBounds(29, 197, 296, 23);
		contentPane.add(lblObjetivo);
		
		txtPublicoOficina = new JTextField();
		txtPublicoOficina.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPublicoOficina.setColumns(10);
		txtPublicoOficina.setBounds(29, 315, 296, 20);
		contentPane.add(txtPublicoOficina);
		
		JLabel lblPblico = new JLabel("* P\u00FAblico:");
		lblPblico.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPblico.setBounds(29, 293, 296, 23);
		contentPane.add(lblPblico);
		
		

		/**
		 * O Botao salvar verifica qual botao foi clicado na tela principal, se foi criar, editar ou visualzar e 
		 * executa a função escolhida. Se algum campo obrigatorio nao foi preenchido ele avisa.
		 */
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tipoDoSalvarOficina) {
				case 1: // colocar codigo de insert aqui
				
						if(txtNomeOficina.getText().trim().equals("") || 
						   txtDescricaoOficina.getText().trim().equals("") || 
						   txtObjetivoOficina.getText().trim().equals("") || 
						   txtPublicoOficina.getText().trim().equals("")) {  
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
							break;
						}
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);

				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "insert into oficina values "
				            			+ "('"+txtNomeOficina.getText()+"',"
				            			+ "'"+txtDescricaoOficina.getText()+"',"
				            			+ "'"+txtObjetivoOficina.getText()+"',"
				            			+ "'"+txtPublicoOficina.getText()+"');";
				            	
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
					
					if(txtNomeOficina.getText().trim().equals("") || 
							   txtDescricaoOficina.getText().trim().equals("") || 
							   txtObjetivoOficina.getText().trim().equals("") || 
							   txtPublicoOficina.getText().trim().equals("")) {  
								JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
								break;
					}
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);

					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "UPDATE oficina "
					            			+ "SET "
					            			+ "nome_oficina = '"+txtNomeOficina.getText()+"',"
					            			+ "descricao_oficina = '"+txtDescricaoOficina.getText()+"',"
					            			+ "objetivo_oficina = '"+txtObjetivoOficina.getText()+"',"
					            			+ "publico_oficina = '"+txtPublicoOficina.getText()+"'"
					            			+ " WHERE cod_oficina = "+codigoOficina+";";
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
		btSalvar.setBounds(88, 360, 89, 23);
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
		btCancelar.setBounds(187, 360, 89, 23);
		contentPane.add(btCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 117, 296, 69);
		contentPane.add(scrollPane);
		txtDescricaoOficina.setFont(new Font("Arial", Font.PLAIN, 13));
		
		//JTextArea txtDescricaoOficina = new JTextArea();
		scrollPane.setViewportView(txtDescricaoOficina);
		txtDescricaoOficina.setWrapStyleWord(true);
		txtDescricaoOficina.setLineWrap(true);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 219, 296, 47);
		contentPane.add(scrollPane_1);
		txtObjetivoOficina.setFont(new Font("Arial", Font.PLAIN, 13));
		
		//JTextArea txtObjetivoOficina = new JTextArea();
		scrollPane_1.setViewportView(txtObjetivoOficina);
		txtObjetivoOficina.setWrapStyleWord(true);
		txtObjetivoOficina.setLineWrap(true);
	}
}
