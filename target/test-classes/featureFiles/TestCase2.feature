Feature: Test case 2

Scenario: Validate filters and sort options for search
	Given the user is on "meredith_url"
	When location is selected as "Lincoln City"
	And arrival date is selected "2025-06-25" and depart date is selected "2025-07-01"
	And add 3 adults and 2 children
	And clear people selection
	And add 2 adults and 1 children
	And click on search
	When add filter options 2 bedrooms, 2 bathrooms and  "Hot Tub: Private" pool
	And sort by <sortOption>
	Then validate first entry name is <firstEntryName>
	Then validate the text after sort is <sortOption>
	
	Examples: 
	|sortOption              | firstEntryName      |
	|"Name - A to Z"         |"A Wave From It All" |
	|"Name - Z to A"         |"Wade N Sea"         |
	|"New Homes - Old to New"|"Halcyon House"      |