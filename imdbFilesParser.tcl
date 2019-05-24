	
	package require sqlite3

	sqlite3 db "getYourShitTogether.sqlite"
	
	set MovieInName "movies.tsv"
	set MovieIn [open $MovieInName r]
	
	set TVInName "tv.tsv"
	set TVIn [open $TVInName r]
	
	set RatingsInName "ratings.tsv"
	set RatingsIn [open $RatingsInName r]
	
	set DirectorInName "crew.tsv"
	set DirectorIn [open $DirectorInName r]
	
	set NamesInName "names.tsv"
	set NamesIn [open $NamesInName r]
	
	set OutFileName1 "FuckThisShitImOut-Movies.txt"
	set OutFile1 [open $OutFileName1 w]
	
	set OutFileName2 "FuckThisShitImOut-TV.txt"
	set OutFile2 [open $OutFileName2 w]
	
	while {[gets $MovieIn line] != -1} {
	
		set line [split $line \t]
		set id [lindex $line 0]
		lappend MovieidList $id
		set MovieArray($id,Title) [lindex $line 2]
		if {[lindex $line 5] != "\\N"} {
			set MovieArray($id,Release) [lindex $line 5]
		} else {
			set MovieArray($id,Release) ""
		}
		if {[lindex $line 8] != "\\N"} {
			set MovieArray($id,Genres) [lindex $line 8]
		} else {
			set MovieArray($id,Genres) ""
		}
		set MovieArray($id,Ratings) ""
		
	}
	
	while {[gets $TVIn line] != -1} {
			
		set line [split $line \t]
		set id [lindex $line 0]
		lappend TVidList $id
		set TVShowArray($id,Title) [lindex $line 2]
		if {[lindex $line 5] != "\\N"} {
			set TVShowArray($id,Release) [lindex $line 5]
		} else {
			set TVShowArray($id,Release) ""
		}
		if {[lindex $line 8] != "\\N"} {
			set TVShowArray($id,Genres) [lindex $line 8]
		} else {
			set TVShowArray($id,Genres) ""
		}
		set TVShowArray($id,Ratings) ""
		
	}
	
	set MovieidList [lsort -unique $MovieidList]
	set TVidList [lsort -unique $TVidList]
	
	while {[gets $RatingsIn line] != -1} {
		
		set line [split $line \t]
		set id [lindex $line 0]
		
		if {[lsearch -sorted $MovieidList $id] != -1} {
			
			set MovieArray($id,Ratings) [lindex $line 1]
			
		} elseif {[lsearch -sorted $TVidList $id] != -1} {
		
			set TVShowArray($id,Ratings) [lindex $line 1]
		
		}
		
	}
	
	while {[gets $DirectorIn line] != -1} {
	
		set line [split $line \t]
		set id [lindex $line 0]
		
		if {[lsearch -sorted $MovieidList $id] != -1} {

			set unFilteredDirectors [split [lindex $line 1] ,]
			foreach dir $unFilteredDirectors {
				lappend directors $dir
			}
			set MovieArray($id,UnfilteredDirectors) $unFilteredDirectors

			
		} elseif {[lsearch -sorted $TVidList $id] != -1} {
			
			set unFilteredDirectors [split [lindex $line 1] ,]
			foreach dir $unFilteredDirectors {
				lappend directors $dir
			}
			set TVShowArray($id,UnfilteredDirectors) $unFilteredDirectors
		
		}
		
	}
	
	set directors [lsort -unique $directors]
	set DirectorMap(\N,Name) ""
	while {[gets $NamesIn line] != -1} {
		
		set line [split $line \t]
		set id [lindex $line 0]
		if {[lsearch -sorted $directors $id] != -1} {
			
			set DirectorMap($id,Name) [lindex $line 1]
			
		}
	
	}
	
	foreach id $MovieidList {
	
		set filteredDirectors ""
	
		if {[info exists MovieArray($id,UnfilteredDirectors)]} {
			set directorList $MovieArray($id,UnfilteredDirectors)
		
			foreach dir $directorList {
				if {[info exists DirectorMap($dir,Name)]} {
					lappend filteredDirectors $DirectorMap($dir,Name)
				}
			}
		}
		set MovieArray($id,Directors) [join $filteredDirectors ,]
		
	}
	
	foreach id $TVidList {
	
		set filteredDirectors ""
	
		if {[info exists TVShowArray($id,UnfilteredDirectors)]} {
			set directorList $TVShowArray($id,UnfilteredDirectors)
		
			foreach dir $directorList {
				if {[info exists DirectorMap($dir,Name)]} {
					lappend filteredDirectors $DirectorMap($dir,Name)
				}
			}
		}
		
		set TVShowArray($id,Directors) [join $filteredDirectors ,]
		
	}
	set counter -1
	foreach id $MovieidList {
	
		puts $OutFile1 "[incr counter]\t$MovieArray($id,Title)\t$MovieArray($id,Release)\t$MovieArray($id,Genres)\t$MovieArray($id,Directors)\t$MovieArray($id,Ratings)\t\t"
	
	}

	set counter -1
	foreach id $TVidList {
	
		puts $OutFile2 "[incr counter]\t$TVShowArray($id,Title)\t$TVShowArray($id,Release)\t$TVShowArray($id,Genres)\t$TVShowArray($id,Directors)\t$TVShowArray($id,Ratings)\t\t"
	
	}
	
	close $OutFile1
	close $OutFile2
	
	puts "Starting copy into Movie Table"

	puts "[db copy replace Movies "FuckThisShitImOut-Movies.txt" \t] records updated"
	
	puts "Starting copy into TVShows"
	
	puts "[db copy replace TVShows "FuckThisShitImOut-TV.txt" \t] records updated"
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	