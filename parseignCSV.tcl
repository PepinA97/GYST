package require sqlite3
sqlite3 db "getYourShitTogether.sqlite"

set InFileName "ign.csv"
set InFile [open $InFileName r]

set OutFileName "gameInfo.txt"
set OutFile [open $OutFileName w]

set counter -1
while {[gets $InFile line] != -1} {
	
	set line [split $line ,]
	set name [join [split [lindex $line 2] |] ,]
	set rating [lindex $line 5]
	set genre [join [split [lindex $line 6] |] ,]
	set release [lindex $line 8]
	set platform [lindex $line 4]
	
	if {[info exists gameArr($name)]} {
		lappend gameArr($name,platform) $platform
	} else {
		set gameArr($name) ""
		set gameArr($name,rating) $rating
		set gameArr($name,genre) $genre
		set gameArr($name,release) $release
		lappend gameArr($name,platform) $platform
		lappend gameList $name
	}	
}

foreach name $gameList {
	set gameArr($name,platform) [join $gameArr($name,platform) ,]
	puts $OutFile "[incr counter]\t$name\t$gameArr($name,release)\t$gameArr($name,genre)\t$gameArr($name,rating)\t$gameArr($name,platform)"
}

	close $OutFile
	close $InFile
	
	puts "[db copy replace VideoGames "gameInfo.txt" \t] rows inserted"