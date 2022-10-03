package com.mycompany.trail_game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPlayerService extends Remote {
    public void updateChat(String msg) throws RemoteException;
    public void updateTurn(int n, int lastPosition) throws RemoteException;
}
