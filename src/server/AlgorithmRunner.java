package server;

import java.rmi.Remote; 
import java.rmi.RemoteException; 
import algorithms.BidirectionalRing;

public interface AlgorithmRunner extends Remote {
    int RANDOM_IDS = BidirectionalRing.RANDOM_IDS;
    int CLOCK_ORDERED_IDS = BidirectionalRing.CLOCK_ORDERED_IDS;
    int COUNTER_ORDERED_IDS = BidirectionalRing.COUNTER_ORDERED_IDS;
    int REPEAT_FOR_RANDOM = 100;

    Complexity runAlgorithms (int n, int a, int idOrder) throws RemoteException;
} 