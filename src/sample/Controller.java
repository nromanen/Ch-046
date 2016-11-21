package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import sample.entities.*;
import sample.serializers.CustomWrt;
import sample.serializers.JsonWrt;
import sample.serializers.XmlWrt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    public  void initialize() throws IOException, JAXBException {
        Subject english=new Subject();
        english.setName("English");
        english.setType(TypeOfLesson.LECTURE);
        english.setCourseN(3);


        Subject spanish=new Subject();
        spanish.setName("Spanish");
        spanish.setType(TypeOfLesson.LECTURE);
        spanish.setCourseN(3);

        Subject chineese=new Subject();
        chineese.setName("Chineese");
        chineese.setType(TypeOfLesson.LECTURE);
        chineese.setCourseN(3);

        Subject math=new Subject();
        math.setName("Math");
        math.setType(TypeOfLesson.LECTURE);
        math.setCourseN(3);

        TimeTable tt1=new TimeTable();
        tt1.setDay(DayOfWeek.MONDAY);
        tt1.setOddnessOfWeek(OddnessOfWeek.EVEN);
        tt1.setPair(Pair.FIRST);
        tt1.setSubject(english);
        tt1.setGroup(new Group());
        tt1.setTeacher(new Teacher());

        TimeTable tt2=new TimeTable();
        tt2.setDay(DayOfWeek.MONDAY);
        tt2.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt2.setPair(Pair.FIRST);
        tt2.setSubject(spanish);
        tt2.setGroup(new Group());
        tt2.setTeacher(new Teacher());

        TimeTable tt3=new TimeTable();
        tt3.setDay(DayOfWeek.MONDAY);
        tt3.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt3.setPair(Pair.THIRD);
        tt3.setSubject(chineese);
        tt3.setGroup(new Group());
        tt3.setTeacher(new Teacher());

        TimeTable tt4=new TimeTable();
        tt4.setDay(DayOfWeek.WEDNESDAY);
        tt4.setOddnessOfWeek(OddnessOfWeek.ODD);
        tt4.setPair(Pair.FORTH);
        tt4.setSubject(math);
        tt4.setGroup(new Group());
        tt4.setTeacher(new Teacher());

        List<TimeTable> timeTables=new ArrayList<>();
        timeTables.add(tt1);
        timeTables.add(tt2);
        timeTables.add(tt3);
        timeTables.add(tt4);

        TimeTableManager ttm=new TimeTableManager(timeTables);
        List<TimeTable> tts=ttm.getLessonsByWeek(OddnessOfWeek.EVEN);


        CustomWrt<List<TimeTable>> cus=new CustomWrt<>();
        List<TimeTable> read = cus.read("src/sample/fileline.txt");
        System.out.println(read.get(0).getSubject().getName());
        File file = new File("src/sample/fileline.txt");
        //cus.write("src/sample/fileline.txt",timeTables);

        ArrayList<Teacher> teachers=new ArrayList<>();
        teachers.add(new Teacher("Olena","Mykolaivna"));
        teachers.add(new Teacher("Name","Surname"));

        JsonWrt<ArrayList<Teacher>> teacherJsonWrt=new JsonWrt<>(new TypeReference<ArrayList<Teacher>>() {
        });

        teacherJsonWrt.write("src/sample/teacher.json1",teachers);
        ArrayList<Teacher> teacher = teacherJsonWrt.read("src/sample/teacher.json");
        System.out.println(teacher.get(0).getFirstName());

    }
}
