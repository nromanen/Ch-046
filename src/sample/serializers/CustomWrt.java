package sample.serializers;

import sample.entities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oleg on 21.11.16.
 */
public class CustomWrt<T> implements Serializabl<T> {
    @Override
    public void write(String path, T t) {
        File file = new File("src/sample/fileline.txt");
        //file.createNewFile();

        OutputStream stream;

        try {

            stream = new FileOutputStream(file);

            for (TimeTable subj : (ArrayList<TimeTable>)t) {stream.write(subj.toString().getBytes());

                stream.write(System.getProperty("line.separator").getBytes());

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T read(String path) {
                        /*
                         *Example: Algebra(LAB), FIRST, [Ann, Parkinson], 101, MONDAY, All
                         *The first group is the subject's title
                         * The second-- it's type
                         */
        Pattern pattern = Pattern.compile("(^[A-Z][a-z]{2,15}+)\\(([A-Z]{2,15})\\)" +
                        /*The third -- it's ENUM number*/
                ",\\s([A-Z]+)" +
                        /*The fourth -- professor's name
                         *The fifth -- professor's surname */
                ",\\s\\[([A-Z][a-z]+),\\s([A-Z][a-z]+)\\]," +
                        /*The sixth -- group number*/
                "\\s(\\d{1,3})," +
                        /*The seventh -- day of week*/
                "\\s([A-Z]+)," +
                        /*The eighth -- oddness of week*/
                "\\s([A-Z][a-z]{2}|[A-Z]{3})");

        File file=new File(path);
        String s2;
        List<TimeTable> timeTables=new ArrayList<>();
        try {
            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                s2=scanner.nextLine();
                Matcher matcher1=pattern.matcher(s2);
                while (matcher1.find()) {
                    TimeTable timeTable=new TimeTable();
                    //System.out.println(matcher1.group());// System.out.println("err");
                    Subject subject = new Subject(matcher1.group(1),TypeOfLesson.valueOf(matcher1.group(2)));
                    timeTable.setSubject(subject);
                    timeTable.setDay(DayOfWeek.valueOf(matcher1.group(7)));
                    timeTable.setPair(Pair.valueOf(matcher1.group(3)));
                    timeTable.setTeacher(new Teacher(matcher1.group(4),matcher1.group(5)));
                    timeTable.setGroup(new Group(matcher1.group(6)));
                    timeTable.setOddnessOfWeek(OddnessOfWeek.valueOf(matcher1.group(8)));
                    timeTables.add(timeTable);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }
        return (T)timeTables;
    }

}
