package com.example.aoc24

val input14 =
    """p=30,37 v=-50,-64
p=24,42 v=55,12
p=10,56 v=-49,82
p=67,69 v=-58,60
p=82,98 v=-57,61
p=29,1 v=20,-40
p=41,61 v=-95,92
p=92,68 v=38,80
p=26,82 v=58,79
p=84,42 v=11,-45
p=71,10 v=-76,21
p=42,3 v=-93,-25
p=53,102 v=93,-63
p=33,4 v=-13,-94
p=20,100 v=57,-32
p=17,50 v=51,39
p=75,41 v=-84,39
p=83,53 v=-64,-96
p=15,38 v=28,-72
p=35,23 v=-9,-69
p=26,92 v=10,56
p=19,54 v=-42,4
p=50,6 v=-72,28
p=88,19 v=-90,-29
p=11,85 v=-5,87
p=22,22 v=-49,60
p=70,81 v=-33,37
p=43,47 v=-93,35
p=5,32 v=-1,39
p=91,70 v=96,-44
p=10,87 v=-38,94
p=67,6 v=32,-99
p=54,41 v=-47,68
p=2,59 v=-75,-53
p=27,20 v=-46,5
p=22,28 v=-13,-75
p=51,29 v=99,66
p=98,28 v=-38,-30
p=26,53 v=-49,48
p=39,83 v=-67,3
p=34,59 v=-46,-57
p=96,92 v=83,26
p=18,70 v=59,23
p=41,84 v=-27,-1
p=5,40 v=-30,20
p=86,83 v=-59,87
p=3,76 v=12,-76
p=49,88 v=6,83
p=37,79 v=90,-5
p=78,42 v=21,50
p=78,55 v=-12,-95
p=66,14 v=31,1
p=53,47 v=-64,62
p=67,18 v=-89,58
p=87,10 v=-78,-29
p=62,86 v=-14,91
p=78,74 v=17,-81
p=12,62 v=-40,45
p=45,102 v=-91,-32
p=38,1 v=-99,97
p=39,81 v=55,-84
p=43,22 v=-85,65
p=44,30 v=43,35
p=58,22 v=-41,-48
p=59,35 v=-35,-45
p=93,23 v=-24,13
p=28,98 v=-39,96
p=95,34 v=-27,55
p=18,70 v=92,-27
p=7,76 v=-11,75
p=1,23 v=-98,-37
p=8,87 v=-3,-70
p=59,22 v=97,-71
p=69,79 v=-36,-23
p=34,82 v=-15,-35
p=5,7 v=-36,44
p=76,70 v=-79,-6
p=77,36 v=13,85
p=68,60 v=80,14
p=13,96 v=92,94
p=37,37 v=-93,-38
p=1,66 v=1,11
p=10,10 v=94,-37
p=97,86 v=-30,-5
p=69,11 v=-76,21
p=30,90 v=22,-96
p=33,26 v=-31,97
p=48,81 v=37,-54
p=19,37 v=72,33
p=49,88 v=36,-69
p=1,61 v=12,44
p=48,83 v=-72,91
p=39,6 v=-56,-55
p=94,98 v=44,-70
p=68,60 v=95,-99
p=44,52 v=-83,-61
p=44,81 v=89,51
p=4,44 v=36,-7
p=17,9 v=-77,-93
p=35,77 v=53,38
p=60,79 v=86,-81
p=16,81 v=85,69
p=54,10 v=-2,36
p=55,73 v=-43,-42
p=20,86 v=-11,-47
p=57,32 v=3,88
p=82,66 v=89,-42
p=90,4 v=-59,-55
p=8,20 v=-76,69
p=18,77 v=-79,-65
p=100,29 v=-24,66
p=22,3 v=-44,-47
p=52,49 v=-90,68
p=16,72 v=45,31
p=57,99 v=33,-82
p=51,56 v=16,9
p=28,90 v=88,-74
p=76,24 v=-43,-10
p=21,31 v=-36,-90
p=68,99 v=27,-40
p=0,84 v=69,83
p=100,88 v=-97,32
p=65,52 v=85,-4
p=31,61 v=-76,71
p=75,101 v=-70,55
p=43,17 v=74,-33
p=56,89 v=62,-54
p=3,50 v=-96,69
p=77,70 v=-49,53
p=69,69 v=-78,72
p=76,78 v=54,-8
p=32,75 v=-34,-11
p=42,100 v=47,75
p=70,28 v=97,4
p=88,42 v=75,39
p=64,20 v=-74,89
p=78,17 v=-72,-98
p=14,79 v=30,26
p=47,50 v=16,38
p=21,75 v=59,-73
p=82,74 v=81,30
p=25,62 v=11,-1
p=94,83 v=67,40
p=82,62 v=16,-5
p=26,97 v=67,50
p=6,77 v=-44,72
p=76,94 v=-17,77
p=55,27 v=-62,-75
p=86,14 v=-90,-97
p=51,66 v=78,-20
p=21,68 v=22,87
p=50,83 v=-68,-8
p=52,81 v=3,-8
p=10,82 v=36,-35
p=99,10 v=38,-65
p=56,61 v=-67,-55
p=46,39 v=-60,73
p=65,64 v=29,-65
p=11,5 v=91,-12
p=93,69 v=38,57
p=62,56 v=-68,61
p=31,86 v=-48,79
p=94,17 v=-90,86
p=71,37 v=-91,35
p=97,48 v=58,-69
p=95,11 v=75,-52
p=37,62 v=-87,38
p=63,76 v=97,41
p=9,74 v=78,-52
p=13,87 v=96,83
p=26,50 v=-46,-69
p=8,68 v=-43,-62
p=45,9 v=39,-2
p=29,79 v=-19,87
p=56,5 v=11,-16
p=92,68 v=25,99
p=98,2 v=-63,-13
p=29,102 v=-93,-36
p=73,9 v=56,-28
p=36,66 v=-83,-46
p=94,80 v=-43,-38
p=53,79 v=23,97
p=50,10 v=-91,-37
p=14,43 v=98,-49
p=3,78 v=78,-1
p=46,46 v=-31,-31
p=94,101 v=38,-70
p=56,23 v=-37,9
p=50,66 v=38,91
p=70,26 v=17,-34
p=7,92 v=7,-76
p=75,36 v=-45,-30
p=71,0 v=62,-51
p=72,92 v=12,9
p=34,39 v=84,84
p=73,88 v=-39,-26
p=51,81 v=-68,64
p=24,82 v=61,4
p=32,2 v=86,86
p=50,102 v=-30,-12
p=98,26 v=-67,14
p=96,88 v=35,-83
p=43,53 v=12,-99
p=77,50 v=56,-72
p=7,50 v=66,92
p=70,66 v=-6,38
p=60,29 v=-70,-33
p=70,11 v=89,24
p=93,58 v=-19,-74
p=96,68 v=98,30
p=70,68 v=-43,38
p=24,99 v=53,-28
p=98,38 v=-74,-9
p=69,49 v=-76,-23
p=9,75 v=-69,64
p=41,19 v=14,-71
p=0,23 v=38,-71
p=1,56 v=36,-23
p=55,52 v=-99,31
p=85,85 v=-22,75
p=18,78 v=69,-27
p=7,96 v=90,-89
p=96,85 v=40,-70
p=7,80 v=-92,87
p=81,6 v=46,-17
p=44,71 v=10,-19
p=12,72 v=73,-4
p=33,86 v=14,67
p=84,94 v=52,-24
p=34,66 v=-56,-65
p=31,60 v=-85,88
p=65,0 v=-35,-97
p=76,83 v=-12,60
p=15,29 v=64,-59
p=71,45 v=-68,43
p=19,77 v=92,95
p=6,85 v=-38,79
p=1,27 v=-59,33
p=38,12 v=-89,-10
p=45,27 v=51,66
p=23,100 v=-13,-70
p=18,43 v=-35,-59
p=63,28 v=72,-72
p=42,7 v=83,-5
p=28,99 v=29,-43
p=96,38 v=11,39
p=47,97 v=74,56
p=81,27 v=87,-64
p=63,6 v=39,93
p=15,43 v=-48,19
p=6,21 v=12,-65
p=52,70 v=-85,-14
p=96,53 v=-55,8
p=3,77 v=-98,-20
p=76,54 v=-86,-95
p=85,77 v=-49,-72
p=96,46 v=-84,39
p=42,79 v=-93,68
p=46,59 v=-17,7
p=52,16 v=-66,32
p=12,70 v=67,23
p=52,7 v=-21,67
p=13,62 v=-5,95
p=25,63 v=-83,19
p=91,25 v=54,24
p=28,9 v=-5,71
p=73,1 v=89,82
p=71,22 v=83,82
p=37,40 v=90,66
p=8,91 v=-3,-85
p=53,66 v=6,38
p=14,94 v=59,10
p=81,17 v=-12,-22
p=82,72 v=-20,-88
p=36,90 v=-47,-80
p=26,91 v=-77,-70
p=73,91 v=23,-40
p=76,68 v=21,53
p=64,30 v=-82,55
p=7,97 v=1,-13
p=69,87 v=57,-28
p=19,65 v=92,31
p=66,50 v=97,-53
p=86,54 v=13,84
p=23,51 v=-48,46
p=97,39 v=-61,-7
p=6,2 v=57,-42
p=51,71 v=-20,40
p=65,17 v=-76,-25
p=62,12 v=-14,-59
p=16,31 v=30,85
p=55,12 v=16,-23
p=22,45 v=96,-87
p=8,97 v=95,14
p=73,91 v=-96,-89
p=35,32 v=-50,-34
p=19,78 v=68,-89
p=88,23 v=-56,-58
p=63,20 v=74,29
p=81,79 v=-10,57
p=11,57 v=-40,-42
p=24,69 v=-83,-12
p=41,7 v=19,69
p=3,101 v=25,69
p=5,67 v=38,-88
p=66,50 v=-47,39
p=59,32 v=-93,16
p=71,48 v=45,-82
p=23,76 v=-46,83
p=27,9 v=-15,-78
p=39,57 v=99,-63
p=64,48 v=-39,-88
p=100,24 v=-78,-61
p=83,8 v=-88,-89
p=74,2 v=64,-65
p=58,54 v=-2,57
p=69,98 v=-66,-97
p=12,49 v=-69,27
p=66,9 v=60,-97
p=34,47 v=72,-26
p=48,4 v=66,62
p=64,22 v=-10,13
p=87,42 v=23,-87
p=32,67 v=6,-73
p=44,77 v=-1,-5
p=19,44 v=86,80
p=77,82 v=-86,3
p=90,99 v=43,-43
p=77,32 v=-28,-26
p=78,7 v=-14,2
p=15,69 v=-44,-69
p=80,23 v=-39,-74
p=93,39 v=-56,78
p=75,51 v=-47,-3
p=47,20 v=74,1
p=33,85 v=39,-47
p=33,2 v=36,50
p=46,43 v=81,-11
p=47,100 v=-62,-74
p=14,31 v=87,-78
p=63,56 v=41,43
p=80,57 v=41,47
p=57,26 v=2,12
p=56,25 v=5,-95
p=50,40 v=-66,35
p=67,30 v=27,-35
p=38,33 v=42,-10
p=12,4 v=28,59
p=2,48 v=54,30
p=10,8 v=88,-33
p=34,79 v=14,-35
p=52,99 v=60,14
p=25,66 v=-13,-50
p=94,44 v=7,-30
p=45,50 v=35,-30
p=72,96 v=-88,52
p=91,56 v=-24,19
p=83,92 v=17,-28
p=64,28 v=95,31
p=34,44 v=49,4
p=32,55 v=-52,-80
p=11,25 v=40,35
p=80,2 v=-6,-74
p=83,27 v=-48,61
p=23,53 v=-3,-92
p=60,44 v=-65,-48
p=46,102 v=-25,-97
p=8,6 v=-36,-2
p=87,88 v=13,18
p=40,84 v=-33,53
p=13,7 v=28,-78
p=60,10 v=27,21
p=92,50 v=44,4
p=81,55 v=-51,-76
p=9,102 v=32,-2
p=65,42 v=-6,54
p=9,96 v=-34,71
p=34,94 v=-87,94
p=74,62 v=90,-52
p=51,8 v=61,48
p=45,27 v=70,5
p=15,74 v=51,-62
p=92,49 v=83,-22
p=4,23 v=-28,32
p=30,54 v=41,-57
p=70,65 v=27,-4
p=39,43 v=-56,-38
p=68,75 v=-78,3
p=71,58 v=60,-65
p=7,73 v=98,49
p=7,54 v=-89,96
p=77,45 v=81,43
p=88,76 v=-37,13
p=62,41 v=99,39
p=65,76 v=77,52
p=72,97 v=-43,25
p=60,30 v=99,5
p=25,35 v=-23,-53
p=84,24 v=-88,-60
p=41,0 v=20,6
p=78,52 v=-18,-72
p=68,65 v=95,19
p=11,40 v=90,-56
p=69,40 v=35,58
p=82,44 v=13,-7
p=32,23 v=-21,32
p=61,8 v=68,-63
p=81,5 v=85,-74
p=55,67 v=66,57
p=15,6 v=71,-63
p=29,93 v=-32,72
p=51,31 v=-20,45
p=45,63 v=37,19
p=56,92 v=-37,-43
p=57,44 v=4,16
p=29,11 v=-50,93
p=8,1 v=42,-17
p=77,71 v=97,76
p=98,97 v=43,-84
p=71,51 v=-42,72
p=4,24 v=92,-10
p=37,71 v=14,-88
p=2,0 v=71,86
p=94,71 v=7,30
p=95,22 v=-44,-56
p=13,58 v=96,-4
p=10,75 v=-47,74
p=91,27 v=32,-68
p=94,31 v=48,-56
p=21,48 v=47,23
p=98,78 v=-73,79
p=69,66 v=-76,49
p=87,67 v=78,60
p=56,32 v=37,-29
p=75,1 v=-72,88
p=90,71 v=-64,-50
p=63,58 v=27,-40
p=0,27 v=-20,-25
p=32,4 v=-87,36
p=68,51 v=-78,-76
p=80,78 v=-82,-77
p=33,7 v=47,-44
p=42,98 v=80,75
p=44,97 v=-19,17
p=91,63 v=-33,98
p=60,48 v=75,92
p=41,32 v=-14,-84
p=7,29 v=-42,-11
p=2,70 v=-63,87
p=85,48 v=-16,-34
p=5,54 v=-82,51
p=56,95 v=-61,-62
p=5,57 v=-57,69
p=80,14 v=-82,-71
p=30,72 v=-52,-92
p=70,49 v=-41,27
p=71,82 v=60,-1
p=71,9 v=25,97
p=100,101 v=91,-57
p=5,48 v=44,38
p=95,75 v=65,83
p=34,47 v=-29,27
p=89,82 v=19,34
p=76,92 v=-29,-2
p=71,92 v=81,-70
p=17,68 v=90,-8
p=71,44 v=96,32
p=57,25 v=-10,54
p=75,67 v=-16,-62
p=46,87 v=-37,41
p=74,98 v=97,78
p=30,22 v=72,-38
p=41,17 v=-23,-21
p=67,34 v=85,61
p=63,58 v=31,42
p=96,5 v=-94,-17
p=64,102 v=99,29
p=99,39 v=-63,27
p=16,84 v=-40,-24
p=22,19 v=55,2
p=70,76 v=-98,-46
p=16,46 v=-40,16
p=37,10 v=7,-28
p=59,49 v=3,-90
p=38,38 v=-79,8
p=43,85 v=45,-58
p=99,23 v=-26,24
p=45,69 v=-70,-62
p=44,22 v=-73,21
p=24,17 v=-10,43
p=61,90 v=-70,98
p=69,65 v=-14,-81
p=23,93 v=-6,-99
p=80,30 v=-25,-25
p=31,53 v=-91,-92
p=45,2 v=78,21
p=50,47 v=-87,-45
p=32,86 v=84,-62
p=47,81 v=97,-92
p=99,95 v=78,-66""".trimIndent().trim().split("\n")