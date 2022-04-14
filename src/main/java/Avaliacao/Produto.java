package Avaliacao;

import java.io.Serializable;


	public class Produto implements Serializable{

		private String nome;
		private float preco;
		private short codigo;

		@Override
		public String toString() {
			return "O Produto é =" + nome + ", Preço= R$" + preco + ", Código=" + codigo;
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


