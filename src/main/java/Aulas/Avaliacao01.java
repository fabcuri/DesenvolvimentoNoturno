package Aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Avaliacao01 {
	private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\contatos_avaliacao.csv";
	public static class Produto implements Serializable{

		private String nome;
		private float preco;
		private short codigo;

		@Override
		public String toString() {
			return "Produto=" + nome + ", Pre�o= R$" + preco + ", C�digo=" + codigo;
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
					break;
				case 2:
					lerObjeto();
					break;
				case 3:
					//solicitarCodigo();
					pesquisarObjeto();
					break;
				case 4:
					//solicitarCodigo();
					excluirObjetoInformado();
					break;
				case 5:
					informarLogObjeto();
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




	public static void lerObjeto() throws IOException, ClassNotFoundException{

		File f = new File(FILE_PATH);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Produto> produtos = (List<Produto>)ois.readObject();

		for(int i =0;i<produtos.size();i++) {

			JOptionPane.showMessageDialog(null, "Produtos:\n"+produtos);
		}
	}


	public static void solicitarCodigo() throws IOException, ClassNotFoundException{
		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o c�digo do produto?"));

	}

	public static void pesquisarObjeto() throws IOException, ClassNotFoundException{
		File f = new File(FILE_PATH);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Produto> produtos = (ArrayList<Produto>)ois.readObject();

		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o c�digo do produto?"));
		for(Produto p: produtos) {
			if(codigoInformado == p.getCodigo()) {
				JOptionPane.showMessageDialog(null, "O produto pesquisado �: "+p.getNome()+p.getPreco());	
			}else {
				JOptionPane.showMessageDialog(null, "O c�digo informado n�o � valido");
			}
		}
	}


	private static void excluirObjetoInformado() throws IOException, ClassNotFoundException {
		File f = new File(FILE_PATH);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Produto> produtos = (ArrayList<Produto>)ois.readObject();
		Short codigoInformado = Short.parseShort(JOptionPane.showInputDialog("Qual o c�digo do produto?"));
		for(Produto p: produtos) {
			if(codigoInformado == p.getCodigo()) {
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
	private static void informarLogObjeto() {
		// TODO Auto-generated method stub

	}
}

		 

		    
		 

		


	