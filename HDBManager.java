package Assignment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class HDBManager extends User{
    private List<BTOProject> createdProjects;

    public HDBManager(String nric, int age, boolean maritalStatus) {
        super(nric, age, maritalStatus);
        this.createdProjects = new ArrayList<>();
    }
    public BTOProject createProject(String projectName, String neighbourhood, 
    Map<String, Integer>flatTypes, Date openingDate, Date closingDate, int availableOfficerSlots)
    {
        BTOProject project = new BTOProject(projectName, neighbourhood, flatTypes, openingDate, closingDate, this, availableOfficerSlots);
        createdProjects.add(project);
        return project;
    }

    public void editProject(BTOProject project, String newProjectName, String newNeighborhood, 
                            Map<String, Integer> newFlatTypes, Date newOpeningDate, 
                            Date newClosingDate, int newOfficerSlots) {
        if (!createdProjects.contains(project)) {
            System.out.println("Error: You are not authorized to edit this project.");
            return;
        }
        project.setProjectName(newProjectName);
        project.setNeighborhood(newNeighborhood);
        project.setFlatTypes(newFlatTypes);
        project.setApplicationOpeningDate(newOpeningDate);
        project.setApplicationClosingDate(newClosingDate);
        project.setAvailableOfficerSlots(newOfficerSlots);
    }

    public void deleteProject(BTOProject project)
    {
        if (!createdProjects.contains(project)) {
            System.out.println("Error: You are not authorized to delete this project.");
            return;
        }
        createdProjects.remove(project);
    }

    public void toggleVisibility(BTOProject project)
    {
        if (!createdProjects.contains(project)) {
            System.out.println("Error: You are not authorized to delete this project.");
            return;
        }
        project.toggleVisibility();
    }

    public void approveOfficerRegistration(Registration registration) {
        registration.updateStatus(RegistrationStatus.APPROVED);
        BTOProject project = registration.getProject();
        project.decrementOfficerSlots();
    }

    public void rejectOfficerRegistration(Registration registration) {
        registration.updateStatus(RegistrationStatus.REJECTED);
    }

    public void approveApplication(Application application) {
        BTOProject project = application.getProject();
        String flatType = application.getFlatType();
        if (project.getAvailableUnits(flatType) > 0) {
            application.updateStatus(ApplicationStatus.SUCCESSFUL);
            project.decrementUnits(flatType);
        } else {
            System.out.println("No available units for " + flatType);
        }
    }

    public void rejectApplication(Application application) {
        application.updateStatus(ApplicationStatus.UNSUCCESSFUL);
    }

    public void approveWithdrawal(Application application) {
        application.updateStatus(ApplicationStatus.WITHDRAWN);
    }

    public void replyEnquiry(Enquiry enquiry, String reply) {
        inquiry.setReply(reply);
    }

    public void generateReport(List<Application> applications, 
                           String maritalStatusFilter, 
                           String flatTypeFilter, 
                           String projectNameFilter,
                           Integer minAge,
                           Integer maxAge) 
    {
    System.out.println("Report for applicants based on provided filters:");
    
    for (Application app : applications) {
        boolean matches = true;

        // Check marital status filter (if provided)
        if (maritalStatusFilter != null && !maritalStatusFilter.trim().isEmpty()) {
            if (!app.getApplicant().getMaritalStatus().equalsIgnoreCase(maritalStatusFilter)) {
                matches = false;
            }
        }
        
        // Check flat type filter (if provided)
        if (flatTypeFilter != null && !flatTypeFilter.trim().isEmpty()) {
            if (!app.getFlatType().equalsIgnoreCase(flatTypeFilter)) {
                matches = false;
            }
        }
        
        // Check project name filter (if provided)
        if (projectNameFilter != null && !projectNameFilter.trim().isEmpty()) {
            if (!app.getProject().getProjectName().equalsIgnoreCase(projectNameFilter)) {
                matches = false;
            }
        }
        
        // Check age range filters (if provided)
        int age = app.getApplicant().getAge();
        if (minAge != null && age < minAge) {
            matches = false;
        }
        if (maxAge != null && age > maxAge) {
            matches = false;
        }
        
        // If the application matches all active filters, print its details
        if (matches) {
            System.out.println("Applicant NRIC: " + app.getApplicant().getNric() +
                               ", Flat Type: " + app.getFlatType() +
                               ", Project: " + app.getProject().getProjectName() +
                               ", Age: " + age +
                               ", Marital Status: " + app.getApplicant().getMaritalStatus());
        }
    }
    }
}   
