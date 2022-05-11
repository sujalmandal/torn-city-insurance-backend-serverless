package s.m.torn;

public class Insurance {

    private long id;
    private String insurerId;
    private String insureeId;

    private int insuranceType;
    private int status;
    private long createdDate;
    private long updatedDate;

    public static enum InsuranceType {
        HAPPY_JUMP
    }

    public static enum Status {
        CLAIMABLE,
        EXPIRED,
        CLAIMED
    }

}
