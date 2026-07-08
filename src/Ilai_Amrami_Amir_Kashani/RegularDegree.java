package Ilai_Amrami_Amir_Kashani;

public abstract class RegularDegree extends Lecturer implements java.io.Serializable {

    public RegularDegree(String name, int id, String degreeName, double salary) throws ActionException {
        super(name, id, degreeName, salary);
    }
}
