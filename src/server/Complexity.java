package server;

import java.io.Serializable;

public class Complexity implements Serializable {
    public static final long serialVersionUID = 12345;
    private int errorCountHS, errorCountLCR;
    private int[] roundCountLCR, msgCountLCR;
    private int[] roundCountHS, msgCountHS;
    private int[] worstRoundCountLCR, worstMsgCountLCR, bestRoundCountLCR, bestMsgCountLCR;
    private int[] worstRoundCountHS, worstMsgCountHS, bestRoundCountHS, bestMsgCountHS;

    public Complexity (int n, int idOrder) {
        errorCountLCR = 0;
        errorCountHS = 0;
        roundCountLCR = new int[n];
        msgCountLCR = new int[n];
        roundCountHS = new int[n];
        msgCountHS = new int[n];

        if (idOrder == AlgorithmRunner.RANDOM_IDS) {
            worstRoundCountLCR = new int[n];
            worstMsgCountLCR = new int[n];
            bestRoundCountLCR = new int[n];
            bestMsgCountLCR = new int[n];
            worstRoundCountHS = new int[n];
            worstMsgCountHS = new int[n];
            bestRoundCountHS = new int[n];
            bestMsgCountHS = new int[n];
        }
    }


    /* SETTERS FOR LCR */    
    public void incErrorCountLCR() {
        errorCountLCR++;
    }
    public void setMsgCountLCR(int n, int count) {
        msgCountLCR[n-2] = count; 
    }
    public void setRoundCountLCR(int n, int count) {
        roundCountLCR[n-2] = count; 
    }

    /* GETTERS FOR LCR */    
    public int getErrorCountLCR() {
        return errorCountLCR;
    }
    public int[] getMsgCountLCR() {
        return msgCountLCR;
    }
    public int[] getRoundCountLCR() {
        return roundCountLCR;
    }


    /* SETTERS FOR HS */    
    public void incErrorCountHS() {
        errorCountHS++;
    }
    public void setMsgCountHS(int n, int count) {
        msgCountHS[n-2] = count; 
    }
    public void setRoundCountHS(int n, int count) {
        roundCountHS[n-2] = count; 
    }
    /* GETTERS FOR HS */    
    public int getErrorCountHS() {
        return errorCountHS;
    }
    public int[] getMsgCountHS() {
        return msgCountHS;
    }
    public int[] getRoundCountHS() {
        return roundCountHS;
    }


    /* GETTERS FOR BEST/WORST CASE LCR */    
    public int[] getBestRoundCountLCR() {
        return bestRoundCountLCR;
    }
    public int[] getBestMsgCountLCR() {
        return bestMsgCountLCR;
    }
    public int[] getWorstRoundCountLCR() {
        return worstRoundCountLCR;
    }
    public int[] getWorstMsgCountLCR() {
        return worstMsgCountLCR;
    }

    /* SETTERS FOR BEST/WORST CASE LCR */    
    public void setBestRoundCountLCR(int n, int count) {
        bestRoundCountLCR[n-2] = count; 
    }
    public void setBestMsgCountLCR(int n, int count) {
        bestMsgCountLCR[n-2] = count; 
    }
    public void setWorstRoundCountLCR(int n, int count) {
        worstRoundCountLCR[n-2] = count; 
    }
    public void setWorstMsgCountLCR(int n, int count) {
        worstMsgCountLCR[n-2] = count; 
    }


    /* GETTERS FOR BEST/WORST CASE HS */    
    public int[] getBestRoundCountHS() {
        return bestRoundCountHS;
    }
    public int[] getBestMsgCountHS() {
        return bestMsgCountHS;
    }
    public int[] getWorstRoundCountHS() {
        return worstRoundCountHS;
    }
    public int[] getWorstMsgCountHS() {
        return worstMsgCountHS;
    }

    /* SETTERS FOR BEST/WORST CASE HS */    
    public void setBestRoundCountHS(int n, int count) {
        bestRoundCountHS[n-2] = count; 
    }
    public void setBestMsgCountHS(int n, int count) {
        bestMsgCountHS[n-2] = count; 
    }
    public void setWorstRoundCountHS(int n, int count) {
        worstRoundCountHS[n-2] = count; 
    }
    public void setWorstMsgCountHS(int n, int count) {
        worstMsgCountHS[n-2] = count; 
    }

}