package Aulas;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.RandomAccessFile;
	import java.io.Serializable;
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.LinkedList;
	import java.util.List;

	 

	public class Aula30032022 {
	    private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\teams senac noturno\\contatos_serializable.csv";
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
	    
	    
	    public static String buscaLocalizadorPassageiro(int numeroAssento, RandomAccessFile ras) throws IOException{
	        final byte  inicioCampoLocalizador = 21;
	        long inicioLinhaPassageiro = 34 * (numeroAssento - 1);
	        ras.seek(inicioLinhaPassageiro+inicioCampoLocalizador);
	        byte[] localizadorBuffer = new byte[9];
	        ras.read(localizadorBuffer);
	        return new String(localizadorBuffer);
	    }
	    
	    
	    public static void atualizaSituacaoPassageiro(int numeroAssento, RandomAccessFile ras, byte situacao) throws IOException{
	        final byte  inicioCampoLocalizador = 31;
	        long inicioLinhaPassageiro = 34 * (numeroAssento - 1);
	        ras.seek(inicioLinhaPassageiro+inicioCampoLocalizador);
	        ras.write(String.valueOf(situacao).getBytes());
	    }
	    
	    public static String buscaSituacaoPassageiro(int numeroAssento, RandomAccessFile ras) throws IOException{
	        final byte  inicioCampoLocalizador = 31;
	        long inicioLinhaPassageiro = 34 * (numeroAssento - 1);
	        ras.seek(inicioLinhaPassageiro+inicioCampoLocalizador);
	        byte[] localizadorBuffer = new byte[9];
	        ras.read(localizadorBuffer);
	        return new String(localizadorBuffer);
	    }
	    
	    public static void main(String[] args) throws IOException, ClassNotFoundException  {
	        File f = new File("C:\\Users\\rafael.queiroz\\Desktop\\passageiros.csv");
	        RandomAccessFile ras = new RandomAccessFile(f, "rw");
	        int passageiro = 6;
	        String localizador = buscaLocalizadorPassageiro(passageiro, ras);
	        System.out.println("Localizador do passageiro:"+ passageiro + " é "+ localizador);
	        passageiro = 4;
	        String situacao = buscaSituacaoPassageiro(passageiro, ras);
	        System.out.println("Situação do passageiro:"+ passageiro + " é "+ situacao);
	        byte situacaoNova = 0;
	        atualizaSituacaoPassageiro(2,ras,situacaoNova);
	        /*
	        File f = new File("C:\\Users\\rafael.queiroz\\Desktop\\arquivo_leitura.txt");
	        RandomAccessFile ras = new RandomAccessFile(f, "rw");
	        ras.seek(20);    
	        ras.write("ACT".getBytes());
	        */

	 

	        /*
	        File f = new File("C:\\Users\\rafael.queiroz\\Desktop\\arquivo_leitura.txt");
	        RandomAccessFile ras = new RandomAccessFile(f, "r");
	        ras.seek(19);    
	        byte[] palavra = new byte[4];
	        ras.read(palavra);
	        
	        System.out.print("palavra: "+ new String(palavra));
	        */
	        /*
	         * File f = new File("C:\\Users\\rafael.queiroz\\Desktop\\arquivo_leitura.txt");
	        RandomAccessFile ras = new RandomAccessFile(f, "r");
	        ras.seek(5);    
	        byte numeroASCII = ras.readByte();
	        char caractere = (char) numeroASCII;
	        System.out.print("ASCII:"+numeroASCII);
	        System.out.print("char:"+ caractere);    
	        byte novaChar = ras.readByte();
	        char c1 = 5;
	        caractere = (char) (caractere + c1);
	        System.out.print("novaChar:"+ novaChar);    
	        if(caractere == numeroASCII) {
	            System.out.println("byte "+ numeroASCII +" é igual a "+ caractere);
	        }
	         */
	        
	    }
	    
	    //escreveObjeto();
	    //leituraObjeto();
	    
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
	
