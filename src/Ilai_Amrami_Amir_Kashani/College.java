package Ilai_Amrami_Amir_Kashani;
//checking if it works

import java.util.Arrays;
import java.util.Objects;

public class College {
    public enum eAddLecturer {Succeed, FailedMatchedId, FailedMatchedName, InvalidSalary, FailedNegativeId, FailWorngTitle}

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

    public eAddLecturer addLecturer(String name, int id, String title, String degreeName, double salary) {
        for (int i = 0; i < lecturerNum; i++) {
            if (name.equals(lecturers[i].getName()))
                return eAddLecturer.FailedMatchedName;
            if (id == lecturers[i].getId())
                return eAddLecturer.FailedMatchedId;
        }
        if (salary < 0)
            return eAddLecturer.InvalidSalary;
        if (id < 0)
            return eAddLecturer.FailedNegativeId;
        Lecturer.eTitle[] eTitles = Lecturer.eTitle.values();
        Lecturer.eTitle eTitle = null;
        for (int i = 0; i < eTitles.length; i++) {
            if (title.equals(eTitles[i].name()))
                eTitle = eTitles[i];
        }
        if (eTitle == null)
            return eAddLecturer.FailWorngTitle;

        Lecturer lecturer = new Lecturer(name, id, eTitle, degreeName, salary);

        Lecturer[] temp = new Lecturer[lecturers.length * 2];
        for (int j = 0; j < lecturerNum; j++)
            temp[j] = lecturers[j];
        temp[lecturerNum] = lecturer;
        lecturerNum++;
        lecturers = temp;
        return eAddLecturer.Succeed;

    }

    public eAddDepartment addDepartment(String name, int numOfStudents) {
        if (findDepartmentIndexByName(name) != -1) {
            return eAddDepartment.FailedMatchedName;
        }
        if (numOfStudents < 0) {
            return eAddDepartment.FailedNegativeNumOfStudents;
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

        return eAddDepartment.Succeed;
    }

    public College.eAddLecturerToDepartment addLecturerToDepartment(String department, String lecturer) {
        int depIndex = findDepartmentIndexByName(department);
        if (depIndex == -1) {
            return eAddLecturerToDepartment.FailNoDepartmentExisted;
        }

        int lecIndex = findLecturerIndexByName(lecturer);
        if (lecIndex == -1) {
            return eAddLecturerToDepartment.NoLecturerExisted;
        }

        return departments[depIndex].addLecturer(lecturers[lecIndex]);
    }


    public eAddCommittee addCommittee(String name, String chairMan) {
        int chairManIndex = findLecturerIndexByName(chairMan);
        if (chairManIndex == -1)
            return eAddCommittee.FailedChairManNotExisted;
        if (findCommitteeIndexByName(name) != -1)
            return eAddCommittee.FailedMatchName;
        Committee committee = new Committee(name);

        eAddChairman res = committee.setChairMan(lecturers[chairManIndex]);
        if (res == eAddChairman.FailChairManIsNotDr)
            return eAddCommittee.FailedChairmanNotDoctor;
        Committee[] temp = new Committee[committees.length * 2];
        for (int j = 0; j < committeeNum; j++)
            temp[j] = committees[j];
        temp[committeeNum] = committee;
        committeeNum++;
        committees = temp;
        return eAddCommittee.Succeed;
    }

    public eAddFriendToCommittee addCommitteeFriend(String committeeName, String friendName) {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            return eAddFriendToCommittee.FailNoFriendExisted;
        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            return eAddFriendToCommittee.FailNoCommitteeExisted;

        return committees[committeeIndex].addFriend(lecturers[friendIndex]);
    }

    public eAddChairman addChairmanToCommittee(String CommitteeName, String chairmanName) {
        int committeeIndex = findCommitteeIndexByName(CommitteeName);
        if (committeeIndex == -1)
            return eAddChairman.FailNoCommitteeExisted;
        int chairmanIndex = findLecturerIndexByName(chairmanName);
        if (chairmanIndex == -1)
            return eAddChairman.FailNoChairmanExisted;

        return committees[committeeIndex].setChairMan(lecturers[chairmanIndex]);
    }

    public eRemoveFriend removeFriend(String friendName, String committeeName) {
        int friendIndex = findLecturerIndexByName(friendName);
        if (friendIndex == -1)
            return eRemoveFriend.FailNoFriendExisted;
        int committeeIndex = findCommitteeIndexByName(committeeName);
        if (committeeIndex == -1)
            return eRemoveFriend.FailNoCommitteeExisted;
        return committees[committeeIndex].removeFriend(lecturers[friendIndex]);
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
        return sumSalary(lecturers, lecturerNum);
    }

    public double departmentAvgSalary(String department) {
        int depNum = findDepartmentIndexByName(department);
        if (depNum == -1)
            return -1;
        return sumSalary(departments[depNum].getLecturers(), departments[depNum].getNumOfLecturers());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        College college = (College) o;
        return lecturerNum == college.lecturerNum && committeeNum == college.committeeNum && departmentNum == college.departmentNum && Objects.equals(name, college.name) && Objects.deepEquals(lecturers, college.lecturers) && Objects.deepEquals(committees, college.committees) && Objects.deepEquals(departments, college.departments);
    }

}

