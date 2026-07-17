package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import util.Repositorio;
import util.Utilitarios;
import util.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Financiamento> listaFinanciamento = new ArrayList<>();

        boolean validar = false;
        int escolherOpcao;

        Utilitarios.mostrarMenuOpcoes();

        do {
            escolherOpcao = Validacoes.validarInteger(
                      Utilitarios.formatarPergunta("Escolha uma opção: "), 1);
            switch (escolherOpcao) {
                case 1:
                    Apartamento novoApartamento = Apartamento.coletarDados();
                    Repositorio.salvarDadosTxt(novoApartamento.toString());
                    listaFinanciamento.add(novoApartamento);
                    break;
                case 2:
                    Casa novaCasa = Casa.coletarDados();
                    Repositorio.salvarDadosTxt(novaCasa.toString());
                    listaFinanciamento.add(novaCasa);
                    break;
                case 3:
                    Terreno novoTerreno = Terreno.coletarDados();
                    Repositorio.salvarDadosTxt(novoTerreno.toString());
                    listaFinanciamento.add(novoTerreno);
                    break;
                case 4:
                    Repositorio.lerDadosTxt();
                    break;
                case 5:
                    validar = true;
                    Repositorio.salvarDadosObjeto(listaFinanciamento);
                    Utilitarios.formatarMensagemSaida("Lendo os dados do arquivo serializado...");
                    Utilitarios.sleep(1000);
                    Repositorio.lerDadosObjeto();
                    Utilitarios.sleep(1000);
                    Utilitarios.formatarMensagemSaida("Saindo do programa...");
                    Utilitarios.sleep(1000);
                    Utilitarios.formatarMensagemSaida("Programa encerrado. Até logo!");
                    break;
                default:
                    Utilitarios.formatarMensagemErro("ERRO: Comando inválido, digite novamente!");
                    break;
            }
        } while (!validar);
    }
}
