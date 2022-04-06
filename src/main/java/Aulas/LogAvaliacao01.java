package Aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogAvaliacao01 {
	static class RegistroAtividade4{
        private String descricao;
        private long total;

 

        public RegistroAtividade4(){
            
        }
        
        public RegistroAtividade4(String descricao, long total){
            this.descricao = descricao;
            this.total = total;
        }
        public String getDescricao() {
            return descricao;
        }

 

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

 

        public long getTotal() {
            return total;
        }

 

        public void setTotal(long total) {
            this.total = total;
        }
        
        
        
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException /* throws Exception */ {
        final String filePath = "C:\\Users\\rafael.queiroz\\Desktop\\atividade4.txt"; 
        final String SEPARADOR = "$#";
        File f = new File(filePath);
        //ArrayList<RegistroAtividade4> lista = new ArrayList<RegistroAtividade4>();
        boolean temErro = false;
        if( f.exists() ){
            FileInputStream fis = new FileInputStream(f);           
            byte[] buffer = new byte[fis.available()];
            int totalBytes = fis.read(buffer);
            String conteudoArquivoText = new String(buffer);
            String[] registrosLinhas = conteudoArquivoText.split("\r\n");
            RegistroAtividade4[] lista =  new RegistroAtividade4[registrosLinhas.length];
            for(short i = 0; i < registrosLinhas.length; i++ ){
                conteudoArquivoText = registrosLinhas[i];
                //System.out.print(conteudoArquivoText);
                String descricao = "";
                long total = 0;     
                int index = conteudoArquivoText.indexOf(SEPARADOR);
                if (index == -1){ // !conteudoArquivoText.contains(SEPARADOR)
                     System.err.println("Arquivo com formato invalido. Separador não encontrado");
                     temErro = true;
                }else{
                   descricao = conteudoArquivoText.substring(0,index);//partes[0];
                   try{
                    total = Long.parseLong(conteudoArquivoText.substring(index + SEPARADOR.length(),
                            conteudoArquivoText.length())); //parte[1]
                    RegistroAtividade4 objeto = new RegistroAtividade4(descricao,total);
                    //lista.add(objeto);
                    lista[i] = objeto;
                   }catch(Exception e){
                        System.err.println("Arquivo possui um total invalido:"+conteudoArquivoText);
                        temErro = true;
                   }
                }  
            }
            if(!temErro){
                for (RegistroAtividade4 obj: lista){
                    System.out.println("Descricao:"+obj.getDescricao() + " | total: "+ obj.getTotal());
                }
            }
        }else{
            System.err.println("Arquivo não encontrado...."+ filePath);
            //Exception e = new Exception("Arquivo não encontrado...."+ filePath);   //throw e;
        }
    }
}
