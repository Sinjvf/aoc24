package com.example.aoc24

val input16 =






    """#############################################################################################################################################
#...........#...........#.....#...........#.........#.....#...#.........#.......#...#.............................#...#....................E#
#.#####.###.#.#.#######.#.#.#.#.#.#######.#.###.#####.###.#.#.#.###.###.###.###.#.#.#.###.#.#.#.#.#####.#########.#.#.###.#.#.###.#########.#
#.#.......#...#.#...#...#.#.#.#.#...#...#.#.#...#.....#.#...#...#...#.........#.#.#...#.#...#.#.#.#.....#.......#...#...#.#.#.#.#.......#...#
#.#.###########.#.#.#.###.#.#.#.###.#.#.#.#.#####.#####.#########.#########.#.#.#.#####.#####.#.#.#######.###.#.#####.#.#.###.#.#######.#.#.#
#.....#...#.....#.#...#...#.#.#.#...#.#...#.....#.#.......#.....#.......#.......#.#.........#...#.............#...#...#.#.#...#.......#...#.#
#.#####.#.#.#####.#########.#.###.###.#########.#.#.#####.#####.#######.#.#.###.#.#######.###############.###.###.#.#.#.#.#.###.#.###.###.#.#
#.#.....#.#.#.....#.........#.....#...#.......#.....#.#...#...#...#...#.#.#...#.#...#...#.....#...#.....#...#.....#...#.#.........#...#...#.#
###.#####.#.#.#####.#.#############.###.#####.###.###.#.###.#.#.#.#.#.#.###.#.#####.#.#.#.#.#.###.#.###.#.#.#.#######.#.#######.#.#####.###.#
#...#.#...#.#.#.....#.#.......#.#...#...#...#.......#...#...#.#.#...#.#.....#.....#.#.#.#.#.......#.#...#.#...#...#...#.......#.#.#...#.#...#
#.###.#.###.#.#######.#.#.###.#.#.###.#####.#######.#.###.###.###.###.###########.#.#.#.#.#.#.#.###.#.###.###.#.#.#.#.#######.#.#.#.#.#.###.#
#...#.......#...#.....#.#...#...#...#.#...#.....#...#.....#.............#.......#.....#.#.#.#.#...........#.....#.#.#.......#...............#
###.#.#####.###.#.#.###.###.#######.#.#.#.###.#.#.#####.###.###.#.#.###.###.###########.###.#.#.#.#####.###.#####.#.#.#######.#.###.#.###.#.#
#...#.#...#.....#.#...#...#.......#...#.#...#.#.#.....#...#.....#.#...#.....#.....#...#.....#.#.#.#...#.#...#...#.#.#.......#.......#.....#.#
#.###.#.#.#.#####.#.#.###.#######.#####.###.#.#######.#.#.#.#.#######.#####.#.###.#.#.#######.#.###.#.###.###.###.#.#.#####.###.###.#######.#
#.#...#.#.#.....#.#.#.....#...............#...#.......#.#...#.......#.#.#...#.#...#.#.........#.....#.........#...#.#.#...#.........#.......#
#.#####.#.#.###.#.#.#######.#.###########.#####.#######.#######.###.#.#.#.###.#.###########.#################.#.###.#.###.#.#####.#.#.#####.#
#.......#...#.#.#.#.......#.#...#.......#.......#.....#...#...#.#.....#...#...#...#.......#.....#.......#...#.#...#.#.....#.#.....#...#...#.#
#.#####.#.#.#.#.#.#.#####.#.###.#.#####.#####.###.#.#####.#.#.#.###.#####.#.#####.#.#####.#####.#.#####.#.#.#####.#.#####.#.#.#####.###.#.###
#...#...#...#...#.#.....#...#...#.#.#...#...#.....#.....#...#.#...#.....#.#...#.#...#...#.#...#.#...#...#.#.......#.#.......#.#.....#...#...#
###.###.#.#.#.#.#.#####.###.#.###.#.#.###.#.###.#####.#######.#.#.#.#.#.###.#.#.#######.#.###.#.###.#.###.#########.#.#####.###.###.#.#####.#
#.#.#...#.#...#.#.....#...#.#...#.#.#.....#.#...#.....#.......#.#.#.#.#...#.#.#.........#...#.#.....#...#.#.....#...#.#...#.....#.....#...#.#
#.#.#.#.#.#.#.#.#####.###.#####.#.#.###.###.#.###.#.###.#########.###.###.###.#.#.###.#####.#.#########.#.#.#.#.#.#.#.#.#########.#####.#.#.#
#.........#.#.........#.#.....#.#.#...#.....#.#...#.#...#.........#...#.......#.#...#.#.....#.........#.#...#.#...#.#...#.........#...#.#.#.#
#.###.#.#.#.###########.#.###.#.#.#.#.#.#####.###.###.###.#.#.#####.#.#########.#.#.###.#####.#####.###.#.###.#######.###.#########.#.###.#.#
#...#.#.#.....#.............#.#.#...#.#.#...#...#.#...#...#.#.#.....#.#...#...#...#.....#.........#...#.#...#.........#.........#...#.#...#.#
###.###.#.#.#.#.#####.#######.#.#####.#.#.#.###.#.#.#######.#.#.#.#####.#.#.###.#####################.#.###.###########.#######.#.#.#.#.###.#
#.......#.....#.....#...#...#.#.....#.#...#.#.#.#.....#.......#.#.#...#.#.#...#.........#.....#...#...#...#.......#.....#...#...#.#.#...#...#
###.###.#.#####.###.#.#.#.#.#.#.#####.#####.#.#.#####.#.#######.###.#.#.#.###.#.#######.#.###.#.#.#.#####.#######.#.#####.#.###.###.#.###.#.#
#.........#.....#...#.#...#.....#.......#...#.#...#.....#.....#...#.#.#.#...#...#...#.....#.#...#.#.............#.......#.#...#.....#.#...#.#
#.#.###.#.#####.#.#.###########.#.#######.#.#.###.#######.###.#.#.#.#.#.###.#####.#.#######.#####.#.###.#######.#.#.#####.###.#######.#.#####
#.........#...#.#.#.....#.....#.#...#.....#.#...#.......#...#.#.....#.....#.......#...#.....#.....#...#...#...#.#...#...#.#...#.......#.....#
###.###.#.#.#.###.#####.#.###.#.###.#.#####.#.#.#######.###.#.#.###########.#########.###.###.#######.#.#.#.###.#####.#.#.#.#######.###.###.#
#.#.....#...#...#.#...#.#...#.......#.#.......#.......#.....#.#.#.........#.#.......#...#.....#.......#.#...#...#...#.#...#...........#...#.#
#.#.###.#.#####.#.#.###.###.#####.###.#.#############.#######.#.#.#####.#.#.#.#.###.###.#.#######.#####.###.#.###.#.#.###############.#.###.#
#.#.#...#.....#.#.#...#...#.#...#...#.#.#...#.......#.....#...#.#.#.......#.#.#.#...#...#.......#.....#.....#.#...#.#...#.#.....#.....#.#...#
#.#.#.###.###.#.#.###.#.###.#.#.#####.#.#.#.#####.#.#####.#.#####.#.#######.#.#.###.#.#########.#########.#.#.#.###.###.#.#.#.###.#######.#.#
#.#.#...#.#.....#.....#.....#.#.......#...#.......#...#...#.#.....#.#.....#.#.#...#.#...#.......#.......#.#.#.#.#.#.#.#.#...#.............#.#
#.#.###.#.#####.#####.#######.#######.###############.#.###.#.#####.#.#.#.#.#####.#.###.#.###.###.#####.#.#.#.#.#.#.#.#.#.#################.#
#.....#.#.....#.....#...#...#.......#.#.........#...#.#...#.#...#.#.#.#.#.#.#.....#.....#...#.....#.....#...#...#.#...#.#.#...........#...#.#
#.#.###.#####.#########.###.#######.###.#######.###.#.###.#.###.#.#.###.###.#.###.#.#######.#.#####.#####.#.#####.#.#.#.###.#########.###.#.#
#.#.........#...#.....#.#.........#...#.....#...#...#.#.#.#.....#.#...#...........#.#.....#...#...#.#...#.#...#.....#...#...#.......#...#...#
#.#.#.#####.###.#.#.###.#.#####.#.###.#####.#.###.#.#.#.#.#######.###.#######.#####.#.###.#######.#.#.#.###.###.#####.#.#.#########.###.#####
#.#.....#.#...#...#...#.#.#...#.#...#.....#.#...#.#.#.#.#...........#.#.....#.#...#.#.#...........#.#.#...#.........#.......................#
#.###.#.#.###.#######.#.#.#.#.#.#.#.#####.#.###.#.###.#.#########.#.#.#.###.###.#.#.#.#.###.#.#####.#.###.###.###############.#.#####.#####.#
#...#...#...#...#...#.#...#.#.#...#.#...#.....#.#.....#...#.....#.#...#.#.#.....#...#.#...#.#.......#...#...#...#...#.......#.#.#...#.#...#.#
#.#.#.###.#####.###.#.#####.#.#####.###.#######.#.#####.###.###.#.###.#.#.###########.###.###.#.#####.#.###.#####.#.#.#####.#.#.#.#.#.#.###.#
#.#...#...#...#...#.#...#...#.#...#.#...#.......#.......#...#...#...#...#...#...#.#...#.....#.#.#.....#.#.....#...#...#.#...#...#.#.....#...#
#.###.#.###.#.###.#.###.#.###.#.#.#.#.#.#.###.#####.#####.###.#####.#.###.#.#.#.#.#.###.###.#.#.#.#####.#####.#.#######.#.###########.###.#.#
#.............#...#.....#...#...#.#...#.....#.......#.....#.#.....#...#...#...#...#.#.....#.#.#.#.#...#.#...#...#.......#.#...#...#.....#...#
#.#.#.#.#####.#.###.#.#.###.#####.#####.###.###.#.###.#.###.###.#.#####.#########.#.#.#.#.#.#.#.#.###.#.#.#.###.#######.#.#.#.#.#.#.#.#.#.#.#
#.#.#.....#...#.#...#.#...#.....#...#.....#.#...#...#.#.......#.#...............#.#...#.#.#.#.#.......#...#...#.#.....#.....#.#.#...#...#.#.#
#.#.#.#####.#.#.#.#.#.###.###.###.#.#.#####.#.#####.#.#.###.###.#.#############.#######.#.#.#.#####.#########.###.###.#######.#.#####.#####.#
#.#.#.#.....#.#.#.....#...#...#...#.#.......#.#...#...#...#.#...#.#...#.....#.#.........#.#.#.....#.#.......#.....#.#...#...#...#...#.#.....#
#.#.#.#.###.###.###.#.#.###.###.#####.#.#####.#.#.#######.#.#.###.#.#.#.#.#.#.#.#.#######.#.#####.#.#.#####.#.#####.###.#.###.###.#.#.#.###.#
#.#...#.........#...#.#.#...#.#.........#...#.#.#.#.......#.#.#.....#.#.#.#...#...#.....#.#.#.....#.#.#...#.#.......#...#.....#...#.#.#.#...#
#.#.#.#####.#####.###.#.#.###.#########.#.#.#.#.#.#.#.#####.#.#######.#.#.###.#.###.###.#.#.#.#######.###.#.#.#######.###.###.#.#.###.#.#.#.#
#.#.#...#.........#...#...#...........#.#.#.#.#.#.#...#...#.#...#...#.#.#.#.#.#.........#.#...#.......#...#.#.#.....#.#...#...#.#.....#...#.#
#.#.#.#.#.#.#####.#.#########.###.###.#.#.#.#.#.#.#.###.#.#.###.#.#.#.###.#.#.#########.#.#####.###.###.#.#.#.#.###.#.###.#.#.#.#####.#####.#
#.#.#.#...#.....#.#...#.....#...#...#.#.#.#...#.#...#...#.....#.#.#.#.....#.#.....#.....#.#.#...#.#.#...#...#.#...#.#...#.#.#...#.........#.#
#.#.#.#.###.#.#.#.###.#.###.#######.#.#.#.###########.#.#######.#.#.#######.#####.#.###.#.#.#.###.#.#.#####.#.###.#.###.#.#.###.###.#.###.#.#
#.....#.....#.#.#.#...#...#.....#...#.....#...........#.........#.#.....#...#.....#.#...#.#...#...#.#.#.....#...#.#.....#.#...#...#.#.....#.#
#.###.#####.###.#.###.###.#.###.#.#########.###########.#.#.###########.#.#.#.#####.#.###.#.###.###.#.#.###.###.#.#######.###.###.#.#.#.###.#
#...#.#...#.....#...#...#...#...#.#.........#...#.....#.#...#.#.........#.#.#.#.....#...#.#.....#...#.#.#...#...#...#.....#.#.....#...#.....#
#.#.#.#.#.#.###.###.###.###.#.###.#.#.#######.#.#####.#.###.#.#.#.###.#.#.#.#.#.#########.#######.###.#.#.#.#######.###.#.#.#########.#####.#
#.......#...#...#.#.#.#.....#.#...#.#.....#...#.#.....#...#.#...#.#...#...#...#...........#.......#...#...#.......#...#.#...........#.......#
###.#######.###.#.#.#.#######.#.###.#####.#.###.#.#######.#.#####.#####.#####.#########.###.#######.#.###########.###.#.#.#.#######.#.#.###.#
#...#.....#...#...#.#.....#...#...#.#...#...#.#.#.........#.....#...#...#.............#.....#.......#.........#.#.#...#.#.#.#.....#...#...#.#
#.#.#.#####.#.#####.#.#.###.###.#.#.###.#####.#.#.#####.#######.#.#.#.#.#.###########.#.#####.###.###.###.###.#.#.#.###.###.#.#.#####.#.###.#
#.#.................#...#...#...#...........#.#.......#.#...#.....#.......#.......#.#...#.....#.#...#.#...#...#.#...#...#...#.........#.#...#
#.#.#.#####.###.#######.#.#.#.#######.#####.#.#######.#.#.#.#.#####.#########.###.#.#####.#####.###.###.###.###.#####.###.#######.#.#.###.###
#...#.#.......#.#.....#.#...#.#...#.......#.....#.....#...#.#.#...#...#.#.....#...#.......#.......#.....#.#.........#.....#.....#.#.#...#...#
###.#.#.#####.#.#.###.#.###.###.#.#####.#.#.#####.#######.###.#.#.#####.#.#####.###.###.###.#####.#######.###.###.#.#######.###.#.###.#.#.#.#
#...#.#.....#.....#...#.....#...#.......#.#.#...#.#...#.#.#...#.#.....#.#...#.....#.#.......#...#.................#.....#...#.#.#.#...#.#.#.#
#.###.#####.#######.###.#.###.###########.#.#.#.#.#.#.#.#.#.###.#####.#.#.#.#####.#.#.#######.#.###.#######.###########.###.#.#.#.#.#.#.###.#
#.#...#.......#.....#...#.#.......#.......#.#.#.#.#.#...#.#.....#...#...#.#...#.....#.........#...#.........#.......#.#.#...#.#...#.#.#.#...#
#.#####.#.#####.#####.###.#.#####.#####.###.#.#.#.#.#####.#########.#####.###.#####.#.#####.#.###.#######.###.#####.#.#.#.###.###.#.#.#.#.#.#
#...........#...#.#...#.........#.....#.#.#.#.....#.#...............#...#...#.......#.#...#.#.#...#.......#...#.....#.#...#.......#.#...#.#.#
#############.###.#.###.#############.#.#.#.#.#####.#.#############.#.#.###.#######.###.#.###.#.#######.###.###.#####.#####.###.###.#.###.#.#
#.....#.......#...#...#.#.....#.......#.#.#.#.#.......#...#.....#.#...#...#...#...#.....#.#...#.......#.#...#...#.......#...#.....#.#...#.#.#
#.###.#.#######.#####.###.###.#.#######.#.#.#.###.#####.###.###.#.###.###.###.#.#########.#.#########.#.#.###.###.#.#.#.#.###.#.#.#.#.#.#.#.#
#...#.#.#.....#.......#...#...#.#.....#.#...#...#.#.....#...#...#.......#.#...#.....#...#...#.........#.#.#.#.....#.#.#.#.#.#.#.#.#.#.#...#.#
#.#.#.#.#.#.#.#.#######.###.###.#.###.#.#.#####.#.###.###.###.###.#####.#.#.#####.#.###.#####.#.#########.#.#######.#.###.#.#.#.#.#.#.#####.#
#.#.#...#.#...#.........#...#...#...#...#.....#.#.#...#...#.#...#.....#...#.......#.........#.#.#.........#.#.......#.....#.....#.#...#...#.#
#.#.#####.###.#####.#.###.###.#####.###########.#.#.#.#.###.###.#.#.#.#######.#############.#.#.#.#########.#.#######.#####.###.#.###.###.#.#
#.#...#.#.#.#...#...#.#.#.#...#.....#...........#...#.#...#.#...#.#.#.........#.....#.....#.#.#.#.#.........#.#.......#.....#...#.......#.#.#
#.###.#.#.#.#.#.#.###.#.#.###.#.#.#.#.###############.#.#.#.#.###.#.###########.###.#.###.#.#.###.#####.#.###.#.#############.#.#####.#.#.#.#
#.#.#.........#.#.#...#.#.....#.....#.........#.....#.#.#...#...#.#.#...#.......#...#...#...#.#.......#.#.#...#.....#.......#.#.....#.#...#.#
#.#.#.#####.#.#.#.###.#.#######.#####.#######.###.#.#.#.###.###.###.#.#.#.#######.#.#.#.#####.#.###.#.#.###.#######.#.#####.#.#####.#.###.#.#
#...#.....#...#...#...#.......#.....#.......#...#.#.#...#...#.#.......#.#.......#...#.#.....#...#...#.#...#...#.....#...#...#.#...#...#...#.#
#.#.#.###.###.###.#.#####.###.###.#.#.###.#####.#.#.###.#.###.###########.#####.###.###.###.#.###.###.###.###.###.#####.#.###.#.###.#######.#
#...#...#.......#...#.....#...#.....#...#.#...#.#.......#.#...#.....#...#.#.....#...#...#.#.#.#...#.........#...#.......#.....#.#...#.....#.#
#.###.#.#.###.#.#######.###.###.#####.#.###.#.#.#.#######.#.###.#.#.#.#.###.#.###.###.###.#.#.###.#.#.#######.#.###############.#.###.###.#.#
#.....#.#.....#...#...#...#.#...#.....#.....#...#.....#...#.....#.#.#.#.....#.......#.#...#.#.....#.#.......#.#...#...#.........#...#.#.#.#.#
#.###.#.#.#.#.###.#.#.###.#.#.###.###.#############.#.#.###.#####.#.#.#############.#.###.#.#########.#####.###.#.###.#.###.#######.#.#.#.#.#
#.....#.#...#.....#.#.........#.#...#.#...#.........#.#.#.....#...#.#.#.........#...#...#.#...........#...#...#.#...#.#...#...#.....#.#.#...#
#.#.#.#.#.#.#.#####.#######.#.#.#.#.#.#.#.#.#########.#.#.#.###.###.#.#.#######.###.###.#.###########.#.#.###.#.###.#.###.#.#.#.#####.#.#####
#.#.#...#.#.#.......#.....#.#.#...#.#.#.#...#...#.#...#.#.#.....#.#.#...#.....#...#.....#...#.....#.....#...#.#.#...#.#.....#.....#...#...#.#
#.#.#.###.#.#.###.###.###.#.#.#.###.###.#####.#.#.#.###.###.#####.#.#########.###.#######.#.###.#.#.#.#######.###.###.#.###########.#.#.#.#.#
#...#.....#.....#.....#.#.#.#.#...#.#...#...#.#...#.....#...#.#...#.#...........#.#...#...#.....#.#.#...#...#.#...#.........#.......#...#...#
#.#.#.###.#.###.#.#####.#.###.###.#.#.###.#.#.###.###.#.#.###.#.#.#.#.#######.#.#.#.###.#.#####.###.###.#.#.#.#.#.#######.#.#.#######.#.###.#
#.#.....#...#.#.#.......#...#...#.#.#.#...#...#.....#.#...#...#.#...#.......#.#.#.#.#...#...#...#...#.#...#.#.#.#.#.......#...#.#.....#...#.#
#.###.###.#.#.#.#########.#.###.###.#.#.#.#.#######.#.#####.#.#.#####.#.#.#.#.#.#.#.#.#####.#.###.###.###.#.#.#.###.###########.#.#######.#.#
#.......#.#...#...#.......#...#.....#.....#.#.....#.#.....#.#...#.....#.#.#.#...#.#.#...#...#.#...#.......#...#...#.#.......#...#.#.....#.#.#
#####.#.#.#.#####.#.#####.#.#.#.#########.###.###.#######.#.#####.###.#.#.###.#.#.#.#.###.#.###.###.#.###.#####.#.#.#.###.#.#.#.#.###.#.#.#.#
#.......#.#.....#.#.....#.#.#...#.......#.#...#.#.#...#...#.#...#...#.#.#.......#.#...#...#.#...#...#...#.....#...#.#.#...#.#.#.#.#...#.#.#.#
#.###.###.#.#.###.#####.#.#######.#.#####.#.###.#.#.#.#.#.#.#.#.#####.#.###.#.###.#.###.###.#.#######.#.#####.#.###.###.###.#.#.#.#.###.#.###
#.#.....#.#.#...#...#...#...#.....#.......#.#...#...#...#.#.#.#...#...#.#.#.#.#...#.#...#...#.......#.#.....#.#...#...#...#.#.#.#...#...#...#
#.#.#.#.#.#.###.#.#.#.#####.#.#############.#.#.#########.#.#.###.#.###.#.#.#.#.###.#.#############.#####.#.#.###.###.###.#.#.#.#####.#####.#
#.#...#.#.#...#.....#.#...#.#...#.........#.#.#...#.......#.....#...#.#...#.#.#.#...#.#.....#.....#.....#.#.#.......#.#...#...#.........#...#
#.#.#.#.#.#.###.###.#.###.#.###.#.#########.###.###.#######.#.#.#####.###.#.#.#.#.###.#.###.#.#.#.#.###.###.#####.###.#.#####.###########.###
#...#.#.#.........#...#...#.#...#...#.....#...#.....#...#.#.#.#.....#.....#.#.#.#...#...#.#...#.#.....#.....#...#.#...#.........#...#...#...#
#.#.#.#.###.#.###.#####.###.#.#####.#.###.###.#.#####.#.#.#.#######.#.#####.#.#.###.#####.#####.###.#########.#.#.#.###.#########.#.#.#.#.#.#
#.#.#.#...#...#...#.......#...#...#.#.#...#...#.#.....#.........#...#.....#...#.#.........#...#.#...#.......#.#...#.#.....#...#...#.........#
#.#.#.#.###.###.#.#######.###.#.#.#.#.#.###.###.#.#.###########.#.#######.#####.#######.#.#.#.#.#####.#####.#.#####.#.#####.#.#.#########.#.#
#.#.#.#...#...#.#.......#.......#.#...#...#.#...#.#.#.....#.....#.#.....#.....#...#.......#.#.#.......#.....#.#.....#.#.....#...#.........#.#
#.#.#.###.###.#.#######.#######.#.#######.#.###.#.###.###.#.#####.#.###.#########.###.#####.#.#####.###.#####.#.#######.#########.###.###.#.#
#.#.#.#.#.....#.#.#...#...#.....#.........#...#.#...#.#.#...#...#...#...........#...#.#.....#.....#...#.......#.........#.........#...#.....#
#.#.#.#.#######.#.#.#.###.#.#############.###.#.#.#.#.#.#####.#.#.###.#########.###.###.#######.#.#.#####################.#######.#.#.#.#.###
#.#...#...#.....#...#...#.#.#...............#.#.#.#.#.......#.#.#...#.......#.....#.....#.....#.#.#.#...#...............#.#.........#.#.#...#
#.#.###.#.###.#.#####.###.#.#.###.#.#######.#.#.###.#######.#.#.###.#######.#.###########.#.#.#.#.###.#.#.###.#########.#.#.#.#####.###.#.###
#.#.....#...#.#.....#...#...#.#...#.#.......#.#...#.#.....#...#...#...................#.#.#...#.#.....#...#...#...#...#.#.........#.#...#...#
#.###.#####.#.#####.###.#######.###.#.#######.###.#.#.#.#########.#######.#########.#.#.#.#.###.###########.###.#.#.#.#.###########.#.#####.#
#.....#...#.#.....#...#.#.......#...#.#.#.....#...#.#.#.....#.....#.....#.#.......#.#.#.#.#...#.....#...#...#...#...#.#...#.....#...#.#.....#
#.###.#.#.#.#####.###.#.#.#.#########.#.#.###.#.###.#.###.###.#####.#.#.#.#.#####.#.#.#.#.###.#.###.#.#.#.###.#######.#.#.#.###.#.###.###.#.#
#.......#.#.........#.#.#.#.....#.............#.....#.#.#.#...#.......#.#.#.#.......#...#.#.....#.....#.#...#.......#.#.#.#...#...#...#...#.#
#.###.###.#.#.#######.#.#.#####.#.###########.#######.#.#.#.#######.#.#.#.#.#######.#####.#####.#####.#####.###.#####.#.#.#.#.#.###.###.###.#
#.#.......#.#.#...#...#...#...#...#.#.........#.........#.#...#...#.#.#.#.#.........#...#...#.................#.#.....#.......#.#...#...#.#.#
#.#.#.#####.#.#.#.#.###.###.#.#.###.#.#.#####.#.#########.###.#.#.###.#.###.#######.#.#.###.###.#####.#######.#.#.#####.#######.#.###.###.#.#
#.#.#...#...#.#.#...#.#.....#...#...#.#.......#.#...#.....#.#...#.....#...#.#.....#...#...#.....#.......#.....#.#.#.......#.................#
#.#.#.#.#.###.#.#####.#######.#.###.#.#.#######.###.#.###.#.#.###########.#.###.###.###.#.#####.#.###.#.#.#####.#.#.###.#.#.#####.#.#.#######
#.#...#.#...#.#.#.....#.........................#...#.#.....#...#.......#.........................#...#.#.#.....#.#.#.#...#.................#
#.###.#.###.###.###.#.#.#.###.###.#.###.#########.#.#.#####.#.#.#.#####.#.###.#.#.###.###.###.#.###.#.#.#.#.#####.#.#.#.#####.#.#.#####.###.#
#.....#...#.#...#...#.#...#.....#.#.............#.#.#.....#...#.#...#...#...#...#...#...#.#...#.....#.#...#.#...#...#.#.#...#...#.#...#.#...#
#.###.###.#.#.###.###.#.#####.#.#.###.#.#####.#.#.#.#####.#.#.#.###.#.#####.#######.###.#.#.###.#####.###.#.#.#.###.#.#.#.#####.#.#.#.###.#.#
#.#...#...#.#.#...#.....#.....#.#...#...#.....#...#...#...#.....#...#.....#...#.....#.#...#.#...#...#...#.#...#.......#.#.......#.#.#.....#.#
#.#.#.#.#.#.#.#.#########.###.#####.#.#.#.#########.###.###.#.###.#####.#####.#.#####.#.###.#.#.#.#.#.#.#.#########.#.#.#########.#.#######.#
#.#.....#.#...#...........................#.......#.#...#.........#...#.......#.#.....#.#...#.....#...#.#.#...........#...#...#...#.......#.#
#.###.#########.###.#.#.#.#.#.#.#######.###.#.#####.#.###.#.#######.#.#########.#.###.#.#.#####.#####.#.###.#######.###.#.#.#.#.#####.###.#.#
#S..........................#...........#...........#...............#...........#.......#.............#.................#...#.............#.#
#############################################################################################################################################""".trimIndent().trim().split("\n")