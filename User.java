package Assignment;
public abstract class User{
    private String nric;
    private String password;
    private int age;
    private boolean maritalStatus;
    private static final String DEFAULT_PASSWORD = "password";
    public User(String nric, int age, boolean maritalStatus) {
        if (!isValidNRIC(nric)) {
            throw new IllegalArgumentException("Invalid NRIC format. NRIC must start with S or T, followed by 7 digits, and end with a letter.");
        }
        this.nric = nric;
        this.password = DEFAULT_PASSWORD;
        this.age = age;
        this.maritalStatus = maritalStatus;
    }

    private boolean isValidNRIC(String nric) {
        return nric.matches("^[ST]\\d{7}[A-Za-z]$");
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getNric() {
        return nric;
    }

    public int getAge() {
        return age;
    }

    public boolean getMaritalStatus() {
        return maritalStatus;
    }
}