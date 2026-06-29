package Ilai_Amrami_Amir_Kashani;
import java.util.Arrays;
import java.util.Objects;

public class College {

    public enum eAddLecturerToDepartment {Succeed, NoLecturerExisted, FailAlreadyExisted, FailNoDepartmentExisted}

    public enum eRemoveLecturerToDepartment {Succeed, FailNoLecturerExisted}

    public enum eAddCommittee {Succeed, FailedMatchName, FailedChairManNotExisted, FailedChairmanNotDoctor}

    public enum eAddFriendToCommittee {Succeed, FailNoCommitteeExisted, FailNoFriendExisted, FailFriendIsChairMan, FailFriendIsInCommittee, FailFriendIsNotDr}

    public enum eAddChairman {Succeed, FailNoCommitteeExisted, FailNoChairmanExisted, FailChairmanIsFriend, FailChairManIsNotDr}

    public enum eRemoveFriend {Succeed, FailNoCommitteeExisted, FailNoFriendExisted, FailFriendNotInCommittee}

    public enum eAddDepartment {Succeed, FailedMatchedName, FailedNegativeNumOfStudents}

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

    public void  addLecturer(String name, int id, String title, String degreeName, double salary)throws ActionException,IllegalArgumentException {
        for (int i = 0; i < lecturerNum; i++) {
            if (name.equals(lecturers[i].getName()))
                throw new ActionException("the lecturer name is already exist. Try again");
            if (id == lecturers[i].getId())
                throw new ActionException("the lecturer id is already exist. Try again");
        }
        Lecturer.eTitle eTitle = Lecturer.eTitle.valueOf(title);
        Lecturer lecturer = new Lecturer(name, id, eTitle, degreeName, salary);

        Lecturer[] temp = new Lecturer[lecturers.length * 2];
        for (int j = 0; j < lecturerNum; j++)
            temp[j] = lecturers[j];
        temp[lecturerNum] = lecturer;
        lecturerNum++;
        lecturers = temp;

    }

    public void addDepartment(String name, int numOfStudents) {
        if (findDepartmentIndexByName(name) != -1) {
            throw new ActionException("department with the same name exsists");
        }
        if (numOfStudents < 0) {
            throw new IllegalArgumentException("number of students cannot be negative.");
        }

        if (departmentNum == departments.length) {
            Department[] temp = new Department[departments.length * 2];
            for (int j = 0; j < departmentNum; j++) {
                temp[j] = departments[j];
            }
            departments = temp;
        }

        departments[departmentNum] = new Department(name, numOfStudents);
        departmentNum++;

    }

    public void addLecturerToDepartment(String department, String lecturer) {
        int depIndex = findDepartmentIndexByName(department);
        if (depIndex == -1) {
            throw new ActionException("department dosent exist");
        }

        int lecIndex = findLecturerIndexByName(lecturer);
        if (lecIndex == -1) {
            throw new ActionException("lecturer dosent exist");
        }

        departments[depIndex].addLecturer(lecturers[lecIndex]);
    }


    public void addCommittee(String name, String chairMan) {
        int chairManIndex = findLecturerIndexByName(chairMan);
        if (chairManIndex == -1)
            throw new ActionException("chair man dosent exist");
        if (findCommitteeIndexByName(name) != -1)
            throw new ActionException("comittee dosent exist");

        Committee committee = new Committee(name);
        committee.setChairMan(lecturers[chairManIndex]);
        Committee[] temp = new Committee[committees.length * 2];
        for (int j = 0; j < committeeNum; j++)
            temp[j] = committees[j];
        temp[committeeNum] = committee;
        committeeNum++;
        committees = temp;
    }

    public void addCommitteeFriend(String committeeName, String friendName) {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
        committees[committeeIndex].addFriend(lecturers[friendIndex]);
    }

    public void addChairmanToCommittee(String CommitteeName, String chairmanName) {
        int committeeIndex = findCommitteeIndexByName(CommitteeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
        int chairmanIndex = findLecturerIndexByName(chairmanName);
        if (chairmanIndex == -1)
            throw new ActionException("the chair man doesn't exists");


         committees[committeeIndex].setChairMan(lecturers[chairmanIndex]);
    }

    public void removeFriend(String friendName, String committeeName) {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            throw new ActionException("the friend doesn't exists");

        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            throw new ActionException("the committee doesn't exists");
         committees[committeeIndex].removeFriend(lecturers[friendIndex]);
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

    public String toStringCommittees() {
        StringBuffer str = new StringBuffer(name + " committees:");
        for (int i = 0; i < committeeNum; i++)
            str.append("\n" + committees[i].toString());
        return str.toString();

    }

    public double sumSalary(Lecturer[] lecturers, int numOfLecturers) {
        double sumSalary = 0;
        for (int i = 0; i < numOfLecturers; i++)
            sumSalary += lecturers[i].getSalary();
        return sumSalary / numOfLecturers;
    }

    public double AvgSalary() {
        if (lecturerNum ==0)
            throw new ActionException("there are no lecturers in the requested field");
        return sumSalary(lecturers, lecturerNum);
    }

    public double departmentAvgSalary(String department) {
        int depNum = findDepartmentIndexByName(department);
        if (depNum == -1)
            throw new ActionException("the department doesn't exists");
        return sumSalary(departments[depNum].getLecturers(), departments[depNum].getNumOfLecturers());
    }
    public boolean compareArticles(String a, String b) throws ActionException {
        int aIndex = findLecturerIndexByName(a);
        int bIndex = findLecturerIndexByName(b);
        if (aIndex==-1)
            throw new ActionException("the first lecturer doesn't exist");
        else if (bIndex==-1)
            throw new ActionException("the second lecturer doesn't exist");
        return  lecturers[aIndex].getNumOfArticles()==lecturers[bIndex].getNumOfArticles();

    }
    public void addArticlesByName(String name,String art) throws ActionException
    {
        int index= findLecturerIndexByName(name);
        if (index==-1)
            throw new ActionException("the lecturer dosent exist");
        lecturers[index].addArticles(art);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        College college = (College) o;
        return lecturerNum == college.lecturerNum && committeeNum == college.committeeNum && departmentNum == college.departmentNum && Objects.equals(name, college.name) && Objects.deepEquals(lecturers, college.lecturers) && Objects.deepEquals(committees, college.committees) && Objects.deepEquals(departments, college.departments);
    }

}

