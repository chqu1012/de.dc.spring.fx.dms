package de.dc.spring.fx.dms.controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.google.common.collect.Iterables;
import de.dc.spring.fx.dms.controller.BaseCalendarPaneController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("all")
public class CalendarPaneController extends BaseCalendarPaneController {
  public void initialize() {
    final Runnable _function = () -> {
      this.buildContent();
    };
    Platform.runLater(_function);
  }
  
  public void buildContent() {
    final CalendarView calendarView = new CalendarView();
    Calendar birthdays = new Calendar("Birthdays");
    Calendar holidays = new Calendar("Holidays");
    Entry<String> entry = new Entry<String>("Hello World Test");
    holidays.addEntry(entry);
    birthdays.setStyle(Calendar.Style.STYLE1);
    holidays.setStyle(Calendar.Style.STYLE2);
    CalendarSource myCalendarSource = new CalendarSource("My Calendars");
    ObservableList<Calendar> _calendars = myCalendarSource.getCalendars();
    Iterables.<Calendar>addAll(_calendars, Collections.<Calendar>unmodifiableList(CollectionLiterals.<Calendar>newArrayList(birthdays, holidays)));
    ObservableList<CalendarSource> _calendarSources = calendarView.getCalendarSources();
    _calendarSources.add(myCalendarSource);
    calendarView.setRequestedTime(LocalTime.now());
    Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
      @Override
      public void run() {
        try {
          while (true) {
            {
              final Runnable _function = () -> {
                calendarView.setToday(LocalDate.now());
                calendarView.setTime(LocalTime.now());
              };
              Platform.runLater(_function);
              Thread.sleep(10000);
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    updateTimeThread.setPriority(Thread.MIN_PRIORITY);
    updateTimeThread.setDaemon(true);
    updateTimeThread.start();
    ObservableList<Node> _children = this.calendarPane.getChildren();
    _children.add(calendarView);
    this.fullAnchor(calendarView);
    this.fullAnchor(this.calendarPane);
  }
}
