package Aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

 

public class  Aula28032022 {
    private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\contatos_avaliacao.csv";
    public static class Contato implements Serializable{
        private String nome;
        private String telefone;
        private String email;
        public Contato() {
            
        }
        public Contato(String nome, String email, String telefone)  {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
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
    
        
        @Override
        public String toString() {
            return "{nome:" + nome + ", telefone:" + telefone + ", email:" + email + "}";
        }
        
        
    }
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
        escreveObjeto();
        leituraObjeto();
    }
    
    public static void leituraObjeto() throws IOException, ClassNotFoundException{
        
        File f = new File(FILE_PATH);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Contato> contatos = (List<Contato>)ois.readObject();
        /*
        byte[] dados = fis.readAllBytes();
        String csv = new String(dados);
        String[] linhas = csv.split("\n");
        for(String linha: linhas) {
            String[] colunas = linha.split(";");
            Contato c = new Contato(colunas[0], colunas[1], colunas[2]);
            contatos.add(c);
        }
        */
        for(Contato c: contatos) {
            System.out.println(c);
        }
    }
    
    public static void escreveObjeto() throws IOException{
        List<Contato> contatos = new ArrayList<Contato>();
        contatos.add(new Contato("João","joao@mail.com", "+55(00)9999-9999"));
        contatos.add(new Contato("Maria","maria@mail.com", "+55(00)9999-9999"));
        contatos.add(new Contato("Pedro","pedro@mail.com", "+55(00)9999-9999"));
        contatos.add(new Contato("Paulo","paulo@mail.com", "+55(00)9999-9999"));
        contatos.add(new Contato("Jose","jose@mail.com", "+55(00)9999-9999"));
        contatos.add(new Contato("Rafael","rafael@mail.com", "+55(00)9999-9999"));
        /*
        String csv = "";
        for(Contato c: contatos) {
            csv += c.getNome() + ";";
            csv += c.getEmail() + ";";
            csv += c.getTelefone() + "\n";        
        }
        */
        File f = new File(FILE_PATH);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(contatos);
        //fos.write(csv.getBytes());
        fos.close();
    }
    

 

}