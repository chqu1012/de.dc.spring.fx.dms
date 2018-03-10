package de.dc.spring.fx.dms.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;

@Controller
public class CalendarPaneController extends BaseCalendarPaneController {

	public void initialize() {
		Platform.runLater(() ->buildContent());
	}

	private void buildContent() {
		CalendarView calendarView = new CalendarView();

		Calendar birthdays = new Calendar("Birthdays");
		Calendar holidays = new Calendar("Holidays");
		
		Entry<String> entry = new Entry<String>("Hello World Test");
		holidays.addEntry(entry);

		birthdays.setStyle(Style.STYLE1);
		holidays.setStyle(Style.STYLE2);
		CalendarSource myCalendarSource = new CalendarSource("My Calendars");
		myCalendarSource.getCalendars().addAll(birthdays, holidays);
		calendarView.getCalendarSources().addAll(myCalendarSource);
		calendarView.setRequestedTime(LocalTime.now());

		Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
			@Override
			public void run() {
				while (true) {
					Platform.runLater(() -> {
						calendarView.setToday(LocalDate.now());
						calendarView.setTime(LocalTime.now());
					});
					try {
						// update every 10 seconds
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		};

		updateTimeThread.setPriority(Thread.MIN_PRIORITY);
		updateTimeThread.setDaemon(true);
		updateTimeThread.start();

		calendarPane.getChildren().add(calendarView);

		fullAnchor(calendarView);
		fullAnchor(calendarPane);
	}
}
