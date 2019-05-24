
	package require sqlite3
	sqlite3 db "getYourShitTogether.sqlite"
	
	set InFileName "anime.csv"
	set InFile [open $InFileName r]
	
	set OutFileName "animeInfo.txt"
	set OutFile [open $OutFileName w]
	
	set counter -1
	while {[gets $InFile line] != -1} {
	
		set line [split $line ,]
		set name [join [split [lindex $line 1] |] ,]
		set genre [join [split [join [split [lindex $line 2] |] ,] \"]]
		set rating [lindex $line 5]
		
		puts $OutFile "[incr counter]\t$name\t$genre\t$rating"
	
	}

	close $OutFile
	close $InFile
	
	puts "[db copy replace Anime "animeInfo.txt" \t] rows inserted" 