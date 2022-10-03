package com.mycompany.trail_game;

import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends javax.swing.JFrame {

    private IServerService serverService;
    private int idPlayer;
    private int otherPlayer;
    private int[] positions;
    private int qtdPieces;
    private int qtdPiecesAdv;
    private boolean activeButtons;
    private boolean requestDraw;
    private boolean gameEnd;
    private int clickCounter = 0;
    private int lastPosition;

    /**
     * Creates new form Jogador
     */
    public Client() {

        positions = new int[24];
        qtdPieces = 0;
        qtdPiecesAdv = 0;
        requestDraw = false;
        gameEnd = false;

        String localization = "//localhost/serverService";

        try {
            serverService = (IServerService) Naming.lookup(localization);
            idPlayer = serverService.informPlayerId();

            if (idPlayer == -1) {
                System.exit(0);
            }

            PlayerImpl player = new PlayerImpl();
            String playerLocalization = "//localhost/player" + idPlayer;

            Naming.rebind(playerLocalization, player);
            serverService.informLocalization(playerLocalization, idPlayer);
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("Erro no construtor do jogador: " + e.getMessage());
        }

        initComponents();

        this.setTitle("Jogador " + idPlayer + " - Trail game");

        if (idPlayer == 1) {
            title.setText("Jogador 1. Aguarde o Jogador #2 se conectar.");
            Color red = new Color(0xdb3c30);
            title.setBackground(red);
            otherPlayer = 2;
            activeButtons = false;
            chatField.setEnabled(false);
            chatButton.setEnabled(false);
        } else {
            title.setText("Jogador 2. Aguarde seu turno.");
            Color blue = new Color(0x3394e8);
            title.setBackground(blue);
            otherPlayer = 1;
            activeButtons = false;
        }

        updateButtonsStates();

    }

    public void updateButtonsStates() {//Habilita os botões de acordo com seu turno
        b1.setEnabled(activeButtons);
        b2.setEnabled(activeButtons);
        b3.setEnabled(activeButtons);
        b4.setEnabled(activeButtons);
        b5.setEnabled(activeButtons);
        b6.setEnabled(activeButtons);
        b7.setEnabled(activeButtons);
        b8.setEnabled(activeButtons);
        b9.setEnabled(activeButtons);
        b10.setEnabled(activeButtons);
        b11.setEnabled(activeButtons);
        b12.setEnabled(activeButtons);
        b13.setEnabled(activeButtons);
        b14.setEnabled(activeButtons);
        b15.setEnabled(activeButtons);
        b16.setEnabled(activeButtons);
        b17.setEnabled(activeButtons);
        b18.setEnabled(activeButtons);
        b19.setEnabled(activeButtons);
        b20.setEnabled(activeButtons);
        b21.setEnabled(activeButtons);
        b22.setEnabled(activeButtons);
        b23.setEnabled(activeButtons);
        b24.setEnabled(activeButtons);

        if (qtdPieces < 9) {
            if (positions[0] > 0) {
                b1.setEnabled(false);
            }
            if (positions[1] > 0) {
                b2.setEnabled(false);
            }
            if (positions[2] > 0) {
                b3.setEnabled(false);
            }
            if (positions[3] > 0) {
                b4.setEnabled(false);
            }
            if (positions[4] > 0) {
                b5.setEnabled(false);
            }
            if (positions[5] > 0) {
                b6.setEnabled(false);
            }
            if (positions[6] > 0) {
                b7.setEnabled(false);
            }
            if (positions[7] > 0) {
                b8.setEnabled(false);
            }
            if (positions[8] > 0) {
                b9.setEnabled(false);
            }
            if (positions[9] > 0) {
                b10.setEnabled(false);
            }
            if (positions[10] > 0) {
                b11.setEnabled(false);
            }
            if (positions[11] > 0) {
                b12.setEnabled(false);
            }
            if (positions[12] > 0) {
                b13.setEnabled(false);
            }
            if (positions[13] > 0) {
                b14.setEnabled(false);
            }
            if (positions[14] > 0) {
                b15.setEnabled(false);
            }
            if (positions[15] > 0) {
                b16.setEnabled(false);
            }
            if (positions[16] > 0) {
                b17.setEnabled(false);
            }
            if (positions[17] > 0) {
                b18.setEnabled(false);
            }
            if (positions[18] > 0) {
                b19.setEnabled(false);
            }
            if (positions[19] > 0) {
                b20.setEnabled(false);
            }
            if (positions[20] > 0) {
                b21.setEnabled(false);
            }
            if (positions[21] > 0) {
                b22.setEnabled(false);
            }
            if (positions[22] > 0) {
                b23.setEnabled(false);
            }
            if (positions[23] > 0) {
                b24.setEnabled(false);
            }
        }

        updateImagesButtons();
    }

    public void updateImagesButtons() {

        switch (positions[0]) {
            case 1:
                b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[1]) {
            case 1:
                b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[2]) {
            case 1:
                b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b3.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[3]) {
            case 1:
                b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[4]) {
            case 1:
                b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b5.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b5.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b5.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[5]) {
            case 1:
                b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b6.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b6.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b6.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[6]) {
            case 1:
                b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b7.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }
        switch (positions[7]) {
            case 1:
                b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b8.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b8.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b8.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[8]) {
            case 1:
                b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b9.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b9.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b9.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[9]) {
            case 1:
                b10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b10.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b10.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b10.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[10]) {
            case 1:
                b11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b11.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b11.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b11.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[11]) {
            case 1:
                b12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b12.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b12.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b12.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[12]) {
            case 1:
                b13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b13.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b13.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b13.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[13]) {
            case 1:
                b14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b14.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b14.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b14.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }
        switch (positions[14]) {
            case 1:
                b15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b15.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b15.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b15.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[15]) {
            case 1:
                b16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b16.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b16.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b16.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[16]) {
            case 1:
                b17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b17.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b17.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b17.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[17]) {
            case 1:
                b18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b18.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b18.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b18.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[18]) {
            case 1:
                b19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b19.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b19.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b19.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[19]) {
            case 1:
                b20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b20.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b20.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b20.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[20]) {
            case 1:
                b21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b21.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b21.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b21.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }
        switch (positions[21]) {
            case 1:
                b22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b22.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b22.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b22.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[22]) {
            case 1:
                b23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b23.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b23.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b23.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }

        switch (positions[23]) {
            case 1:
                b24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelha.png")));
                b24.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaVermelhaBloqueada.png")));
                break;
            case 2:
                b24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzul.png")));
                b24.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaAzulBloqueada.png")));
                break;
            default:
                b24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                b24.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png")));
                break;
        }
    }

    public void alterButtonsStates(int valueCompared, boolean state) {
        if (positions[0] == valueCompared) {
            b1.setEnabled(state);
        }
        if (positions[1] == valueCompared) {
            b2.setEnabled(state);
        }
        if (positions[2] == valueCompared) {
            b3.setEnabled(state);
        }
        if (positions[3] == valueCompared) {
            b4.setEnabled(state);
        }
        if (positions[4] == valueCompared) {
            b5.setEnabled(state);
        }
        if (positions[5] == valueCompared) {
            b6.setEnabled(state);
        }
        if (positions[6] == valueCompared) {
            b7.setEnabled(state);
        }
        if (positions[7] == valueCompared) {
            b8.setEnabled(state);
        }
        if (positions[8] == valueCompared) {
            b9.setEnabled(state);
        }
        if (positions[9] == valueCompared) {
            b10.setEnabled(state);
        }
        if (positions[10] == valueCompared) {
            b11.setEnabled(state);
        }
        if (positions[11] == valueCompared) {
            b12.setEnabled(state);
        }
        if (positions[12] == valueCompared) {
            b13.setEnabled(state);
        }
        if (positions[13] == valueCompared) {
            b14.setEnabled(state);
        }
        if (positions[14] == valueCompared) {
            b15.setEnabled(state);
        }
        if (positions[15] == valueCompared) {
            b16.setEnabled(state);
        }
        if (positions[16] == valueCompared) {
            b17.setEnabled(state);
        }
        if (positions[17] == valueCompared) {
            b18.setEnabled(state);
        }
        if (positions[18] == valueCompared) {
            b19.setEnabled(state);
        }
        if (positions[19] == valueCompared) {
            b20.setEnabled(state);
        }
        if (positions[20] == valueCompared) {
            b20.setEnabled(state);
        }
        if (positions[21] == valueCompared) {
            b21.setEnabled(state);
        }
        if (positions[22] == valueCompared) {
            b22.setEnabled(state);
        }
        if (positions[23] == valueCompared) {
            b23.setEnabled(state);
        }
    }

    public void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            alterButtonsStates(i, false);
        }
    }

    public void buttonClick(int n) {
        //No início do jogo, apenas marca a posição com a cor de sua peça
        if (qtdPieces < 9) {
            if (idPlayer == 1) {
                positions[n - 1] = 1;
            } else {
                positions[n - 1] = 2;
            }
            qtdPieces++;
            //Só troca o turno quando a jogada é válida, no início todas serão válidas
            title.setText("Você clicou o botão #" + n + ". Agora a vez do jogador #" + otherPlayer);

            activeButtons = false;
            if (qtdPieces == 9) {
                activeButtons = true;
                clickCounter = 0;
            }
            updateButtonsStates();
            try {
                serverService.sendMove(n, lastPosition, otherPlayer);
            } catch (RemoteException e) {
                System.out.println("Erro no buttonClick do jogador: " + e.getMessage());
            }

        } else if (clickCounter == 2) {
            try {
                //Movimentação de peças
                clickCounter = 0;
                if (idPlayer == 1) {
                    positions[n - 1] = 1;
                    positions[lastPosition] = 0;
                } else {
                    positions[n - 1] = 2;
                    positions[lastPosition] = 0;
                }
                //Só troca o turno quando a jogada é válida, no início todas serão válidas
                title.setText("Você clicou o botão #" + n + ". Agora a vez do jogador #" + otherPlayer);

                activeButtons = true;
                updateButtonsStates();
                serverService.sendMove(n, lastPosition, otherPlayer);
            } catch (RemoteException e) {
                System.out.println("Erro no buttonClick do jogador: " + e.getMessage());
            }

        }

    }

    public void sendChatMsg() {
        String msg = "";
        msg = chatField.getText();

        if (msg.equals("/d") && !gameEnd) {
            chatArea.setText(chatArea.getText() + "\n Você desistiu! Seu adversario venceu.");
            title.setText("Você desistiu! Seu adversario venceu.");
            disableAllButtons();
            gameEnd = true;
        } else if (msg.equals("/e") && !gameEnd) {
            if (requestDraw) {
                chatArea.setText(chatArea.getText() + "\n Aguarde o adversário concordar com empate.");
            } else if (!gameEnd) {
                requestDraw = true;
                chatArea.setText(chatArea.getText() + "\n Você solicitou empate ao adversário.");
            }
        } else if (!msg.isBlank()) {
            chatArea.setText(chatArea.getText() + "\n Eu: " + msg);
        }
        try {
            serverService.sendMsg(msg, otherPlayer);
        } catch (RemoteException e) {
            System.out.println("Erro no enviaMensagemChat do jogador: " + e.getMessage());
        }
        chatField.setText("");
    }

    public class PlayerImpl extends UnicastRemoteObject implements IPlayerService {

        public PlayerImpl() throws RemoteException {
            super();
            System.out.println("----- Jogador #" + idPlayer + " -----");
        }

        @Override
        public void updateChat(String msg) throws RemoteException {
            if (msg.equals("/d") && !gameEnd) {
                chatArea.setText(chatArea.getText() + "\n Você venceu! Seu adversario desistiu.");
                title.setText("Você venceu! Seu adversario desistiu.");
                disableAllButtons();
                gameEnd = true;
            } else if (msg.equals("/e") && !gameEnd) {
                if (requestDraw) {
                    chatArea.setText(chatArea.getText() + "\n Jogadores concordaram com empate.\n Fim de jogo.");
                    title.setText("Fim de jogo! Jogadores concordaram com empate. ");
                    disableAllButtons();
                    //clienteChat.enviaMensagem("/e");
                    requestDraw = false;
                    gameEnd = true;

                    try {
                        serverService.sendMsg("/e", otherPlayer);
                    } catch (RemoteException e) {
                        System.out.println("Erro no atualizaChat do jogador: " + e.getMessage());
                    }

                } else {
                    chatArea.setText(chatArea.getText() + "\n Seu adversário solicitou empate\n Envie /e para aceitar.");
                }
            } else if (msg.equals("/f")) {
                chatArea.setText(chatArea.getText() + "\n Fim de jogo. Você perdeu!");
                title.setText("Fim de jogo! Você perdeu.");
                disableAllButtons();
                gameEnd = true;
            } else if (msg.equals("/i")) {//Quando os dois se conectam o jogador 1 pode jogar
                if (idPlayer == 1) {
                    activeButtons = true;
                    updateButtonsStates();
                    chatField.setEnabled(true);
                    chatButton.setEnabled(true);
                    title.setText("Jogador #1. Inicie a partida!");
                    chatArea.setText("Jogador #2 se conectou.");
                }
            } else if (!msg.isBlank()) {
                chatArea.setText(chatArea.getText() + "\n Adversario: " + msg);
            }
            chatArea.setCaretPosition(chatArea.getText().length());
        }

        @Override
        public void updateTurn(int n, int lastPosition) throws RemoteException {
            //Aguarda seu turno enquanto recebe uma jogada válida do adversário

            
            //No início do jogo apenas dispões as peças no tabuleiro
            if (qtdPiecesAdv < 9) {
                
                //Atualiza o vetor com a jogada recebida do adversário
                if (idPlayer == 1) {
                    positions[n - 1] = 2;
                } else {
                    positions[n - 1] = 1;
                }
                qtdPiecesAdv++;
                if(qtdPiecesAdv == 9 && idPlayer == 1) {
                    title.setText("Seu adversário clicou o botão #" + n + ". Sua vez\nClique em um botão seu e em outro botão vazio para movimentar sua peça");
                } else {
                    title.setText("Seu adversário clicou o botão #" + n + ". Sua vez");
                }
            } else {//Desenvolvimento do jogo após peças dispostas
                //Atualiza o vetor trocando a jogada do advserário com a posição vazia
                title.setText("Seu adversário clicou o botão #" + n + ". Sua vez\nClique em um botão seu e em outro botão vazio para movimentar sua peça");
                if (idPlayer == 1) {//n-1 é a posição do botão na interface
                    positions[lastPosition] = 0;
                    positions[n - 1] = 2;
                } else {
                    positions[lastPosition] = 0;
                    positions[n - 1] = 1;
                }
            }
            //Só habilita a movimentação de peças após as 9 estiverem dispostas no tabuleiro
            if (qtdPieces == 9 && qtdPiecesAdv == 9) {
                //Habilita somente as peças do jogador correspondente ao turno
                if (idPlayer == 1) {
                    alterButtonsStates(1, true);
                    alterButtonsStates(2, false);
                } else {
                    alterButtonsStates(2, true);
                    alterButtonsStates(1, false);
                }

                updateImagesButtons();
            } else {//No início do jogo apenas atualiza o tabuleiro de acordo com as peças colocadas
                activeButtons = true;
                updateButtonsStates();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        chatField = new javax.swing.JTextField();
        chatButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        title = new javax.swing.JTextArea();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b18 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b19 = new javax.swing.JButton();
        b20 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        chatArea.setEditable(false);
        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(620, 220, 230, 180);

        chatField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatFieldActionPerformed(evt);
            }
        });
        chatField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chatFieldKeyPressed(evt);
            }
        });
        getContentPane().add(chatField);
        chatField.setBounds(620, 410, 230, 22);

        chatButton.setText("Enviar");
        chatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatButtonActionPerformed(evt);
            }
        });
        getContentPane().add(chatButton);
        chatButton.setBounds(680, 440, 120, 25);

        title.setColumns(20);
        title.setRows(5);
        jScrollPane3.setViewportView(title);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(30, 70, 480, 86);

        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b1.setContentAreaFilled(false);
        b1.setFocusPainted(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        getContentPane().add(b1);
        b1.setBounds(30, 180, 50, 50);

        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b2.setContentAreaFilled(false);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        getContentPane().add(b2);
        b2.setBounds(230, 180, 50, 50);

        b24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b24.setContentAreaFilled(false);
        b24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b24ActionPerformed(evt);
            }
        });
        getContentPane().add(b24);
        b24.setBounds(440, 580, 50, 50);

        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b3.setContentAreaFilled(false);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        getContentPane().add(b3);
        b3.setBounds(440, 180, 50, 50);

        b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b4.setContentAreaFilled(false);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        getContentPane().add(b4);
        b4.setBounds(100, 250, 50, 50);

        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b5.setContentAreaFilled(false);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        getContentPane().add(b5);
        b5.setBounds(230, 250, 50, 50);

        b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b6.setContentAreaFilled(false);
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        getContentPane().add(b6);
        b6.setBounds(360, 250, 50, 50);

        b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b7.setContentAreaFilled(false);
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        getContentPane().add(b7);
        b7.setBounds(180, 330, 50, 50);

        b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b8.setContentAreaFilled(false);
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        getContentPane().add(b8);
        b8.setBounds(230, 330, 50, 50);

        b9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b9.setContentAreaFilled(false);
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        getContentPane().add(b9);
        b9.setBounds(290, 330, 50, 50);

        b10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b10.setContentAreaFilled(false);
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });
        getContentPane().add(b10);
        b10.setBounds(30, 380, 50, 50);

        b11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b11.setContentAreaFilled(false);
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        getContentPane().add(b11);
        b11.setBounds(100, 380, 50, 50);

        b12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b12.setContentAreaFilled(false);
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });
        getContentPane().add(b12);
        b12.setBounds(180, 380, 50, 50);

        b16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b16.setContentAreaFilled(false);
        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });
        getContentPane().add(b16);
        b16.setBounds(180, 430, 50, 50);

        b17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b17.setContentAreaFilled(false);
        b17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b17ActionPerformed(evt);
            }
        });
        getContentPane().add(b17);
        b17.setBounds(230, 430, 50, 50);

        b18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b18.setContentAreaFilled(false);
        b18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b18ActionPerformed(evt);
            }
        });
        getContentPane().add(b18);
        b18.setBounds(280, 430, 50, 50);

        b13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b13.setContentAreaFilled(false);
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        getContentPane().add(b13);
        b13.setBounds(280, 380, 50, 50);

        b14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b14.setContentAreaFilled(false);
        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });
        getContentPane().add(b14);
        b14.setBounds(360, 380, 50, 50);

        b15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b15.setContentAreaFilled(false);
        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });
        getContentPane().add(b15);
        b15.setBounds(430, 380, 50, 50);

        b19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b19.setContentAreaFilled(false);
        b19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b19ActionPerformed(evt);
            }
        });
        getContentPane().add(b19);
        b19.setBounds(110, 500, 50, 50);

        b20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b20.setContentAreaFilled(false);
        b20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b20ActionPerformed(evt);
            }
        });
        getContentPane().add(b20);
        b20.setBounds(230, 500, 50, 50);

        b21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b21.setContentAreaFilled(false);
        b21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b21ActionPerformed(evt);
            }
        });
        getContentPane().add(b21);
        b21.setBounds(360, 500, 50, 50);

        b22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b22.setContentAreaFilled(false);
        b22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b22ActionPerformed(evt);
            }
        });
        getContentPane().add(b22);
        b22.setBounds(30, 570, 50, 50);

        b23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/espacoVazio.png"))); // NOI18N
        b23.setContentAreaFilled(false);
        b23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b23ActionPerformed(evt);
            }
        });
        getContentPane().add(b23);
        b23.setBounds(230, 580, 50, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trail_tab.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 180, 460, 440);

        jLabel2.setText("/e - Empatar");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(620, 520, 90, 16);

        jLabel3.setText("/d - Desistir");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(620, 490, 90, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatButtonActionPerformed
        sendChatMsg();
    }//GEN-LAST:event_chatButtonActionPerformed

    private void chatFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatFieldActionPerformed

    }//GEN-LAST:event_chatFieldActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 0;
        }
        buttonClick(1);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 1;
        }
        buttonClick(2);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 2;
        }
        buttonClick(3);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 3;
        }
        buttonClick(4);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 4;
        }
        buttonClick(5);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 5;
        }
        buttonClick(6);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 6;
        }
        buttonClick(7);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 7;
        }
        buttonClick(8);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 8;
        }
        buttonClick(9);
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 9;
        }
        buttonClick(10);
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 10;
        }
        buttonClick(11);
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 11;
        }
        buttonClick(12);
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 12;
        }
        buttonClick(13);
    }//GEN-LAST:event_b13ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 13;
        }
        buttonClick(14);
    }//GEN-LAST:event_b14ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 14;
        }
        buttonClick(15);
    }//GEN-LAST:event_b15ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 15;
        }
        buttonClick(16);
    }//GEN-LAST:event_b16ActionPerformed

    private void b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 16;
        }
        buttonClick(17);
    }//GEN-LAST:event_b17ActionPerformed

    private void b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 17;
        }
        buttonClick(18);
    }//GEN-LAST:event_b18ActionPerformed

    private void b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 18;
        }
        buttonClick(19);
    }//GEN-LAST:event_b19ActionPerformed

    private void b20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b20ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 19;
        }
        buttonClick(20);
    }//GEN-LAST:event_b20ActionPerformed

    private void b21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b21ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 20;
        }
        buttonClick(21);
    }//GEN-LAST:event_b21ActionPerformed

    private void b22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b22ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 21;
        }
        buttonClick(22);
    }//GEN-LAST:event_b22ActionPerformed

    private void b23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b23ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 22;
        }
        buttonClick(23);
    }//GEN-LAST:event_b23ActionPerformed

    private void b24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b24ActionPerformed
        clickCounter++;
        if (clickCounter == 1) {
            lastPosition = 23;
        }
        buttonClick(24);
    }//GEN-LAST:event_b24ActionPerformed

    private void chatFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatFieldKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            sendChatMsg();
        }
    }//GEN-LAST:event_chatFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b18;
    private javax.swing.JButton b19;
    private javax.swing.JButton b2;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton chatButton;
    private javax.swing.JTextField chatField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea title;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });

    }
}
