package com.mycompany.trail_game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {
    
    private ServerSocket serverSocketGame;
    private ServerSocket serverSocketChat;
    private int qtdPlayers;
    private ConnectionServerGame player1;
    private ConnectionServerGame player2;
    private ConexaoServidorChat chat1;
    private ConexaoServidorChat chat2;
    private int moveJ1;
    private int moveJ2;
    private boolean gameEnd;
    
    public Server(){
        System.out.println("----- Servidor -----");
        qtdPlayers = 0;
        
        try {
            serverSocketGame = new ServerSocket(51734);
            serverSocketChat = new ServerSocket(51738);
        } catch (IOException ex) {
            System.out.println("Erro no construtor do servidor");
        }
    }
    
    public void allowConnections(){
        try{
            System.out.println("Aguardando conexões...");
            while(qtdPlayers < 2){
                //Aguarda jogador se conectar ao jogo
                Socket socketJogo = serverSocketGame.accept();
                qtdPlayers++;
                System.out.println("Jogador " + qtdPlayers + " se conectou");
                
                //Define thread jogador que ficará executando
                ConnectionServerGame player = new ConnectionServerGame(socketJogo, qtdPlayers);
                
                Thread threadJogo = new Thread(player);
                threadJogo.start();
                
                //Aguarda jogador se conectar ao chat
                Socket socketChat = serverSocketChat.accept();
                //Define thread chat do jogador que ficará executando
                ConexaoServidorChat chat = new ConexaoServidorChat(socketChat, qtdPlayers);
                
                //Armazena nos atributos locais onde está cada conexão
                if(qtdPlayers == 1){
                    player1 = player;
                    chat1 = chat;
                }else{
                    player2 = player;
                    chat2 = chat;
                }
                
                Thread threadChat = new Thread(chat);
                threadChat.start();
            }
            //Não aceita mais conexões
            System.out.println("Os 2 jogadores já se conectaram.");
            /* Após os dois jogadores se conectarem, o primeiro jogador a se conectar
              recebe uma mensagem "/i" que permite o início do jogo*/
            chat1.sendAdversaryMsg("/i");
        } catch (IOException ex) {
            System.out.println("Erro em permiteConexoes()");
        }
    }
    
    private class ConnectionServerGame implements Runnable {
    
        private Socket socket;
        private DataInputStream entrada;
        private DataOutputStream saida;
        private int idJogador;

        public ConnectionServerGame(Socket socket, int idJogador){
            this.socket = socket;
            this.idJogador = idJogador;

            try {
                entrada = new DataInputStream(socket.getInputStream());
                saida = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                System.out.println("Erro no run do jogador");
            }
        }

        public void run(){
            try{
                //Envia ao jogador o seu id
                saida.writeInt(idJogador);
                saida.flush();
                //Execução da thread
                while(true){
                    if(idJogador == 1){
                        //Recebe o clique do botão feito pelo jogador e envia para o adversário
                        moveJ1 = entrada.readInt();
                        System.out.println("Jogador 1 clicou o botão " + moveJ1);
                        player2.sendAdversaryMove(moveJ1);
                    }else{
                        moveJ2 = entrada.readInt();
                        System.out.println("Jogador 2 clicou o botão " + moveJ2);
                        player1.sendAdversaryMove(moveJ2);
                    }
 
                    if(gameEnd == true){
                        System.out.println("Fim de jogo.");
                        break;
                    }
                }
                player1.closeConnection();
                player2.closeConnection();
            } catch (IOException ex) {
                System.out.println("Erro no run do jogador");
                System.exit(0);
            }
        }
        
        public void sendAdversaryMove(int botao){
            try{
                saida.writeInt(botao);
                saida.flush();
            } catch (IOException ex) {
                System.out.println("Erro no enviaJogadaAdversario() do Servidor");
            }
        }
        
        public void closeConnection(){
           try{
                socket.close();
                System.out.println("CONEXÃO ENCERRADA");
            } catch (IOException ex) {
                System.out.println("Erro no fechaConexao() do Servidor");
            } 
        }
    
    }
    
    private class ConexaoServidorChat implements Runnable {
    
        private Socket socket;
        private DataInputStream input;
        private DataOutputStream output;
        private int idPlayer;

        public ConexaoServidorChat(Socket socket, int idPlayer){
            this.socket = socket;
            this.idPlayer = idPlayer;

            try {
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                System.out.println("Erro no run do jogador");
            }
       
        }

        public void run(){
            try{
                
                String receivedMsg = "";
                //Execução da thread do chat
                while(!receivedMsg.equals("exit")){
                    //Aguarda jogador digitar mensagem no campo de texto
                    receivedMsg = input.readUTF();
                    //Só estabelece conexão entre os chats quando os dois jogadores se conectam
                    if(player2 != null){
                        if(idPlayer == 1){
                            //Jogador1 envia mensagem via chat2 para o adversário
                            chat2.sendAdversaryMsg(receivedMsg);
                        }else{
                            //Jogador2 envia mensagem via chat1 para o adversário
                            chat1.sendAdversaryMsg(receivedMsg);
                        }
                    }
                }
                
                chat1.closeConnection();
                chat2.closeConnection();
            } catch (IOException ex) {
                System.out.println("Erro no run do ConexaoChat");
            }
        }
        
        public void sendAdversaryMsg(String msg){
            try{
                output.writeUTF(msg);
                output.flush();
            } catch (IOException ex) {
                System.out.println("Erro no enviaMsgAdversario() do Servidor");
            }
        }
        
        public void closeConnection(){
           try{
                socket.close();
                System.out.println("-----CONEXÃO ENCERRADA-----");
            } catch (IOException ex) {
                System.out.println("Erro no fechaConexao() do Servidor");
            } 
        }
    
    }
    
    public static void main(String[] args){
        Server servidor = new Server();
        servidor.allowConnections();
    }
}
