package aplicacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import models.Conexao;


public class Aplicacao {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		int opcao;
		Scanner scn = new Scanner(System.in);
		
		while(true) {
			Connection con = Conexao.criarConexao();
			System.out.println("Cadastro de operações");
			System.out.println("1-Inserir\n2-Atualizar\n3-Listar\n4-Deletar\n0-Encerrar\n");
			opcao = scn.nextInt();
			
			if(opcao ==1 ) {
				
				System.out.println("Qual é o produto (minerio/calcario): ");
				String carga = scn.next();
				System.out.print("Qual a quantidade de " + carga + "?: ");
				double quantidade = scn.nextDouble();
				System.out.print("Quem é o destinatário?: ");
				String destinatario = scn.next();
				System.out.print("Digite o ano da operação: ");
				int ano = scn.nextInt();
				System.out.print("Digite o mês da operação (entre janeiro - 01 e dezembro 12): ");
				int mes =  scn.nextInt();
				System.out.print("Digite o dia da operação (dias entre 1 e 9 deve ter 0");
				int dia = scn.nextInt();
				Date dataoperacao = new Date(ano, mes, dia);
				System.out.println("Digite o nome do motorista do caminhão: ");
				String motorista = scn.next();
				
				String sql = "insert into operacao(tipocarga, quantidade, destinatario, dataoperacao, motorista) values (?, ?, ?, ?, ?)";
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, carga);
					pst.setDouble(2, quantidade);
					pst.setString(3, destinatario);
					pst.setDate(4, dataoperacao);
					pst.setString(5, motorista);
					pst.executeUpdate();
										
					System.out.println("Inserido com sucesso...");
				}catch (SQLException e) {
					e.printStackTrace();
				}		
			}
			else if(opcao == 2) {
				int id;
				System.out.println("Digite o ID: ");
				id = scn.nextInt();
				
				System.out.println("Qual é o produto (minerio/calcario): ");
				String carga = scn.next();
				System.out.print("Qual a quantidade de " + carga + "?: ");
				double quantidade = scn.nextDouble();
				System.out.print("Quem é o destinatário?: ");
				String destinatario = scn.next();
				System.out.print("Digite o ano da operação: ");
				int ano = scn.nextInt();
				System.out.print("Digite o mês da operação (entre janeiro - 01 e dezembro 12): ");
				int mes =  scn.nextInt();
				System.out.print("Digite o dia da operação (dias entre 1 e 9 deve ter 0");
				int dia = scn.nextInt();
				Date dataoperacao = new Date(ano, mes, dia);
				System.out.println("Digite o nome do motorista do caminhão: ");
				String motorista = scn.next();
				
				String sql = "update operacao set tipocarga = ?, quantidade=?, destinatario=?, dataoperacao=?, motorista=?";
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, carga);
					pst.setDouble(2, quantidade);
					pst.setString(3, destinatario);
					pst.setDate(4, dataoperacao);
					pst.setString(5, motorista);
					pst.executeUpdate();
										
					System.out.println("Inserido com sucesso...");
				}catch (SQLException e) {
					e.printStackTrace();
				}		
				
				
			}
			else if(opcao == 3) {
				System.out.println("\n");
				String sql = "select * from operacao";
				Statement stmt = con.createStatement();
				ResultSet resultado = stmt.executeQuery(sql);

				
				while (resultado.next()) {
					System.out.println(resultado.getString("tipocarga") +"  "+resultado.getInt("quantidade")+"  "+resultado.getString("Destinatario")+"  "+resultado.getDate("dataoperacao") + "  " + resultado.getString("motorista"));
				}
				
				System.out.println("\n");
			}
			else if(opcao == 4) {
				int id;
				System.out.println("Digite o ID: ");
				id = scn.nextInt();
				
				String sql = "delete from operacao where id=?";
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, id);
					pst.executeUpdate();
					System.out.println("deletado com sucesso com sucesso...");
				}catch (SQLException e) {
					e.printStackTrace();
				}		

				
			}else if(opcao == 0) {
				System.out.println("Sistema encerrado...");
				break;
			}
			else {
				System.out.println("Opcão inválida...");
			}
			
		}

		
	}

}
