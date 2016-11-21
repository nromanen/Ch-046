package sample.entities;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oleg on 16.11.2016.
 */
public class TimeTableManager {
   private List<TimeTable> timeTables;
//    public static List<TimeTable> getLessonsByDay(List<TimeTable> timeTables,DayOfWeek dayOfWeek){
//        List<TimeTable> res=new ArrayList<>();
//        for (TimeTable tt:timeTables)
//        {
//            if (tt.getDay().equals(dayOfWeek))
//                res.add(tt);
//
//        }
//        return res;
//    }

    public  List<TimeTable> getLessonsByWeek(OddnessOfWeek oddnessOfWeek){
        List<TimeTable> res=new ArrayList<>();
        for (TimeTable tt:timeTables)
        {
            if (tt.getOddnessOfWeek().equals(oddnessOfWeek))
                res.add(tt);
        }
        return res;
    }

    public List<TimeTable> getLessonsByDayAndWeek(OddnessOfWeek oddnessOfWeek
    ,DayOfWeek dayOfWeek){
        List<TimeTable> res=new ArrayList<>();
        for (TimeTable tt:timeTables)
        {
            if (tt.getOddnessOfWeek().equals(oddnessOfWeek) && tt.getDay().equals(dayOfWeek))
                res.add(tt);
        }
        return res;
    }

    public TimeTableManager(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    //builder
    public  List<Pair> getFreeDaylyPairs(DayOfWeek dayOfWeek){
       Pair[] pairs=Pair.values();
        List<Pair> list=new ArrayList<>(Arrays.asList(pairs));

        for (int i = 0; i < timeTables.size(); i++) {
            if (timeTables.get(i).getDay().equals(dayOfWeek))
            for (int j=0; j<list.size();j++)
            if (timeTables.get(i).getPair().equals(list.get(j))){
                list.remove(list.get(j));
            }

        }

        return list ;
    }



}
