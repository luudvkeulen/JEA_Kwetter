package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "UserGroup.findAll", query = "SELECT ug FROM UserGroup ug")
public class UserGroup implements Serializable {

    @Id
    private String groupName;

    @ManyToMany
    @JoinTable(name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "groupName",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"))
    private Set<User> users = new HashSet<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public UserGroup() {

    }

    public UserGroup(String groupName) {
        this.groupName = groupName;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserGroup)) {
            return false;
        }

        UserGroup otherGroup = (UserGroup) obj;
        if (this.hashCode() == otherGroup.hashCode()) {
            return true;
        }

        return this.groupName.equals(otherGroup.groupName);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.groupName);
        return hash;
    }

}
