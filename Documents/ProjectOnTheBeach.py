def compile( self, fileName ):
    file = open( fileName )
    lines = []
    
    for temp in file.readlines():
        lines.append( temp )

    return lines

if __name__ == '__main__':
    compile( '/Users/Matas/Desktop/CS Project/text.txt' )

    
    
    
    
