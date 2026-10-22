package LLD.CandidateRecruiterSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateRecruiterSystem {

    private Map<String, Recruiter> recruitersMap;
    private Map<String, Candidate> candidateMap;

    public CandidateRecruiterSystem() {
        this.recruitersMap = new HashMap<>();
        this.candidateMap = new HashMap<>();
    }

    public boolean addRecruiter(String recruiterId) {

        if(!this.recruitersMap.containsKey(recruiterId)){
            List<Candidate> candidatesList = new ArrayList<>();
            Recruiter recruiter = new Recruiter(recruiterId, candidatesList);
            this.recruitersMap.put(recruiterId, recruiter);
            return true;
        }
        return false;
    }

    public boolean addCandidate(String candidateId) {

        if(!this.candidateMap.containsKey(candidateId)){
            Candidate candidate = new Candidate(candidateId, null);
            this.candidateMap.put(candidateId, candidate);
            return true;
        }
        return false;
    }

    public boolean assign(String candidateId, String recruiterId) {

        if(this.recruitersMap.containsKey(recruiterId) && this.candidateMap.containsKey(candidateId)){
            Candidate candidate = this.candidateMap.get(candidateId);
            Recruiter recruiter = this.recruitersMap.get(recruiterId);
            List<Candidate> candidatesList = null;

            if(candidate.getRecruiter() != null) {
                Recruiter previousRecruiter = candidate.getRecruiter();
                candidatesList = previousRecruiter.getCandidateList();
                Candidate candidateToRemove = this.candidateMap.get(candidateId);

                for(Candidate candidate1: candidatesList){
                    if(candidate1.getId().equals(candidateId)){
                        candidateToRemove = candidate1;
                        break;
                    }
                }
                candidatesList.remove(candidateToRemove);
                previousRecruiter.setCandidateList(candidatesList);
            }
            candidate.setRecruiter(recruiter);
            candidatesList = recruiter.getCandidateList();
            candidatesList.add(candidate);
            return true;
        }
        return false;
    }

    public boolean unassign(String candidateId) {

        if(this.candidateMap.containsKey(candidateId)){
            Candidate candidate = this.candidateMap.get(candidateId);
            Recruiter recruiter = candidate.getRecruiter();
            List<Candidate> candidatesList = recruiter.getCandidateList();
            Candidate candidateToRemove = null;

            for(Candidate candidate1: candidatesList){
                if(candidate1.getId().equals(candidate.getId())){
                    candidateToRemove = candidate1;
                    break;
                }
            }
            candidatesList.remove(candidateToRemove);
            recruiter.setCandidateList(candidatesList);
            candidate.setRecruiter(null);
            return true;
        }
        return false;
    }

    public boolean removeRecruiter(String recruiterId) {

        if(this.recruitersMap.containsKey(recruiterId)){
            Recruiter recruiter = this.recruitersMap.get(recruiterId);
            for(Candidate candidate : recruiter.getCandidateList()){
                candidate.setRecruiter(null);
                if(this.candidateMap.containsKey(candidate.getId())){
                    this.candidateMap.get(candidate.getId()).setRecruiter(null);
                }
            }
            this.recruitersMap.remove(recruiterId);
            return true;
        }
        return false;
    }

    public boolean removeCandidate(String candidateId) {

        if(this.candidateMap.containsKey(candidateId)){
            this.candidateMap.remove(candidateId);

            for(Map.Entry<String, Recruiter> entry : this.recruitersMap.entrySet()){
                Recruiter recruiter = entry.getValue();
                List<Candidate> candidatesList = recruiter.getCandidateList();
                Candidate candidateToRemove = null;

                for(Candidate candidate1: candidatesList){
                    if(candidate1.getId().equals(candidateId)){
                        candidateToRemove = candidate1;
                        break;
                    }
                }
                candidatesList.remove(candidateToRemove);
                recruiter.setCandidateList(candidatesList);
            }
            return true;
        }
        return false;
    }

    public String getRecruiter(String candidateId) {

        if(this.candidateMap.containsKey(candidateId) && this.candidateMap.get(candidateId).getRecruiter() != null){
            return this.candidateMap.get(candidateId).getRecruiter().getId();
        }
        return "";
    }

    public List<String> getCandidates(String recruiterId) {

        List<String> candidatesList = new ArrayList<>();

        if(this.recruitersMap.containsKey(recruiterId)){
            for(Candidate candidate : this.recruitersMap.get(recruiterId).getCandidateList()){
                candidatesList.add(candidate.getId());
            }
        }
        return candidatesList;
    }

    public static void main(String[] args) {

        CandidateRecruiterSystem sys = new CandidateRecruiterSystem();

        System.out.println(sys.addRecruiter("R1"));
        System.out.println(sys.addCandidate("C1"));
        System.out.println(sys.addCandidate("C2"));
        System.out.println(sys.assign("C1", "R1"));
        System.out.println(sys.assign("C2", "R1"));
        System.out.println(sys.getRecruiter("C1"));
        System.out.println(sys.getCandidates("R1"));

        System.out.println();

        sys = new CandidateRecruiterSystem();

        System.out.println(sys.addRecruiter("R1"));
        System.out.println(sys.addRecruiter("R2"));
        System.out.println(sys.addCandidate("C1"));
        System.out.println(sys.assign("C1", "R1"));
        System.out.println(sys.assign("C1", "R2"));
        System.out.println(sys.getRecruiter("C1"));
        System.out.println(sys.getCandidates("R1"));
        System.out.println(sys.getCandidates("R2"));
        System.out.println(sys.removeRecruiter("R2"));
        System.out.println(sys.getRecruiter("C1"));

        System.out.println();
    }
}
