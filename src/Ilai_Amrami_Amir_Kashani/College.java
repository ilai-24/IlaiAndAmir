package Ilai_Amrami_Amir_Kashani;
import java.util.*;

public class College {

    public enum ETitle{Bachelor,Doctor,Master,Professor};
    private String name;
    private ArrayList<Lecturer>lecturers;
    private  ArrayList<Department>departments;
    private ArrayList<Committee<?>> committees;

    public College(String name) {
        this.name = name;
        lecturers = new ArrayList<Lecturer>();
        departments = new ArrayList<Department>();
        committees = new ArrayList<>();

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public int getLecturerNum() {
        return lecturers.size();
    }

    public  ArrayList<Committee<?>> getCommittees() {
        return committees;
    }

    public int getCommitteeNum() {
        return committees.size();
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public int getDepartmentNum() {
        return departments.size();
    }


    public void  addLecturer(String name, int id, String degreeName, double salary, String grantedProfessor,String sTitle)throws ActionException {
        for (int i = 0; i < lecturers.size(); i++) {
            if (id == lecturers.get(i).getId())
                throw new ActionException("the lecturer id is already exist. Try again");
        }
            ETitle eTitle;
        try {
                eTitle =ETitle.valueOf(sTitle);
        }
        catch (IllegalArgumentException e) {
            throw  new ActionException("the lecturer Title is wrong. Try again");
        }
        Lecturer lecturer = null;

        if (eTitle == ETitle.Bachelor){
            lecturer=new Bachelor(name,id,degreeName,salary);
        }
        if (eTitle == ETitle.Master){
            lecturer =new Master(name,id,degreeName,salary);
        }

        if (eTitle == ETitle.Doctor){
             lecturer=new Doctor(name,id,degreeName,salary);
        }

        if (eTitle == ETitle.Professor){
             lecturer=new Professor(name,id,degreeName,salary,grantedProfessor);
        }


        lecturers.add(lecturer);

    }

    public void addDepartment(String name, int numOfStudents) throws ActionException {
        if (findDepartmentIndexByName(name) != -1) {
            throw new ActionException("department with the same name is already exists");
        }
        Department department = new Department(name, numOfStudents);

        departments.add(department);

    }

    public void addLecturerToDepartment(String department, String lecturer) throws ActionException{
        int depIndex = findDepartmentIndexByName(department);
        if (depIndex == -1) {
            throw new ActionException("department doesnt exist");
        }

        int lecIndex = findLecturerIndexByName(lecturer);
        if (lecIndex == -1) {
            throw new ActionException("lecturer doesnt exist");
        }

        departments.get(depIndex).addLecturer(lecturers.get(lecIndex));
    }


    public void addCommittee(String name, String chairMan,String friendType) throws ActionException{
        int chairManIndex = findLecturerIndexByName(chairMan);
        if (chairManIndex == -1)
            throw new ActionException("chair man doesnt exist");
        if (findCommitteeIndexByName(name) != -1)
            throw new ActionException("committee name is already exist");

        Committee committee=null;

        if (friendType.equals("Professor"))
            committee = new Committee<Professor>(name, lecturers.get(chairManIndex), Professor.class);
        else if (friendType.equals("Doctor"))
            committee = new Committee<Doctor>(name, lecturers.get(chairManIndex), Doctor.class);
        else if (friendType.equals("RegularDegree"))
            committee = new Committee<RegularDegree>(name,  lecturers.get(chairManIndex), RegularDegree.class);
        else
            throw new ActionException("unknown friend type");

        committees.add(committee);
    }

    public void addCommitteeFriend(String committeeName, String friendName)throws ActionException {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");

        committees.get(committeeIndex).addFriend(lecturers.get(friendIndex));
    }

    public void addChairmanToCommittee(String CommitteeName, String chairmanName)throws ActionException {
        int committeeIndex = findCommitteeIndexByName(CommitteeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
        int chairmanIndex = findLecturerIndexByName(chairmanName);
        if (chairmanIndex == -1)
            throw new ActionException("the chair man doesn't exists");

         committees.get(committeeIndex).setChairMan(lecturers.get(chairmanIndex));
    }

    public void removeFriend(String friendName, String committeeName)throws ActionException {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");

        committees.get(committeeIndex).removeFriend(lecturers.get(friendIndex));
    }

    public void addArticle(String lecturer,String article) throws ActionException {
        int lectureIndex = findLecturerIndexByName(lecturer);
        if (lectureIndex == -1)
            throw new ActionException("the lecturer doesn't exists");

        if (!(lecturers.get(lectureIndex) instanceof HighDegrees))
            throw new ActionException("the lecturer is not Doctor/Proffesor");

        ((HighDegrees) lecturers.get(lectureIndex)).addArticles(article);

    }

    public int findLecturerIndexByName(String name) {
        int index = -1;
        for (int i = 0; i < lecturers.size(); i++) {
            if (lecturers.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }
    public int findDepartmentIndexByName(String name) {
        int index = -1;
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }
    public int findCommitteeIndexByName(String name) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public String toStringLecturers() {
        StringBuffer str = new StringBuffer(name + " lecturers:");
        Iterator<Lecturer> iterator = lecturers.iterator();
        for (int i = 0; i < lecturers.size(); i++)
            str.append("\n" + lecturers.get(i).toString());
        return str.toString();
    }

    public String toStringCommittees()
    {
        return committees.toString();

    }

    public double sumSalary(ArrayList<Lecturer>lecturers) throws ActionException {
        double sumSalary = 0;
        for (int i = 0; i < lecturers.size(); i++)
            sumSalary += lecturers.get(i).getSalary();
        return sumSalary / lecturers.size();
    }

    public double AvgSalary() throws ActionException{
        if (lecturers.isEmpty())
            throw new ActionException("there are no lecturers the college");
        return sumSalary(lecturers);
    }

    public double departmentAvgSalary(String department) throws ActionException{
        int depNum = findDepartmentIndexByName(department);
        if (depNum == -1)
            throw new ActionException("the department doesn't exists");
        if (departments.get(depNum).getNumOfLecturers() ==0)
            throw new ActionException("there are no lecturers in the department");
        return sumSalary(departments.get(depNum).getLecturers());
    }


    public int compareDoctorsAndProfessors(String doctor1Name,String doctor2Name) throws ActionException {
        int doc1Index = findLecturerIndexByName(doctor1Name);
        if (doc1Index == -1)
            throw new ActionException(doctor1Name+ " doesn't exists");
        int doc2Index = findLecturerIndexByName(doctor2Name);
        if(doc2Index == -1)
            throw new ActionException(doctor2Name+ " doesn't exists");
        if (!(lecturers.get(doc1Index) instanceof HighDegrees))
            throw new ActionException( doctor1Name+" is not Doctor/Proffesor");
        if (!(lecturers.get(doc2Index) instanceof HighDegrees))
            throw new ActionException(doctor2Name+" is not Doctor/Proffesor");
        if (lecturers.get(doc1Index).equals(lecturers.get(doc2Index)))
            throw new ActionException("the doctors are the same person");

        return  ((HighDegrees) lecturers.get(doc1Index)).compareTo((HighDegrees) lecturers.get(doc2Index));
    }

    public int compareCommittees(String committeeName1, String committeeName2, Comparator c) throws ActionException
    {
        int c1Index=findCommitteeIndexByName(committeeName1);
        if(c1Index==-1)
            throw new ActionException("the first committee doesn't exist");

        int c2Index=findCommitteeIndexByName(committeeName2);
        if(c2Index==-1)
            throw new ActionException("the second committee doesn't exist");

        if (committees.get(c1Index).equals(committees.get(c2Index)))
            throw new ActionException("the committees are the same committees");

        return c.compare(committees.get(c1Index),committees.get(c2Index));



    }
    public void copyCommittee(String com) throws ActionException
    {
        int index=findCommitteeIndexByName(com);
        if(index==-1)
            throw new ActionException("the committee doesn't exist");
        Committee committee;
        try {
             committee=committees.get(index).clone();
        }
        catch (CloneNotSupportedException e) {
            throw new ActionException("the committee is not Cloneable");
        }
        if (findCommitteeIndexByName(committee.getName()) !=-1)
            throw new ActionException("the new committee is already exist");



        committees.add(committee);


    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof College))
            return false;
        College college = (College) o;
        return college.getName().equals(name);
    }

}

