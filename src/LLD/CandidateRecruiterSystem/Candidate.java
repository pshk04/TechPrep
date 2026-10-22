package LLD.CandidateRecruiterSystem;

public class Candidate {

    private String id;
    private Recruiter recruiter;

    public Candidate(String id, Recruiter recruiter) {
        this.id = id;
        this.recruiter = recruiter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}
