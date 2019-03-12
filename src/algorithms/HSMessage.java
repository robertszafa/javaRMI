package algorithms;

public class HSMessage extends Message {
    protected static final int IN_DIR = 1;
    protected static final int OUT_DIR = 0;
    private int dir;
    private int hopCount;

    /**
     * HS algorithm messsage
     * @param id
     * @param dir
     * @param hopCount
     */
    protected HSMessage(int id, int dir, int hopCount) {
        super(id);
        this.dir = dir;
        this.hopCount = hopCount;
    }

    /**
     * 
     * @param id
     * @param hopCount
     */
    protected HSMessage(int id, int hopCount) {
        super(id);
        this.hopCount = hopCount;
    }


    /**
     * @return the id
     */
    protected int getId() {
        return getData();
    }

    /**
     * @return the dir
     */
    protected int getDir() {
        return dir;
    }

    /**
     * @return the hopCount
     */
    protected int getHopCount() {
        return hopCount;
    }
}