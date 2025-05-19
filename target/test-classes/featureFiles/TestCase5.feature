Feature: Test case 5

Scenario: Validate error messages
	Given the user is on "oregonBeachVacation_url"
	When scroll to "Plan your next Vacation Rental with Oregon Beach Vacations!"
	When select "List with Us" from the ribbon
	When select "List Your Home" from the dropdown
	When scroll to "Interested in Management?"
	When enter form details <full_name>, <email> and <phone_number>
	Then validate error messages <full_name_message>, <email_message> and <phone_number_message>
	
	Examples:
	
	|full_name  |full_name_message |email              |email_message                      |phone_number |phone_number_message       |
	|"test123"  |"Enter valid Name"|"test124!gmail.com"|"Please enter valid email address."|"1234567890" |"Not a valid phone number."|