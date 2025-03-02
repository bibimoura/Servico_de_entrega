package com.correios;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Correios {
    public static String ARQUIVO = "pacote.txt";

    // Cadastrar novo pacote
    public static void cadastrarPacote(List<Pacote> pacotes) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n=-=--=-=-=-=-Cadastrar novo pacote =-=--=-=-=-=-");

        int id;
        boolean existePacote;

        do {
            System.out.print("ID do pacote: ");
            id = input.nextInt();
            input.nextLine();

            existePacote = false;

            for (Pacote pacote : pacotes) {
                if (pacote.getId() == id) {
                    existePacote = true;
                    break;
                }
            }

            if (existePacote) {
                System.out.println("Esse ID já existe! Digite um ID novo.");
            }
        } while (existePacote);

        System.out.print("Nome do destinatário: ");
        String name = input.nextLine();

        System.out.print("DESTINO [1] Pacote Nacional [2] Pacote Internacional: ");
        int destino = input.nextInt();
        input.nextLine();

        if (destino == 1) {
            System.out.print("Estado de destino: ");
            String estado = input.nextLine();
            pacotes.add(new PacoteNacional(id, name, estado));
            Correios.salvarPacote(pacotes);
            System.out.print("Cadastro concluido!");

        } else if (destino == 2) {
            System.out.print("País de destino: ");
            String pais = input.nextLine();
            pacotes.add(new PacoteInternacional(id, name, pais));
            Correios.salvarPacote(pacotes);
            System.out.print("Cadastro concluido!");

        } else {
            System.out.print("Destino inválido!");
        }
    }
    // Alterar informações de um pacote
    public static void alterarPacote( List<Pacote> pacotes) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=-=--=-=-=-=-Alterar informações de um pacote =-=--=-=-=-=-");
        System.out.print("Informe o ID do pacote: ");
        int novoId = input.nextInt();

        input.nextLine();
        boolean idEncontrado = false;

        for (Pacote pacote : pacotes) {
            if (pacote.getId() == novoId) {
                idEncontrado = true;

                System.out.print("Alterar o nome do destinatário? [Sim/Não] ou [sim/não]: ");
                String op = input.nextLine();

                if (op.equalsIgnoreCase("Sim")) {
                    System.out.print("Novo nome do destinatário: ");
                    String novoName = input.nextLine();
                    pacote.setName(novoName);
                    System.out.println("Informações alteradas!");
                } else {
                System.out.println("Nenhuma alteração no nome.");
            }

                // Alterar destino
                System.out.print("Alterar o destino? [Sim/Não] ou [sim/não]: ");
                op = input.nextLine();

                if (op.equalsIgnoreCase("Sim")) {
                    if (pacote instanceof PacoteNacional nacional) {
                        System.out.print("Novo estado de destino: ");
                        String novoDestino = input.nextLine();
                        nacional.alterarDestino(novoDestino);
                        System.out.println("Informações alteradas!");

                    } else if (pacote instanceof PacoteInternacional internacional) {
                        System.out.print("Novo país de destino: ");
                        String novoDestino = input.nextLine();
                        internacional.alterarDestino(novoDestino);
                        System.out.println("Informações alteradas!");
                    }
                } else {
                    System.out.println("Nenhuma alteração no destino.");
                }
                salvarPacote(pacotes);
                break;
            }
        }
        if (!idEncontrado) {
            System.out.println("Pacote não encontrado.");
        }
    }
    // Enviar um pacote
    public static void enviarPacote(List<Pacote> pacotes) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("\n=-=-==-=- Enviar pacote =-=-=-=-=");
        System.out.print("Informe o ID do pacote: ");
        int id = input.nextInt();
        input.nextLine();

        boolean removido = false;

        for (int i = 0; i < pacotes.size(); i++) {
            if (pacotes.get(i).getId() == id) {

                pacotes.remove(i);
                System.out.println("Pacote enviado!" );
                removido = true;

                salvarPacote(pacotes);
                break;
            }
        }
        if (!removido) {
            System.out.println("Pacote não encontrado.");
        }
    }
    // Listar pacotes que não foram enviados
    public static void listarPacotes(List<Pacote> pacotes)  {
        System.out.println("\n=-=--=-=-=-=-Pacotes que não foram enviados =-=--=-=-=-=-");

        boolean naoEnviado = false;

        for (Pacote pacote : pacotes) {
            if (!pacote.enviado()) {
                System.out.println(pacote.informacoes());
                naoEnviado = true;
            }
        }
        if (!naoEnviado) {
            System.out.println("Não há pacotes não enviados.");
        }
    }
    // Ler arquivo
    public static List<Pacote> lerPacotes() throws IOException {
        List<Pacote> pacotes = new ArrayList<>();
        String linha;

        FileReader fr = new FileReader(ARQUIVO);
        BufferedReader br = new BufferedReader(fr);

        while ((linha = br.readLine()) != null) {
            if (linha.trim().isEmpty()) {
                continue;
            }

            Pacote pacote = Pacote.fromString(linha);
            if (pacote != null) {
                pacotes.add(pacote);
            } else {
                System.out.println("ERRO");
            }
        }
        br.close();
        return pacotes;
    }
    // Salvar arquivo
    public static void salvarPacote(List<Pacote> pacotes) throws IOException{
        FileWriter fw = new FileWriter(ARQUIVO);
        BufferedWriter bf = new BufferedWriter(fw);
        for(Pacote pct : pacotes){
            bf.write(pct.toString());
            bf.newLine();
        }
        bf.close();
    }
}

