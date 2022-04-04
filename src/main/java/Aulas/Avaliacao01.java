package Aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Avaliacao01 {
	private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\contatos_avaliacao.csv";
	public static class Produto implements Serializable{

		private String nome;
		private float preco;
		private short codigo;
		@Override
		public String toString() {
			return "nome=" + nome + ", preco=" + preco + ", codigo=" + codigo;
		}
		public Produto() {
			super();
			// TODO Auto-generated constructor stub
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
	public static void escreveObjeto() throws IOException{
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(new Produto("Batedeira",542.12, 12));
		produtos.add(new Produto("Geladeira",3120.20, 55));
		produtos.add(new Produto("Notebook",8750.00, 68));
		produtos.add(new Produto("Desktop",4780.20, 71));
		produtos.add(new Produto("CamaKing",2101.21, 66));
		produtos.add(new Produto("Microondas",540.33, 33));

		File f = new File(FILE_PATH);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(produtos);
		//fos.write(csv.getBytes());
		fos.close();
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException  {
		escreveObjeto();
		//leituraObjeto();
	}
	public static void leituraObjeto() throws IOException, ClassNotFoundException{

		File f = new File(FILE_PATH);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<Produto> produtos = (List<Produto>)ois.readObject();

		for(Produto p: produtos) {
			System.out.println(p);
		}
	}
}
	