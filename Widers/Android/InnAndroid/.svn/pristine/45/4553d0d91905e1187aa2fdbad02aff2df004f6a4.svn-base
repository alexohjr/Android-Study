package com.and.andelectronics.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.and.andelectronics.R;
import com.and.andelectronics.infrastructure.model.lodgement.Room;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by yowonsm on 2018-06-03.
 */
public class TestViewActivity extends AppCompatActivity {

    private Context context;
    private Room room;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testview);
        //ButterKnife.bind(this);
        this.context = this;

//        List<EventDay> events = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        events.add(new EventDay(calendar, R.drawable.sample_icon));
//
//        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
//        calendarView.setEvents(events);
//
//
//        calendarView.setOnDayClickListener(new OnDayClickListener() {
//            @Override
//            public void onDayClick(EventDay eventDay) {
//                Calendar clickedDayCalendar = eventDay.getCalendar();
//            }
//        });

        DatePickerBuilder builder = new DatePickerBuilder(this, listener)
                .pickerType(CalendarView.RANGE_PICKER);

        DatePicker datePicker = builder.build();
        datePicker.show();

        /*final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        button = (Button) findViewById(R.id.get_selected_dates);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        calendar.deactivateDates(list);
        ArrayList<Date> arrayList = new ArrayList<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-mm");
            String strdate = DateUtil.nowDate();
            String strdate2 = DateUtil.nowDate();
            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSelectedDate(new Date())
                .withDeactivateDates(list)
                .withHighlightedDates(arrayList);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("list",  calendar.getSelectedDates().toString());
            }
        });*/
    }
    private OnSelectDateListener listener = new OnSelectDateListener() {
        @Override
        public void onSelect(List<Calendar> calendars) {
            String fromDate;
            String toDate;
            if(calendars != null && calendars.size() > 0){


                for(int i=0; i< calendars.size(); i++){
                    int from  = calendars.get(i).get(Calendar.DATE);
                    fromDate = String.valueOf(from);
                }
            }
        }
    };
}