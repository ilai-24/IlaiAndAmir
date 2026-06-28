package Ilai_Amrami_Amir_Kashani;

import java.util.Scanner;

public class Main {
    //Ilai Amrami:216760843
    // Amir Kashani:330917154
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String committee, chairMan, name, sTitle, degreeName;
        int id, case_num;
        double salary;
        boolean exit = true;

        System.out.println("Enter the name of the college");
        name = input.next();
        College college = new College(name);

        while (exit) {
            System.out.println("enter the function number[0,11] you want to use");
            case_num = input.nextInt();
            while (case_num < 0 || case_num > 11) {
                System.out.println("Wrong function number. Enter the function number[0,11] you want to use");
                case_num = input.nextInt();
            }

            switch (case_num) {
                case 0:
                    exit = false;
                    break;
                case 1: //add a lecturer;
                    System.out.println("Enter the name of the  lecturer");
                    name = input.next();
                    System.out.println("Enter the id of the lecturer ");
                    id = input.nextInt();
                    System.out.println("Enter the title of the lecturer (Bachelor,Master,Doctor) ");
                    sTitle = input.next();
                    System.out.println("Enter the degree name of the lecturer ");
                    degreeName = input.next();
                    System.out.println("Enter the salary of the lecturer");
                    salary = input.nextDouble();


                    College.eAddLecturer resLecturer = college.addLecturer(name, id, sTitle, degreeName, salary);

                    if (resLecturer == College.eAddLecturer.FailedMatchedName)
                        System.out.println("the lecturer name is already exist. Try again");
                    if (resLecturer == College.eAddLecturer.FailedMatchedId)
                        System.out.println("the lecturer id is already exist.Try again");
                    if (resLecturer == College.eAddLecturer.Succeed)
                        System.out.println("the lecturer was added");
                    if (resLecturer == College.eAddLecturer.InvalidSalary)
                        System.out.println("The salary cant be negative.Try again");
                    if (resLecturer == College.eAddLecturer.FailedNegativeId)
                        System.out.println("The ID cant be negative.Try again");
                    if (resLecturer == College.eAddLecturer.FailWorngTitle)
                        System.out.println("The title is wrong. Try again");
                    break;
                case 2: //add a committee
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();
                    System.out.println("Enter the name of the chair man");
                    chairMan = input.next();

                    College.eAddCommittee resCommittee = college.addCommittee(committee, chairMan);
                    if (resCommittee == College.eAddCommittee.FailedChairmanNotDoctor)
                        System.out.println("The chairMan is not a Dr. Try again");
                    if (resCommittee == College.eAddCommittee.FailedChairManNotExisted)
                        System.out.println("The chairMan doesnt exist in the college.Try again");
                    if (resCommittee == College.eAddCommittee.FailedMatchName)
                        System.out.println("There is already a committee with the same name.Try again");
                    if (resCommittee == College.eAddCommittee.Succeed)
                        System.out.println("The committee was added successfully");


                    break;
                case 3: //add a friend to the committee
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();

                    System.out.println("Enter the name of the new committee friend (lecturer)");
                    name = input.next();
                    College.eAddFriendToCommittee resultAddFriendToCommittee = college.addCommitteeFriend(committee, name);

                    if (resultAddFriendToCommittee == College.eAddFriendToCommittee.FailFriendIsInCommittee)
                        System.out.println("The friend is already  in the committee. Try again");
                    if (resultAddFriendToCommittee == College.eAddFriendToCommittee.FailFriendIsChairMan)
                        System.out.println("The friend is the chair man.Try again");
                    if (resultAddFriendToCommittee == College.eAddFriendToCommittee.FailNoFriendExisted)
                        System.out.println("The friend doesnt exist in the college");
                    if (resultAddFriendToCommittee == College.eAddFriendToCommittee.FailNoCommitteeExisted)
                        System.out.println("The committee doesnt exist.Try again");
                    if (resultAddFriendToCommittee == College.eAddFriendToCommittee.Succeed)
                        System.out.println("The friend was successfully added");
                    break;
                case 4: //add new chairman
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();

                    System.out.println("Enter the name of the  chairman");
                    chairMan = input.next();
                    College.eAddChairman resAddChairman = college.addChairmanToCommittee(committee, chairMan);

                    if (resAddChairman == College.eAddChairman.FailChairManIsNotDr)
                        System.out.println("The chairMan is not a doctor. Try again");
                    if ((resAddChairman == College.eAddChairman.FailChairmanIsFriend))
                        System.out.println("The chairman is a friend in the committee. Try again");
                    if (resAddChairman == College.eAddChairman.FailNoCommitteeExisted)
                        System.out.println("The committee doesnt exist.Try again");
                    if (resAddChairman == College.eAddChairman.FailNoChairmanExisted)
                        System.out.println("The chairman doesnt exist.Try again");
                    if (resAddChairman == College.eAddChairman.Succeed)
                        System.out.println("The chairman was added successfully");

                    break;

                case 5: //remove friend from Committee
                    System.out.println("Enter the name of the committee");
                    committee = input.next();
                    System.out.println("Enter the friend you would like to remove");
                    name = input.next();

                    College.eRemoveFriend resultRemoveFriend = college.removeFriend(name, committee);

                    if (resultRemoveFriend == College.eRemoveFriend.FailNoFriendExisted)
                        System.out.println("The friend doesnt exist in the college.Try again");
                    if (resultRemoveFriend == College.eRemoveFriend.FailNoCommitteeExisted)
                        System.out.println("The committee doesnt exist.Try again");
                    if (resultRemoveFriend == College.eRemoveFriend.FailFriendNotInCommittee)
                        System.out.println("The friend is not in the committee.Try again");
                    if (resultRemoveFriend == College.eRemoveFriend.Succeed)
                        System.out.println("The friend was successfully removed.");
                    break;

                case 6: //add a new department
                    System.out.println("Enter the name of the department");
                    String departmentName = input.next();
                    System.out.println("Enter the amount of students");
                    int studentsNum = input.nextInt();
                    College.eAddDepartment resultAddDepartment = college.addDepartment(departmentName, studentsNum);
                    if (resultAddDepartment == College.eAddDepartment.FailedMatchedName)
                        System.out.println("The department name is already exist. Try again");
                    if (resultAddDepartment == College.eAddDepartment.FailedNegativeNumOfStudents)
                        System.out.println("The num of students cant be negative.Try again");
                    if (resultAddDepartment == College.eAddDepartment.Succeed)
                        System.out.println("The department was added successfully");
                    break;
                case 7://add a lecturer to a department
                    System.out.println("Enter the name of the  lecturer");
                    String lecName = input.next();
                    System.out.println("Enter the name of the department");
                    String depName = input.next();
                    College.eAddLecturerToDepartment resultAddLecturerToDepartmet = college.addLecturerToDepartment(depName, lecName);
                    if (resultAddLecturerToDepartmet == College.eAddLecturerToDepartment.FailNoDepartmentExisted)
                        System.out.println("The department doesnt exist.Try again");
                    if (resultAddLecturerToDepartmet == College.eAddLecturerToDepartment.NoLecturerExisted)
                        System.out.println("The lecturer doesnt exist.Try again");
                    if (resultAddLecturerToDepartmet == College.eAddLecturerToDepartment.FailAlreadyExisted)
                        System.out.println("The lecturer is already in a department.Try again");
                    if ((resultAddLecturerToDepartmet == College.eAddLecturerToDepartment.Succeed))
                        System.out.println("The lecturer was added to the department successfully");
                    break;

                case 8: //average of all the lecturers salary in the college
                    System.out.println("The salary average of the whole college is: " + college.AvgSalary());
                    break;

                case 9: //average of all the lecturers in a specific department
                    System.out.println("Enter the name of the department");
                    String dep = input.next();
                    double avg = college.departmentAvgSalary(dep);
                    if (avg == -1) {
                        System.out.println("The department doesnt exist.");
                        break;
                    }
                    System.out.println("The salary average of the department is: " + avg);
                    break;
                case 10: //toString of all lecturers
                    System.out.println(college.toStringLecturers());
                    break;
                case 11: //toString of all committees
                    System.out.println(college.toStringCommittees());
                    break;

            }
        }
    }
}







