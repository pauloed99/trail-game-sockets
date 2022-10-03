package com.mycompany.trail_game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerService extends Remote {
    public int informPlayerId() throws RemoteException;
    public void sendMove(int n, int lastPosition, int playerId) throws RemoteException;
    public void sendMsg(String msg, int playerId) throws RemoteException;
    public void informLocalization(String localization, int playerId) throws RemoteException;
}
