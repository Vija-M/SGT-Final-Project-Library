package objects;

import java.util.StringJoiner;

public class UserAccess {
    private int userAccessID;
    private int userID;
    private int roleID;

    public UserAccess(int userAccessID, int userID, int roleID) {
        this.userAccessID = userAccessID;
        this.userID = userID;
        this.roleID = roleID;
    }

    public int getUserAccessID() {
        return userAccessID;
    }

    public void setUserAccessID(int userAccessID) {
        this.userAccessID = userAccessID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserAccess.class.getSimpleName() + "[", "]")
                .add("userAccessID=" + userAccessID)
                .add("userID=" + userID)
                .add("roleID=" + roleID)
                .toString();
    }
}

