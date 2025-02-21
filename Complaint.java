package Assigment1;

public class Complaint {
    public String complaint;
    public String status;
    public Complaint(String complaint) {
        this.complaint = complaint;
        this.status = "pending";
    }
    public String getComplaint() {
        return complaint;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
