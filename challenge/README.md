## Guidelines:

  * This project is a mock of a real life problem. We intentionally made this project too much work to complete in the given amount of time, and do not expect you to work on this in one sitting.

  * The tasks are in no particular order. Your work will be assessed not only on how much you complete but also **how** you complete the tasks you decide to build. Please more focus on the business logic. Work will be graded on, but not limited to, code design, reusability, scalability, coding style, and writing maintainable code.

  * Feel free to use any standard Java / Spring libraries.

  * Email us with as many questions as you want. Questions sent during non working hours may have a slower response time.

## Notes:
  * No code required in `gateway` directory.  That package is setup with mock functions that just print to console.  You can assume they behave as in the comments.

## Submission:
  * Commit your changes locally and Zip the entire project folder with your completed work.
  * Email us the generated zip file.

<br><br>

# Uber's Rental Service

Uber will begin offering vehicle rentals for partners that do not own their own vehicle. Create a Car Rental Service for a Mobile Frontend and an Internal Manager Dashboard.

### Project Requirements

**The goal of this challenge is to implement endpoints to our service handler that follow the specification below.**

### Booking Frontend (External Users) will:
- Allow users to rent a car for X amount of hours. Users should receive a confirmation text message (use Texter Gateway inside `gateway/texter.java`).

- Users should receive a text message 24 hours before the scheduled pickup time (Use Scheduler `gateway/scheduler.java`).

- Implement an endpoint that allows the user to check-in and confirm Vehicle Pick-up.

- Users will be charged at the time of pick-up and should receive a text message stating that their vehicle has been picked up, along with the amount charged for the entire rental time.

- Users should receive a text message 1 hour before the scheduled rental return time.

- Implement an endpoint with logic to allow the user to confirm Vehicle Drop-off.

- Users should receive a text message stating that their vehicle has been dropped off, along with the final charged price (there should be an overtime fee of 1.5x rate).

- Users should receive a text message if the vehicle has not been returned past the scheduled due time.

- Users should be able to cancel a reservation with the following cancellation policy:
	- Cancelling within 24 hour is non-refundable
	- Cancelling 48 hours in advance will result in a 25% non-refundable deposit (i.e. 75% returned to user)
	- Users should receive a text message stating that the reservation has been cancelled, along with the final price they were charged

- Users should be able to update their reservation (pick-up and drop-off times).


### Manager Dashboard (Internal Users)

Create endpoints that will be used to display information about our rental service to our internal Ops team. This new dashboard should be able to display:

- Realtime available vehicle inventory for a specific lot.
- Current unavailable inventory and when each vehicle is expected to be returned.
- Overdue inventory with contact information for the renter.
- Schedule of availability for a specific vehicle.

Keep in mind that this dashboard will be used at a global scale, so we do not want to query the database every time a user navigates to the dashboard.

### Bonus if you have time:
- Add logging for events that should be captured.
- Add tests to your handler functions.
