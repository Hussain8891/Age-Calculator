package hussainshaikh.com.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView todaydate;
    TextView enter_date;
    ImageView select_date;
    Button calculatebtn;
    TextView result_year,result_month,result_day;
    TextView age_years,age_months,age_days,age_weeks,age_hours,age_minutes,age_seconds;
    //for store date
    String date_of_birth,today_date;

    DatePickerDialog.OnDateSetListener setListener;

    //for exit toast
    private long backPressed;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todaydate = (TextView) findViewById(R.id.today_date);
        enter_date = (TextView) findViewById(R.id.enter_date);
        select_date = (ImageView) findViewById(R.id.take_date);
        calculatebtn = (Button) findViewById(R.id.calculate_btn);

        result_year = (TextView) findViewById(R.id.year_result);
        result_month = (TextView) findViewById(R.id.month_result);
        result_day = (TextView) findViewById(R.id.day_result);

        //for advance
        age_years = (TextView) findViewById(R.id.age_years);
        age_months = (TextView) findViewById(R.id.age_months);
        age_days = (TextView) findViewById(R.id.age_days);
        age_weeks = (TextView) findViewById(R.id.age_weeks);
        age_hours = (TextView) findViewById(R.id.age_hours);
        age_minutes = (TextView) findViewById(R.id.age_minutes);
        age_seconds = (TextView) findViewById(R.id.age_seconds);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //for date pattern
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //for today date
         today_date = simpleDateFormat.format(Calendar.getInstance().getTime());
        todaydate.setText(today_date);

        //for take date
        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener
                        ,year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                date_of_birth = day+"/"+month+"/"+year;
                enter_date.setText(date_of_birth);
            }
        };
        //for Calculate age
        calculatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date_of_birth == null){
                    Toast.makeText(MainActivity.this, "Please enter valid date", Toast.LENGTH_SHORT).show();
                }else{
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date1 = simpleDateFormat1.parse(date_of_birth);
                        Date date2 = simpleDateFormat1.parse(today_date);

                        long startdate = date1.getTime();
                        long enddate = date2.getTime();

                        //for years
                        Period period = new Period(startdate,enddate,PeriodType.yearMonthDay());
                        int year = period.getYears();
                        int month = period.getMonths();
                        int days = period.getDays();

                        result_year.setText(year+"");
                        result_month.setText(month+"");
                        result_day.setText(days+"");

                        //for advance
                        Period period_years = new Period(startdate,enddate,PeriodType.years());
                        Period period_months = new Period(startdate,enddate,PeriodType.months());
                        Period period_days = new Period(startdate,enddate,PeriodType.days());
                        Period period_weeks = new Period(startdate,enddate,PeriodType.weeks());
                        Period period_hours = new Period(startdate,enddate,PeriodType.hours());
                        Period period_minutes = new Period(startdate,enddate,PeriodType.minutes());
                        Period period_seconds = new Period(startdate,enddate,PeriodType.seconds());

                        int age_in_years = period_years.getYears();
                        int age_in_months = period_months.getMonths();
                        int age_in_days = period_days.getDays();
                        int age_in_weeks = period_weeks.getWeeks();
                        int age_in_hours = period_hours.getHours();
                        int age_in_minutes = period_minutes.getMinutes();
                        int age_in_seconds = period_seconds.getSeconds();

                       age_years.setText(age_in_years+"");
                       age_months.setText(age_in_months+"");
                       age_days.setText(age_in_days+"");
                       age_weeks.setText(age_in_weeks+"");
                       age_hours.setText(age_in_hours+"");
                       age_minutes.setText(age_in_minutes+"");
                       age_seconds.setText(age_in_seconds+"");



                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }

    //for exit toast

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(this,"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressed = System.currentTimeMillis();
    }
}