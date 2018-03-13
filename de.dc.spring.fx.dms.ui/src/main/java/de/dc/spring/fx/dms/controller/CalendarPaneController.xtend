package de.dc.spring.fx.dms.controller

import java.time.LocalDate
import java.time.LocalTime
import org.springframework.stereotype.Controller
import com.calendarfx.model.Calendar
import com.calendarfx.model.Calendar.Style
import com.calendarfx.model.CalendarSource
import com.calendarfx.model.Entry
import com.calendarfx.view.CalendarView
import javafx.application.Platform

@Controller 
class CalendarPaneController extends BaseCalendarPaneController {
	
	def initialize() {
		Platform::runLater[buildContent]
	}

	def buildContent() {
		val calendarView = new CalendarView
		var birthdays = new Calendar("Birthdays")
		var holidays = new Calendar("Holidays")
		var entry = new Entry<String>("Hello World Test")
		holidays.addEntry(entry)
		birthdays.setStyle(Style::STYLE1)
		holidays.setStyle(Style::STYLE2)
		var myCalendarSource = new CalendarSource("My Calendars")
		myCalendarSource.calendars+=#[birthdays, holidays]
		calendarView.calendarSources+=myCalendarSource
		calendarView.requestedTime = LocalTime::now
		var updateTimeThread = new Thread("Calendar: Update Time Thread") {
			override void run() {
				while (true) {
					Platform::runLater([
						calendarView.today = LocalDate::now
						calendarView.time = LocalTime::now
					])
					// update every 10 seconds
					sleep(10000)
				}
			}
		}
		updateTimeThread.priority = Thread::MIN_PRIORITY
		updateTimeThread.daemon = true
		updateTimeThread.start
		calendarPane.children+=calendarView
		calendarView.fullAnchor
		calendarPane.fullAnchor
	}
}
