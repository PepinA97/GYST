
	package require sqlite3

	sqlite3 db "getYourShitTogether.sqlite"

	puts "Starting copy into Movie Table"

	puts "[db copy replace Movies "FuckThisShitImOut-Movies.txt" \t] records updated"
	
	puts "Starting copy into TVShows"
	
	puts "[db copy replace TVShows "FuckThisShitImOut-TV.txt" \t] records updated"