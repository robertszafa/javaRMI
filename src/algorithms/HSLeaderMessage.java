package algorithms;

public class HSLeaderMessage extends HSMessage {
    private int leaderId;

    /**
     * 
     * @param leaderId
     * @param hopCount
     */
    protected HSLeaderMessage(int leaderId, int hopCount) {
        super(Node.LEADER_STATUS, hopCount);
        this.leaderId = leaderId;
    }


    /**
     * @return the leaderId
     */
    protected int getLeaderId() {
        return leaderId;
    }
}