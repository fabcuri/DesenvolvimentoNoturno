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

public class Aula28032022 {
	private final static String FILE_PATH= "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\contatos_serializable.csv";
	

	public static class Contato implements Serializable{
	private String nome;
	private String telefone;
	private String email;
	public Contato() {
		
	}
	public Contato(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
 
   
}
	
	
	@Override
	public String toString() {
		return "Aula28032022 []";
	}
	public static void main(String[]args) throws IOException, ClassNotFoundException {
		escreveObjeto();
		leituraObjeto();
	
	}
	public static void leituraObjeto() throws IOException, ClassNotFoundException {
		File f=new File(FILE_PATH);
		FileInputStream fis= new FileInputStream(f);
		ObjectInputStream ois= new ObjectInputStream(fis);
		List<Contato> contatos= (List<Contato>)ois.readObject();
		for(Contato c:contatos) {
			System.out.println(c);
		}
	}
	public static void escreveObjeto() throws IOException {
		List<Contato> contatos= new ArrayList<Contato>();
		contatos.add(new Contato("Maria","maria@gmail.com","+55(00)8888-8888"));
		contatos.add(new Contato("Joao","joao@gmail.com","+55(00)8888-8888"));
		contatos.add(new Contato("Jose","jose@gmail.com","+55(00)8888-8888"));
		contatos.add(new Contato("Ana","ana@gmail.com","+55(00)8888-8888"));
		contatos.add(new Contato("Paulo","paulo@gmail.com","+55(00)8888-8888"));
		
		String csv="";
		for(Contato c:contatos) {
			csv+=c.getNome()+";";
			csv+=c.getEmail()+";";
			csv+=c.getTelefone()+"\n";
		}
		File f=new File(FILE_PATH);
		FileOutputStream fos= new FileOutputStream(f);
		ObjectOutputStream oos= new ObjectOutputStream(fos);
		oos.writeObject(csv.getBytes());
		fos.close();
		
		
		}
}
