package util;

import modelo.Financiamento;

import java.io.*;
import java.util.List;

public class Repositorio {

    // Constantes
    private static final String ARQUIVO_TXT = "DadosFinanciamento.txt";
    private static final String ARQUIVO_DAT = "DadosSerializados.dat";

    // Métodos
    /**
    * Salva os dados do financiamento em arquivo texto.
    * @param texto dados formatados a serem salvos.
    */
    public static void salvarDadosTxt(String texto) {
        FileWriter escreverDados = null;

        try {
            escreverDados = new FileWriter(ARQUIVO_TXT, true);
            escreverDados.write(texto);
            escreverDados.flush();
            escreverDados.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Lê e exibe os dados do arquivo texto.
    */
    public static void lerDadosTxt() {
        FileReader lerDados = null;

        try {
            lerDados = new FileReader(ARQUIVO_TXT);
            int arquivo;
            while ((arquivo = lerDados.read()) != -1) {
                System.out.print((char) arquivo);
            }
            lerDados.close();
        } catch (FileNotFoundException e) {
            Utilitarios.formatarMensagemErro("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Salva a lista de financiamentos em arquivo serializado.
    * @param lista lista de financiamentos a ser salva.
    */
    public static void salvarDadosObjeto(List<Financiamento> lista) {
        ObjectOutputStream salvarObjeto = null;

        try {
            salvarObjeto = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DAT));
            for  (Financiamento objeto : lista) {
                salvarObjeto.writeObject(objeto);
            }
            salvarObjeto.flush();
            salvarObjeto.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Lê e exibe os dados serializados do arquivo.
    */
    public static void lerDadosObjeto() {
        ObjectInputStream lerObjeto = null;

        try {
            lerObjeto = new ObjectInputStream(new FileInputStream(ARQUIVO_DAT));
            Object objeto = null;
            while ((objeto = lerObjeto.readObject()) != null) {
                if(objeto instanceof Financiamento){
                    System.out.println(((Financiamento)objeto));
                }
            }
            lerObjeto.close();
        } catch (EOFException e) {
            Utilitarios.formatarResposta("Leitura concluida!");
        } catch (FileNotFoundException e) {
            Utilitarios.formatarMensagemErro("Arquivo não encontrado!");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
