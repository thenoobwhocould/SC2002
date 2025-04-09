// Applicant.java
public class Applicant extends User {
    // Aggregation: An applicant applies to one Project.
    private Project project;
    
    public Applicant(String NRIC, int age, String maritalStatus, String password) {
        super(NRIC, age, maritalStatus, password);
    }
    
    // Getter and Setter for Project
    public Project getProject() {
        return project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    // Methods
    public void viewProjects() {
        System.out.println("Viewing available projects...");
    }
    
    public Application applyProject(Project project) {
        setProject(project);
        System.out.println("Applied to project: " + project.getProjectName());
        // Creating a new Application stub with simple details.
        return new Application("Application for " + project.getProjectName());
    }
    
    public void requestWithdrawal() {
        System.out.println("Withdrawal requested for project: " + (project != null ? project.getProjectName() : "None"));
    }
    
    public void submitEnquiry(String text) {
        System.out.println("Enquiry submitted: " + text);
    }
    
    public void editEnquiry(int enquiryId, String newText) {
        System.out.println("Enquiry " + enquiryId + " edited to: " + newText);
    }
    
    public void deleteEnquiry(int enquiryId) {
        System.out.println("Enquiry " + enquiryId + " deleted.");
    }
    
    public ApplicationStatus viewApplicationStatus() {
        System.out.println("Viewing application status for project: " + (project != null ? project.getProjectName() : "None"));
        // Stub: return PENDING for demonstration.
        return ApplicationStatus.PENDING;
    }
    
    public void viewEnquiryResponse(int enquiryId) {
        System.out.println("Viewing response for enquiry " + enquiryId);
    }
}
