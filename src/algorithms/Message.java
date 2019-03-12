package algorithms;

public class Message {
    int data;
    
    /**
     * 
     * @param data
     */
    protected Message(int data) {
        this.data = data;
    }

    /**
     * @return the data
     */
    protected int getData() {
        return data;
    }

    protected boolean isLeaderMsg() {
        return data == Node.LEADER_STATUS;
    }
}