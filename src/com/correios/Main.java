package com.correios;

import java.util.List;
import java.util.Scanner;
import java.lang.Exception;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Pacote> pacotes = Correios.lerPacotes();

        int opcao;

        do {
            System.out.println("\n=-=-=-=-=-=- CORREIOS =-=-=-=-=-=");
            System.out.println("[1]. Cadastrar novo pacote.");
            System.out.println("[2]. Alterar informações de um pacote.");
            System.out.println("[3]. Enviar um pacote.");
            System.out.println("[4]. Listar pacotes que não foram enviados.");
            System.out.println("[5]. Sair");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Escolha uma opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    Correios.cadastrarPacote(pacotes);
                    break;

                case 2:
                    Correios.alterarPacote(pacotes);
                    break;

                case 3:
                    Correios.enviarPacote(pacotes);
                    break;

                case 4:
                    Correios.listarPacotes(pacotes);
                    break;

                case 5:
                    System.out.println("Fim do expediente!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 5);
    }
}
