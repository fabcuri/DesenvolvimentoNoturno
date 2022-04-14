package Avaliacao;

import java.io.File;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;





public class Executavel {
	private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\produtos_avaliacao.csv";

	public static void main(String[] args) throws IOException, ClassNotFoundException  {
		byte opcao = 0;
		while(opcao >= 0) {
			String opcaoTxt = JOptionPane.showInputDialog(
					"Menu:\n"
							+ "1:Cadastrar novo item\n"
							+ "2:Exibir itens cadastrados\n"
							+ "3:Pesquisar item\n"
							+ "4:Excluir item\n"
							+ "5:Informar o log\n"
							+ "6:Encerrar o programa");
			if(opcaoTxt == null || opcaoTxt.equals("") || opcaoTxt.equals("6")) {
				opcao = -1;
			}
			try {
				opcao = Byte.parseByte(opcaoTxt);
				switch(opcao) {
				case 1:
					escreverObjeto();
					Log.escreverLog1();
					break;
				case 2:		
					lerModel();
					lerView();
					Log.escreverLog2();
					break;
				case 3:
					//solicitarCodigo();
					pesquisarObjeto();
					Log.escreverLog3();
					break;
				case 4:
					//solicitarCodigo();
					excluirObjetoInformado();
					Log.escreverLog4();
					break;
				case 5:
					Log.lerModelLog();
					Log.lerViewLog();
					break;
				default:
					opcao = -1;
				}
			}catch(NumberFormatException e) {
				opcao = -1;
				e.printStackTrace();
			}

		}
	}


	public static void escreverObjeto() throws IOException{

		String nome = JOptionPane.showInputDialog("Informe o nome:");
		String preco = JOptionPane.showInputDialog("Informe o preco:");
		String codigo = JOptionPane.showInputDialog("Informe o codigo:");
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(Float.parseFloat(preco));
		produto.setCodigo(Short.parseShort(codigo));
		insereController(produto);

	}
	private static void insereController(Produto obj) {
		insereModel(obj);
	}


	private static void insereModel(Produto obj) {
		File f = new File (FILE_PATH);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(new String(obj.toString()+"\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





	private static void lerView() {
		String conteudo = lerModel();
		int linhas = conteudo.split("\n").length;
		JOptionPane.showMessageDialog(null, "Total de itens:"+linhas +"\n"+conteudo);
	}

	private static String lerModel() {
		String retorno = "";
		File f = new File (FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] conteudo = fis.readAllBytes();
			retorno = new String(conteudo);
			retorno = retorno.replaceAll(";", " - ");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}


	public static void pesquisarObjeto() throws IOException, ClassNotFoundException{
		File f = new File (FILE_PATH);
		String codigoInformado = (JOptionPane.showInputDialog("Qual o código do produto?"));
		FileInputStream fis= new FileInputStream(f);
		byte[] dados=fis.readAllBytes();
		String conteudoArquivoCSV= new String(dados);
		String[] linhas=conteudoArquivoCSV.split("\n");


		
		String[][] linhasDeDados=new String[linhas.length][3];
		for(int i=0;i<linhas.length;i++) {
			String[] campos=linhas[i].split(";");
			if (codigoInformado.equals(campos[2])){
				JOptionPane.showMessageDialog(null," O produto é: "+campos[0]+campos[1]);
				break;

			}
		}
	}

	private static void excluirObjetoInformado() throws IOException, ClassNotFoundException{
		File f = new File (FILE_PATH);
		String codigoInformado = (JOptionPane.showInputDialog("Qual o código do produto?"));
		FileInputStream fis= new FileInputStream(f);
		byte[] dados=fis.readAllBytes();
		String conteudoArquivoCSV= new String(dados);
		String[] linhas=conteudoArquivoCSV.split("\n");



		String[][] linhasDeDados=new String[linhas.length][3];
		String novoConteudo="";
		for(int i=0;i<linhas.length;i++) {
			String[] campos=linhas[i].split(";");
			if (codigoInformado.equals(campos[2])){
				campos[0]=null;
				campos[1]=null;
				novoConteudo=campos[i]+"\n";
				break;
			}
		}
		FileOutputStream fos= new FileOutputStream(f);
		fos.write(novoConteudo.getBytes());
		fos.close();
		
				}
		
	}



		 

		    
		 

		


	