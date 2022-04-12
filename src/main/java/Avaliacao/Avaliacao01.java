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

import Avaliacao.Avaliacao01.Produto;




public class Avaliacao01 {
	private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\produtos_avaliacao.csv";
	public static class Produto implements Serializable{

		private String nome;
		private float preco;
		private short codigo;

		@Override
		public String toString() {
			return "Produto=" + nome + ", Preço= R$" + preco + ", Código=" + codigo;
		}
		public Produto() {
			super();
		}
		public Produto(String nome, double preco, int codigo) {
			super();
			this.nome = nome;
			this.preco = (float) preco;
			this.codigo = (short) codigo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public float getPreco() {
			return preco;
		}
		public void setPreco(float preco) {
			this.preco = preco;
		}
		public short getCodigo() {
			return codigo;
		}
		public void setCodigo(short codigo) {
			this.codigo = codigo;
		}
	}


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



	public static void solicitarCodigo() throws IOException, ClassNotFoundException{
		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o código do produto?"));

	}

	public static Produto[] pesquisarObjeto() throws IOException, ClassNotFoundException{
		File f = new File (FILE_PATH);
		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o código do produto?"));
		Produto[] lista = null;
		try{
			FileInputStream fis = new FileInputStream(f);
			byte[] dados = fis.readAllBytes();
			String conteudoArquivo = new String(dados);
			String[] linhas = conteudoArquivo.split("\r\n");
			lista = new Produto[linhas.length];
			int index = 0;
			for(String linha: linhas) {
				Produto p = new Produto();
				lista[index] = p;
				String[] colunas = linha.split(";");
				p.setCodigo(Short.parseShort(colunas[2]));
				p.setNome(colunas[0]);
				p.setPreco(Float.parseFloat(colunas[1]));
				index++;
				if(codigoInformado == p.getCodigo()) {
					JOptionPane.showMessageDialog(null, "O produto pesquisado é: "+p.getNome()+p.getPreco());	
				}else {
					JOptionPane.showMessageDialog(null, "O código informado não é valido");
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}
		return lista;	
	}

	private static void excluirObjetoInformado() throws IOException, ClassNotFoundException {
		File f = new File(FILE_PATH);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Produto> produtos = (ArrayList<Produto>)ois.readObject();
		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o código do produto?"));
		for(Produto p: produtos) {
			if(codigoInformado == p.getCodigo()) {
				JOptionPane.showMessageDialog(null, "O produto a excluir é: "+p.getNome()+p.getPreco());	
			}else {
				JOptionPane.showMessageDialog(null, "O código informado não é valido");
			}


			try {
				FileInputStream fis1 = new FileInputStream(f);
				byte[] conteudo = fis1.readAllBytes();
				String retorno = new String(conteudo);
				String[] itens = retorno.split("\n");
				String novoConteudo = "";
				for(int i = 0; i<itens.length;i++) {
					novoConteudo+= itens[i]+"\n";

				}
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(novoConteudo.getBytes());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


}


		 

		    
		 

		


	