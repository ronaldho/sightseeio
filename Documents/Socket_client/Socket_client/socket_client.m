//
//  socket_client.m
//  Socket_client
//
//  Created by Personal on 8/10/14.
//  Copyright (c) 2014 Matas Empakeris. All rights reserved.
//

#import "socket_client.h"
#import "FastSocket.h"

@implementation socket_client

-(long)sendFile:(NSString*)filename
{
    self.client = [[FastSocket alloc] initWithHost:@"localhost" andPort:@"8889"];
    [self.client connect];
    NSLog(@"client has opened a connection with the server");
    long sent = [self.client sendFile:filename];
    NSLog(@"file sent");
    
    return sent;
}

-(long)receiveFile:(NSString *)filename
{
    self.client = [[FastSocket alloc] initWithHost:@"localhost" andPort:@"8889"];
    [self.client connect];
    NSLog(@"client has opened a connecction with the server");
    long received = 
}
@end
