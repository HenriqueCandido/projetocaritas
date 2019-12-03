package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.*;

/**
 * Classe TelaPrincipalAdministrador é a tela principal do sistema que dá acesso a todas as outras telas.
 * Por essa classe, o usuario consegue Criar, Deletar, visualizar e alterar cadastros das tabelas do banco.
 * @author hcand
 *
 */
public class TelaPrincipalAdministrador extends JFrame {

	private JPanel contentPane;
	private JTable tbCadastros;
	private int tipoDoCadastro;
	private int tipoDoSalvar;
	private JLabel lbCadastroSelecionado= new JLabel("-");
	
	
	
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
	public TelaPrincipalAdministrador() {
		setResizable(false);
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Cria variavel scrollpane que é utilizada nos botões pra carregar a tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(211, 64, 517, 322);
		contentPane.add(scrollPane);
		
		/**
		 * O botao sair, volta pra tela de login
		 */
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login = new Login();
				
				login.setVisible(true);
				
				dispose();
				
				
			}
		});
		btSair.setFont(new Font("Arial", Font.PLAIN, 11));
		btSair.setBounds(656, 7, 72, 23);
		contentPane.add(btSair);
		
		JLabel lblEscolhaAOpo = new JLabel("Clique abaixo no assunto desejado\r\n");
		lblEscolhaAOpo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEscolhaAOpo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaAOpo.setBounds(10, 11, 189, 14);
		contentPane.add(lblEscolhaAOpo);
		
		
		
		/**
		 * O Botao usuario carrega todos os cadastros de usuarios do banco de dados na tabela
		 * da interface e muda o label para o nome USUARIO
		 * 
		 */
		JButton btUsuario = new JButton("Usu\u00E1rio");
		btUsuario.setFont(new Font("Arial", Font.PLAIN, 11));
		btUsuario.addActionListener(new ActionListener() {
			
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
			
				lbCadastroSelecionado.setText("Usuário");
				tipoDoCadastro = 1;
				String colunas[]={"Cod. Usu.:","Nome Usu.:","Tipo Usu.:"};
			    String dados[][] = new String[100][3];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_usuario,nome_usuario,tipo_usuario FROM usuario;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
			}
		
			
		});
		btUsuario.setBounds(41, 49, 110, 23);
		contentPane.add(btUsuario);
		
		
		
		/**
		 *  O Botao responsavel carrega todos os cadastros de responsaveis do banco de dados na tabela
		 * da interface e muda o label para o nome RESPONSAVEL
		 */
		JButton btResponsavel = new JButton("Respons\u00E1vel");
		btResponsavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				lbCadastroSelecionado.setText("Responsável");
				tipoDoCadastro = 2;
				String colunas[]={"Cod. Resp.","Nome Resp.:","CPF Resp.:"};
			    String dados[][] = new String[100][3];
				
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_responsavel,nome_responsavel,cpf_responsavel FROM responsavel;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
			}
		});
		btResponsavel.setFont(new Font("Arial", Font.PLAIN, 11));
		btResponsavel.setBounds(41, 80, 110, 23);
		contentPane.add(btResponsavel);
		
		
		/**
		 *  O Botao Atendido carrega todos os cadastros de atendidos do banco de dados na tabela
		 * da interface e muda o label para o nome ATENDIDO
		 */
		JButton btAtendido = new JButton("Atendido");
		btAtendido.setFont(new Font("Arial", Font.PLAIN, 11));
		btAtendido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				lbCadastroSelecionado.setText("Atendido");
				tipoDoCadastro = 7;
				String colunas[]={"Cod. Aten.:","Nome Aten.:","Cod. Resp.:","Nome Resp.:"};
			    String dados[][] = new String[100][4];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_atendido,nome_atendido,atendido.cod_responsavel,nome_responsavel"
		            			+ " FROM atendido, responsavel "
		            			+ "WHERE atendido.cod_responsavel = responsavel.cod_responsavel;";
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
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
			}
		});
		btAtendido.setBounds(41, 242, 110, 23);
		contentPane.add(btAtendido);
		
		
		/**
		 * O Botao instrutor carrega todos os cadastros de instrutores do banco de dados na tabela
		 * da interface e muda o label para o nome INSTRUTOR
		 */
		JButton btInstrutor = new JButton("Instrutor");
		btInstrutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Instrutor");
				tipoDoCadastro = 3;
				String colunas[]={"Cod. Inst.","Nome Inst.:","CPF Inst.:"};
			    String dados[][] = new String[100][3];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_instrutor,nome_instrutor,cpf_instrutor FROM instrutor;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
			}
		});
		btInstrutor.setFont(new Font("Arial", Font.PLAIN, 11));
		btInstrutor.setBounds(41, 114, 110, 23);
		contentPane.add(btInstrutor);
		
		/**
		 *  O Botao Oficina carrega todos os cadastros de Oficina do banco de dados na tabela
		 * da interface e muda o label para o nome OFICINA
		 */
		JButton btOficina = new JButton("Oficina");
		btOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Oficina");
				tipoDoCadastro = 4;
				String colunas[]={"Cod. Ofic.","Nome Ofic.:"};
			    String dados[][] = new String[100][2];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_oficina,nome_oficina FROM oficina;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		       
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
			}
		});
		btOficina.setFont(new Font("Arial", Font.PLAIN, 11));
		btOficina.setBounds(41, 145, 110, 23);
		contentPane.add(btOficina);
		
		/**
		 * O Botao Espaco carrega todos os cadastros de espacos do banco de dados na tabela
		 * da interface e muda o label para o nome Espaco
		 */
		JButton btEspaco = new JButton("Espa\u00E7o");
		btEspaco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Espaço");
				tipoDoCadastro = 5;
				String colunas[]={"Cod. Esp.","Nome Esp.:","Qtd. Lugares:"};
			    String dados[][] = new String[100][3];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT cod_espaco,nome_espaco,lugares_espaco FROM espaco;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
			}
		});
		btEspaco.setFont(new Font("Arial", Font.PLAIN, 11));
		btEspaco.setBounds(41, 179, 110, 23);
		contentPane.add(btEspaco);
		
		/**
		 * O Botao Periodo carrega todos os cadastros dos Periodos do banco de dados na tabela
		 * da interface e muda o label para o nome PERIODO
		 */
		JButton btPeriodo = new JButton("Periodo");
		btPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Periodo");
				tipoDoCadastro = 6;
				String colunas[]={"Cod. Peri.","Dia:","Periodo:","Horário:"};
			    String dados[][] = new String[100][4];
				
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM periodo;";
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
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
			}
		});
		btPeriodo.setFont(new Font("Arial", Font.PLAIN, 11));
		btPeriodo.setBounds(41, 213, 110, 23);
		contentPane.add(btPeriodo);
		
		
		/**
		 * Abre a tela de chamada e carrega o combo box com uma lista de turmas para que o usuario escolha e pesquise
		 * alunos das turmas
		 */
		JButton btChamada = new JButton("Chamada");
		btChamada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaChamada chamada = new TelaChamada();
				

		        
		        String dadosChamada[][] = new String[100][2];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "select cod_turma, nome_turma" +
		            			  " FROM  turma;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	int x = 0;
		            	while(rs.next()) {
		            		
		            			dadosChamada[x][0] =rs.getString(1);
		            			dadosChamada[x][1] =rs.getString(2);
		            			chamada.cbTurmaChamada.addItem(dadosChamada[x][0]+"  -  "+dadosChamada[x][1]);
		            			x++;
		            	}
		            	
		            }

					// Handle any errors that may have occurred.
					catch (Exception e1) {
						e1.printStackTrace();
					}
		        
		        	chamada.setVisible(true);
		        	//dispose();
			}
		});
		btChamada.setFont(new Font("Arial", Font.PLAIN, 11));
		btChamada.setBounds(41, 387, 110, 36);
		contentPane.add(btChamada);
		
		/**
		 * O Botao Atividades carrega todos os cadastros de atividades do banco de dados na tabela
		 * da interface e muda o label para o nome ATIVIDADE
		 */
		JButton btAtividades = new JButton("Atividades");
		btAtividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Atividades");
				tipoDoCadastro = 8;
				String colunas[]={"Cod. Ativ.:","Cod. Inst.:","Cod. Ofic.:","Nome Ofic.:","Cod. Esp.:","Cod. Periodo:"};
			    String dados[][] = new String[100][6];
				
			
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT atividade.cod_atividade,"
		            			+ "atividade.cod_instrutor,atividade.cod_oficina,oficina.nome_oficina,cod_espaco,cod_periodo"
		            			+ " FROM atividade, espaco_atividade, periodo_atividade, oficina "
		            			+ "WHERE atividade.cod_oficina = oficina.cod_oficina AND "
		            			+ "atividade.cod_atividade = espaco_atividade.cod_atividade AND "
		            			+ "atividade.cod_atividade = periodo_atividade.cod_atividade order by cod_atividade;";
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
		            			dados[x][5]= rs.getString(6);
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
			}
		});
		btAtividades.setFont(new Font("Arial", Font.PLAIN, 11));
		btAtividades.setBounds(41, 275, 110, 23);
		contentPane.add(btAtividades);
		
		
		/**
		 * Abre a tela de matriculas e carrega os combobox para que o usuario escolha da lista
		 */
		JButton btMatriculas = new JButton("Matriculas");
		btMatriculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaMatriculas02 matricula02 = new TelaMatriculas02();
				
		        
		        String dadosMatriculas02[][] = new String[100][2];
				
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "select cod_atendido, nome_atendido" +
		            			  " FROM  atendido;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	int x = 0;
		            	while(rs.next()) {
		            		
		            			dadosMatriculas02[x][0] =rs.getString(1);
		            			dadosMatriculas02[x][1] =rs.getString(2);
		            			matricula02.cbPessoaMatricula.addItem(dadosMatriculas02[x][0]+"  -  "+dadosMatriculas02[x][1]);
		            			x++;
		            	}
		            	
		            	SQL = "select cod_turma, nome_turma" +
		            			  " FROM  turma;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it. 	
		            	
		            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            	x = 0;
		            	while(rs.next()) {
		            		
		            			dadosMatriculas02[x][0] =rs.getString(1);
		            			dadosMatriculas02[x][1] =rs.getString(2);
		            			matricula02.cbTurmaMatricula.addItem(dadosMatriculas02[x][0]+"  -  "+dadosMatriculas02[x][1]);
		            			x++;
		            	}
		            	
		            }

					// Handle any errors that may have occurred.
					catch (Exception e1) {
						e1.printStackTrace();
					}
	
				
		        	matricula02.setVisible(true);
		        	//dispose();
			}
		});
		btMatriculas.setFont(new Font("Arial", Font.PLAIN, 11));
		btMatriculas.setBounds(41, 343, 110, 36);
		contentPane.add(btMatriculas);
		/*
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(211, 97, 517, 289);
		contentPane.add(scrollPane);
		
		tbCadastros = new JTable(dados,colunas);
		scrollPane.setViewportView(tbCadastros);
		*/
		
		
		JLabel lblParaGerenciarOs = new JLabel("para gerenciar os cadastros:");
		lblParaGerenciarOs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblParaGerenciarOs.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaGerenciarOs.setBounds(10, 24, 189, 14);
		contentPane.add(lblParaGerenciarOs);
		
		/**
		 * O Botao turmas carrega todos os cadastros de turmas do banco de dados na tabela
		 * da interface e muda o label para o nome TURMA
		 */
		JButton btTurmas = new JButton("Turmas");
		btTurmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lbCadastroSelecionado.setText("Turmas");
				tipoDoCadastro = 9;
				String colunas[]={"Cod. Turma:","Nome Turma:","Cod. Atividade:"};
			    String dados[][] = new String[100][3];
				
			
		        try {
		        		// Establish the connection.
		        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            	con = DriverManager.getConnection(connectionUrl);
		            
		            	// Create and execute an SQL statement that returns some data.
		            	String SQL = "SELECT * FROM turma;";
		            	stmt = con.createStatement();
		            	rs = stmt.executeQuery(SQL);
		            		
		            	// Iterate through the data in the result set and display it.
		            	int x = 0; 	
		            	while (rs.next()) {
		            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
		            			dados[x][0]= rs.getString(1);
		            			dados[x][1]= rs.getString(2);
		            			dados[x][2]= rs.getString(3);
		            	
		            			x++;
		            	}
		            }

				// Handle any errors that may have occurred.
				catch (Exception e1) {
					e1.printStackTrace();
				}
	
				tbCadastros = new JTable(dados,colunas);
				scrollPane.setViewportView(tbCadastros);
				
				
				
			}
		});
		btTurmas.setFont(new Font("Arial", Font.PLAIN, 11));
		btTurmas.setBounds(41, 309, 110, 23);
		contentPane.add(btTurmas);
		
		JLabel lblSelecioneUmCadastro = new JLabel("Selecione um cadastro e clique na a\u00E7\u00E3o desejada abaixo ou");
		lblSelecioneUmCadastro.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSelecioneUmCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneUmCadastro.setBounds(230, 11, 366, 14);
		contentPane.add(lblSelecioneUmCadastro);
		
		JLabel lblCriarPraAdicionar = new JLabel(" criar pra adicionar um novo:");
		lblCriarPraAdicionar.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCriarPraAdicionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriarPraAdicionar.setBounds(230, 24, 367, 14);
		contentPane.add(lblCriarPraAdicionar);
		
		/**
		 * De acordo com o codigo carregado quando o usuario clica em um tipo de cadastro do lado esquerdo,
		 * o programa vai abrir um formulario em branco diferente para que o usuario crie um cadastro novo.
		 * Por exemplo: se o usuario clicou em atendido, quando clicar em criar, vai abrir formulario de atendidos.
		 * nos formularios com combobox, tb é feito o carregamento das listas pro usuario escolher
		 */
		JButton btCriar = new JButton("Criar");
		btCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tbCadastros == null) {
					JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					
				}
				else {
				
					tipoDoSalvar = 1;
					switch(tipoDoCadastro) {
					case 1: //abre tela usuario
					
						TelaUsuario usuario = new TelaUsuario();
						usuario.tipoDoSalvarUsuario = tipoDoSalvar;
						usuario.setVisible(true);
					
						//dispose();
						break;
					case 2: //abre tela responsavel
						
						TelaResponsavel responsavel = new TelaResponsavel();
						responsavel.tipoDoSalvarResponsavel = tipoDoSalvar;
						responsavel.setVisible(true);
					
						//dispose();
						break;
					case 3: //abre tela instrutor
						
						TelaInstrutor instrutor = new TelaInstrutor();
						instrutor.tipoDoSalvarInstrutor = tipoDoSalvar;

						instrutor.setVisible(true);
					
						//dispose();
						break;
					case 4: //abre tela oficina
						
						TelaOficina oficina = new TelaOficina();
						oficina.tipoDoSalvarOficina = tipoDoSalvar;
						
						oficina.setVisible(true);
					
						//dispose();
						break;
					case 5: //abre tela espaço
						
						TelaEspaco espaco = new TelaEspaco();
						espaco.tipoDoSalvarEspaco = tipoDoSalvar;
						espaco.setVisible(true);
					
						//dispose();
						break;
					case 6: //abre tela periodo
						
						TelaPeriodo periodo = new TelaPeriodo();
						periodo.tipoDoSalvarPeriodo = tipoDoSalvar;

						periodo.setVisible(true);
					
						//dispose();
						break;
					case 7: //abre tela atendido
						
						TelaAtendido atendido = new TelaAtendido();
						atendido.tipoDoSalvarAtendido = tipoDoSalvar;

						String dados7[][] = new String[100][2];
									
							        try {
							        		// Establish the connection.
							        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							            	con = DriverManager.getConnection(connectionUrl);
							            
							            	// Create and execute an SQL statement that returns some data.
							            	String SQL = "SELECT cod_responsavel,nome_responsavel FROM responsavel;";
							            	stmt = con.createStatement();
							            	rs = stmt.executeQuery(SQL);
							            		
							            	// Iterate through the data in the result set and display it. 	
							            	
							            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
							            	int x = 0;
							            	while(rs.next()) {
							            		
							            		dados7 [x][0] =rs.getString(1);
						            			dados7 [x][1] =rs.getString(2);
				       	
										       	atendido.cbResponsavelAtendido.addItem(dados7[x][0]+"  -  "+dados7[x][1]);
							            		
										       	x++;
							            		
							            	}
							            	
							            }

									// Handle any errors that may have occurred.
									catch (Exception e1) {
										e1.printStackTrace();
									}
							        
							     	
							     
						

						atendido.setVisible(true);
					
						//dispose();
						break;
					case 8: //abre tela atividades
						
						TelaAtividades atividades = new TelaAtividades();
						atividades.tipoDoSalvarAtividades = tipoDoSalvar;
						
						String dados8[][] = new String[100][2];
						
						// Adiciona valores no instrutor da tela atividades
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_instrutor,nome_instrutor FROM instrutor;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		dados8 [x][0] =rs.getString(1);
			            			dados8 [x][1] =rs.getString(2);
	       	
							       	atividades.cbInstrutorAtividade.addItem(dados8[x][0]+"  -  "+dados8[x][1]);
				            		
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
				        
				     // Adiciona valores na oficina da tela atividades
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_oficina,nome_oficina FROM oficina;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		dados8 [x][0] =rs.getString(1);
			            			dados8 [x][1] =rs.getString(2);
	       	
							       	atividades.cbOficinaAtividade.addItem(dados8[x][0]+"  -  "+dados8[x][1]);
				            		
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
						
				        // Adiciona valores no espaco da tela atividades
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_espaco,nome_espaco FROM espaco;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		dados8 [x][0] =rs.getString(1);
			            			dados8 [x][1] =rs.getString(2);
	       	
							       	atividades.cbEspacoAtividade.addItem(dados8[x][0]+"  -  "+dados8[x][1]);
				            		
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
						
				        // Adiciona valores no periodo da tela atividades
				        String[][] dados8_1 = new String[100][4];
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_periodo, dia_periodo,periodo_periodo,horario_periodo FROM periodo;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		dados8_1 [x][0] =rs.getString(1);
			            			dados8_1 [x][1] =rs.getString(2);
			            			dados8_1 [x][2] =rs.getString(3);
			            			dados8_1 [x][3] =rs.getString(4);
	       	
							       	atividades.cbPeriodoAtividade.addItem(dados8_1[x][0]+"  -  "+dados8_1[x][1]+" - "+dados8_1[x][2]+"  -  "+dados8_1[x][3]);
				            		
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
						
						

						atividades.setVisible(true);
					
						//dispose();
						break;
					case 9: //abre tela turmas
						
						TelaTurmas turmas = new TelaTurmas();
						turmas.tipoDoSalvarTurmas = tipoDoSalvar;
						String dados9[][] = new String[100][1];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_atividade FROM atividade;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		dados9 [x][0] =rs.getString(1);
			            			
	       	
							       	turmas.cbAtividadeTurmas.addItem(dados9[x][0]);
				            		
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
	
						turmas.setVisible(true);
					
						//dispose();
						break;
					default:
						JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					}
					
				}
	
			}
		});
		btCriar.setFont(new Font("Arial", Font.PLAIN, 11));
		btCriar.setBounds(274, 394, 89, 29);
		contentPane.add(btCriar);
		
		/**
		 *  De acordo com o codigo carregado quando o usuario clica em um tipo de cadastro do lado esquerdo, e com a linha
		 *  escolhida pelo usuario, o programa vai abrir um formulario carregado com as informacoes da linha selecionada
		 *  para que o usuario visualize as informacoes do cadastro. Os campos sao bloqueados para edição.
		 * Por exemplo: se o usuario clicou em atendido, e escolheu um dos registros, quando clicar em visualizar,
		 * vai abrir formulario de atendidos preenchido.
		 * Nos formularios com combobox, tb é feito o carregamento das listas pro usuario escolher.
		 */
		JButton btVisualizar = new JButton("Visualizar");
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tbCadastros == null) {
					JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					
				}
				else {
				int linhaTabela = tbCadastros.getSelectedRow();
				
				if(linhaTabela == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Registro");
				}
				else {
					tipoDoSalvar = 3;
					Object linha = tbCadastros.getValueAt(linhaTabela,0);
				
					System.out.println(linha);
					
					
					switch(tipoDoCadastro) {
					case 1: //abre tela usuario
					
						TelaUsuario usuario = new TelaUsuario();
						usuario.tipoDoSalvarUsuario = tipoDoSalvar;
						String dados[] = new String[3];
					
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT nome_usuario,senha_usuario,tipo_usuario "
				            		    	+ "FROM usuario WHERE cod_usuario = "+linha+";";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	while(rs.next()) {
				            			dados [0] =rs.getString(1);
				            			dados [1] =rs.getString(2);
				            			dados [2] =rs.getString(3);
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
				        
				        usuario.txtNomeUsuario.setText(dados[0]);
				        usuario.txtSenhaUsuario.setText(dados[1]);
				        usuario.txtTipoUsuario.setText(dados[2]);
			        
				        
						usuario.txtNomeUsuario.setEnabled(false);
						usuario.txtSenhaUsuario.setEnabled(false);
						usuario.txtTipoUsuario.setEnabled(false);
					
						usuario.setVisible(true);
					
						//dispose();
						break;
					case 2: //abre tela responsavel
						
						TelaResponsavel responsavel = new TelaResponsavel();
						responsavel.tipoDoSalvarResponsavel = tipoDoSalvar;
						String dados2[] = new String[12];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM responsavel WHERE cod_responsavel = "+linha+";";
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
				        responsavel.txtNascimentoResponsavel.setText(AjustarData.ajustar(dados2[5]));
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
					
						//dispose();
						break;
					case 3: //abre tela instrutor
						
						TelaInstrutor instrutor = new TelaInstrutor();
						instrutor.tipoDoSalvarInstrutor = tipoDoSalvar;
						String dados3[] = new String[12];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM instrutor WHERE cod_instrutor = "+linha+";";
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
				        instrutor.txtNascimentoInstrutor.setText(AjustarData.ajustar(dados3[5]));
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
					
						//dispose();
						break;
					case 4: //abre tela oficina
						
						TelaOficina oficina = new TelaOficina();
						oficina.tipoDoSalvarOficina = tipoDoSalvar;
						String dados4[] = new String[5];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM oficina WHERE cod_oficina = "+linha+";";
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
					
						//dispose();
						break;
					case 5: //abre tela espaço
						
						TelaEspaco espaco = new TelaEspaco();
						espaco.tipoDoSalvarEspaco = tipoDoSalvar;
						String dados5[] = new String[3];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM espaco WHERE cod_espaco = "+linha+";";
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
					
						//dispose();
						break;
					case 6: //abre tela periodo
						
						TelaPeriodo periodo = new TelaPeriodo();
						periodo.tipoDoSalvarPeriodo = tipoDoSalvar;
						String dados6[] = new String[4];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM periodo WHERE cod_periodo = "+linha+";";
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
					
						//dispose();
						break;
					case 7: //abre tela atendido
						
						TelaAtendido atendido = new TelaAtendido();
						atendido.tipoDoSalvarAtendido = tipoDoSalvar;
						
						String dados7[] = new String[13];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "select cod_atendido,nome_atendido," + 
				            			"ra_atendido,nis_atendido,nascimento_atendido,rua_atendido," + 
				            			"numero_atendido,bairro_atendido,cidade_atendido,telefone_atendido," + 
				            			"email_atendido,atendido.cod_responsavel,nome_responsavel " + 
				            			" FROM atendido, responsavel" + 
				            			" WHERE atendido.cod_responsavel = responsavel.cod_responsavel AND " + 
				            			" cod_atendido ="+linha+";";
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
					
						//dispose();
						break;
					case 8: //abre tela atividades
						
						TelaAtividades atividades = new TelaAtividades();
						atividades.tipoDoSalvarAtividades = tipoDoSalvar;
						String dados8[] = new String[11];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
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
				            					" atividade.cod_atividade = "+linha+"; ";
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
					
						//dispose();
						break;
					case 9: //abre tela turmas
						
						TelaTurmas turmas = new TelaTurmas();
						turmas.tipoDoSalvarTurmas = tipoDoSalvar;
						String dados9[] = new String[3];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM turma"
				            			+ " WHERE cod_turma ="+linha+";";
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
					
						//dispose();
						break;
					default:
						JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					}
					
				}
				}
			
			}
		});
		
		
		
		
		btVisualizar.setFont(new Font("Arial", Font.PLAIN, 11));
		btVisualizar.setBounds(373, 394, 89, 29);
		contentPane.add(btVisualizar);
		
		
		/**
		 *  De acordo com o codigo carregado quando o usuario clica em um tipo de cadastro do lado esquerdo, e com a linha
		 *  escolhida pelo usuario, o programa vai abrir um formulario carregado com as informacoes da linha selecionada
		 *  para que o usuario edite as informacoes do cadastro. Os campos nao sao bloqueados para edicao.
		 * Por exemplo: se o usuario clicou em atendido, e escolheu um dos registros, quando clicar em Alterar,
		 * vai abrir formulario de atendidos preenchido.
		 * Nos formularios com combobox, tb é feito o carregamento das listas pro usuario escolher.
		 */
		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tbCadastros == null) {
					JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					
				}
				else {
				int linhaTabela = tbCadastros.getSelectedRow();
				
				if(linhaTabela == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Registro");
				}
				else {
					tipoDoSalvar = 2;
					Object linha = tbCadastros.getValueAt(linhaTabela,0);
				
					System.out.println(linha);
					
					
					switch(tipoDoCadastro) {
					case 1: //abre tela usuario
					
						TelaUsuario usuario = new TelaUsuario();
						usuario.tipoDoSalvarUsuario = tipoDoSalvar;
						usuario.codigoUsuario = linha;
						String dados[] = new String[3];
					
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT nome_usuario,senha_usuario,tipo_usuario "
				            		    	+ "FROM usuario WHERE cod_usuario = "+linha+";";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	while(rs.next()) {
				            			dados [0] =rs.getString(1);
				            			dados [1] =rs.getString(2);
				            			dados [2] =rs.getString(3);
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
				        
				        usuario.txtNomeUsuario.setText(dados[0]);
				        usuario.txtSenhaUsuario.setText(dados[1]);
				        usuario.txtTipoUsuario.setText(dados[2]);
			        
					
						usuario.setVisible(true);
					
						//dispose();
						break;
					case 2: //abre tela responsavel
						
						TelaResponsavel responsavel = new TelaResponsavel();
						responsavel.tipoDoSalvarResponsavel = tipoDoSalvar;
						responsavel.codigoResponsavel = linha;
						String dados2[] = new String[12];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM responsavel WHERE cod_responsavel = "+linha+";";
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
				        responsavel.txtNascimentoResponsavel.setText(AjustarData.ajustar(dados2[5]));
				        responsavel.txtRuaResponsavel.setText(dados2[6]);
				        responsavel.txtNumeroResponsavel.setText(dados2[7]);
				        responsavel.txtBairroResponsavel.setText(dados2[8]);
				        responsavel.txtCidadeResponsavel.setText(dados2[9]);
				        responsavel.txtTelefoneResponsavel.setText(dados2[10]);
				        responsavel.txtEmailResponsavel.setText(dados2[11]);
				      
						
					
						responsavel.setVisible(true);
					
						//dispose();
						break;
					case 3: //abre tela instrutor
						
						TelaInstrutor instrutor = new TelaInstrutor();
						instrutor.tipoDoSalvarInstrutor = tipoDoSalvar;
						instrutor.codigoInstrutor = linha;
						String dados3[] = new String[12];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM instrutor WHERE cod_instrutor = "+linha+";";
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
				        instrutor.txtNascimentoInstrutor.setText(AjustarData.ajustar(dados3[5]));
				        instrutor.txtRuaInstrutor.setText(dados3[6]);
				        instrutor.txtNumeroInstrutor.setText(dados3[7]);
				        instrutor.txtBairroInstrutor.setText(dados3[8]);
				        instrutor.txtCidadeInstrutor.setText(dados3[9]);
				        instrutor.txtTelefoneInstrutor.setText(dados3[10]);
				        instrutor.txtEmailInstrutor.setText(dados3[11]);
				        
				      
				        
					
					
						instrutor.setVisible(true);
					
						//dispose();
						break;
					case 4: //abre tela oficina
						
						TelaOficina oficina = new TelaOficina();
						oficina.tipoDoSalvarOficina = tipoDoSalvar;
						oficina.codigoOficina = linha;
						String dados4[] = new String[5];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM oficina WHERE cod_oficina = "+linha+";";
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
	

						oficina.setVisible(true);
					
						//dispose();
						break;
					case 5: //abre tela espaço
						
						TelaEspaco espaco = new TelaEspaco();
						espaco.tipoDoSalvarEspaco = tipoDoSalvar;
						espaco.codigoEspaco = linha;
						String dados5[] = new String[3];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM espaco WHERE cod_espaco = "+linha+";";
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
				       

						espaco.setVisible(true);
					
						//dispose();
						break;
					case 6: //abre tela periodo
						
						TelaPeriodo periodo = new TelaPeriodo();
						periodo.tipoDoSalvarPeriodo = tipoDoSalvar;
						periodo.codigoPeriodo = linha;
						String dados6[] = new String[4];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM periodo WHERE cod_periodo = "+linha+";";
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
	

						periodo.setVisible(true);
					
						//dispose();
						break;
					case 7: //abre tela atendido
						
						TelaAtendido atendido = new TelaAtendido();
						atendido.tipoDoSalvarAtendido = tipoDoSalvar;
						atendido.codigoAtendido = linha;
						
						String dados7[] = new String[13];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "select cod_atendido,nome_atendido," + 
				            			"ra_atendido,nis_atendido,nascimento_atendido,rua_atendido," + 
				            			"numero_atendido,bairro_atendido,cidade_atendido,telefone_atendido," + 
				            			"email_atendido,atendido.cod_responsavel,nome_responsavel " + 
				            			" FROM atendido, responsavel" + 
				            			" WHERE atendido.cod_responsavel = responsavel.cod_responsavel AND " + 
				            			" cod_atendido ="+linha+";";
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
				        atendido.cbResponsavelAtendido.addItem(dados7[11]+"  -  "+dados7[12]);
				        
				        //só pro combobox
				        
				        
				        String dados7_1[][] = new String[100][2];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_responsavel,nome_responsavel FROM responsavel;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		if(!dados7[11].equals(rs.getString(1))) {
				            			dados7_1 [x][0] =rs.getString(1);
				            			dados7_1 [x][1] =rs.getString(2);
	       	
				            			atendido.cbResponsavelAtendido.addItem(dados7_1[x][0]+"  -  "+dados7_1[x][1]);
				            		}
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
				        
						atendido.setVisible(true);
					
						//dispose();
						break;
					case 8: //abre tela atividades
						
						TelaAtividades atividades = new TelaAtividades();
						atividades.tipoDoSalvarAtividades = tipoDoSalvar;
						atividades.codigoAtividades = linha;
						String dados8[] = new String[11];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
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
				            					" atividade.cod_atividade = "+linha+"; ";
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
				       
				        //
				        
				        String dados8_1[][] = new String[100][11];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
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
				            					" periodo_atividade.cod_periodo = periodo.cod_periodo;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		if(!dados8[0].equals(rs.getString(1))) {
				            			
				            			dados8_1[x][0] =rs.getString(1);
				            			dados8_1[x][1] =rs.getString(2);
				            			dados8_1[x][2] =rs.getString(3);
				            			dados8_1[x][3] =rs.getString(4);
				            			dados8_1[x][4] =rs.getString(5);
				            			dados8_1[x][5] =rs.getString(6);
				            			dados8_1[x][6] =rs.getString(7);
				            			dados8_1[x][7] =rs.getString(8);
				            			dados8_1[x][8] =rs.getString(9);
				            			dados8_1[x][9] =rs.getString(10);
				            			dados8_1[x][10] =rs.getString(11);
				
				            			atividades.cbInstrutorAtividade.addItem(dados8_1[x][1]+"  -  "+dados8_1[x][2]);
				 				        atividades.cbOficinaAtividade.addItem(dados8_1[x][3]+"  -  "+dados8_1[x][4]);
				 				        atividades.cbEspacoAtividade.addItem(dados8_1[x][5]+"  -  "+dados8_1[x][6]);
				 				        atividades.cbPeriodoAtividade.addItem(dados8_1[x][7]+"  -  "+dados8_1[x][8]+" - "+dados8_1[x][9]+" - "+dados8_1[x][10]);

	       	
				            			
				            		}
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
			
						atividades.setVisible(true);
					
						//dispose();
						break;
					case 9: //abre tela turmas
						
						TelaTurmas turmas = new TelaTurmas();
						turmas.tipoDoSalvarTurmas = tipoDoSalvar;
						turmas.codigoTurmas = linha;
						String dados9[] = new String[3];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT * FROM turma"
				            			+ " WHERE cod_turma ="+linha+";";
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
				        
				        
				        String dados9_1[][] = new String[100][1];
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_atividade FROM atividade;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it. 	
				            	
				            	//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            	int x = 0;
				            	while(rs.next()) {
				            		
				            		if(!dados9[2].equals(rs.getString(1))) {
				            			dados9_1 [x][0] =rs.getString(1);
				            			
	       	
				            			turmas.cbAtividadeTurmas.addItem(dados9_1[x][0]);
				            		}
							       	x++;
				            		
				            	}
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
					
						turmas.setVisible(true);
					
						//dispose();
						break;
					default:
						JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					}
					
				}
				}
	
				
			}
		});
		btAlterar.setFont(new Font("Arial", Font.PLAIN, 11));
		btAlterar.setBounds(472, 394, 89, 29);
		contentPane.add(btAlterar);
		
		/**
		 *  O botao delete, deleta do banco de dados o cadastro selecionado na tabela acima.
		 */
		JButton btDelete = new JButton("Delete");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tbCadastros == null) {
					JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					
				}
				else {
				int linhaTabela = tbCadastros.getSelectedRow();
				
				if(linhaTabela == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Registro");
				}
				else {
					
					Object linha = tbCadastros.getValueAt(linhaTabela,0);
				
					System.out.println(linha);
					
					
					switch(tipoDoCadastro) {
					case 1: //deleta registro usuario
					
	
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM usuario WHERE cod_usuario = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
				        
				        
				        String colunas[]={"Cod. Usu.:","Nome Usu.:","Tipo Usu.:"};
					    String dados[][] = new String[100][3];
						
						
				        try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "SELECT cod_usuario,nome_usuario,tipo_usuario FROM usuario;";
				            	stmt = con.createStatement();
				            	rs = stmt.executeQuery(SQL);
				            		
				            	// Iterate through the data in the result set and display it.
				            	int x = 0; 	
				            	while (rs.next()) {
				            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
				            			dados[x][0]= rs.getString(1);
				            			dados[x][1]= rs.getString(2);
				            			dados[x][2]= rs.getString(3);
				            			x++;
				            	}
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							e1.printStackTrace();
						}
			
						tbCadastros = new JTable(dados,colunas);
						scrollPane.setViewportView(tbCadastros);
				        
					
						//dispose();
						break;
					case 2: //abre tela responsavel
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM responsavel WHERE cod_responsavel = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						 
						 String colunas2[]={"Cod. Resp.","Nome Resp.:","CPF Resp.:"};
						 String dados2[][] = new String[100][3];
							
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT cod_responsavel,nome_responsavel,cpf_responsavel FROM responsavel;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados2[x][0]= rs.getString(1);
					            			dados2[x][1]= rs.getString(2);
					            			dados2[x][2]= rs.getString(3);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados2,colunas2);
							scrollPane.setViewportView(tbCadastros);
					
						//dispose();
						break;
					case 3: //abre tela instrutor
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM instrutor WHERE cod_instrutor = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas3[]={"Cod. Inst.","Nome Inst.:","CPF Inst.:"};
						    String dados3[][] = new String[100][3];
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT cod_instrutor,nome_instrutor,cpf_instrutor FROM instrutor;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados3[x][0]= rs.getString(1);
					            			dados3[x][1]= rs.getString(2);
					            			dados3[x][2]= rs.getString(3);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados3,colunas3);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 4: //abre tela oficina
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM oficina WHERE cod_oficina = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas4[]={"Cod. Ofic.","Nome Ofic.:"};
						    String dados4[][] = new String[100][2];
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT cod_oficina,nome_oficina FROM oficina;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados4[x][0]= rs.getString(1);
					            			dados4[x][1]= rs.getString(2);
					       
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados4,colunas4);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 5: //abre tela espaço
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM espaco WHERE cod_espaco = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas5[]={"Cod. Esp.","Nome Esp.:","Qtd. Lugares:"};
						    String dados5[][] = new String[100][3];
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT cod_espaco,nome_espaco,lugares_espaco FROM espaco;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados5[x][0]= rs.getString(1);
					            			dados5[x][1]= rs.getString(2);
					            			dados5[x][2]= rs.getString(3);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados5,colunas5);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 6: //abre tela periodo
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM periodo WHERE cod_periodo = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas6[]={"Cod. Peri.","Dia:","Periodo:","Horário:"};
						    String dados6[][] = new String[100][4];
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT * FROM periodo;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados6[x][0]= rs.getString(1);
					            			dados6[x][1]= rs.getString(2);
					            			dados6[x][2]= rs.getString(3);
					            			dados6[x][3]= rs.getString(4);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados6,colunas6);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 7: //abre tela atendido
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM atendido WHERE cod_atendido = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas7[]={"Cod. Aten.:","Nome Aten.:","Cod. Resp.:","Nome Resp.:"};
						    String dados7[][] = new String[100][4];
							
							
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT cod_atendido,nome_atendido,atendido.cod_responsavel,nome_responsavel"
					            			+ " FROM atendido, responsavel "
					            			+ "WHERE atendido.cod_responsavel = responsavel.cod_responsavel;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados7[x][0]= rs.getString(1);
					            			dados7[x][1]= rs.getString(2);
					            			dados7[x][2]= rs.getString(3);
					            			dados7[x][3]= rs.getString(4);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados7,colunas7);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 8: //abre tela atividades
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM espaco_atividade WHERE cod_atividade = "+linha+""
				            			+ "DELETE FROM periodo_atividade WHERE cod_atividade = "+linha+""
				            			+ "DELETE FROM atividade WHERE cod_atividade = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas8[]={"Cod. Ativ.:","Cod. Inst.:","Cod. Ofic.:","Nome Ofic.:","Cod. Esp.:","Cod. Periodo:"};
						 String dados8[][] = new String[100][6];
							
						
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT atividade.cod_atividade,"
					            			+ "atividade.cod_instrutor,atividade.cod_oficina,oficina.nome_oficina,cod_espaco,cod_periodo"
					            			+ " FROM atividade, espaco_atividade, periodo_atividade, oficina "
					            			+ "WHERE atividade.cod_oficina = oficina.cod_oficina AND "
					            			+ "atividade.cod_atividade = espaco_atividade.cod_atividade AND "
					            			+ "atividade.cod_atividade = periodo_atividade.cod_atividade;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados8[x][0]= rs.getString(1);
					            			dados8[x][1]= rs.getString(2);
					            			dados8[x][2]= rs.getString(3);
					            			dados8[x][3]= rs.getString(4);
					            			dados8[x][4]= rs.getString(5);
					            			dados8[x][5]= rs.getString(6);
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados8,colunas8);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					case 9: //abre tela turmas
						
						 try {
				        		// Establish the connection.
				        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				            	con = DriverManager.getConnection(connectionUrl);
				            
				            	// Create and execute an SQL statement that returns some data.
				            	String SQL = "DELETE FROM turma WHERE cod_turma = "+linha+";";	    	
				            	stmt = con.createStatement();
				            	stmt.execute(SQL);
				            		
				            	
				            }

						// Handle any errors that may have occurred.
						catch (Exception e1) {
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Registro não pode ser deletado.");
						}
						
						 String colunas9[]={"Cod. Turma:","Nome Turma:","Cod. Atividade:"};
						    String dados9[][] = new String[100][3];
							
						
					        try {
					        		// Establish the connection.
					        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					            	con = DriverManager.getConnection(connectionUrl);
					            
					            	// Create and execute an SQL statement that returns some data.
					            	String SQL = "SELECT * FROM turma;";
					            	stmt = con.createStatement();
					            	rs = stmt.executeQuery(SQL);
					            		
					            	// Iterate through the data in the result set and display it.
					            	int x = 0; 	
					            	while (rs.next()) {
					            			//System.out.println(rs.getString(4) + " " + rs.getString(6));
					            			dados9[x][0]= rs.getString(1);
					            			dados9[x][1]= rs.getString(2);
					            			dados9[x][2]= rs.getString(3);
					            	
					            			x++;
					            	}
					            }

							// Handle any errors that may have occurred.
							catch (Exception e1) {
								e1.printStackTrace();
							}
				
							tbCadastros = new JTable(dados9,colunas9);
							scrollPane.setViewportView(tbCadastros);
						 
						break;
					default:
						JOptionPane.showMessageDialog(null, "Escolha um tipo de Cadastro a sua esquerda!");
					}
					
				}
				}
				
				
				
				
			}
		});
		btDelete.setFont(new Font("Arial", Font.PLAIN, 11));
		btDelete.setBounds(571, 394, 89, 29);
		contentPane.add(btDelete);
		lbCadastroSelecionado.setFont(new Font("Arial", Font.BOLD, 11));
		
		//JLabel lbCadastroSelecionado = new JLabel(selecionado);
		lbCadastroSelecionado.setBounds(217, 36, 398, 27);
		contentPane.add(lbCadastroSelecionado);
	}
}
