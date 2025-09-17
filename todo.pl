#!/usr/bin/env perl

# Semplice script per cercare tutti i commenti di TODO nei file .tex

# Usage:
# 1. Save this code as 'find_todos.pl'.
# 2. Place it in the directory that contains the 'Documentation' folder.
# 3. Run from your terminal: perl find_todos.pl

use strict;
use warnings;
use File::Find;

# --- Configuration ---
my $search_dir = 'Documentation';
# ---------------------

# Check if the target directory exists before starting.
if ( ! -d $search_dir ) {
    die "Error: The directory '$search_dir' was not found in the current location.\n";
}
# The 'find' function from File::Find will recursively walk the directory tree.
# It calls the 'process_file' subroutine for each file and directory it finds.
find( \&process_file, $search_dir );

# This subroutine is called by File::Find for every item in the search directory.
sub process_file {
    # The default variable $_ holds the basename of the current item.
    # We only care about files that end with the .tex extension.
    # The -f check ensures it's a file, not a directory.
    return unless -f $_ && /\.tex$/i;

    # $File::Find::name contains the full, platform-correct path to the file.
    my $filepath = $File::Find::name;

    # Try to open the file for reading.
    # Using the 3-argument open is safer and platform-agnostic.
    my $fh;
    unless ( open( $fh, '<', $_ ) ) {
        warn "Could not open file '$filepath': $!";
        return;
    }

    my $line_num = 0;
    while ( my $line = <$fh> ) {
        $line_num++;

        # We look for lines that:
        # 1. Start with optional whitespace (\s*).
        # 2. Are followed by a '%' comment character.
        # 3. Contain the word 'todo' somewhere after the '%'.
        # The 'i' flag makes the match case-insensitive (TODO, Todo, todo, etc.).
        # The '\b' ensures we match 'todo' as a whole word.
        if ( $line =~ /^\s*%.*?\btodo\b/i ) {
            # chomp removes the trailing newline character for cleaner printing.
            chomp $line;
            # Print the result in a standard format: file:line_number:content
            print "$filepath:$line_num: $line\n";
        }
    }

    close $fh;
}
