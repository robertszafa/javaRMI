package algorithms;

public class LCRLeaderMessage extends LCRMessage {
    private int leaderId;

    /**
     * 
     * @param leaderId
     */
    protected LCRLeaderMessage(int leaderId) {
        super(Node.LEADER_STATUS);
        this.leaderId = leaderId;
    }

    /**
     * @return the leaderId
     */
    protected int getLeaderId() {
        return leaderId;
    }
}