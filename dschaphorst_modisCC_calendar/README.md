<h1>Modis Client Coding Challenge</h1>
Finding available ordering time intervals from calendar

 You are given an array of calendars. Each calendar--pickup, curbside, or delivery--contains an array of ranges.
 A range contains the day of week and open hour (start) and closed hour (end) of a location, in local time.
 The earliest time a store can fulfilll an order is 42 minutes, given as integer A,  after store opening time on any given day.
 The latest time a store can* fulfill an order is 10 minutes, given as integer B, before closing time on any given day.
 If the range day or hour is in the past,
 it should be excluded in the output.

 Create an Android app which print below Output on a Fragment at all available hours of a calendar type in 15-minute increments, given as integer
 C, from store open to close on each day.

 Input
 See calendars.json file in Resources folder

 Output
 Friday, December 2, 2021 at 1:15:00 PM Pacific Standard Time
 Friday, December 2, 2021 at 1:30:00 PM Pacific Standard Time
 ...
 ...
 Wednesday, December 15, 2021 at 9:45:00 PM Pacific Standard Time

 Constraints
 A = 42
 B = 10
 C = 15
 D = pickup | curbside | delivery


func findAvailableOrderingTimes(a: Int = 42, b: Int = 10, c: Int = 15, d: String = "pickup"){
// Start your code here...
}

<h2>Assumptions Made</h2>
<ul>
 <li> I do not have input validation, but I do have mild null handling. It is expected that all JSON
input file arrays will be formatted exactly the same as the sample input. I have created an
enum class [OrderTypes] that should be updated if there are any more types added, but at a minimum,
the three given types --pickup, curbside, or delivery-- should be included in the JSON provided.</li>
 <li> If the input JSON is to be changed, the [fileName] in [AvailabilityViewModel] should be updated,
and the file should be placed in ***dschaphorst_modisCC_calendar/app/src/main/assets*** </li>
 <li> The final line of the instructions states that all date ranges that are in the past should be
omitted from the output; however, the sample JSON was all dates in the past. Because of this, I
have added a check for items in the past on line 51 in [AvailabilityViewModel], but it is 
currently commented out. Please uncomment that line to restore that functionality. </li>
</ul>
