package server;

import algorithms.BidirectionalRing;
import static server.AlgorithmRunner.*;

import java.util.stream.IntStream;

public class AlgorithmImpl implements AlgorithmRunner {

    public AlgorithmImpl() {
        super();
    }


    /**
     * Run LCR and HS over all ring sizes from 2 to n. Repeast each ring size repeatSizeofN in 
     * case of random id assignment. Fill the count arrays with num of msgs and rounds. 
     */
    public Complexity runAlgorithms(int n, int a, int idOrder) {
        Complexity counts = new Complexity(n, idOrder);
        BidirectionalRing ring;

        /* loop through all ring sizes from 2 to n */
        for (int ringSize = 2; ringSize <= n; ringSize++) {

            // RANDOM IDS -> repeat size of ring to get best, worst and average case
            if (idOrder == RANDOM_IDS) {
                int[] tempRoundCountLCR = new int[ringSize]; 
                int[] tempMsgCountLCR = new int[ringSize]; 
                int[] tempRoundCountHS = new int[ringSize]; 
                int[] tempMsgCountHS = new int[ringSize]; 

                for (int i = 0; i < REPEAT_FOR_RANDOM; i++) {
                    ring = new BidirectionalRing(ringSize, a, idOrder);
                    tempRoundCountLCR[i] = ring.LCR();
                    tempMsgCountLCR[i] = ring.getMsgCount();
                    if (!ring.hasCorrectLeader()) counts.incErrorCountLCR();

                    // clear all info stored by the nodes. Leave only their id's
                    // nodes will be in the same state as when the ring was created the first time
                    ring.resetRing();
                    tempRoundCountHS[i] = ring.HS();
                    tempMsgCountHS[i] = ring.getMsgCount();
                    if (!ring.hasCorrectLeader()) counts.incErrorCountHS();
                }
                // average case
                counts.setRoundCountLCR(ringSize, 
                                        IntStream.of(tempRoundCountLCR).sum() / REPEAT_FOR_RANDOM);
                counts.setMsgCountLCR(ringSize, 
                                        IntStream.of(tempMsgCountLCR).sum() / REPEAT_FOR_RANDOM);
                counts.setRoundCountHS(ringSize, 
                                        IntStream.of(tempRoundCountHS).sum() / REPEAT_FOR_RANDOM);
                counts.setMsgCountHS(ringSize, 
                                        IntStream.of(tempMsgCountHS).sum() / REPEAT_FOR_RANDOM);
                // worst case
                counts.setWorstRoundCountLCR(ringSize, 
                                        IntStream.of(tempRoundCountLCR).max().getAsInt());
                counts.setWorstMsgCountLCR(ringSize, 
                                        IntStream.of(tempMsgCountLCR).max().getAsInt());
                counts.setWorstRoundCountHS(ringSize, 
                                        IntStream.of(tempRoundCountHS).max().getAsInt());
                counts.setWorstMsgCountHS(ringSize, 
                                        IntStream.of(tempMsgCountHS).max().getAsInt());
                // best case
                counts.setBestRoundCountLCR(ringSize, 
                                        IntStream.of(tempRoundCountLCR).min().getAsInt());
                counts.setBestMsgCountLCR(ringSize, 
                                        IntStream.of(tempMsgCountLCR).min().getAsInt());
                counts.setBestRoundCountHS(ringSize, 
                                        IntStream.of(tempRoundCountHS).min().getAsInt());
                counts.setBestMsgCountHS(ringSize, 
                                        IntStream.of(tempMsgCountHS).min().getAsInt());
            }
            else { // CLOCKWISE OR COUNTER CLOCKWISER IDS
                ring = new BidirectionalRing(ringSize, a, idOrder);
                counts.setRoundCountLCR(ringSize, ring.LCR());
                counts.setMsgCountLCR(ringSize, ring.getMsgCount());
                if (!ring.hasCorrectLeader()) counts.incErrorCountLCR();

                // clear all info stored by the nodes. Leave only their id's
                // nodes will be in the same state as when the ring was created the first time
                ring.resetRing();
                counts.setRoundCountHS(ringSize, ring.HS());
                counts.setMsgCountHS(ringSize, ring.getMsgCount());
                if (!ring.hasCorrectLeader()) counts.incErrorCountLCR();
            }
        }
        
        return counts;
    }
}