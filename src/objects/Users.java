package objects;

import java.util.Date;
import java.util.StringJoiner;

public class Users {

    private int userID;
    private String userFirstName;
    private String userLastName;
    private String email;
    private String phone;
    private Date birthDate;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String userHistory;

    public Users(int userID, String userFirstName, String userLastName, String email, String phone, Date birthDate, String address, String city, String country, String postalCode, String userHistory) {
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.setCity(city);
        this.setCountry(country);
        this.postalCode = postalCode;
        this.setUserHistory(userHistory);
    }

    public Users() {
    }

    public Users(int userID, String userFirstName, String userLastName) {
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() > 10) {
            System.out.println("Phone number is too long, please try once again!");
            return;
        }
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 70) {
            System.out.println("Address is too long, please try once again!");
            return;
        }
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.length() > 20) {
            System.out.println("Name of the city is too long, please try once again!");
            return;
        } else if (city.isEmpty()) {
            this.city = "Riga";
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.length() > 20) {
            System.out.println("Name of the country is too long, please try once again!");
            return;
        } else if (country.isEmpty()) {
            this.country = "Latvia";
        }
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (country.length() > 10) {
            System.out.println("Postal code is too long, please try once again!");
            return;
        }
        this.postalCode = postalCode;
    }

    public String getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(String userHistory) {
        if (country.length() > 200) {
            System.out.println("User history is too long, please try once again! Limit is 200 characters.");
            return;
        } else if (userHistory.isEmpty()) {
            this.userHistory = "Latvia";
        }
        this.userHistory = userHistory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Users.class.getSimpleName() + "[", "]")
                .add("userID=" + userID)
                .add("userFirstName='" + userFirstName + "'")
                .add("userLastName='" + userLastName + "'")
                .add("email='" + email + "'")
                .add("phone='" + phone + "'")
                .add("birthDate=" + birthDate)
                .add("address='" + address + "'")
                .add("city='" + city + "'")
                .add("country='" + country + "'")
                .add("postalCode='" + postalCode + "'")
                .add("userHistory='" + userHistory + "'")
                .toString();
    }
}