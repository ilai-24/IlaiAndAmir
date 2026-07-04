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
            System.out.println("enter the function number[0,15] you want to use");
            case_num = input.nextInt();
            while (case_num < 0 || case_num > 15) {
                System.out.println("Wrong function number. Enter the function number[0,11] you want to use");
                case_num = input.nextInt();
            }

            switch (case_num) {
                case 0:
                    exit = false;
                    break;
                case 1: //add a lecturer;
                    String grantedProfessor = "";
                    System.out.println("Enter the name of the  lecturer");
                    name = college.getUniqueName(input,"Lecturer");
                    System.out.println("Enter the id of the lecturer ");
                    id = input.nextInt();
                    System.out.println("Enter the title of the lecturer (Bachelor,Master,Doctor,Professor) ");
                    sTitle = input.next();

                    if (sTitle.equals("Professor")){
                        System.out.println("Enter the name of the of the body that granted the professor's title");
                        grantedProfessor = input.next();
                    }

                    System.out.println("Enter the degree name of the lecturer ");
                    degreeName = input.next();
                    System.out.println("Enter the salary of the lecturer");
                    salary = input.nextDouble();

                    try {
                        college.addLecturer(name, id, degreeName, salary,grantedProfessor,sTitle);
                        System.out.println("Lecturer added successfully");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    }


                    break;
                case 2: //add a committee
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();
                    System.out.println("Enter the name of the chair man");
                    chairMan = input.next();

                    try {
                        college.addCommittee(committee, chairMan);
                        System.out.println("The committee was added successfully");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }

                    break;
                case 3: //add a friend to the committee
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();

                    System.out.println("Enter the name of the new committee friend (lecturer)");
                    name = input.next();
                    try {
                        college.addCommitteeFriend(committee, name);
                        System.out.println("The friend was successfully added");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;
                case 4: //add new chairman
                    System.out.println("Enter the name of the  committee");
                    committee = input.next();

                    System.out.println("Enter the name of the  chairman");
                    chairMan = input.next();
                    try {
                        college.addChairmanToCommittee(committee, chairMan);
                        System.out.println("The chairman was added successfully");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;

                case 5: //remove friend from Committee
                    System.out.println("Enter the name of the committee");
                    committee = input.next();
                    System.out.println("Enter the friend you would like to remove");
                    name = input.next();

                    try {
                        college.removeFriend(name, committee);
                        System.out.println("The friend was successfully removed.");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;

                case 6: //add a new department
                    System.out.println("Enter the name of the department");
                    String departmentName = college.getUniqueName(input,"Department");
                    System.out.println("Enter the amount of students");
                    int studentsNum = input.nextInt();
                    try {
                        college.addDepartment(departmentName, studentsNum);
                        System.out.println("The department was added successfully");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;
                case 7://add a lecturer to a department
                    System.out.println("Enter the name of the  lecturer");
                    String lecName = input.next();
                    System.out.println("Enter the name of the department");
                    String depName = input.next();
                    try {
                        college.addLecturerToDepartment(depName, lecName);
                        System.out.println("The lecturer was added to the department successfully");
                    } catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;

                case 8: //average of all the lecturers salary in the college
                    try {
                        double avg = college.AvgSalary();
                        System.out.println("The salary average of the college is: " + avg);
                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;

                case 9: //average of all the lecturers in a specific department
                    System.out.println("Enter the name of the department");
                    String dep = input.next();
                    try {
                        double avg = college.departmentAvgSalary(dep);
                        System.out.println("The salary average of the department is: " + avg);
                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;
                case 10: //toString of all lecturers
                    System.out.println(college.toStringLecturers());
                    break;
                case 11: //toString of all committees
                    System.out.println(college.toStringCommittees());
                    break;

                case 12: // adding article
                    System.out.println("Enter the name of the lecturer");
                    String lec = input.next();
                    System.out.println("Enter the name of the article");
                    String art = input.next();
                    try {
                        college.addArticle(lec,art);
                        System.out.println("The article was added successfully");
                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 13://comparing Doctors and Professors by articles
                    System.out.println("Enter the name of the first lecturer");
                    String lec1 = input.next();

                    System.out.println("Enter the name of the second lecturer");
                    String lec2 = input.next();
                    try {
                        int comparedNum=college.compareDoctorsAndProfessors(lec1,lec2);
                        if (comparedNum==0)
                            System.out.println( lec1+" and "+lec2+" have the same articles");
                        else if (comparedNum ==1)
                            System.out.println( lec1+" has more articles then "+lec2);
                        else
                            System.out.println(lec2+" has more articles then "+lec1);

                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 14://comparing committees num of lec or articles
                    System.out.println("Enter the name of the first committee");
                    String com1 = input.next();

                    System.out.println("Enter the name of the second committee");
                    String com2 = input.next();
                    int compare=-1;
                    while (compare!=1 && compare!=0) {
                        System.out.println("press 0 for comparing by number of lecturers/ press 1 for comparing by number of articles");
                        compare = input.nextInt();
                    }

                    try {
                        int result;
                        if (compare==0)
                            result=college.compareCommittees(com1,com2,new CompareCommitteesByFriends());
                        else
                            result=college.compareCommittees(com1,com2,new CompareCommitteesByArticles());

                        if (result==0)
                            System.out.println( com1+" and "+com2+" have the same amount");
                        else if (result ==1)
                            System.out.println( com1+" has more  then "+com2);
                        else
                            System.out.println(com2+" has more articles then "+com1);

                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 15://copying a committee
                    System.out.println("Enter the name of the committee");
                    String com = input.next();

                    try {
                        college.copyCommittee(com);
                            System.out.println("the committee was copied successfully");
                    }
                    catch (ActionException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong form of input");
                    }
                    break;
            }
        }
    }
}







