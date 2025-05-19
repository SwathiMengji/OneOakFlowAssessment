Feature: Test case 4

Scenario: : Validate hover options, location and UI elements
	Given the user is on "rebl_url"
	When hover over "Vacation Rentals" and validate color change
	Then validate <options> under "Vacation Rentals"
	When hover over "Locations" and validate color change
	When hover over and click on "Chandler"
	Then validate "CHANDLER VACATION RENTALS" on home page
	Then validate property name "THE SEASIDE BOHEMIAN" and location "Chandler"
	
	Examples:
	|options|
	|"Locations, Luxury Properties, Pet Friendly, Rentals with Pools, Rentals with Hot Tubs, Large Group, Extended Stays, Property Types, All Properties"|