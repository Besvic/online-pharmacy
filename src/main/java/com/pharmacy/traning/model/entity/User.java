package com.pharmacy.traning.model.entity;

/**
 * The type User.
 */
public class User {

    private long id;
    private Position position;
    private UserStatus userStatus;
    private String name;
    private double cash;
    private String login;
    private String password;
    private String photo;

    /**
     * Instantiates a new User.
     *
     * @param id         the id
     * @param name       the name
     * @param cash       the cash
     * @param login      the login
     * @param password   the password
     * @param userStatus the user status
     * @param position   the position
     * @param photo      the photo
     */
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

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(String position) {
        this.position = Position.valueOf(position.toUpperCase());
    }

    /**
     * Gets user status.
     *
     * @return the user status
     */
    public UserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Sets user status.
     *
     * @param userStatus the user status
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = UserStatus.valueOf(userStatus.toUpperCase());
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets cash.
     *
     * @return the cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * Sets cash.
     *
     * @param cash the cash
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /***
     *
     * Equals
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && cash == user.cash && userStatus == user.userStatus &&
                position == null ? user.position == null : position.equals(user.position)
                && name == null ? user.name == null : name.equals(user.name) &&
                login == null ? user.login == null : login.equals(user.login) &&
                password == null ? user.password == null : password.equals(user.password) &&
                photo == null ? user.photo == null : photo.equals(user.photo);
    }

    /***
     *
     * Hash Code
     * @return
     */
    @Override
    public int hashCode() {
        return Long.hashCode(id) + Double.hashCode(cash) + position.hashCode() + userStatus.hashCode()
                + login.hashCode() + password.hashCode() + name.hashCode() + photo.hashCode();
    }

    /***
     *
     * To String
     * @return
     */
    @Override
    public String toString() {
        return new String(new StringBuffer()
                .append("User{ id= ").append(id)
                .append(", name= ").append(name)
                .append(", cash= ").append(cash)
                .append(", login= ").append(login)
                .append(", password = ").append(password)
                .append(", status= ").append(userStatus)
                .append(", position= ").append(position)
                .append(" }"));

    }

    /**
     * The type User builder.
     */
    public static class UserBuilder{

        private long id;
        private String name;
        private double cash;
        private String login;
        private String password;
        private UserStatus userStatus;
        private Position position;
        private String photo;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public UserBuilder setId(long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets cash.
         *
         * @param cash the cash
         * @return the cash
         */
        public UserBuilder setCash(double cash) {
            this.cash = cash;
            return this;
        }

        /**
         * Sets login.
         *
         * @param login the login
         * @return the login
         */
        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        /**
         * Sets password.
         *
         * @param password the password
         * @return the password
         */
        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets user status.
         *
         * @param userStatus the user status
         * @return the user status
         */
        public UserBuilder setUserStatus(String userStatus) {
            this.userStatus = UserStatus.valueOf(userStatus.toUpperCase());
            return this;
        }

        /**
         * Sets position.
         *
         * @param position the position
         * @return the position
         */
        public UserBuilder setPosition(String position) {
            this.position = Position.valueOf(position.toUpperCase());
            return this;
        }

        /**
         * Sets photo.
         *
         * @param photo the photo
         * @return the photo
         */
        public UserBuilder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        /**
         * Create user user.
         *
         * @return the user
         */
        public User createUser() {
            return new User(id, name, cash, login, password, userStatus, position, photo);
        }
    }

}
