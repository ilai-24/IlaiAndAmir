package Ilai_Amrami_Amir_Kashani;
import java.util.Comparator;
import java.util.Objects;

public class College {

    public enum ETitle{Bachelor,Doctor,Master,Professor};

    private String name;
    private Lecturer[] lecturers;
    private int lecturerNum;
    private Committee[] committees;
    private int committeeNum;
    private Department[] departments;
    private int departmentNum;

    public College(String name) {
        this.name = name;
        lecturers = new Lecturer[1];
        lecturerNum = 0;
        committees = new Committee[1];
        committeeNum = 0;
        departments = new Department[1];
        departmentNum = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public int getLecturerNum() {
        return lecturerNum;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public int getCommitteeNum() {
        return committeeNum;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public int getDepartmentNum() {
        return departmentNum;
    }

    public void  addLecturer(String name, int id, String degreeName, double salary, String grantedProfessor,String sTitle)throws ActionException {
        for (int i = 0; i < lecturerNum; i++) {
            if (name.equals(lecturers[i].getName()))
                throw new ActionException("the lecturer name is already exist. Try again");
            if (id == lecturers[i].getId())
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


        Lecturer[] temp = new Lecturer[lecturers.length * 2];
        for (int j = 0; j < lecturerNum; j++)
            temp[j] = lecturers[j];
        temp[lecturerNum] = lecturer;
        lecturerNum++;
        lecturers = temp;

    }

    public void addDepartment(String name, int numOfStudents) throws ActionException {
        if (findDepartmentIndexByName(name) != -1) {
            throw new ActionException("department with the same name is already exists");
        }
        Department department = new Department(name, numOfStudents);

        Department[] temp = new Department[departments.length * 2];
        for (int j = 0; j < departmentNum; j++) {
            temp[j] = departments[j];
        }
        departments = temp;
        departments[departmentNum] = department;
        departmentNum++;

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

        departments[depIndex].addLecturer(lecturers[lecIndex]);
    }


    public void addCommittee(String name, String chairMan) throws ActionException{
        int chairManIndex = findLecturerIndexByName(chairMan);
        if (chairManIndex == -1)
            throw new ActionException("chair man doesnt exist");
        if (findCommitteeIndexByName(name) != -1)
            throw new ActionException("committee name is already exist");

        Committee committee = new Committee(name,lecturers[chairManIndex]);

        Committee[] temp = new Committee[committees.length * 2];
        for (int j = 0; j < committeeNum; j++)
            temp[j] = committees[j];
        temp[committeeNum] = committee;
        committeeNum++;
        committees = temp;
    }

    public void addCommitteeFriend(String committeeName, String friendName)throws ActionException {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
        committees[committeeIndex].addFriend(lecturers[friendIndex]);
    }

    public void addChairmanToCommittee(String CommitteeName, String chairmanName)throws ActionException {
        int committeeIndex = findCommitteeIndexByName(CommitteeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
        int chairmanIndex = findLecturerIndexByName(chairmanName);
        if (chairmanIndex == -1)
            throw new ActionException("the chair man doesn't exists");

         committees[committeeIndex].setChairMan(lecturers[chairmanIndex]);
    }

    public void removeFriend(String friendName, String committeeName)throws ActionException {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");

        committees[committeeIndex].removeFriend(lecturers[friendIndex]);
    }

    public void addArticle(String lecturer,String article) throws ActionException {
        int lectureIndex = findLecturerIndexByName(lecturer);
        if (lectureIndex == -1)
            throw new ActionException("the lecturer doesn't exists");

        if (!(lecturers[lectureIndex] instanceof Doctor))
            throw new ActionException("the lecturer is not Doctor/Proffesor");

        ((Doctor) lecturers[lectureIndex]).addArticles(article);

    }

    public int findLecturerIndexByName(String name) {
        for (int i = 0; i < lecturerNum; i++) {
            if (lecturers[i].getName().equals(name))
                return i;
        }
        return -1;
    }

    public int findCommitteeIndexByName(String name) {
        for (int i = 0; i < committeeNum; i++) {
            if (committees[i].getName().equals(name))
                return i;
        }
        return -1;
    }

    public int findDepartmentIndexByName(String name) {
        for (int i = 0; i < departmentNum; i++) {
            if (departments[i].getName().equals(name))
                return i;
        }
        return -1;
    }

    public String toStringLecturers() {
        StringBuffer str = new StringBuffer(name + " lecturers:");
        for (int i = 0; i < lecturerNum; i++)
            str.append("\n" + lecturers[i].toString());
        return str.toString();
    }

    public String toStringCommittees()
    {
        StringBuffer str = new StringBuffer(name + " committees:");
        for (int i = 0; i < committeeNum; i++)
            str.append("\n" + committees[i].toString());
        return str.toString();

    }

    public double sumSalary(Lecturer[] lecturers, int numOfLecturers) throws ActionException {
        double sumSalary = 0;
        for (int i = 0; i < numOfLecturers; i++)
            sumSalary += lecturers[i].getSalary();
        return sumSalary / numOfLecturers;
    }

    public double AvgSalary() throws ActionException{
        if (lecturerNum ==0)
            throw new ActionException("there are no lecturers the college");
        return sumSalary(lecturers, lecturerNum);
    }

    public double departmentAvgSalary(String department) throws ActionException{
        int depNum = findDepartmentIndexByName(department);
        if (depNum == -1)
            throw new ActionException("the department doesn't exists");
        if (departments[depNum].getNumOfLecturers() ==0)
            throw new ActionException("there are no lecturers in the department");
        return sumSalary(departments[depNum].getLecturers(), departments[depNum].getNumOfLecturers());
    }
    public int compareDoctorsAndProfessors(String doctor1Name,String doctor2Name) throws ActionException {
        int doc1Index = findLecturerIndexByName(doctor1Name);
        if (doc1Index == -1)
            throw new ActionException(doctor1Name+ " doesn't exists");
        int doc2Index = findLecturerIndexByName(doctor2Name);
        if(doc2Index == -1)
            throw new ActionException(doctor2Name+ " doesn't exists");
        if (!(lecturers[doc1Index] instanceof Doctor))
            throw new ActionException( doctor1Name+" is not Doctor/Proffesor");
        if (!(lecturers[doc2Index] instanceof Doctor))
            throw new ActionException(doctor2Name+" is not Doctor/Proffesor");
        if (lecturers[doc1Index].equals(lecturers[doc2Index]))
            throw new ActionException("the doctors are the same person");

        return  ((Doctor) lecturers[doc1Index]).compareTo((Doctor) lecturers[doc2Index]);


    }

    public int compareCommittees(String committeeName1, String committeeName2, Comparator c) throws ActionException
    {
        int c1Index=findCommitteeIndexByName(committeeName1);
        if(c1Index==-1)
            throw new ActionException("the first committee doesn't exist");

        int c2Index=findCommitteeIndexByName(committeeName2);
        if(c2Index==-1)
            throw new ActionException("the second committee doesn't exist");

        if (committees[c1Index].equals(committees[c2Index]))
            throw new ActionException("the committees are the same committees");

        return c.compare(committees[c1Index],committees[c2Index]);



    }
    public void copyCommittee(String com) throws ActionException
    {
        int index=findCommitteeIndexByName(com);
        if(index==-1)
            throw new ActionException("the committee doesn't exist");
        addCommittee("new "+committees[index].getName(),committees[index].getChairMan().getName());
        if (committees[index].getNumOfFriends()!=0) {
            committees[committeeNum].setNumOfFriends(committees[index].getNumOfFriends());
            committees[committeeNum].setCommitteeFriends(committees[index].getCommitteeFriends());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof College))
            return false;
        College college = (College) o;
        return college.getName().equals(name);
    }

}

