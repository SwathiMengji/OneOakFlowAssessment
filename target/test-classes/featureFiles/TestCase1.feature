#1.	Test case 1: Validate the social media urls
#	Visit https://www.meredithlodging.com/ 
#	Scroll down to Footer.
#	For all the social media icons, Using findElements, find the respective URLs of all the social media icons and print them on system console.
#	Click on all the social media icon one by one and open them in a new window. Get the url of the new window and print the same on the system console.
#	Validate the URL found by findelements and in new window are same.

Feature: Test case 1

Scenario: Validate the social media urls in footer
	Given the user is on "meredith_url"
	When user scolls down to footer
	And finds all urls of social media icons using findelements
	When open all social media handles in new windows
	Then validate the urls found match the urls opened

