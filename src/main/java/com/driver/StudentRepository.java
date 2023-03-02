package com.driver;

import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class StudentRepository {

    Map<String,Student> studentMap;
    Map<String,Teacher> teacherMap;

    Map<String,List<String>> studentTeacherMap;

    public StudentRepository() {
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.studentTeacherMap = new HashMap<>();
    }

    public void saveStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher) {
        if (studentMap.containsKey(student) && teacherMap.containsKey(teacher)) {
            List<String> studentlist = new ArrayList<>();

            if (studentTeacherMap.containsKey(teacher))
                studentlist = studentTeacherMap.get(teacher);

            studentlist.add(student);
            studentTeacherMap.put(teacher, studentlist);
        }
    }
    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public List<String> getStudentsByTeacherName(String teacher) {
            return studentTeacherMap.get(teacher);
    }
    public void deleteTeacherByName(String teacher) {
        List<String> studentslist = new ArrayList<>();

        if(studentTeacherMap.containsKey(teacher)){
            studentslist = studentTeacherMap.get(teacher);

            for(String student : studentslist){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }
        }

        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
      studentTeacherMap.remove(teacher);
    }


    public void deleteAllTeachers() {
        teacherMap = new HashMap<>();

        HashSet<String> studentSet = new HashSet<>();

        for(String tName : studentTeacherMap.keySet()){
            for(String sName : studentTeacherMap.get(tName)){
                studentSet.add(sName);
            }
        }

        for(String sName : studentSet){
            if(studentMap.containsKey(sName)){
                studentMap.remove(sName);
            }
        }
        studentTeacherMap = new HashMap<>();
    }
}
