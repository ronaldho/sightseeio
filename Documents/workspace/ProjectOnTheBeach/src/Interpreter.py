#! /usr/bin/env python
# -*- coding: utf-8 -*-

import re

'''
Created on May 7, 2013

@author: Matas
'''

HRES = 'HRES'
activeEligible = 'activeEligible'
SRES = 'SRES'
INT = 'INT'
ID = 'ID'

token_exprs = [
    (r'}', HRES, r'}' ),
    (r'{', HRES, r'{' ),
    (r'\(', HRES, r'\(' ),
    (r'\)', HRES, r'\(' ),
    (r'\+', SRES , r'\+' ),
    (r'-', SRES, r'-' ),
    (r'\*', SRES, r'\*' ),
    (r'/', HRES, '/' ),
    (r'COMMENT', HRES, '//' ),
    (r'KOMENTARAS', HRES, '//'),
    (r'END', HRES, ';' ),
    (r'BAIK', HRES, ';'),
    (r'smallerthan', SRES, r'<' ),
    (r'mažnegu', SRES, r'<'),
    (r'smallerequal', SRES, r'<=' ),
    (r'mažvienodas', SRES, r'<=' ),
    (r'biggerthan', SRES, r'>' ),
    (r'didnegu', SRES, r'>' ),
    (r'biggerequal', SRES, r'>=' ),
    (r'didvienodas', SRES, r'>ž' ),
    (r'==', SRES, r'==' ),
    (r'is', SRES, r'='  ),
    (r'yra', SRES, r'=' ),
    (r'isnot', SRES, r'!=' ),
    (r'nėra', SRES, r'!=' ),
    (r'number', SRES, r'int' ),
    (r'numeris', SRES, r'int' ),
    (r'withinc', SRES, r'++' ),
    (r'supadid', SRES, r'++' ),
    (r'withdec', SRES, r'--' ),
    (r'supamaž', SRES, r'--' ),
    (r'repif', HRES, r'while', activeEligible ),
    (r'repjeigu', HRES, r'while', activeEligible),
    (r'if', HRES, r'if', activeEligible ),
    (r'jeigu', HRES, r'if', activeEligible ),
    (r'phrase', HRES , r'string' ),
    (r'frazė', HRES, r'string' ),
    (r'nothen', HRES , r'else if', activeEligible ),
    (r'netai', HRES, r'else if', activeEligible ),
    (r'otherwise', HRES, r'else' ),
    (r'kitaip', HRES, r'else' ),
    (r'printout', HRES, r'printf', activeEligible ),
    (r'spaudink', HRES, r'printf', activeEligible ),
    '''(r'[0-9]+', INT ),
    (r'[A-Za-z][A-Za-z0-9_]*', ID ), NOT USED! FROM PROTOTYPE'''
    ]

def compileFile( filename ):
    filer = open( filename )
    byteCode = open( '/Users/Matas/Desktop/compile.txt', 'w')
    lines = []
    parsed = []
    ran = False
    isActive = False
    wasNotToken = False
    inString = False
    inComment = False
    
    for temp in filer.readlines():
        lines.append( temp )
    
    for line in lines:
        line = line.split(' ')
        
        for word in line:
            parsed.append( word )
                 
    for index in range( len( parsed ) ):
        ran = False
        

        if inString == False and inComment == False:
            for i in range( len( token_exprs ) ):
                if token_exprs[ i ][ 0 ] == parsed[ index ] or parsed[ index ].translate( None, '\n' ) == token_exprs[i][0] or parsed[ index ].translate( None, '\t' ) == token_exprs[i][0]:
                    ran = True
                    if token_exprs[i][2]:
                        if parsed[ index ].find( '\t' ) != -1:
                            byteCode.write( '\t' )
                         
                        if len( token_exprs[i] ) == 4:                
                            byteCode.write( token_exprs[i][2] + ' ' )
                        
                        if len( token_exprs[i] ) == 4 and isActive == False :
                            isActive = True
                            byteCode.write( '( ' )
                        
                        elif isActive == True and wasNotToken == True and token_exprs[i][1] is HRES:
                            isActive = False
                            byteCode.write( ') ')
                        
                        if len( token_exprs[i] ) < 4:
                            byteCode.write( token_exprs[i][2] + ' ')
                        
                        if parsed[ index ].find( '\n' ) != -1:
                            byteCode.write( '\n' )
                            
                        if parsed[ index ] == 'COMMENT':
                            inComment = True
                    
                    wasNotToken = False
                    break
            
        if not ran:
            byteCode.write( parsed[ index ] + ' ' )
            wasNotToken = True
            
            if re.search( '".*"', parsed[ index ] ):
                inString = False
                
            elif re.match( '".*', parsed[ index ] ):
                inString = True
                
            elif re.search( '.*"' , parsed[ index ] ):
                inString = False
                print( parsed[ index ] )
                
            if inComment == True and parsed[ index ].find( '\n' ) != -1:
                inComment = False
                
    byteCode.close()


if __name__ == '__main__':
    compileFile( '/Users/Matas/Desktop/CS Project/text.txt' )