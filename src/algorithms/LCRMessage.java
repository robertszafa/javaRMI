package algorithms;

public class LCRMessage extends Message{

    /**
     * LCR algorithm message
     * @param id
     */
    protected LCRMessage(int id) {
        super(id);
    }

    /**
     * @return the id
     */
    protected int getId() {
        return getData();
    }
}