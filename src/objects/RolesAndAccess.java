package objects;

import java.util.StringJoiner;

public class RolesAndAccess {
    private int roleID;
    private String role;
    private boolean isLibrarian;

    public RolesAndAccess(int roleID, String role, boolean isLibrarian) {
        this.roleID = roleID;
        this.role = role;
        this.isLibrarian = isLibrarian;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!(role.isEmpty()) || role.matches("[a-zA-Z]")) {
            this.role = role;
        } else {
            System.out.println("Please add user's role!");
        }
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean isLibrarian) {
                    this.isLibrarian = false;
        }


    @Override
    public String toString() {
        return new StringJoiner(", ", RolesAndAccess.class.getSimpleName() + "[", "]")
                .add("roleID=" + roleID)
                .add("role='" + role + "'")
                .add("isLibrarian=" + isLibrarian)
                .toString();
    }
}
