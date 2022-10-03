package com.mycompany.trail_game;

import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IServerService {

    private int qtdPlayers;
    private IPlayerService player1;
    private IPlayerService player2;

    public Server() throws RemoteException {
        super();
        System.out.println("----- Servidor -----");
        qtdPlayers = 0;
    }

    @Override
    public int informPlayerId() throws RemoteException {
        if (qtdPlayers < 2) {
            qtdPlayers++;
            System.out.println("Jogador #" + qtdPlayers + " se conectou");

            if (qtdPlayers == 2) {
                //Não aceita mais conexões
                System.out.println("Os 2 jogadores já se conectaram.");
                /* Após os dois jogadores se conectarem, o primeiro jogador a se conectar
                  recebe uma mensagem "/i" que permite o início do jogo*/
                sendMsg("/i", 1);
            }

            return qtdPlayers;
        } else {
            System.out.println("ERRO: Reinicie o servidor");
            return -1;
        }
    }

    @Override
    public void sendMove(int n, int lastPosition, int playerId) throws RemoteException {
        if (playerId == 1) {
            player1.updateTurn(n, lastPosition);
        } else {
            player2.updateTurn(n, lastPosition);
        }
    }

    @Override
    public void sendMsg(String msg, int playerId) throws RemoteException {
        if (playerId == 1) {
            player1.updateChat(msg);
        } else {
            player2.updateChat(msg);
        }
    }

    @Override
    public void informLocalization(String localization, int playerId) throws RemoteException {
        try {
            //Armazena nos atributos locais onde está cada jogador
            if (playerId == 1) {
                player1 = (IPlayerService) Naming.lookup(localization);
            } else if (playerId == 2) {
                player2 = (IPlayerService) Naming.lookup(localization);
            }
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws RemoteException {
        Server server = new Server();
        String localization = "//localhost/serverService";
        try {
            /*
                - Antes de executar o servidor execute o rmiregistry dentro do
                diretório de classes: tsoroyematatu\target\classes
                
                1) Abrir terminal e navegar até o Path:
                        cd (Diretório do projeto)/tsoroyematatu/target/classes
            
                2) Digitar comando dentro do diretório para executar o RMIRegistry
                        rmiregistry
            
             */

            Naming.rebind(localization, server);
            System.out.println("Aguardando Clientes!");
        } catch (RemoteException ex) {

        } catch (MalformedURLException ex) {
            System.out.println("Erro de url mal formado:" + ex.getMessage());
        }
    }
}
