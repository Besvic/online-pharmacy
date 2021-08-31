package com.example.online_pharmacy.entity;

public class User {

    private long id;
    private Position position;
    private UserStatus userStatus;
    private String name;
    private double cash;
    private String login;
    private String password;

    public User() {
    }

    public User(long id, String name, double cash, String login, String password, UserStatus userStatus, Position position) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.login = login;
        this.password = password;
        this.userStatus = userStatus;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && cash == user.cash && userStatus == user.userStatus && position.equals(user.position)
                && name.equals(user.name) && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + Double.hashCode(cash) + position.hashCode() + userStatus.hashCode()
                + login.hashCode() + password.hashCode() + name.hashCode();
    }

    @Override // FIXME: 23.08.2021  to string builder
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        return buffer.append("User{ id= ")
                .append(id)
                .append(", name= ")
                .append(name)
                .append(", cash= ")
                .append(cash)
                .append(", login= ")
                .append(login)
                .append(", password = ")
                .append(password)
                .append(", status= ")
                .append(userStatus)
                .append(", position= ")
                .append(position)
                .append(" }")
                .toString();

    }

    public static class UserBuilder{

        private long id;
        private String name;
        private double cash;
        private String login;
        private String password;
        private UserStatus userStatus;
        private Position position;

        public UserBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setCash(double cash) {
            this.cash = cash;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setUserStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public UserBuilder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public User createUser() {
            return new User(id, name, cash, login, password, userStatus, position);
        }
    }

}
