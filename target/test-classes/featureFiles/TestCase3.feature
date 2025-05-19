Feature: Test case 3

Scenario: Validate data at checkout
	Given the user is on "meredith_url"
	When location is selected as "Lincoln City"
	And arrival date is selected "2025-06-25" and depart date is selected "2025-07-01"
	And add 3 adults and 2 children
	And clear people selection
	And add 2 adults and 1 children
	And click on search
	When add filter options 2 bedrooms, 2 bathrooms and  "Hot Tub: Private" pool
	When select first entry
	Then print the name of property
	Then validate details on checkout page
	
	
	