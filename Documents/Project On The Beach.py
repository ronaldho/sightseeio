import sys
import re

def compile( fileName ):
    file = open( fileName )
    lines = []
    
    for temp in file.readlines():
        lines.append( temp )

    words = []
    for line in lines:
        for word in line:
            words.append( word )

    return words
    
    
    
    
