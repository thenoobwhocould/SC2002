// HDBOfficer.java
public class HDBOfficer extends Applicant {
    
    public HDBOfficer(String NRIC, int age, String maritalStatus, String password) {
        super(NRIC, age, maritalStatus, password);
    }
    
    public void registerToProject(BTOProject project) {
        System.out.println("HDB Officer " + getNRIC() + " registered to project: " + project.getProjectName());
    }
    
    public void updateFlatAvailability() {
        System.out.println("Flat availability updated by HDB Officer " + getNRIC());
    }
    
    public void replyEnquiry() {
        System.out.println("HDB Officer " + getNRIC() + " replied to an enquiry.");
    }
    
    public Receipt generateReceipt(Application application) {
        System.out.println("Generating receipt for application: " + application.getApplicationDetails());
        // Return a stub Receipt.
        return new Receipt("Receipt for " + application.getApplicationDetails());
    }
}
