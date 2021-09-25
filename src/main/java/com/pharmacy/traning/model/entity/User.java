package com.pharmacy.traning.model.entity;

public class User {
    public static long currentId;
    private long id;
    private Position position;
    private UserStatus userStatus;
    private String name;
    private double cash;
    private String login;
    private String password;
    private String photo;

    public User() {
    }

    public User(long id, String name, double cash, String login, String password, UserStatus userStatus
            , Position position, String photo) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.login = login;
        this.password = password;
        this.userStatus = userStatus;
        this.position = position;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = Position.valueOf(position.toUpperCase());
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = UserStatus.valueOf(userStatus.toUpperCase());
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && cash == user.cash && userStatus == user.userStatus && position.equals(user.position)
                && name.equals(user.name) && login.equals(user.login) && password.equals(user.password) && photo.equals(user.photo);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) + Double.hashCode(cash) + position.hashCode() + userStatus.hashCode()
                + login.hashCode() + password.hashCode() + name.hashCode() + photo.hashCode();
    }

    @Override
    public String toString() {
        return new String(new StringBuffer().append("User{ id= ")
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
                .append(" }"));

    }

    public static class UserBuilder{

        private long id;
        private String name;
        private double cash;
        private String login;
        private String password;
        private UserStatus userStatus;
        private Position position;
        private String photo;

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

        public UserBuilder setUserStatus(String userStatus) {
            this.userStatus = UserStatus.valueOf(userStatus.toUpperCase());
            return this;
        }

        public UserBuilder setPosition(String position) {
            this.position = Position.valueOf(position.toUpperCase());
            return this;
        }

        public UserBuilder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public User createUser() {
            return new User(id, name, cash, login, password, userStatus, position, photo);
        }
    }

}
