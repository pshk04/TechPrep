package LLD.CandidateRecruiterSystem;

import java.util.List;

public class Recruiter {

    private String id;
    private List<Candidate> candidateList;

    public Recruiter(String id, List<Candidate> candidateList) {
        this.id = id;
        this.candidateList = candidateList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }
}
