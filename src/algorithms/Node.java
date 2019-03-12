/**
 * Robert Szafarczyk, February 2019, id: 201307211
 * 
 * Node class describes the local state of the node, its memory and methods to communicate with
 * its neighbours.
 */

package algorithms;

import java.util.HashMap;


public class Node {
    // Node states
    protected static final int LEADER_STATUS = -1;
    protected static final int UNKNOWN_STATUS = 0;

    private boolean isTerminated;
    private int status;
    private int id;
    private int leaderId;
    private int phaseHS;
    private int msgCounter; // count all send messages
    private Node clockwiseNeighbour;
    private Node counterclockwiseNeighbour;
    private Message clockBuffMsg;
    private Message counterBuffMsg;
    private HashMap<Node, Message> receivedMsg;

    /**
     * 
     * @param id of the node
     */
    protected Node (int id) {
        this.id = id;
        isTerminated = false;
        status = UNKNOWN_STATUS;
        msgCounter = 0;
        phaseHS = 0;
        receivedMsg = new HashMap<>();
    }

    /**
     * flush all memory, put into uknown state, node not terminated
     */
    protected void resetNode() {
        isTerminated = false;
        status = UNKNOWN_STATUS;
        leaderId = 0;
        phaseHS = 0;
        msgCounter = 0;
        clockBuffMsg = null;
        counterBuffMsg = null;
        receivedMsg.clear();
    }

    /**
     * will only send if there is a message in the buffer to the clockwise neighbour
     */
    protected void sendClock() {
        if (clockBuffMsg != null) {
            clockwiseNeighbour.receiveMsg(this, clockBuffMsg);
            msgCounter++;
        }
        clockBuffMsg = null;
    }

    /**
     * will only send if there is a message in the buffer to the counterclockwise neighbour
     */
    protected void sendCounterclock() {
        if (counterBuffMsg != null) {
            counterclockwiseNeighbour.receiveMsg(this, counterBuffMsg);
            msgCounter++;
        }
        counterBuffMsg = null;
    }

    // this will override any message previously received fromNode
    protected void receiveMsg(Node fromNode, Message msg) {
        // this will override the previous rcvd message -> 
        // a node will never have access to more than one message from each neighbour
        receivedMsg.put(fromNode, msg);
    }

    /**
     * @return the receivedMsg from clockwise neighbour
     */
    protected Message getRcvdMsgFromClock() {
        return receivedMsg.get(clockwiseNeighbour);
    }

    /**
     * @return the receivedMsg from counter clockwise neighbour
     */
    protected Message getRcvdMsgFromCounterclock() {
        return receivedMsg.get(counterclockwiseNeighbour);
    }

    /**
     * nullify the received msgs buffer
     */
    protected void flushRcvdMsgs() {
        receivedMsg.clear();
    }

    /**
     * @return the current phaseHS
     */
    protected int getPhase() {
        return phaseHS;
    }

    /**
     * increment phaseHS by 1
     */
    protected void incPhase() {
        phaseHS++;
    }

    /**
     * @return the id
     */
    protected int getId() {
        return id;
    }

    /**
     * @return the leaderId
     */
    protected int getLeaderId() {
        return leaderId;
    }

    /**
     * @return the msgCounter
     */
    protected int getMsgCounter() {
        return msgCounter;
    }

    /**
     * @return the status
     */
    protected int getStatus() {
        return status;
    }
    
    /**
     * @return the clockwiseNeighbour
     */
    protected Node getClockwiseNeighbour() {
        return clockwiseNeighbour;
    }

    /**
     * @return the counterclockwiseNeighbour
     */
    protected Node getCounterclockwiseNeighbour() {
        return counterclockwiseNeighbour;
    }

    protected void terminate() {
        isTerminated = true;
    }

    /**
     * Message generation for clockwise neighbour
     * @param clockBuffMsg the clockBuffMsg to set
     */
    protected void setClockBuffMsg(Message clockBuffMsg) {
        this.clockBuffMsg = clockBuffMsg;
    }

    /**
     * Message generation for clockwise neighbour
     * @param counterBuffMsg the counterBuffMsg to set
     */
    protected void setCounterBuffMsg(Message counterBuffMsg) {
        this.counterBuffMsg = counterBuffMsg;
    }

    /**
     * @param leaderId the leaderId to set
     */
    protected void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    /**
     * @return the isTerminated
     */
    protected boolean isTerminated() {
        return isTerminated;
    }

    /**
     * @param clockwiseNeighbour the clockwiseNeighbour to set
     */
    protected void setClockwiseNeighbour(Node clockwiseNeighbour) {
        this.clockwiseNeighbour = clockwiseNeighbour;
    }

    /**
     * @param counterclockwiseNeighbour the counterclockwiseNeighbour to set
     */
    protected void setCounterclockwiseNeighbour(Node counterclockwiseNeighbour) {
        this.counterclockwiseNeighbour = counterclockwiseNeighbour;
    }

    /**
     * @param status the status to set
     */
    protected void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) { 
        // if the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
        // check if o is a Node
        if (!(o instanceof Node)) { 
            return false; 
        } 
        // typecast to Node 
        Node n = (Node) o; 
        // compare the ID's of the Nodes
        return this.id == n.getId(); 
    } 
} 